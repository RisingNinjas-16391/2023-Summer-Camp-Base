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

public class BlueAutoCommand extends SequentialCommandGroup {

    public BlueAutoCommand(MecanumDrive drive, ShooterSubsystem shooter, FeederSubsystem feeder) {
        SequentialCommandGroup autoBlue = new SequentialCommandGroup(
                new FollowTrajectoryCommand(drive, () -> drive.trajectorySequenceBuilder((new Pose2d()))
<<<<<<< HEAD
                        .strafeRight(3)
                        .forward(35)
                        //launch ball here
                        .strafeLeft(3)
                        .back(35)
                        .back(85)
=======
                        .strafeRight(16)
                        .forward(48.5)
                        .waitSeconds(1.5)
                        .back(44)
                        .strafeLeft(16)
                        .strafeRight(16)
                        .forward(135)
>>>>>>> 7fac26325a2938f050b6dac0f0dd11b9f2c80742
                        .build())
        );


        addCommands(
                autoBlue
        );
    }

}
