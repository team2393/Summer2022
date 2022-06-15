package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StayPut extends CommandBase
{
    private DriveTrain drive_train;

    public StayPut(DriveTrain drive_train)
    {
        this.drive_train = drive_train;
    }

    @Override
    public void execute() 
    {
        drive_train.drive(0, 0); 
    }
}
