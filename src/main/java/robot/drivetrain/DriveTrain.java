package robot.drivetrain;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain
 {
    /** Don't rotate swerve module unless speed is at least this
     *  to avoid spinning in place
    */
    private static final double MINIMUM_SPEED_THRESHOLD = .05;

    /** Modules located in four corners of apx. square robot */
    private SwerveModule[] modules = new SwerveModule[]
    {
        new SwerveModule(0, -20), // Front left
        new SwerveModule(1, 87),  // Front right
        new SwerveModule(2, 195), // Back right
        new SwerveModule(3, -107) // Back left
    };

    /** Size of chassis which is not exactly a square,
     *  distance between swerve modules
     */
    final public static double WIDTH = .64135; 
    final public static double LENGTH = .61595; 
    
    /** Kinematics that translate chassis speed to module settings and vice versa */
    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d( LENGTH / 2,  WIDTH / 2),
        new Translation2d( LENGTH / 2, -WIDTH / 2), 
        new Translation2d(-LENGTH / 2, -WIDTH / 2), 
        new Translation2d(-LENGTH / 2,  WIDTH / 2) );

    /** Gyro that provides heading of robot */
    private PigeonIMU gyro = new PigeonIMU(0);

    /** Drive all modules with same angle and speed */
    public void drive(double angle, double speed)
    {
        // modules[0].setSwerveModule(angle, speed);
        // modules[1].setSwerveModule(angle, speed);
        // modules[2].setSwerveModule(angle, speed);
        // modules[3].setSwerveModule(angle, speed);
        
        // for (int i=0; i<modules.length; ++i)
        //     modules[i].setSwerveModule(angle, speed);

        for (SwerveModule module : modules)
            module.setSwerveModule(angle, speed);
    }

    /** Swerve
     *  @param vx Speed in 'X' (forward/back) direction
     *  @param vy Speed in 'Y' (left/right) direction
     *  @param vr Speed for rotation
     *  @param center Center of rotation
     */
    public void swerve(double vx, double vy, double vr, Translation2d center)
    {
        // Translate desired chassis movement to settings of the 4 swerve modules
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(new ChassisSpeeds(vx, vy, vr), center);
   
        for (int i=0; i<modules.length; ++i)
        {
            // Optimize module rotation
            states[i] = SwerveModuleState.optimize(states[i], modules[i].getCurrentAngle());

            // Actually moving? Then rotate as requested
            if (Math.abs(states[i].speedMetersPerSecond) > MINIMUM_SPEED_THRESHOLD)
                modules[i].setSwerveModule(states[i].angle.getDegrees(),
                                           states[i].speedMetersPerSecond);
            else // Not moving? Keep module pointed where it was
                modules[i].setSwerveModule(modules[i].getCurrentAngle().getDegrees(),
                                           0);
        }

        SmartDashboard.putNumber("gyro", gyro.getFusedHeading());
    }

 }
