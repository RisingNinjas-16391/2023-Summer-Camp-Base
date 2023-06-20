package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

@Config 
@Autonomous(group = "drive")
public class SampleAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        PivotSubsystem pivot = new PivotSubsystem(hardwareMap, telemetry);
        FeederSubsystem feeder = new FeederSubsystem(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        Trajectory Forward10 = drive.trajectoryBuilder(new Pose2d())
                .forward(-10)
                .build();

        Trajectory Back10 = drive.trajectoryBuilder(new Pose2d())
                .back(-10)
                .build();
        waitForStart();

        drive.followTrajectory(Forward10);
        drive.followTrajectory(Back10);
        pivot.setPower(100);
        pivot.setAngle(0);
        feeder.setPower(100);
        sleep(5000);
    }
}