// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import robot.drivetrain.Rotator;

/** Robot for testing swerve drive */
public class SwervyRobot extends TimedRobot
{
    private Rotator frontRightRotator = new Rotator(1, -90);
    private Rotator frontLeftRotator = new Rotator (0, -17);
    private Rotator backLeftRotator = new Rotator (3, -105);
    private Rotator backRightRotator = new Rotator (2, 20);
    private XboxController joystick = new XboxController(0);
 
    @Override
    public void robotInit()
    {
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("** " + getClass().getName());
        System.out.println("********************************");
        System.out.println("********************************");
    }
 
    @Override
    public void teleopPeriodic() 
    {
        double angle = joystick.getLeftX()*180;
        backRightRotator.setAngle(angle);
        backLeftRotator.setAngle(angle);
        frontRightRotator.setAngle(angle);
        frontLeftRotator.setAngle(angle);   
    }
}
