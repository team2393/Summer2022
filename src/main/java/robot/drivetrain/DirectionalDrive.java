// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.OI;

/** Command for human to drive robot in direction in which it's currently pointed
 * 
 *  'Forward' means go whereever the nose of the robot points
 */
public class DirectionalDrive extends CommandBase
{
    private DriveTrain drive_train;
    private SelectCenter center = new SelectCenter();

    public DirectionalDrive(DriveTrain drive_train)
    {
        this.drive_train = drive_train;
        addRequirements(drive_train);
    }

    @Override
    public void execute()
    {
        double vx = OI.getForwardBackward();
        double vy = OI.getLeftRight();
        double vr = OI.getRotation();
        drive_train.swerve(vx, vy, vr, center.determineCenter());
    }
}
