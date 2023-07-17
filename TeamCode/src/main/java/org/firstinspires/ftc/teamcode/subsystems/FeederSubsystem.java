package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FeederSubsystem extends SubsystemBase {

    private final DcMotorEx feeder;
    private final Telemetry telemetry;

    //TODO: Tune kP for arm. If the arm moves too fast lower, if it moves too slow increase
    public static PIDFController kPIDF = new PIDFController(0.0001,0,0,0);

    //TODO: Replace with preferred starting angle upon initialization
    private double desiredPosition = 0;

    public static double tolerance = 5;

    public FeederSubsystem(@NonNull HardwareMap hwMap, @NonNull Telemetry telemetry){
        feeder = hwMap.get(DcMotorEx.class, "feeder");

        feeder.setDirection(DcMotorSimple.Direction.FORWARD);
        feeder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        feeder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        feeder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        this.telemetry = telemetry;
    }

    public void setPower(double power){
        feeder.setPower(power);
    }

    public void setPosition(int desiredTicks) {
        desiredPosition = desiredTicks;
    }

    public double getEncoderTicks() {
        return feeder.getCurrentPosition();
    }

    public double calculatePID() {
        return kPIDF.calculate(getEncoderTicks(), desiredPosition);
    }

    public boolean atSetpoint() {
        return (Math.abs(desiredPosition - getEncoderTicks()) < tolerance);
    }

    public boolean isBusy() {
        return feeder.isBusy();
    }

    @Override
    public void periodic() {
        setPower(calculatePID());
        telemetry.addLine("Feeder")
                .addData("\nEncoder Ticks Feeder:", getEncoderTicks())
                .addData("\nDesired Feeder Position", desiredPosition)
                .addData("\nFeeder Power", calculatePID())
                .addData("\nAt Setpoint", atSetpoint());

        telemetry.update();
    }
}
