package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class RedAutoCommand extends SequentialCommandGroup {

<<<<<<< Updated upstream
    public RedAutoCommand(MecanumDrive drive, PivotSubsystem pivot, FeederSubsystem feeder) {

        SequentialCommandGroup autoRed = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
                        .forward(-10)
                        .back(-10)
                        .strafeRight(-10)
                        .strafeLeft(-10)
                        .build())
        );
=======
    public RedAutoCommand(MecanumDrive drive, ShooterSubsystem shooter, FeederSubsystem feeder) {
        SequentialCommandGroup autoRed = new SequentialCommandGroup(

                new ShooterAutoCommand(shooter, 140),
                new WaitCommand(2000),
                new FeederAutoCommand(feeder, 0.7),
                new WaitCommand(15000),
                new ShooterAutoCommand(shooter,0),
                new FeederAutoCommand(feeder, 0)


            );

>>>>>>> Stashed changes

        addCommands(
            autoRed
        );

    }
}


