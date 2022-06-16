package robot.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.OI;

public class AbsoluteDrive extends CommandBase
{
    private DriveTrain drive_train;

    public AbsoluteDrive(DriveTrain drive_train)
    {
        this.drive_train = drive_train;
    }

    @Override
    public void execute()      
    {
        double vx = OI.getForwardBackward();
        double vy = OI.getLeftRight();
        double vr = OI.getRotation();

        Translation2d absoluteDirection = new Translation2d(vx, vy).rotateBy(Rotation2d.fromDegrees(-drive_train.getheading()));

        drive_train.swerve(absoluteDirection.getX(), absoluteDirection.getY(), vr, new Translation2d(0, 0));
    }  

}
