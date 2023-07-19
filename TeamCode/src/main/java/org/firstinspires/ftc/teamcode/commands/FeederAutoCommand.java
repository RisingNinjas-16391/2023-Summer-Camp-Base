package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;

import java.util.function.DoubleSupplier;

public class FeederAutoCommand extends CommandBase {
    private final FeederSubsystem feederSubsystem;
    private final DoubleSupplier power;

    public FeederAutoCommand (final FeederSubsystem feeder, final DoubleSupplier power) {
        feederSubsystem = feeder;
        this.power = power;

        addRequirements(feederSubsystem);
    }

    public FeederAutoCommand(final FeederSubsystem feeder, final double power) {
        this(feeder, () -> power);
    }

    @Override
    public void execute() {
        feederSubsystem.setPower(power.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return !feederSubsystem.isBusy();
    }
}

