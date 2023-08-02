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

<<<<<<< HEAD
    public FeederAutoCommand (final FeederSubsystem feeder, final double power) {
        this(feeder, () -> power);
=======
    public FeederAutoCommand(final FeederSubsystem feeder, final double power) {
        this(feeder, ()-> power);
>>>>>>> 7fac26325a2938f050b6dac0f0dd11b9f2c80742
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

