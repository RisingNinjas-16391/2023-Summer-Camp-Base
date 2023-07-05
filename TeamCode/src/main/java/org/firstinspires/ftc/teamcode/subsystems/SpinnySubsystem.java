package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SpinnySubsystem extends SubsystemBase {
    private final DcMotor spinny;
    public SpinnySubsystem(@NonNull HardwareMap hwMap) {
        spinny = hwMap.get(DcMotor.class, "spinny");
        spinny.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setPower(double power) {
        spinny.setPower(power);
    }

    public double getPower() {
        return spinny.getPower();
    }
}
