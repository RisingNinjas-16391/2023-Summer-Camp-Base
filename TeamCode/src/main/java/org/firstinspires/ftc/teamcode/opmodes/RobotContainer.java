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
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

public class RobotContainer {
    private DriveSubsystem driveSubsystem;
    private MecanumDrive mecanumDrive;
    private final PivotSubsystem pivotSubsystem;
    private final FeederSubsystem feederSubsystem;

    private final GamepadEx driverController;

    private final GamepadButton zeroPos;
    private final GamepadButton scorePos;
    private final GamepadButton intake;
    private final GamepadButton outtake;

    public RobotContainer(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        driveSubsystem = new DriveSubsystem(hwMap, telemetry);

        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);

        driverController = new GamepadEx(gamepad1);

        zeroPos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_DOWN);
        scorePos = new GamepadButton(driverController, GamepadKeys.Button.DPAD_UP);
        intake = new GamepadButton(driverController, GamepadKeys.Button.RIGHT_BUMPER);
        outtake = new GamepadButton(driverController, GamepadKeys.Button.LEFT_BUMPER);

        setDefaultCommands();
        configureButtonBindings();
    }

    public RobotContainer(HardwareMap hwMap, int autoNum, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hwMap);
        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);

        driverController = null;
        zeroPos = null;
        scorePos = null;
        intake = null;
        outtake = null;

        setAutoCommands(autoNum, telemetry);
    }

    public void setDefaultCommands(){
        driveSubsystem.setDefaultCommand(new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
        pivotSubsystem.setDefaultCommand(new PivotPowerCommand(pivotSubsystem, () -> (driverController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER))));
    }

    public void configureButtonBindings(){
        zeroPos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(100)));
        scorePos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(-45)));

        intake.whileHeld(new FeederCommand(feederSubsystem, () -> -0.7).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0).perpetually());
        outtake.whileHeld(new FeederCommand(feederSubsystem, () -> 0.25).perpetually())
                .whenReleased(new FeederCommand(feederSubsystem, () -> 0).perpetually());
    }

    private void setAutoCommands(int chooser, Telemetry telemetry) {
        Command BlueAutoCommand = new BlueAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem);
        Command RedAutoCommand = new RedAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem);

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
