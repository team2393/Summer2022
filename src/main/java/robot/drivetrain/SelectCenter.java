package robot.drivetrain;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import robot.OI;

public class SelectCenter 
{
    private Translation2d center = new Translation2d(0, 0);

    public Translation2d determineCenter()
    {
        if (OI.resetCenter())
            center = new Translation2d(0, 0);
        if (OI.leftCenter())
            center = new Translation2d(center.getX(), center.getY() + DriveTrain.WIDTH / 2);
        if (OI.rightCenter())
            center = new Translation2d(center.getX(), center.getY() - DriveTrain.WIDTH / 2);
        if (OI.frontCenter())
            center = new Translation2d(center.getX() + DriveTrain.LENGTH / 2, center.getY());
        if (OI.backCenter())
            center = new Translation2d(center.getX()- DriveTrain.LENGTH / 2, center.getY());
        SmartDashboard.putNumber("CenterX", center.getX());
        SmartDashboard.putNumber("CenterY", center.getY());
        return center;
    }
    
}
