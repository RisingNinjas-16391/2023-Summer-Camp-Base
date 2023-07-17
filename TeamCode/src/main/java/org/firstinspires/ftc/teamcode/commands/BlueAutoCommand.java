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
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(10)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(73)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(10)
                        .turn(Math.toRadians(180))
                        .forward(77)
                        .build()),
                new FeederAutoCommand(feeder, 1),
                new WaitCommand(1000),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(5)
                        .turn(Math.toRadians(180))
                        .forward(72)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(90)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(62)
                        .strafeRight(50)
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
