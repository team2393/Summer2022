package robot;
import edu.wpi.first.wpilibj.XboxController;

public class OI
{
    private static final XboxController joystick = new XboxController(0);
    
    public static void reset()
    {
        joystick.getAButtonPressed();
        joystick.getBButtonPressed();
        joystick.getXButtonPressed();
        joystick.getYButtonPressed();
        joystick.getRightBumperPressed();
    }

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

    public static boolean frontCenter()
    {
        return joystick.getYButtonPressed();
    }

    public static boolean resetCenter()
    {
        return joystick.getRightBumperPressed();
    }

    public static boolean rightCenter()
    {
        return joystick.getBButtonPressed();
    }

    public static boolean leftCenter()
    {
        return joystick.getXButtonPressed();
    }

    public static boolean backCenter()
    {
        return joystick.getAButtonPressed();
    }
}

