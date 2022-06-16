// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** Command that moves modules' angle to fixed heading and otherwise stays put */
public class StayPut extends CommandBase
{
    private DriveTrain drive_train;
    private double angle;

    public StayPut(DriveTrain drive_train, double angle)
    {
        this.drive_train = drive_train;
        this.angle = angle;
    }

    @Override
    public void execute() 
    {
        drive_train.drive(angle, 0); 
    }
}
