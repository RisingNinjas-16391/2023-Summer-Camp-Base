package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class RedAutoCommand extends SequentialCommandGroup {

    public RedAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(13)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(200),
                new FeederAutoCommand(feeder, 0.7),
                new WaitCommand(400),
                new FeederAutoCommand(feeder, 0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .lineToLinearHeading( new Pose2d(0, -14, Math.toRadians(180)))
                        .build()),
                new PivotCommand(pivot, Math.toRadians(92)),
                new FeederAutoCommand(feeder, -0.6),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(45)
                        .build()),
                new WaitCommand(400),
                new FeederAutoCommand(feeder, 0),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .lineToLinearHeading( new Pose2d(0, -14, Math.toRadians(180)))
                        .forward(45)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(400),
                new FeederAutoCommand(feeder, 0.7),
                new WaitCommand(200),
                new FeederAutoCommand(feeder, 0),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(43)
                        .strafeLeft(63)
                        .forward(40)
                        .build()),
                new ParallelCommandGroup(
                    new PivotCommand(pivot, Math.toRadians(93)),
                    new FeederAutoCommand(feeder, 0.6),
                    new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                            .forward(25)
                            .build())
                ),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(-10)
                        .back(-10)
                        .strafeRight(-10)
                        .strafeLeft(-10)
                        .build())
        );

        addCommands(
                autoRed
        );
    }

}
