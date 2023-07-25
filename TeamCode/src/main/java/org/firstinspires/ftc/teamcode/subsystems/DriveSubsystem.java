package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive drive;
    private final PIDController kPID;
    private Pose2d desiredDrivePower = new Pose2d(0, 0, 0);

    private double desiredHeading;
    private double previousDesiredHeading;

    private static final double omegaSpeed = 1;
    private final Telemetry telemetry;

    public DriveSubsystem(@NonNull HardwareMap hardwareMap, @NonNull Telemetry telemetry) {
        drive = new MecanumDrive(hardwareMap);
        kPID = new PIDController(1, 0, 0);
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {

    }

    public void setWeightedDrivePower(@NonNull Pose2d drivePower) {
        if (Math.abs(drivePower.getHeading()) > 0.1) {
            setHeading((getHeading() + drivePower.getHeading() * omegaSpeed) % (Math.PI));
        }
        desiredDrivePower = drivePower;
    }

    public void setHeading(double heading) {
        desiredHeading = heading;
    }

    public double getHeading() {
        return drive.getRawExternalHeading();
    }

    public double calculatePID() {
        return kPID.calculate(getHeading(), desiredHeading);
    }
}
