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
        TrajectorySequenceSupplier pathA = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(10)
                .build();

        TrajectorySequenceSupplier pathB = () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .back(45)
                .strafeRight(67)
                .build();

        TrajectorySequenceSupplier pathC = () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(40)
                .build();

        TrajectorySequenceSupplier pathD = () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .forward(30)
                .build();

        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(30)),
                new FollowTrajectoryCommand(drive, pathA),
                new ScoreCommand(pivot, feeder),
                new PivotCommand(pivot, Math.toRadians(30)),
                new FollowTrajectoryCommand(drive, pathB),
                new PivotCommand(pivot, Math.toRadians(80)),
                new FollowTrajectoryCommand(drive, pathC),
                new PivotCommand(pivot, Math.toRadians(100)),
                new FeederAutoCommand(feeder, () -> -0.7),
                new FollowTrajectoryCommand(drive, pathD),
                new FeederAutoCommand(feeder, () -> 0)
        );

        addCommands(
                autoBlue
        );
    }

}
