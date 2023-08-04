package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class ScoreCommand extends SequentialCommandGroup {
    public ScoreCommand(PivotSubsystem pivot, FeederSubsystem feeder, ShooterSubsystem shooter) {
        SequentialCommandGroup scoreCommand = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(80)),
                new WaitCommand(500),

                new ParallelCommandGroup(
                        // shoot ball at 30% power
                        new ShooterAutoCommand(shooter, 0.3),
                        new SequentialCommandGroup(
                                new WaitCommand(700), // ramps flywheel for 1 seconds
                                new FeederAutoCommand(feeder, 0.7),
                                new WaitCommand(1000)
                        )
                ),
                new FeederAutoCommand(feeder, 0).withTimeout(100),
                new ShooterAutoCommand(shooter, 0).withTimeout(100)
        );

        addCommands(scoreCommand);
    }
}
