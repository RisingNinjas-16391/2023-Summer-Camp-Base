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
    private final ShooterSubsystem shooterSubsystem;
    private final GamepadEx driverController;
    private final GamepadButton intake;
    private final GamepadButton outtake;

    public RobotContainer(HardwareMap hwMap, Gamepad gamepad1, Telemetry telemetry){
        driveSubsystem = new DriveSubsystem(hwMap, telemetry);
        feederSubsystem = new FeederSubsystem(hwMap);
        shooterSubsystem = new ShooterSubsystem(hwMap, telemetry);

        driverController = new GamepadEx(gamepad1);

        intake = new GamepadButton(driverController, GamepadKeys.Button.RIGHT_BUMPER);
        outtake = new GamepadButton(driverController, GamepadKeys.Button.LEFT_BUMPER);

        setDefaultCommands();
        configureButtonBindings();
    }

    public RobotContainer(HardwareMap hwMap, int autoNum, Telemetry telemetry) {
        mecanumDrive = new MecanumDrive(hwMap);
        feederSubsystem = new FeederSubsystem(hwMap);
        shooterSubsystem = new ShooterSubsystem(hwMap, telemetry);

        driverController = null;
        intake = null;
        outtake = null;

        setAutoCommands(autoNum, telemetry);
    }

    public void setDefaultCommands(){
        driveSubsystem.setDefaultCommand(new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX));
    }

    public void configureButtonBindings(){
        intake.whileHeld(new FeederCommand(feederSubsystem, () -> -0.7).perpetually());
        shooterSubsystem.setDefaultCommand(new ShooterCommand(shooterSubsystem, () -> driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));
    }

    private void setAutoCommands(int chooser, Telemetry telemetry) {
        Command BlueAutoCommand = new BlueAutoCommand(mecanumDrive, shooterSubsystem, feederSubsystem);
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
