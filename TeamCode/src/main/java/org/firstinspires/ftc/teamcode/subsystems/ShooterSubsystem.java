package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class ShooterSubsystem extends SubsystemBase {
    private final DcMotorEx shooter;
    private final Telemetry telemetry;
    private static final double gearRatio = 1;
    private final PIDFController kShooterController = new PIDFController(0.08, 0.1, 0, 0);
    private double desiredRPM = 0;

    public ShooterSubsystem(@NonNull HardwareMap hwMap, @NonNull Telemetry telemetry){
        this.telemetry = telemetry;
        shooter = hwMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void periodic() {
        if (shooter != null) {
            setPower(calculatePID());

            telemetry.addLine("Shooter")
                    .addData("\nCurrent RPM:", getRPM())
                    .addData("\nDesired RPM:", desiredRPM)
                    .addData("\nPower", calculatePID())
                    .addData("\nIs Finished: ", shooter.isBusy())
                    .addData("\nAmperage", shooter.getCurrent(CurrentUnit.AMPS));
        }


        telemetry.update();
    }

    public void setRPM(double RPM) {
        desiredRPM = RPM;
    }

    public double getRPM() {
        return shooter.getVelocity(AngleUnit.RADIANS) * (60.0 / (2 * Math.PI)) * gearRatio;
    }

    public double calculatePID() {
        return kShooterController.calculate(getRPM(), desiredRPM);
    }
    public void setPower(double power){
        shooter.setPower(power);
    }

    public double getPower(){
        return shooter.getPower();
    }

    public boolean isBusy() {
        return shooter.isBusy();
    }

    public boolean atSetpoint() {
        if (Math.abs(desiredRPM - getRPM()) < 10) {
            return true;
        } else {
            return false;
        }
    }
}
