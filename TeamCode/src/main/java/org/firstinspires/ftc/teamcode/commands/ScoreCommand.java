package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class ScoreCommand extends SequentialCommandGroup {
    public ScoreCommand(PivotSubsystem pivot, FeederSubsystem feeder) {
        SequentialCommandGroup scoreCommand = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(45)),
                new WaitCommand(500),
                new ParallelCommandGroup(
                        new PivotCommand(pivot, Math.toRadians(100)),
                        new FeederAutoCommand(feeder, () -> 0.25)
                ),
                new WaitCommand(800),
                new FeederAutoCommand(feeder, () -> 0).withTimeout(100)
        );

        addCommands(scoreCommand);
    }
}
