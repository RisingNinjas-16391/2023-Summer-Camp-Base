package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.BlueAutoCommand;
import org.firstinspires.ftc.teamcode.commands.FeederAutoCommand;
import org.firstinspires.ftc.teamcode.commands.PivotPowerCommand;
import org.firstinspires.ftc.teamcode.commands.RedAutoCommand;
import org.firstinspires.ftc.teamcode.commands.FeederCommand;
import org.firstinspires.ftc.teamcode.commands.PivotCommand;
import org.firstinspires.ftc.teamcode.commands.ScoreCommand;
import org.firstinspires.ftc.teamcode.commands.ShooterCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpHeadingCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ShooterSubsystem;

public class RobotContainer {
    private DriveSubsystem driveSubsystem;
    private MecanumDrive mecanumDrive;
    private final FeederSubsystem feederSubsystem;
    private final PivotSubsystem pivotSubsystem;
    private final ShooterSubsystem shooterSubsystem;
    private final GamepadEx driverController;
    private final GamepadButton intake;
    private final GamepadButton outtake;
    private final GamepadButton feedPos;
    private final GamepadButton scorePos;

    public RobotContainer(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        driveSubsystem = new DriveSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);
        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        shooterSubsystem = new ShooterSubsystem(hwMap);

        driverController = new GamepadEx(gamepad1);

        intake = new GamepadButton(driverController, GamepadKeys.Button.RIGHT_BUMPER);
        outtake = new GamepadButton(driverController, GamepadKeys.Button.LEFT_BUMPER);
        scorePos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_UP);
        feedPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_DOWN);

        setDefaultCommands();
        configureButtonBindings();
    }

    public RobotContainer(HardwareMap hwMap, int autoNum, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hwMap);
        feederSubsystem = new FeederSubsystem(hwMap);
        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        shooterSubsystem = new ShooterSubsystem(hwMap);

        driverController = null;
        intake = null;
        outtake = null;
        scorePos = null;
        feedPos = null;

        setAutoCommands(autoNum, telemetry);
    }

    public void setDefaultCommands(){
        driveSubsystem.setDefaultCommand(new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
        pivotSubsystem.setDefaultCommand(new PivotPowerCommand(pivotSubsystem, () -> (driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) - driverController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER))));
    }

    public void configureButtonBindings(){
        intake.whileHeld(new FeederCommand(feederSubsystem, () -> 0.7).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0));

        outtake.whileHeld(new FeederCommand(feederSubsystem, () -> -0.5).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0));
    }

    private void setAutoCommands(int chooser, Telemetry telemetry) {
        Command BlueAutoCommand = new BlueAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem, shooterSubsystem);
        Command RedAutoCommand = new RedAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem, shooterSubsystem);

        switch (chooser) {
            case 0:
                BlueAutoCommand.schedule();
                break;
            case 1:
                RedAutoCommand.schedule();
                break;
        }
    }
}
