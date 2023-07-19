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

                new PivotCommand(pivot, Math.toRadians(40)),
                new WaitCommand(500),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(0)))
                        .forward(9)
                        .build()),
                new WaitCommand(500),
                new ParallelCommandGroup(
                        new PivotCommand(pivot, Math.toRadians(60)),
                        new SequentialCommandGroup(
                                new WaitCommand(500),
                                new FeederAutoCommand(feeder, () -> -0.47))


                ),
                new WaitCommand(800),
                new FeederAutoCommand(feeder,() -> 0),
                new PivotCommand(pivot, Math.toRadians(0)),
                new FollowTrajectoryCommand(drive,() -> drive.trajectorySequenceBuilder(new Pose2d(9, 0, Math.toRadians(0)))
                        .back(45)
                        .turn(Math.toRadians(180))
                        .build()),
                new ParallelCommandGroup(
                        new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(new Pose2d(-36,0, Math.toRadians(180)))
                                .strafeLeft(20)
                                .forward(19)
                                .build()),
                        new PivotCommand(pivot, Math.toRadians(102)),
                        new WaitCommand(500),
                        new FeederAutoCommand(feeder,()->1)

                ),
                new PivotCommand(pivot, Math.toRadians(97)),
                new WaitCommand(1200),
                new FollowTrajectoryCommand(drive,() -> drive.trajectorySequenceBuilder(new Pose2d(-54,-20, Math.toRadians(180)))
                        .back(18)
                        .strafeRight(18)
                        .turn(Math.toRadians(180))
                        .build()),
                new PivotCommand(pivot, Math.toRadians(30)),
                new FollowTrajectoryCommand(drive,() -> drive.trajectorySequenceBuilder(new Pose2d(-36,-2, Math.toRadians(0)))
                        .forward(45)
                .build()),
                new WaitCommand(500),
                new ParallelCommandGroup(
                        new PivotCommand(pivot, Math.toRadians(60)),
                        new SequentialCommandGroup(
                                new WaitCommand(500),
                                new FeederAutoCommand(feeder, () -> -0.47))
                ),
                new FollowTrajectoryCommand(drive,() -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(45)
                        .strafeRight(78)
                        .build()),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(64)
                        .build()),
                new ParallelCommandGroup(
//                        new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
//                                .build()),
                        new WaitCommand(500),
                        new FeederCommand(feeder,()-> 1)
                        ),


                new WaitCommand(500),
                new FeederAutoCommand(feeder,() -> 1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder,() -> 0)

                );


        addCommands(
                autoBlue
        );
    }

}
