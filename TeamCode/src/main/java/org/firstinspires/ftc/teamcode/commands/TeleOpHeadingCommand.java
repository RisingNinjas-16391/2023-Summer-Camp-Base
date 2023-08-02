package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class TeleOpHeadingCommand extends CommandBase {
    private final DriveSubsystem drive;

    private final DoubleSupplier angle;

    public TeleOpHeadingCommand(final DriveSubsystem drive, final DoubleSupplier angle) {
        this.drive = drive;

        this.angle = angle;

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.setHeading(drive.getHeading());
    }
    @Override
    public void execute() {
        drive.setHeading(Math.toRadians(angle.getAsDouble()));
    }

    @Override
    public boolean isFinished() {
        return drive.isFinished();
    }
}
