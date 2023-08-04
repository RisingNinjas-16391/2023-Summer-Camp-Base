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

    public BlueAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder, ShooterSubsystem shooter) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                // move the arm to 90 degrees
                new PivotCommand(pivot, Math.toRadians(80)),

                new ParallelCommandGroup(
                        // shoot ball at 30% power
                        new ShooterAutoCommand(shooter, 0.3),
                        new SequentialCommandGroup(
                                new WaitCommand(550), // ramps flywheel for 1/2 seconds
                                new FeederAutoCommand(feeder, 0.7),
                                new WaitCommand(1000)
                        )
                ),
                //stop shooter and feeder
                new ShooterAutoCommand(shooter, 0),
                new FeederAutoCommand(feeder, 0),


                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(65)
                        .build()),

                new PivotCommand(pivot, Math.toRadians(140)),
                new WaitCommand(2500),
                new PivotCommand(pivot, Math.toRadians(80)),

//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
//                    .back(50)
//                    .build()),

                new ParallelCommandGroup(
                    // shoot ball at 30% power
                    new ShooterAutoCommand(shooter, 0.3),
                    new SequentialCommandGroup(
                            new WaitCommand(950), // ramps flywheel for 1/2 seconds
                            new FeederAutoCommand(feeder, 0.7),
                            new WaitCommand(1000)
                    )
                ),

                new FeederAutoCommand(feeder, 0),
                new ShooterAutoCommand(shooter, 0),

                new PivotCommand(pivot, Math.toRadians(140)),
                new WaitCommand(2500),
                new PivotCommand(pivot, Math.toRadians(80)),

//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((drive.getPoseEstimate()))
//                    .back(50)
//                    .build()),

                new ParallelCommandGroup(
                        // shoot ball at 30% power
                        new ShooterAutoCommand(shooter, 0.3),
                        new SequentialCommandGroup(
                                new WaitCommand(950), // ramps flywheel for 1/2 seconds
                                new FeederAutoCommand(feeder, 0.7),
                                new WaitCommand(1000)
                        )
                ),

                new FeederAutoCommand(feeder, 0),
                new ShooterAutoCommand(shooter, 0)

//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder(drive.getPoseEstimate())
//                        .back(56)
//                        .turn(Math.toRadians(90))
//                        .forward(12)
//                        .waitSeconds(.75)
//                        .build()),
//
//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
//                        .forward(20)
//                        .build())
                );

        addCommands(
                autoBlue
        );
    }

}
