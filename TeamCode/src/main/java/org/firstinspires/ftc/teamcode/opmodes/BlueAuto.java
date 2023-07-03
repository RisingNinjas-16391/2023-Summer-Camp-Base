package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

@Config 
@Autonomous(group = "drive")
public class BlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        PivotSubsystem pivot = new PivotSubsystem(hardwareMap, telemetry);
        FeederSubsystem feeder = new FeederSubsystem(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        Trajectory strafeRight50 = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(-50)
                .build();

        Trajectory forward30 = drive.trajectoryBuilder(new Pose2d())
                .forward(-30)
                .build();

        Trajectory forward75 = drive.trajectoryBuilder((new Pose2d()))
                .forward(-75)
                .build();

        Trajectory back75 = drive.trajectoryBuilder((new Pose2d()))
                .back(-75)
                .build();



        waitForStart();

        drive.followTrajectory(strafeRight50);
        drive.followTrajectory(forward30);

        feeder.setPower(1); //switch sign if it's feeding the wrong way
        sleep(500);
        feeder.setPower(0);

        drive.turn(Math.toRadians(-80));
        drive.followTrajectory(forward75);
        drive.turn(Math.toRadians(100));
        drive.followTrajectory((back75));
    }
}
