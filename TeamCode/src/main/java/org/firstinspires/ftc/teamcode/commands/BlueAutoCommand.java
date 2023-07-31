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
                new ShooterAutoCommand(shooter, 135),
                new FeederAutoCommand(feeder, -0.5)
        );

        addCommands(
                autoBlue
        );
    }

}
