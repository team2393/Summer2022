// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.SwerveModule;

/** Robot for testing swerve drive */
public class SwervyRobot extends TimedRobot
{
    private DriveTrain drive_train =new DriveTrain();
   
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
        //double angle = OI.getDegrees();
        //double speed = OI.getForwardBackward();
        //drive_train.drive(angle, speed);  
        
        drive_train.swerve(OI.getForwardBackward(), OI.getLeftRight(), OI.getRotation());
        
    }
}
