package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "Teleop")
public class TeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        waitForStart();
        new RobotContainer(hardwareMap, gamepad1, telemetry);

    }
}