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
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(20)
                        .back(20)
                        .turn(Math.toRadians(175))
                        .forward(45)
                        .back(10)
                        .turn(Math.toRadians(175))
                        .forward(80)
                        .back(50)
                        .strafeRight(55)
                        .forward(60)
                        .build())
//                new FeederCommand(feeder, () -> 1),
//                new PivotCommand(pivot, Math.toRadians(90))
        );

        addCommands(
                autoBlue
        );
    }

}
