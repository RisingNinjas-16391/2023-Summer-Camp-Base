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

        // TODO: Tune auto written by Brian
        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                // Pivot to score
                new PivotCommand(pivot,Math.toRadians(80)),
                // Drive to scoring point
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
                        .strafeLeft(15)
                        .build()),
                // Shoot ball
                new FeederAutoCommand(feeder,-1),
                // Group to move arm and move back
                new ParallelCommandGroup(
                        new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
                                .strafeRight(15)
                                .back(64)
                                .build()),
                        new PivotCommand(pivot, Math.toRadians(-10))
                ),
                // Intake
                new FeederAutoCommand(feeder,1),
                // Start driving forward and then raise arm 1sec in
                new ParallelCommandGroup(
                        new SequentialCommandGroup(
                                new WaitCommand(1000),
                                new PivotCommand(pivot, Math.toRadians(80))
                        ),
                        new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
                            .forward(64)
                            .strafeLeft(15)
                            .build())),
                // shoot ball
                new FeederAutoCommand(feeder, -1),
                // Wait for ball to shoot
                new WaitCommand(1000),
                // Move to park
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
                        .strafeRight(15)
                        .back(95)
                        .build())


                );

        addCommands(
                autoRed
        );
    }

}
