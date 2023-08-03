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

public class RedAutoCommand extends SequentialCommandGroup {

    public RedAutoCommand(MecanumDrive drive, FeederSubsystem feeder, ShooterSubsystem shooter) {

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new FeederAutoCommand(feeder, 1),
                new ShooterAutoCommand(shooter, 1),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                    .forward(47)
                        .build()),
                new FeederAutoCommand(feeder,0),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                    .strafeRight(12)
                    .waitSeconds(3)
                    .strafeLeft(12)
                    .back(42)
                    .waitSeconds(1)
                    .turn(Math.toRadians(180))
                    .strafeLeft(12)
                    .back(128)
                    .build())
        );


        addCommands(
                autoRed
        );
    }

}
