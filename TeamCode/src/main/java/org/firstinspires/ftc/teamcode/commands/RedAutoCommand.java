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
                new WaitCommand(400),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .forward(10)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(70)),
                new FeederAutoCommand(feeder, 0.3),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, 0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(10, 0, Math.toRadians(0)))
                        .back(20)
                        .turn(Math.toRadians(180))
                        .forward(20)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(85)),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, -0.7),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(-30, 0, Math.toRadians(180)))
                        .strafeRight(5)
                        .forward(15)
                        .build()),
                new WaitCommand(300),
                new FeederAutoCommand(feeder, 0),
                new WaitCommand(1000),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(-45, -5, Math.toRadians(180)))
                        .back(5)
                        .strafeLeft(3)
                        .turn(Math.toRadians(180))
                        .forward(55)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(70)),
                new FeederAutoCommand(feeder, 0.3),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, 0),
                new PivotCommand(pivot, Math.toRadians(0)),

                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(15, -2, Math.toRadians(0)))
                        .back(60)
                        .strafeLeft(70)
                        .forward(80)
                        .build()),
                new FeederAutoCommand(feeder, 0)

        );


        addCommands(
                autoRed
        );
    }

}
