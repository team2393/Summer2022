package robot.drivetrain;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class DriveTrain extends SubsystemBase
 {
    /** Don't rotate swerve module unless speed is at least this
     *  to avoid spinning in place
     */
    private static final double MINIMUM_SPEED_THRESHOLD = .05;

    /** Modules located in four corners of apx. square robot */
    private SwerveModule[] modules = new SwerveModule[]
    {
        new SwerveModule(0,  -20),  // Front left
        new SwerveModule(1,   87),  // Front right
        new SwerveModule(2,  195),  // Back right
        new SwerveModule(3, -107)   // Back left
    };

    /** Size of chassis which is not exactly a square,
     *  distance between swerve modules
     */
    final public static double WIDTH  = .64135; 
    final public static double LENGTH = .61595; 
    
    /** Kinematics that translate chassis speed to module settings and vice versa */
    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d( LENGTH / 2,  WIDTH / 2),
        new Translation2d( LENGTH / 2, -WIDTH / 2), 
        new Translation2d(-LENGTH / 2, -WIDTH / 2), 
        new Translation2d(-LENGTH / 2,  WIDTH / 2) );

    /** Gyro that provides heading of robot */
    private PigeonIMU gyro = new PigeonIMU(0);
    private double gyro_offset = 0;


    private SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(0));
    
    public DriveTrain()
    {
        SmartDashboard.setDefaultNumber("XYPvalue", 1);
        SmartDashboard.setDefaultNumber("RotationPValue", 5);
    }


    public void reset() 
    {
        gyro_offset = gyro.getFusedHeading();
        odometry.resetPosition(new Pose2d(0, 0, Rotation2d.fromDegrees(0)), Rotation2d.fromDegrees(getheading()));
    } 

    public double getheading()
    {
        return gyro.getFusedHeading() - gyro_offset;
    } 

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
            if (Math.abs(states[i].speedMetersPerSecond) < MINIMUM_SPEED_THRESHOLD)
                states[i] = new SwerveModuleState(0, modules[i].getCurrentAngle());

            modules[i].setSwerveModule(states[i].angle.getDegrees(),
                                       states[i].speedMetersPerSecond);
        }

    }

    @Override
    public void periodic() 
    {
        SwerveModuleState states[] = new SwerveModuleState[modules.length];
        for (int i=0; i<modules.length; ++i)
        {
            states[i] = modules[i].getState();
        }
        odometry.update(Rotation2d.fromDegrees(getheading()), states);


        SmartDashboard.putNumber("gyro", getheading());
        SmartDashboard.putNumber("x", odometry.getPoseMeters().getX());
        SmartDashboard.putNumber("y", odometry.getPoseMeters().getY());
        SmartDashboard.putNumber("angle", odometry.getPoseMeters().getRotation().getDegrees());
    }

    public CommandBase createTrajectoryCommand(Trajectory trajectory, double endAngle)
    {
        PIDController xController = new PIDController(SmartDashboard.getNumber("XYPvalue", 0), 0, 0);
        PIDController yController = new PIDController(SmartDashboard.getNumber("XYPvalue", 0), 0, 0);
        
        final ProfiledPIDController thetaController = new ProfiledPIDController(SmartDashboard.getNumber("RotationPValue", 0), 0, 0,
                new TrapezoidProfile.Constraints(Math.toRadians(30),
                                                 Math.toRadians(30)));
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        
        
        Supplier<Pose2d> pose = () -> odometry.getPoseMeters();
        Consumer<SwerveModuleState[]> outputModuleStates = states ->
        {
            for (int i=0; i<modules.length; ++i)
            {
                modules[i].setSwerveModule(states[i].angle.getDegrees(),
                                           states[i].speedMetersPerSecond);
            }
        };
        Supplier<Rotation2d> desiredRotation =() -> Rotation2d.fromDegrees(endAngle);
        return new SwerveControllerCommand(trajectory, pose, kinematics, xController, yController, thetaController, desiredRotation, outputModuleStates, this);
    }
 }
