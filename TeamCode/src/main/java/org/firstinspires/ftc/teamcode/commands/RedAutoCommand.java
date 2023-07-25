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
                new WaitCommand(1000),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(18)
                        .build()),


                new PivotCommand(pivot, Math.toRadians(64)),
                new WaitCommand(2000),

                new FeederAutoCommand(feeder, 0.6),
                new WaitCommand(1000),
                new FeederAutoCommand(feeder,0),


                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(55)
                        .build()),

                new PivotCommand(pivot, Math.toRadians(0)),

                new FollowTrajectoryCommand(drive, ()->drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .strafeLeft(65)
                        .forward(45)
                        .build()),

                new PivotCommand(pivot, Math.toRadians(120)),

                new FollowTrajectoryCommand(drive, ()->drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(45)
                        .build()),

                new FeederAutoCommand(feeder, -0.8),
                new WaitCommand(1000),
                new FeederAutoCommand(feeder,0)

        );

        addCommands(
                autoRed
        );
    }

}
