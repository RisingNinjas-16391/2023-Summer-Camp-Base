package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, FeederSubsystem feeder) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .back(47)
                        .strafeLeft(12)
                        .waitSeconds(1)
                        .strafeRight(12)
                        .forward(42)
                        .waitSeconds(1)
                        .turn(Math.toRadians(180))
                        .strafeRight(12)
                        .forward(128)
                        .build())
        );

        addCommands(
                autoBlue
        );
    }

}
