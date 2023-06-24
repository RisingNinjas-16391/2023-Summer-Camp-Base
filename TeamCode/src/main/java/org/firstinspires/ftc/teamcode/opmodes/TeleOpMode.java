package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.FeederCommand;
import org.firstinspires.ftc.teamcode.commands.PivotCommand;
import org.firstinspires.ftc.teamcode.commands.PivotPowerCommand;
import org.firstinspires.ftc.teamcode.commands.TeleOpDriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FeederSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

@Config
@TeleOp(group="TeleOp")
public class TeleOpMode extends CommandOpMode {
    private DriveSubsystem driveSubsystem;
    private PivotSubsystem pivotSubsystem;
    private FeederSubsystem feederSubsystem;

    private TeleOpDriveCommand driveCommand;
    private PivotPowerCommand pivotPowerCommand;
    private FeederCommand feederCommand;
    private GamepadEx driverController;
    
    private GamepadButton zeroPos;
    private GamepadButton feedPos;
    private GamepadButton scorePos;

    @Override
    public void initialize() {
        this.driverController = new GamepadEx(gamepad1);
        this.driveSubsystem = new DriveSubsystem(hardwareMap, telemetry);
        this.pivotSubsystem = new PivotSubsystem(hardwareMap, telemetry);
        this.feederSubsystem = new FeederSubsystem(hardwareMap);
        
        this.zeroPos = new GamepadButton(driverController, GamepadKeys.Button.Y);
        this.feedPos = new GamepadButton(driverController, GamepadKeys.Button.B);
        this.scorePos = new GamepadButton(driverController, GamepadKeys.Button.X);

        this.driveCommand = new TeleOpDriveCommand(driveSubsystem, driverController::getLeftY, driverController::getLeftX, driverController::getRightX);
        this.pivotPowerCommand = new PivotPowerCommand(pivotSubsystem, () -> (driverController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) - driverController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)));
        this.feederCommand = new FeederCommand(feederSubsystem, () -> (driverController.getButton(GamepadKeys.Button.RIGHT_BUMPER) ? 1 : 0) - (driverController.getButton(GamepadKeys.Button.LEFT_BUMPER) ? 1 : 0));

        register(driveSubsystem, pivotSubsystem, feederSubsystem);

        setDefaultCommands();
        configureButtonBindings();
    }

  
    private void configureButtonBindings(){
        zeroPos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(0)));
        feedPos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(90)));
        scorePos.whenPressed(new PivotCommand(pivotSubsystem, Math.toRadians(-30)));
    }
    

    private void setDefaultCommands() {
        driveSubsystem.setDefaultCommand(driveCommand);
        pivotSubsystem.setDefaultCommand(pivotPowerCommand);
        feederSubsystem.setDefaultCommand(feederCommand);
    }


}
