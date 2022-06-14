package robot.drivetrain;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain
 {
    /** Don't rotate sverve module unless speed is at least this
     *  to avoid spinning in place
    */
    private static final double MINIMUM_SPEED_THRESHOLD = .05;

    private SwerveModule[] modules = new SwerveModule[]
    {
        new SwerveModule(0, -20),
        new SwerveModule(1, 87),
        new SwerveModule(2, 195),
        new SwerveModule(3, -107)
    };

    /** Size of chassis, distance between swerve modules */
    final public static double WIDTH = .64135; 
    final public static double LENGTH = .61595; 
    
    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
         new Translation2d( LENGTH / 2,  WIDTH / 2),
         new Translation2d( LENGTH / 2, -WIDTH / 2), 
         new Translation2d(-LENGTH / 2, -WIDTH / 2), 
         new Translation2d(-LENGTH / 2,  WIDTH / 2) );

    private PigeonIMU gyro = new PigeonIMU(0);

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

    public void swerve (double vx, double vy, double vr, Translation2d center)
    {
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(new ChassisSpeeds(vx, vy, vr), center);
   
        for (int i=0; i<modules.length; ++i)
        {
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
