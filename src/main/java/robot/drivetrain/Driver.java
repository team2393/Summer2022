// Copyright (c) FIRST Team 2393 and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Driver 
{
    // Measured by driving some distance and computing
    // raw encoder counts over measured distance
    final static double COUNTS_PER_INCH = 949;
    final static double INCHES_PER_METER = 39.37;

    private int channel;
    private WPI_TalonFX driver;
    
    /** @param channel CAN bus ID */
    public Driver (int channel)
    {
        this.channel = channel;
        driver = new WPI_TalonFX(channel);
        driver.setSelectedSensorPosition(0);
        SmartDashboard.setDefaultNumber("F", 2.8);
    }

    /** @return Get position in meters */
    public double getPosition()
    {
        // return Units.inchesToMeters(driver.getSelectedSensorPosition() / COUNTS_PER_INCH);
        return driver.getSelectedSensorPosition() / COUNTS_PER_INCH / INCHES_PER_METER;
    }
    
    /** @return Get speed in meters/sec */
    public double getSpeed()
    {
        return driver.getSelectedSensorVelocity()*10 / COUNTS_PER_INCH / INCHES_PER_METER;
    }
   
    /** @param speed Speed -1..1 */
    public void run (double speed)
    {
        driver.set(speed);
        SmartDashboard.putNumber("position" + channel, getPosition());
        SmartDashboard.putNumber("speed" + channel, getSpeed());
    }

    /** @param speed Speed in m/s */
    public void setSpeed(double desired_speed)
    {
        driver.setVoltage(desired_speed * SmartDashboard.getNumber("F", 0));
        SmartDashboard.putNumber("speed" + channel, getSpeed());
        SmartDashboard.putNumber("desired speed" + channel, desired_speed);
    }
}
