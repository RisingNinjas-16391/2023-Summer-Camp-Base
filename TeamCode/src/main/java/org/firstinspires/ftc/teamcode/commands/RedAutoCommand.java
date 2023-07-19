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
                new FeederAutoCommand(feeder,.25),
                new PivotCommand(pivot, Math.toRadians(30)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .forward(15)
                        .build()),
                new FeederAutoCommand(feeder,-.5),
                new WaitCommand(500),
                new FeederAutoCommand(feeder,0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(15, 0, Math.toRadians(0)))
                        .back(15)
                        .turn(Math.toRadians(180))
                        .build()),
                new PivotCommand(pivot, Math.toRadians(100)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(180)))
                        .forward(41)
                        .build()),
                new FeederAutoCommand(feeder,.5),
                new WaitCommand(750),
                new PivotCommand(pivot, Math.toRadians(25)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(-41,0, Math.toRadians(180)))
                        .back(25)
                        .turn(Math.toRadians(180))
                        .forward( 35)
                        .build()),
                new FeederAutoCommand(feeder,-.25),






                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(20,0, Math.toRadians(0)))
                        .back(50)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(-30,0, Math.toRadians(0)))
                        .strafeLeft(71)
                        .forward(65)
                        .build())
        );

        addCommands(
                autoRed
        );
    }

}
