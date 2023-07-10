package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class RedAutoCommand extends SequentialCommandGroup {

    public RedAutoCommand (MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {
        TrajectorySequenceSupplier forward50 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-50)
                .build();

        TrajectorySequenceSupplier back50 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .back(-50)
                .build();

        TrajectorySequenceSupplier strafeRight50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(-50)
                .build();

        TrajectorySequenceSupplier strafeLeft50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(-50)
                .build();

        TrajectorySequenceSupplier turnLeft90 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(-90))
                .build();

        TrajectorySequenceSupplier turnRight90 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(90))
                .build();

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, forward50),
                new FollowTrajectoryCommand(drive, back50),
                new FollowTrajectoryCommand(drive, strafeLeft50),
                new FollowTrajectoryCommand(drive, strafeRight50),
                new FollowTrajectoryCommand(drive, turnLeft90),
                new FollowTrajectoryCommand(drive, turnRight90),

                new PivotCommand(pivot, Math.toRadians(-90)),
                new WaitCommand(2000),

                new FeederAutoCommand(feeder, () -> -1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, () -> 0),

                new PivotCommand(pivot, Math.toRadians(0))
        );

        addCommands(
                autoRed
        );
    }

}
