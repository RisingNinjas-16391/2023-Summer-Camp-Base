package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.opmodes.RobotContainer;

@TeleOp(name = "TeleOp", group = "Teleop")
public class CommandTeleOp extends CommandOpMode {

    @Override
    public void initialize() {
        waitForStart();
        new RobotContainer(hardwareMap, gamepad1, telemetry);

    }
}