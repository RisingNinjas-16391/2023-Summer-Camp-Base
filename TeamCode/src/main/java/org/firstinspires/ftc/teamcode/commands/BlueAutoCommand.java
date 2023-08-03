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
                new PivotCommand(pivot,Math.toRadians(90)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .strafeRight(5)
                        .build()),
                new FeederAutoCommand(feeder,-1),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .back(64)
                        .build()),
                new FeederAutoCommand(feeder,1)
    );

        addCommands(
                autoBlue
        );
    }

}
