package robot.drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Driver 
{
    final static double COUNTS_PER_INCH = 949;
    final static double INCHES_PER_METER = 39.37;

    private int channel;
    private WPI_TalonFX driver;
    
    public Driver (int channel)
    {
        this.channel = channel;
        driver = new WPI_TalonFX(channel);
        driver.setSelectedSensorPosition(0);
        SmartDashboard.putNumber("F", .215);
    }

    public double getPosition()
    {
        return driver.getSelectedSensorPosition() / COUNTS_PER_INCH / INCHES_PER_METER;
    }
    
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
        driver.set(desired_speed*SmartDashboard.getNumber("F", 0));
        SmartDashboard.putNumber("speed" + channel, getSpeed());
    }
}
