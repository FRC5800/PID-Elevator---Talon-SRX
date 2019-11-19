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
    this.elevatorMaster.setInverted(false);
    this.elevatorSlave.setInverted(false);
    elevatorMaster.setSensorPhase(true);

		resetEncoder();
  }
  
   public boolean changeStatus = true;

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

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
