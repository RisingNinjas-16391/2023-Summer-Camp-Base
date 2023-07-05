package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(group="drive")
public class RedAuto extends CommandOpMode {

    public void initialize(){
        waitForStart();
        new RobotContainer(hardwareMap, 1, telemetry);
    }

}
