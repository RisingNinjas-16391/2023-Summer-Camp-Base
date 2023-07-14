package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FeederSubsystem extends SubsystemBase {

    private final DcMotorEx feeder;
    private final Telemetry telemetry;

    //TODO: Tune kP for arm. If the arm moves too fast lower, if it moves too slow increase
    public static PIDFController kPIDF = new PIDFController(2,0,0,0.2);

    //TODO: Replace with preferred starting angle upon initialization
    private double desiredPosition = 0;

    //TODO: Tune for arm, if the arm goes up without doing anything lower, if it falls then increase it
    public static double kG = 0.3;

    //TODO: Replace with starting angle offset
    public static double angleOffset = 110;

    public static double tolerance = 0.2;

    public FeederSubsystem(@NonNull HardwareMap hwMap, @NonNull Telemetry telemetry){
        feeder = hwMap.get(DcMotorEx.class, "feeder");

        feeder.setDirection(DcMotorSimple.Direction.FORWARD);

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
