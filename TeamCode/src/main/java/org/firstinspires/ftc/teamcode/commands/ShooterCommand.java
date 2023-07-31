package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final DoubleSupplier power;

    public ShooterCommand (final ShooterSubsystem shooter, final DoubleSupplier power) {
        shooterSubsystem = shooter;
        this.power = power;

        addRequirements(shooterSubsystem);
    }

    public ShooterCommand(final ShooterSubsystem shooter, final double power) {
        this(shooter, () -> power);
    }

    @Override
    public void execute() {
        shooterSubsystem.setPower(power.getAsDouble());
    }
//
//    @Override
//    public boolean isFinished() {
//        return !shooterSubsystem.isBusy();
//    }
}

