package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {

        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(0)),
                new WaitCommand(300),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .forward(8)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(70)),
                new FeederAutoCommand(feeder, 0.3),
                new WaitCommand(300),
                new FeederAutoCommand(feeder, 0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(8, 0, Math.toRadians(0)))
                        .back(20)
                        .turn(Math.toRadians(180))
                        .forward(30)
                        .strafeRight(18)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(95)),
                new WaitCommand(300),
                // Intake Second Cone
                new FeederAutoCommand(feeder, -0.8),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(10)
                        .build()),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, -0.1),
                new WaitCommand(300),
                new PivotCommand(pivot, Math.toRadians(0)),
                // Drive to Score
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(5)
                        .turn(Math.toRadians(180))
                        .strafeRight(22)
                        .forward(50)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(70)),
                new FeederAutoCommand(feeder, 0.4),
                new WaitCommand(300),
                new FeederAutoCommand(feeder, 0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(50)
                        .setConstraints(drive.getVelocityConstraint(30, Math.toRadians(30), DriveConstants.TRACK_WIDTH), drive.getAccelerationConstraint(30))
                        .strafeRight(85)
                        .resetConstraints()
                        .turn(Math.toRadians(-3))
                        .build()),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(80)
                        .build()),
                new FeederAutoCommand(feeder, 0),
                new PivotCommand(pivot, Math.toRadians(0))

        );

        addCommands(
                autoBlue
        );
    }

}
