package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.internal.android.dx.util.Warning;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
<<<<<<< Updated upstream
                new PivotCommand(pivot, Math.toRadians(-30)),
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(10)
                        .build()),
                new PivotCommand(pivot, Math.toRadians(-30)),
                new FeederAutoCommand(feeder, () -> 1)
=======
                new ShooterAutoCommand(shooter, 140),
                new WaitCommand(2000),
                new FeederAutoCommand(feeder, 0.7),
                new WaitCommand(15000),
                new ShooterAutoCommand(shooter,0),
                new FeederAutoCommand(feeder, 0)


>>>>>>> Stashed changes
        );

        addCommands(
                autoBlue
        );
    }

}
