// Copyright (c) Team 2393, FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package robot.auto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.Trajectory.State;

/** Plot trajectory on laptop
 * 
 *  Use to check trajectories without robot
 */
public class TrajectoryViewer
{
    private final Trajectory trajectory;
    private final double time_step;

    private class TrajectoryPlot extends JPanel
    {
        private static final long serialVersionUID = 1L;
        private double xmin, xmax, ymin, ymax, speedmax, traj_width, traj_height, scale;

        @Override
        protected void paintComponent(final Graphics g)
        {
            xmin = 0;
            xmax = 1;
            ymin = 0;
            ymax = 1;
            speedmax = 0;
            // Determine bounding box of trajectory
            for (State state : trajectory.getStates())
            {
                final double x = state.poseMeters.getTranslation().getX(),
                             y = state.poseMeters.getTranslation().getY();
                xmin = Math.min(xmin, x);
                xmax = Math.max(xmax, x);
                ymin = Math.min(ymin, y);
                ymax = Math.max(ymax, y);
                speedmax = Math.max(speedmax, Math.abs(state.velocityMetersPerSecond));
            }
            // Largest size (width or height) of trajectory
            traj_width = xmax - xmin;
            traj_height = ymax - ymin;
            final double traj_size = Math.max(traj_width, traj_height);
            // Scale to fit onto plot allowing for 10 pixels around edges
            final double plot_size = Math.min(getWidth(), getHeight()) - 20;
            scale = plot_size / traj_size;

            final double total_time = trajectory.getTotalTimeSeconds();
            // Draw all  states
            g.setColor(Color.BLUE);
            for (double time=0; time < total_time+1;  time += time_step)
                draw(g, trajectory.sample(time));
            // Draw start and end again in standout colors
            g.setColor(Color.GREEN);
            draw(g, trajectory.sample(0));
            g.setColor(Color.RED);
            draw(g, trajectory.sample(total_time));
        }

        private void draw(final Graphics g, final State state)
        {
            final int x = 10 + (int) Math.round((traj_height - state.poseMeters.getTranslation().getY() + ymin) * scale);
            final int y = 10 + (int) Math.round((traj_width  - state.poseMeters.getTranslation().getX()) * scale);
            g.fillOval(x-5, y-5, 10, 10);

            final double speed = state.velocityMetersPerSecond * 20 / speedmax;
            final int x1 = x - (int) Math.round(state.poseMeters.getRotation().getSin() * speed);
            final int y1 = y - (int) Math.round(state.poseMeters.getRotation().getCos() * speed);
            g.drawLine(x, y, x1, y1);
        }
    }

    public TrajectoryViewer(final Trajectory trajectory)
    {
        this(trajectory, 0.5);
    }

    public TrajectoryViewer(final Trajectory trajectory, final double time_step)
    {
        this.trajectory = trajectory;
        this.time_step = time_step;
        SwingUtilities.invokeLater(this::createAndShowPlot);
    }

    private void createAndShowPlot()
    {
        final JFrame frame = new JFrame("Trajectory Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new TrajectoryPlot(),
                                  BorderLayout.CENTER);

        frame.getContentPane().add(new JLabel(String.format("Total time: %.2f seconds",
                                  trajectory.getTotalTimeSeconds())),
        BorderLayout.SOUTH);

        frame.setBounds(10, 10, 600, 800);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception
    {
        TrajectoryConfig config = new TrajectoryConfig(1.0, 1.0);

        List<Pose2d> waypoints = new ArrayList<>();
        waypoints.add(new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
        waypoints.add(new Pose2d(1, 1, Rotation2d.fromDegrees(90)));
        
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(waypoints, config);

        // Show it
        new TrajectoryViewer(trajectory, 0.25);
    }
}
