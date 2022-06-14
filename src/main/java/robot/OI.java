package robot;
import edu.wpi.first.wpilibj.XboxController;

public class OI
{
    private static final XboxController joystick = new XboxController(0);

    public static double getForwardBackward()
    {
        return -joystick.getLeftY()*1;
    }

    public static double getLeftRight()
    {
        return -joystick.getLeftX(); 
    }

    public static double getRotation()
    {
        return -joystick.getRightX() * Math.PI / 2; 
    }

    public static double getDegrees()
    {
        return -joystick.getRightX()*180;
    }
}
