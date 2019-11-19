/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.SubsystemJoystick;

public class LiftMove extends Command {
  double speed;
  public LiftMove(double sp) {
    
    this.speed = sp;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.liftPID);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.liftPID.isLimitActive()){
       if (this.speed >= 0){
        Robot.liftPID.liftManche(this.speed);
       } else {
        Robot.liftPID.liftManche(0);
       }
  } else { 
    Robot.liftPID.liftManche(this.speed);
  }

  SmartDashboard.putBoolean("Fim de curso", Robot.liftPID.isLimitActive());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    Robot.liftPID.liftManche(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.liftPID.liftManche(0);
  }
}