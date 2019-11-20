/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.constants.Constants;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {

  private TalonSRX elevatorMaster = new TalonSRX(0);
  private TalonSRX elevatorSlave = new TalonSRX(1);
  private DigitalInput limitSwtichUp = new DigitalInput(4);
  private DigitalInput limitSwtichDown = new DigitalInput(9); 

  public Lift(){
    this.elevatorMaster.setNeutralMode(NeutralMode.Brake);
    this.elevatorSlave.setNeutralMode(NeutralMode.Brake);
    this.elevatorMaster.setInverted(true);
		this.elevatorSlave.setInverted(true);
		this.liftInit();
		resetEncoder();
  }

  public void movePosition(double distance){
    this.elevatorSlave.follow(this.elevatorMaster);
    this.elevatorMaster.set(ControlMode.Position, distance);
	}

	public void liftManche(double sp){
		this.elevatorMaster.set(ControlMode.PercentOutput, sp);
    this.elevatorSlave.follow(this.elevatorMaster);
	}

	public boolean isTopLimitActive(){
		return this.limitSwtichUp.get();
	}

	public boolean isLimitActive(){
		return this.limitSwtichDown.get();
	}

	public void resetEncoder(){
  elevatorMaster.setSelectedSensorPosition(0, 0, 30);}

	public void stop(){
		if(isLimitActive()){
			elevatorMaster.setSelectedSensorPosition(0, 0, 80);
	
		}
	}

  public void liftInit(){
		elevatorMaster.configFactoryDefault();
		
    elevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,Constants.kTimeoutMs);

		elevatorMaster.setSensorPhase(Constants.kSensorPhase);
		elevatorMaster.setInverted(Constants.kMotorInvert);

		elevatorMaster.configNominalOutputForward(0, Constants.kTimeoutMs);
		elevatorMaster.configNominalOutputReverse(0, Constants.kTimeoutMs);
		elevatorMaster.configPeakOutputForward(1, Constants.kTimeoutMs);
		elevatorMaster.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		elevatorMaster.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		elevatorMaster.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		elevatorMaster.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		elevatorMaster.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		elevatorMaster.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		int absolutePosition = elevatorMaster.getSensorCollection().getPulseWidthPosition();

		absolutePosition &= 0xFFF;
		if (Constants.kSensorPhase) { absolutePosition *= -1; }
		if (Constants.kMotorInvert) { absolutePosition *= -1; }
		
		elevatorMaster.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}