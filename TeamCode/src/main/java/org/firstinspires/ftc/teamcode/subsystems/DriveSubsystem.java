package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;

import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.SubsystemBase;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

@Config
public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive drive;
    private final PIDFController kHeadingPID;
    public static PIDCoefficients turnController = new PIDCoefficients(1.5, 0, 0);

    private Pose2d desiredDrivePower = new Pose2d(0, 0, 0);

    private double desiredHeading = 0;
    private boolean flipped = false;

    private double desiredX;

    public static double omegaSpeed = 0.5;
    private final Telemetry telemetry;

    public DriveSubsystem(@NonNull HardwareMap hardwareMap, @NonNull Telemetry telemetry) {
        drive = new MecanumDrive(hardwareMap);
        kHeadingPID = new PIDFController(turnController);
        kHeadingPID.setInputBounds(0, 2 * Math.PI);
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        drive.setWeightedDrivePower(new Pose2d(desiredDrivePower.getX(), desiredDrivePower.getY(), calculatePID()));

        telemetry.addLine("Drivetrain")
                .addData("\nCurrent Heading:", getHeading())
                .addData("\nDesired Heading:", desiredHeading)
                .addData("\nTurn Power", calculatePID());

    }

    public void setWeightedDrivePower(@NonNull Pose2d drivePower) {
        if (flipped) {
            desiredDrivePower = new Pose2d(-drivePower.getX(), -drivePower.getY(), drivePower.getHeading());
        } else {
            desiredDrivePower = drivePower;
        }
        if (Math.abs(drivePower.getHeading()) > 0.1) {
            setHeading((getHeading() + (drivePower.getHeading()) * omegaSpeed));
        }
    }

    public void setHeading(double heading) {
        desiredHeading = heading;
    }

    public double getHeading() {
        return drive.getExternalHeading();
    }
    public void reverseDrivetrain() {
        flipped = !flipped;
    }

    public double calculatePID() {
        kHeadingPID.setTargetPosition(desiredHeading);
        return kHeadingPID.update(getHeading());
    }

    public boolean isFinished() {
        if ((desiredHeading - getHeading()) < 0.2) {
            return true;
        } else {
            return false;
        }
    }
}
