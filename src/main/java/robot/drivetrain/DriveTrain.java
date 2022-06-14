package robot.drivetrain;

import com.fasterxml.jackson.databind.introspect.WithMember;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class DriveTrain
 {
    /**
     *
     */
    private static final double MINIMUM_SPEED_THRESHOLD = .05;

    private SwerveModule[] modules = new SwerveModule[]
    {
    new SwerveModule(0, -20),
    new SwerveModule(1, 87),
    new SwerveModule(2, 195),
    new SwerveModule(3, -107)
    };

    final public static double WIDTH = .64135; 
    final public static double LENGTH = .61595; 

    
     private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
         new Translation2d( LENGTH / 2,  WIDTH / 2),
         new Translation2d( LENGTH / 2, -WIDTH / 2), 
         new Translation2d(-LENGTH / 2, -WIDTH / 2), 
         new Translation2d(-LENGTH / 2,  WIDTH / 2)
     );

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

            if (Math.abs(states[i].speedMetersPerSecond) > MINIMUM_SPEED_THRESHOLD)
                modules[i].setSwerveModule(states[i].angle.getDegrees(),
                                           states[i].speedMetersPerSecond);
            else 
                modules[i].setSwerveModule(modules[i].getCurrentAngle().getDegrees(),
                                           0);
        }

    }




 }
