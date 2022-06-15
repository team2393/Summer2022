package robot.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class StayPut extends CommandBase
{
  private DriveTrain driveTrain;

  public StayPut(DriveTrain drivetrain)
  {
      this.driveTrain = drivetrain;
  }
@Override
public void execute() 
{
   driveTrain.drive(0, 0); 
}
}
