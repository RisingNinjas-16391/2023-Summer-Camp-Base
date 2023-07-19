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
                new WaitCommand(1500),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(20)
                        .build()),


                new PivotCommand(pivot, Math.toRadians(60)),
                new WaitCommand(2000),

                new FeederAutoCommand(feeder, 0.3),
                new WaitCommand(1000),
                new FeederAutoCommand(feeder,0),


                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                            .back(55)
                        .build()),

                new PivotCommand(pivot, Math.toRadians(0)),

                new FollowTrajectoryCommand(drive, ()->drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .strafeRight(65)
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
                autoBlue
        );
    }

}
