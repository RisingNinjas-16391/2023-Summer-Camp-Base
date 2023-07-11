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
        TrajectorySequenceSupplier pathA = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-55)
                .strafeRight(-60)
                .back(-85)
                .build();

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, pathA)

        );

        addCommands(
                autoRed
        );
    }

}
