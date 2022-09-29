// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package robot;

import edu.wpi.first.wpilibj.TimedRobot;

/** Robot for testing swerve drive */
public class SwervyRobot extends TimedRobot
{
    @Override
    public void robotInit()
    {
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("** " + getClass().getName());
        System.out.println("********************************");
        System.out.println("********************************");
    }

    // Next, you would add components to the robot
    // and do something in the auto and teleop phases
}
