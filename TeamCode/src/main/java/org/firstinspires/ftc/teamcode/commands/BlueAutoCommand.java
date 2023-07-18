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
            new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(12)
                    .build()),
            new PivotCommand(pivot, Math.toRadians(-50)),
            new FeederCommand(feeder, -1),
            new WaitCommand(500),
            new FeederCommand(feeder, 0),
            new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .back(13)
                .turn(Math.toRadians(180))
                .forward(40)
                .build()),
            new PivotCommand(pivot, Math.toRadians(-100)),
            new FeederCommand(feeder, 1),
            new WaitCommand(500),
            new FeederCommand(feeder, 0),
            new PivotCommand(pivot, Math.toRadians(-50)),
            new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .back(13)
                .turn(Math.toRadians(180))
                .forward(40)
                .build()),
            new FeederCommand(feeder, -1),
            new WaitCommand(500),
            new FeederCommand(feeder, 0),
            new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .back(40)
                .strafeRight(63)
                .forward(65)
                .build())
        );

        addCommands(
                autoBlue
        );
    }

}
