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
                //lift up arm, drive to peg and score preloaded cone
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(2000),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .forward(20)
                        .build()),
                new FeederCommand(feeder, -1),

                //get second cone
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(20)
                        .turn(Math.toRadians(180))
                        .forward(50)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(0)),
                new WaitCommand(2000),
                new FeederCommand (feeder, 1),

                //lift up arm, drive to peg and score second cone
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(2000),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .turn(Math.toRadians(180))
                        .forward(70)
                        .build()),
                new FeederCommand (feeder, -1),

                //get on ramp
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(50)
                        .strafeRight(100)
                        .forward(100)
                        .build())
        );

        addCommands(
                autoBlue
        );
    }

}
