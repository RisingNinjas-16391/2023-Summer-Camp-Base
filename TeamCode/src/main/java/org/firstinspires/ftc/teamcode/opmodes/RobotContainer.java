package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.BlueAutoCommand;
import org.firstinspires.ftc.teamcode.commands.RedAutoCommand;
import org.firstinspires.ftc.teamcode.commands.FeederCommand;
import org.firstinspires.ftc.teamcode.commands.PivotCommand;
import org.firstinspires.ftc.teamcode.commands.SpinnyCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SpinnySubsystem;

public class RobotContainer {
    private DriveSubsystem driveSubsystem;
    private MecanumDrive mecanumDrive;
    private PivotSubsystem pivotSubsystem;
    private FeederSubsystem feederSubsystem;
    private SpinnySubsystem spinnySubsystem;

    private GamepadEx driverController;

    private GamepadButton zeroPos;
    private GamepadButton scorePos;
    private GamepadButton intake;
    private GamepadButton outake;
    private GamepadButton spinny;
    private GamepadButton up;
    private GamepadButton down;

    public RobotContainer(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        driveSubsystem = new DriveSubsystem(hwMap, telemetry);

        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);
        spinnySubsystem = new SpinnySubsystem(hwMap);

        driverController = new GamepadEx(gamepad1);

        zeroPos = new GamepadButton(driverController, GamepadKeys.Button.B);
        scorePos = new GamepadButton(driverController, GamepadKeys.Button.A);
        intake = new GamepadButton(driverController, GamepadKeys.Button.RIGHT_BUMPER);
        outake = new GamepadButton(driverController, GamepadKeys.Button.LEFT_BUMPER);

        setDefaultCommands();
        configureButtonBindings();
    }

    public RobotContainer(HardwareMap hwMap, int autoNum, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hwMap);
        pivotSubsystem = new PivotSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);
        spinnySubsystem = new SpinnySubsystem(hwMap);

        driverController = null;
        zeroPos = null;
        scorePos = null;
        intake = null;
        outake = null;
        spinny = null;

        setAutoCommands(autoNum, telemetry);
    }

    public void setDefaultCommands(){
        driveSubsystem.setDefaultCommand(new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
        feederSubsystem.setDefaultCommand(new FeederCommand(feederSubsystem,() -> (driverController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER))));
    }

    public void configureButtonBindings(){
        zeroPos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(0)));
        scorePos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(-90)));
        spinny.whileHeld(new SpinnyCommand(spinnySubsystem, () -> 0.7).perpetually());
        intake.whileHeld(new FeederCommand(feederSubsystem, () -> 0.7).perpetually());
        outake.whileHeld(new FeederCommand(feederSubsystem, () -> -0.7).perpetually());
    }

    private void setAutoCommands(int chooser, Telemetry telemetry) {
        Command BlueAutoCommand = new BlueAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem, spinnySubsystem);
        Command RedAutoCommand = new RedAutoCommand(mecanumDrive, pivotSubsystem, feederSubsystem, spinnySubsystem);

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
