package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SpinnySubsystem;

public class RedAutoCommand extends SequentialCommandGroup {

    public RedAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder, SpinnySubsystem spinny){
        TrajectorySequenceSupplier strafeLeft50 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .strafeLeft(-50)
                .build();

        TrajectorySequenceSupplier forward30 = () -> drive.trajectorySequenceBuilder(new Pose2d())
                .forward(-30)
                .build();

        TrajectorySequenceSupplier forward75 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .forward(-75)
                .build();

        TrajectorySequenceSupplier back75 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .back(-75)
                .build();

        TrajectorySequenceSupplier turn80 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(80))
                .build();

        TrajectorySequenceSupplier turn100 = () -> drive.trajectorySequenceBuilder((new Pose2d()))
                .turn(Math.toRadians(-100))
                .build();

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new PivotCommand(pivot, Math.toRadians(-90)),
                new WaitCommand(2000),

                new FollowTrajectoryCommand(drive, strafeLeft50),
                new FollowTrajectoryCommand(drive, forward30),

                new FeederAutoCommand(feeder, () -> -1),
                new WaitCommand(500),
                new FeederAutoCommand(feeder, () -> 0),

                new FollowTrajectoryCommand(drive, turn80),
                new FollowTrajectoryCommand(drive, forward75),
                new FollowTrajectoryCommand(drive, turn100),
                new FollowTrajectoryCommand(drive, back75)
        );

        addCommands(
                autoRed
        );
    }

}
