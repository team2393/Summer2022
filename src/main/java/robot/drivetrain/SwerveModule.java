// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule 
{
    private Rotator rotator;
    private Driver driver;    

    /** Construct swerve module
     *  @param channel PMW channel of rotator, CAN bus ID (-1) of driver
     *  @param offset Offset from 'forward' in degrees
     */
    public SwerveModule(int channel, double offset)
    {
        rotator = new Rotator(channel, offset);
        driver = new Driver (channel+1);
    }

    /** @param angle Angle in degrees
     *  @param speed Speed in meters/sec
     */
    public void setSwerveModule (double angle, double speed)
    {
        rotator.setAngle(angle);
        driver.setSpeed(speed);
    }

    public Rotation2d getCurrentAngle() 
    {
        return rotator.getAngle();
    }

    public SwerveModuleState getState()
    {
        return new SwerveModuleState(driver.getSpeed(), rotator.getAngle()); 
    }
}

