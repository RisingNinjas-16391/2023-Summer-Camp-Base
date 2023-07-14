package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive drive;
    private final Telemetry telemetry;

    public DriveSubsystem(@NonNull HardwareMap hardwareMap, @NonNull Telemetry telemetry) {
        drive = new MecanumDrive(hardwareMap);
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        telemetry.addLine("Drivetrain")
                .addData("Left Front", drive.getWheelPositions().get(0))
                .addData("Left Rear", drive.getWheelPositions().get(1))
                .addData("Right Front", drive.getWheelPositions().get(2))
                .addData("Right Rear", drive.getWheelPositions().get(3));

    }

    public void setWeightedDrivePower(@NonNull Pose2d drivePower) {
        drive.setWeightedDrivePower(drivePower);
    }
}
