package frc.robot;

import frc.robot.Robot;
import frc.robot.subsystems.SubsystemJoystick;
import frc.robot.commands.*;

public class OI {
    public OI(){
        Robot.joystick.whenHeld(SubsystemJoystick.d_A, new LiftTest(5000));
        Robot.joystick.whenHeld(SubsystemJoystick.d_LB, new LiftTest(10000));
        Robot.joystick.whenHeld(SubsystemJoystick.d_B, new LiftStop());
        Robot.joystick.whenHeld(SubsystemJoystick.d_RB, new LiftBringZero());
        Robot.joystick.whenHeld(SubsystemJoystick.d_X, new LiftMove(0.6));
        Robot.joystick.whenHeld(SubsystemJoystick.d_Y, new LiftMove(-0.6));
    }
}
