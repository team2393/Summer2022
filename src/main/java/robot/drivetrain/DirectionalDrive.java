package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.OI;

public class DirectionalDrive extends CommandBase
{
    private DriveTrain drive_train;
    private SelectCenter center = new SelectCenter();

    public DirectionalDrive(DriveTrain drive_train)
    {
        this.drive_train = drive_train;
    }

    @Override
    public void execute()
    {
        double vx = OI.getForwardBackward();
        double vy = OI.getLeftRight();
        double vr = OI.getRotation();
        drive_train.swerve(vx, vy, vr, center.determineCenter());
    }
}
