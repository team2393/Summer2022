// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.OI;

/** Command for human to drive robot in absolute field coordinates
 * 
 *  'Forward' means 'up' in the field, no matter which way the
 *  nose of the robot points
 */
public class AbsoluteDrive extends CommandBase
{
    private DriveTrain drive_train;

    public AbsoluteDrive(DriveTrain drive_train)
    {
        this.drive_train = drive_train;
        addRequirements(drive_train);
    }

    @Override
    public void execute()      
    {
        // Speed vector (vx, vy) meant to be in field coordinates,
        // vx going "up" 
        double vx = OI.getForwardBackward();
        double vy = OI.getLeftRight();

        // If robot also points 'up', we could use (vx, vy) as given,
        // but generally we need to rotate (vx, vy) backwards from the current heading
        // of the robot to see how the robot needs to move:
        Rotation2d correction = Rotation2d.fromDegrees(-drive_train.getHeading());
        Translation2d absoluteDirection = new Translation2d(vx, vy).rotateBy(correction);
        
        // Swerve robot in 'absoluteDirection', while rotating as requested
        double vr = OI.getRotation();
        drive_train.swerve(absoluteDirection.getX(), absoluteDirection.getY(), vr, new Translation2d(0, 0));
    }  
}
