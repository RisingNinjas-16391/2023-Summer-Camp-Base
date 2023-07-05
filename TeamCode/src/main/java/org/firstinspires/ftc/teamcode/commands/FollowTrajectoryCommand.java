package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.helpers.TrajectorySequenceSupplier;

public class FollowTrajectoryCommand extends CommandBase {
    private final MecanumDrive drivetrain;
    private final TrajectorySequenceSupplier trajectory;

    public FollowTrajectoryCommand(MecanumDrive drivetrain, TrajectorySequenceSupplier trajectory){
        this.drivetrain = drivetrain;
        this.trajectory = trajectory;
    }

    @Override
    public void initialize(){
        drivetrain.followTrajectorySequence(trajectory.getAsTrajectorySequence());
    }

    @Override
    public boolean isFinished(){
        return !drivetrain.isBusy();
    }

}
