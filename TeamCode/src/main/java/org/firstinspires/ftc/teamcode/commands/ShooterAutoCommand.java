package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterAutoCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final DoubleSupplier power;

    public ShooterAutoCommand(final ShooterSubsystem feeder, final DoubleSupplier power) {
        shooterSubsystem = feeder;
        this.power = power;

        addRequirements(shooterSubsystem);
    }

    public ShooterAutoCommand(final ShooterSubsystem feeder, final double power) {
        this(feeder, () -> power);
    }

    @Override
    public void execute() {
        shooterSubsystem.setPower(power.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return !shooterSubsystem.isBusy();
    }
}

