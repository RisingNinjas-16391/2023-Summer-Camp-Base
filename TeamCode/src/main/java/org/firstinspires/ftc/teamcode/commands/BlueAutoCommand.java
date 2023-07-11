package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {
        TrajectorySequenceSupplier forward50 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-50)
                .build();

        TrajectorySequenceSupplier back50 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .back(-10)
                .build();

        TrajectorySequenceSupplier strafeRight50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(-10)
                .build();

        TrajectorySequenceSupplier strafeLeft50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(-50)
                .build();

        TrajectorySequenceSupplier turnLeft90 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequenceSupplier turnRight90 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(90))
                .build();

        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, back50),
                new PivotCommand(pivot, Math.toRadians(0)),
                new WaitCommand(2000),
                new FollowTrajectoryCommand(drive, forward50),
                new FollowTrajectoryCommand(drive, strafeLeft50),
                new FollowTrajectoryCommand(drive, back50),

                new FeederAutoCommand(feeder, () -> -1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, () -> 0),

                new PivotCommand(pivot, Math.toRadians(0))
        );

        addCommands(
                autoBlue
        );
    }

}
