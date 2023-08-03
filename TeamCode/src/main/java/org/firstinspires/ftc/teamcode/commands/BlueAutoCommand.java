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
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

<<<<<<< HEAD
    public BlueAutoCommand(MecanumDrive drive, FeederSubsystem feeder, ShooterSubsystem shooter) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FeederAutoCommand(feeder, 1),
                new ShooterAutoCommand(shooter, () -> 1),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                        .back(47)
                        .strafeLeft(12)
                        .waitSeconds(1)
                        .strafeRight(12)
                        .forward(42)
                        .waitSeconds(1)
                        .turn(Math.toRadians(180))
                        .strafeRight(12)
                        .forward(128)
                        .build()),

                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(20)
                        .build())
                );
=======
    public BlueAutoCommand(MecanumDrive drive, FeederSubsystem feeder) {

        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(-45)
                        .waitSeconds(1)
                        .forward(45)
                        .waitSeconds(1).forward(-45)
                        .waitSeconds(1)
                        .forward(45)
                        .waitSeconds(1)
                        .strafeLeft(6)
                        .waitSeconds(1)
                        .forward(-135)
                        .waitSeconds(1)
                        .build())
        );
>>>>>>> a585cb708fa245033eace4f6efd9364344af633a

        addCommands(
                autoBlue
        );
    }

}
