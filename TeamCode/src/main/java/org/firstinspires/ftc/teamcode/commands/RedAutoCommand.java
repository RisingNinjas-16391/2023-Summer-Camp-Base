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
                //lift up arm, drive to peg and score preloaded cone
                new FeederAutoCommand(feeder, .25),
                new PivotCommand(pivot, Math.toRadians(42)),
                new WaitCommand(200),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(20)
                        .build()),
                new FeederAutoCommand(feeder, -1),
                new WaitCommand(100),
                new FeederAutoCommand(feeder, 0),

                //get second cone
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(21)
                        .turn(Math.toRadians(178))
                        .build()),
                new PivotCommand(pivot, Math.toRadians(120)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(36)
                        .build()),
                new FeederAutoCommand (feeder, 1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, .25),

                //lift up arm, drive to peg and score second cone
                new PivotCommand(pivot, Math.toRadians(42)),
                new WaitCommand(500),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(20)
                        .turn(Math.toRadians(180))
                        .forward(38)
                        .build()),
                new FeederAutoCommand (feeder, -1),
                new WaitCommand(100),
                new FeederAutoCommand (feeder, 0),
                new PivotCommand(pivot, Math.toRadians(10)),

                //get on ramp
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(50)
                        .strafeLeft(68)
                        .forward(100)
                        .back(2)
                        .build())
        );

        addCommands(
                autoRed
        );
    }

}
