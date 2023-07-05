package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.ParallelRaceGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.commands.FeederCommand;
import org.firstinspires.ftc.teamcode.commands.PivotCommand;
import org.firstinspires.ftc.teamcode.commands.PivotPowerCommand;
import org.firstinspires.ftc.teamcode.commands.SpinnyCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SpinnySubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder, SpinnySubsystem spinny){
        TrajectorySequenceSupplier strafeRight50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeRight(-50)
                .build();

        TrajectorySequenceSupplier forward30 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .forward(-30)
                .build();

        TrajectorySequenceSupplier forward75 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-75)
                .build();

        TrajectorySequenceSupplier back75 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .back(-75)
                .build();

        TrajectorySequenceSupplier turn80 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(-80))
                .build();

        TrajectorySequenceSupplier turn100 = ()->drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(100))
                .build();

        SequentialCommandGroup initToFeed = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(-45)),
                new FollowTrajectoryCommand(drive, strafeRight50),
                new WaitCommand(500),
                new FollowTrajectoryCommand(drive, forward30),
                new FeederCommand(feeder, () -> -1),
                new WaitCommand(500),
                new FeederCommand(feeder, () -> 0)
        );

        SequentialCommandGroup onRamp = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, turn80),
                new FollowTrajectoryCommand(drive, forward75),
                new FollowTrajectoryCommand(drive, turn100),
                new FollowTrajectoryCommand(drive, back75)
        );

        addCommands(
                initToFeed,
                new WaitCommand(1000),
                onRamp
        );
    }

}
