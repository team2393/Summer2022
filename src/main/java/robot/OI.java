package robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class OI
{
    /**
     *
     */
    private static final double DEADBAND = .2;
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

        return MathUtil.applyDeadband(-joystick.getLeftY(), DEADBAND);
        
    }

    public static double getLeftRight()
    {
        return MathUtil.applyDeadband(-joystick.getLeftX(), DEADBAND); 
    }

    public static double getRotation()
    {
        return MathUtil.applyDeadband(-joystick.getRightX(), DEADBAND) * Math.PI / 2; 
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

