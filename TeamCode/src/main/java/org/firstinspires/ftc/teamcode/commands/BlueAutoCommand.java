package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
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
                new PivotCommand(pivot, Math.toRadians(90)),
                new WaitCommand(300),
                new FeederAutoCommand(feeder, -0.7),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(10)
                        .build()),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, 0),
                new WaitCommand(300),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(5)
                        .turn(Math.toRadians(180))
                        .strafeRight(30)
                        .forward(53)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(80)),
                new FeederAutoCommand(feeder, 0.3),
                new WaitCommand(300),
                new FeederAutoCommand(feeder, 0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(50)
                        .strafeRight(85)
                        .turn(Math.toRadians(-3))
                        .forward(70)
                        .build()),
                new FeederAutoCommand(feeder, 0),
                new PivotCommand(pivot, Math.toRadians(0))

        );

        addCommands(
                autoBlue
        );
    }

}
