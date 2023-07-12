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
        TrajectorySequenceSupplier pathA = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-20)
                .back(-74)
                .strafeRight(-47)
                .forward(-75)
                .build();

        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, pathA)

        );

        addCommands(
                autoBlue
        );
    }

}
