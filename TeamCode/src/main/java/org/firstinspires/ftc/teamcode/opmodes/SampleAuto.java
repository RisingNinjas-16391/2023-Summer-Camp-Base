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
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Config 
@Autonomous(group = "drive")
public class SampleAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        PivotSubsystem pivot = new PivotSubsystem(hardwareMap, telemetry);
        FeederSubsystem feeder = new FeederSubsystem(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap);

        Trajectory trajectory1 = drive.trajectoryBuilder(new Pose2d())
                .forward(50)
                .back(50)
                .strafeRight(50)
                .strafeLeft(50)
                .build();

        TrajectorySequence turn = drive.trajectorySequenceBuilder(new Pose2d())
                .turn(Math.toRadians(180))
                .build();

        waitForStart();

        drive.followTrajectory(trajectory1);
        drive.followTrajectorySequence(turn);
    }
}