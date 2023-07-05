package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.SpinnySubsystem;

import java.util.function.DoubleSupplier;

public class SpinnyCommand extends CommandBase {
    private final SpinnySubsystem spinnySubsystem;
    private final DoubleSupplier power;

    public SpinnyCommand(final SpinnySubsystem spinny, final DoubleSupplier power) {
        spinnySubsystem = spinny;
        this.power = power;

        addRequirements(spinnySubsystem);
    }

    @Override
    public void execute() {
        spinnySubsystem.setPower(power.getAsDouble());
    }

    @Override
    public boolean isFinished(){
        return !spinnySubsystem.isBusy();
    }
}
