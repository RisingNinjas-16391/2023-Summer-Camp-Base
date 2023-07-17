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
                        .forward(12)
                        .build()),
                new WaitCommand(500),
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, () -> -1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder,() -> 0),
                new PivotCommand(pivot, Math.toRadians(-45)),
                new FollowTrajectoryCommand(drive,() -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(54)
                        .strafeRight(90)
                        .forward(70)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(90)),
                new WaitCommand(3000),
                new FeederAutoCommand(feeder,() -> 1)
                );

        addCommands(
                autoBlue
        );
    }

}
