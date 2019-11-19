package frc.robot;

import frc.robot.Robot;
import frc.robot.subsystems.SubsystemJoystick;
import frc.robot.commands.*;

public class OI {
    public OI(){
        Robot.joystick.whenHeld(SubsystemJoystick.d_A, new LiftMove(0.5));
        Robot.joystick.whenHeld(SubsystemJoystick.d_B, new LiftMove(-0.5));
    }
}
