package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterAutoCommand extends CommandBase {
    private final FeederSubsystem feederSubsystem;
    private final DoubleSupplier power;

    public ShooterAutoCommand(final FeederSubsystem feeder, final DoubleSupplier power) {
        feederSubsystem = feeder;
        this.power = power;

        addRequirements(feederSubsystem);
    }

    public ShooterAutoCommand(final FeederSubsystem feeder, final double power) {
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

