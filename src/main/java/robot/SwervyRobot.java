// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.drivetrain.AbsoluteDrive;
import robot.drivetrain.DirectionalDrive;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.StayPut;

/** Robot for testing swerve drive */
public class SwervyRobot extends TimedRobot
{
    private DriveTrain drive_train =new DriveTrain();
    private CommandBase absolute_drive = new AbsoluteDrive(drive_train);
    private CommandBase directional_drive = new DirectionalDrive(drive_train);
    private CommandBase reset = new InstantCommand(() -> drive_train.reset()); 

    @Override
    public void robotInit()
    {
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("** " + getClass().getName());
        System.out.println("********************************");
        System.out.println("********************************");

        reset.setName("Reset");
        SmartDashboard.putData("Reset", reset);
        SmartDashboard.putData("Absolute Drive", absolute_drive);
        SmartDashboard.putData("Direction Drvie", directional_drive);
    }

    @Override
    public void robotPeriodic() 
    {   // Run the 'scheduler' all the time to support commands and subsystems
        CommandScheduler.getInstance().run(); 
    }

    @Override
    public void teleopInit()
    {
        // Reset everything
        OI.reset();
        reset.schedule();

        // Start with 'normal' drive mode
        directional_drive.schedule();
    }

    @Override
    public void teleopPeriodic() 
    {
        // Change drive modes
        if (OI.toggleDriveMode())
            if (directional_drive.isScheduled())
                absolute_drive.schedule();
            else  
                directional_drive.schedule();
        
        // Reset
        if (OI.doReset())
            reset.schedule();
    }

    @Override
    public void autonomousInit() 
    {
        drive_train.reset();

        TrajectoryConfig config = new TrajectoryConfig(1.0, 1.0);

        // Roughly this shape:
        //
        //  /----------------> end (and then back)
        //  |
        //  |
        // Start
        List<Pose2d> waypoints = new ArrayList<>();
        waypoints.add(new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
        waypoints.add(new Pose2d(2.4, 0.1, Rotation2d.fromDegrees(0)));
        waypoints.add(new Pose2d(2.76, -2.27, Rotation2d.fromDegrees(-90)));
        waypoints.add(new Pose2d(2.6, -4.56, Rotation2d.fromDegrees(-90)));
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(waypoints, config);
        CommandBase leg1 = drive_train.createTrajectoryCommand(trajectory, -90);

        waypoints.clear();
        waypoints.add(new Pose2d(2.6, -4.56, Rotation2d.fromDegrees(90)));
        waypoints.add(new Pose2d(2.76, -2.27, Rotation2d.fromDegrees(90)));
        waypoints.add(new Pose2d(2.4, 0.1, Rotation2d.fromDegrees(180)));
        waypoints.add(new Pose2d(0.0, -0.1, Rotation2d.fromDegrees(180)));
        trajectory = TrajectoryGenerator.generateTrajectory(waypoints, config);
        CommandBase leg2 = drive_train.createTrajectoryCommand(trajectory, 0);

        new SequentialCommandGroup(leg1,
                                   leg2,
                                   new StayPut(drive_train, 90.0)).schedule();
    }
}
