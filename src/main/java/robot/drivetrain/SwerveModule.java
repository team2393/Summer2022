package robot.drivetrain;

public class SwerveModule 
{
    private Rotator rotator;
    private Driver driver;    

    public SwerveModule(int channel, double offset)
    {
        rotator = new Rotator(channel, offset);
        driver = new Driver (channel+1);
    }

    public void setSwerveModule (double angle, double speed)
    {
        rotator.setAngle(angle);
        driver.setSpeed(speed);
    }
}

