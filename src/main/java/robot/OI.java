package robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;

/** Operator interface: Which button etc. does what */
public class OI
{
    /** Joystick deadband */
    private static final double DEADBAND = .2;

    /** Maximum Speed meters/sec */
    private static final double MAX_SPEED = .5;

    /** Maximum rotation speed in radians/sec */
    private static final double MAX_ROTATION = Math.PI / 2;

    private static final XboxController joystick = new XboxController(0);

    /** Clear any 'pressed' button state */
    public static void reset()
    {
        joystick.getAButtonPressed();
        joystick.getBButtonPressed();
        joystick.getXButtonPressed();
        joystick.getYButtonPressed();
        joystick.getRightBumperPressed();
        joystick.getLeftBumperPressed();
    }

    /** @return Forward (or backw.) speed in meters/sec */
    public static double getForwardBackward()
    {
        return MAX_SPEED * MathUtil.applyDeadband(-joystick.getLeftY(), DEADBAND);   
    }

    /** @return Left (or right) speed in meters/sec */
    public static double getLeftRight()
    {
        return MAX_SPEED * MathUtil.applyDeadband(-joystick.getLeftX(), DEADBAND); 
    }

    /** @return Rotational speed in rad/sec */
    public static double getRotation()
    {
        return MAX_ROTATION * MathUtil.applyDeadband(-joystick.getRightX(), DEADBAND); 
    }
    
    public static boolean resetCenter()
    {
        return joystick.getRightBumperPressed();
    }

    public static boolean frontCenter()
    {
        return joystick.getYButtonPressed();
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

    public static boolean toggleDriveMode()
    {
        return joystick.getLeftBumperPressed();
    }
}

