package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StayPut extends CommandBase
{
    private DriveTrain drive_train;
    private double angle;

    public StayPut(DriveTrain drive_train, double angle)
    {
        this.drive_train = drive_train;
        this.angle = angle;
    }

    @Override
    public void execute() 
    {
        drive_train.drive(angle, 0); 
    }
}
