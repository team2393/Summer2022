// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import robot.drivetrain.SwerveModule;

/** Robot for testing swerve drive */
public class SwervyRobot extends TimedRobot
{
    private SwerveModule frontLeftSwerveModule = new SwerveModule(0, -20);
    private SwerveModule frontRightSwerveModule = new SwerveModule(1, 87);
    private SwerveModule backRightSwerveModule = new SwerveModule(2, 195);
    private SwerveModule backLeftSwerveModule = new SwerveModule(3, -107);

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
        double angle = -joystick.getRightX()*180;
        double speed = -joystick.getLeftY()*1;
        backRightSwerveModule.setSwerveModule(angle, speed);
        backLeftSwerveModule.setSwerveModule(angle, speed);
        frontRightSwerveModule.setSwerveModule(angle, speed);
        frontLeftSwerveModule.setSwerveModule(angle, speed);   
    }
}
