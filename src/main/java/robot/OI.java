package robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

public class OI
{
    /** Joystick deadband */
    private static final double DEADBAND = .2;

    /** Maximum Speed meters/sec */
    private static final double MAX_SPEED = 2.0;

    /** Maximum rotation in radians/sec */
    private static final double MAX_ROTATION = Math.PI;

    private static final XboxController joystick = new XboxController(0);

    public static void reset()
    {
        joystick.getAButtonPressed();
        joystick.getBButtonPressed();
        joystick.getXButtonPressed();
        joystick.getYButtonPressed();
        joystick.getRightBumperPressed();
    }

    /** @return Forward (or backw.) speed in meters/sec */
    public static double getForwardBackward()
    {
        return MAX_SPEED * MathUtil.applyDeadband(-joystick.getLeftY(), DEADBAND);   
    }

    public static double getLeftRight()
    {
        return MAX_SPEED * MathUtil.applyDeadband(-joystick.getLeftX(), DEADBAND); 
    }

    public static double getRotation()
    {
        return MAX_ROTATION * MathUtil.applyDeadband(-joystick.getRightX(), DEADBAND); 
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

