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



        Trajectory trajectoryForward = drive.trajectoryBuilder(new Pose2d())
                .forward(-12)
                .build();

        Trajectory trajectoryForward_2 = drive.trajectoryBuilder(new Pose2d())
                .forward(-1)
                .build();

        Trajectory trajectoryBackward = drive.trajectoryBuilder(trajectoryForward.end())
                .back(-13)
                .build();

        Trajectory trajectoryRight = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(-10)
                .build();

        waitForStart();

//feeder.setPower(75);
        drive.followTrajectory(trajectoryForward);
        feeder.setPower(0);
//pivot.setPower(100);
//pivot.setAngle(0.7);
//drive.followTrajectory(trajectoryForward_2);
        feeder.setPower(-25);
        sleep(500);
        drive.followTrajectory(trajectoryBackward);
        sleep(500);
        drive.followTrajectory(trajectoryRight);
        sleep(500);
        drive.followTrajectory(trajectoryForward);
    }
}