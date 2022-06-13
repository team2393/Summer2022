package robot.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.parts.RotationEncoder;
import robot.parts.SparkMini;

public class Rotator 
{
    private SparkMini motor;
    private RotationEncoder encoder;


    public Rotator (int channel, double offset)
    {
        motor = new SparkMini(channel);
        encoder = new RotationEncoder(channel, offset);
        SmartDashboard.setDefaultNumber("P", 0.05);
    }

    public void run(double speed)
    {
        motor.set(speed);
        SmartDashboard.putNumber("Angle", encoder.getHeading().getDegrees());
    }

    public void setAngle(double desired)
    {
        double angle = encoder.getHeading().getDegrees();
        double error = Math.IEEEremainder(desired - angle, 360.0);
        double output = error*SmartDashboard.getNumber("P", 0);
        SmartDashboard.putNumber("Angle", encoder.getHeading().getDegrees());
        SmartDashboard.putNumber("Desired", desired);
        motor.set(output);
    }
}
