package robot.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.parts.RotationEncoder;
import robot.parts.SparkMini;

public class Rotator 
{
    private int channel;
    private SparkMini motor;
    private RotationEncoder encoder;


    public Rotator (int channel, double offset)
    {
        this.channel = channel;
        motor = new SparkMini(channel);
        encoder = new RotationEncoder(channel, offset);

        SmartDashboard.setDefaultNumber("Offset" + channel, offset);
        SmartDashboard.setDefaultNumber("P", 0.05);
    }

    public void run(double speed)
    {
        motor.set(speed);
        SmartDashboard.putNumber("Angle" + channel, encoder.getHeading().getDegrees());
    }

    public void setAngle(double desired)
    {
        encoder.setZero(SmartDashboard.getNumber("Offset"+channel, 0));
        double angle = encoder.getHeading().getDegrees();
        double error = Math.IEEEremainder(desired - angle, 360.0);
        double output = error*SmartDashboard.getNumber("P", 0);
        SmartDashboard.putNumber("Angle" + channel, encoder.getHeading().getDegrees());
        SmartDashboard.putNumber("Desired" + channel, desired);
        motor.set(output);
    }
}
