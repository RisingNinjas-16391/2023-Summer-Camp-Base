package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FeederSubsystem extends SubsystemBase {
    private final DcMotor feeder;
    public FeederSubsystem(@NonNull HardwareMap hwMap){
        feeder = hwMap.get(DcMotor.class, "feeder");
        feeder.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power){
        feeder.setPower(power);
    }

    public double getPower(){
        return feeder.getPower();
    }

    public boolean isBusy() {
        return feeder.isBusy();
    }
}
