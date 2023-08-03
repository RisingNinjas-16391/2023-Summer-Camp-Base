package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, ShooterSubsystem shooter, FeederSubsystem feeder) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FeederAutoCommand(feeder, -0.5),
                new ShooterAutoCommand(shooter, -175)
//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
//                        .strafeRight(16)
//                        .forward(48.5)
//                        .waitSeconds(1.5)
//                        .back(44)
//                        .strafeLeft(16)
//                        .strafeRight(16)
//                        .forward(135)
//                        .build()),
//                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
//                        .forward(20)
//                        .build())
        );


        addCommands(
                autoBlue
        );
    }

}
