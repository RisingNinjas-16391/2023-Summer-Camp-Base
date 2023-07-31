package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

public class ShooterAutoCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private final DoubleSupplier power;

    public ShooterAutoCommand(final ShooterSubsystem shooter, final DoubleSupplier RPM) {
        shooterSubsystem = shooter;
        this.power = RPM;

        addRequirements(shooterSubsystem);
    }

    public ShooterAutoCommand(final ShooterSubsystem shooter, final double RPM) {
        this(shooter, () -> RPM);
    }

    @Override
    public void execute() {
        shooterSubsystem.setRPM(power.getAsDouble());
    }

    @Override
    public boolean isFinished() {
        return shooterSubsystem.atSetpoint();
    }
}

