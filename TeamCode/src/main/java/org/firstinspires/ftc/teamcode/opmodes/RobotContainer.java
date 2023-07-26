package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.BlueAutoCommand;
import org.firstinspires.ftc.teamcode.commands.PivotPowerCommand;
import org.firstinspires.ftc.teamcode.commands.RedAutoCommand;
import org.firstinspires.ftc.teamcode.commands.FeederCommand;
import org.firstinspires.ftc.teamcode.commands.PivotCommand;
import org.firstinspires.ftc.teamcode.commands.ScoreCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpHeadingCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class RobotContainer {
    private DriveSubsystem driveSubsystem;
    private MecanumDrive mecanumDrive;
    private final FeederSubsystem feederSubsystem;

    private final GamepadEx driverController;

    private final GamepadButton forwardPos;
    private final GamepadButton backPos;
    private final GamepadButton leftPos;
    private final GamepadButton rightPos;

    private final GamepadButton intake;
    private final GamepadButton outtake;

    public RobotContainer(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        driveSubsystem = new DriveSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);

        driverController = new GamepadEx(gamepad1);

        forwardPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_UP);
        backPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_DOWN);
        leftPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_LEFT);
        rightPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_RIGHT);

        intake = new GamepadButton(driverController, GamepadKeys.Button.RIGHT_BUMPER);
        outtake = new GamepadButton(driverController, GamepadKeys.Button.LEFT_BUMPER);

        setDefaultCommands();
        configureButtonBindings();
    }

    public RobotContainer(HardwareMap hwMap, int autoNum, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hwMap);
        feederSubsystem = new FeederSubsystem(hwMap);

        driverController = null;
        forwardPos = null;
        backPos = null;
        leftPos = null;
        rightPos = null;

        intake = null;
        outtake = null;

        setAutoCommands(autoNum, telemetry);
    }

    public void setDefaultCommands(){
        driveSubsystem.setDefaultCommand(new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
    }

    public void configureButtonBindings(){
        forwardPos.whenPressed(new TeleOpHeadingCommand(driveSubsystem, () -> 0));
        backPos.whenPressed(new TeleOpHeadingCommand(driveSubsystem, () -> 180));
        leftPos.whenPressed(new TeleOpHeadingCommand(driveSubsystem, () -> 90));
        rightPos.whenPressed(new TeleOpHeadingCommand(driveSubsystem, () -> 270));


        intake.whileHeld(new FeederCommand(feederSubsystem, () -> -0.7).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0).perpetually());
        outtake.whileHeld(new FeederCommand(feederSubsystem, () -> 0.25).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0).perpetually());
    }

    private void setAutoCommands(int chooser, Telemetry telemetry) {
        Command BlueAutoCommand = new BlueAutoCommand(mecanumDrive, feederSubsystem);
        Command RedAutoCommand = new RedAutoCommand(mecanumDrive, feederSubsystem);

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
