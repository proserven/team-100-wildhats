package org.usfirst.frc100.Robot2014;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveTrainDriveLeftMotorMain;
    public static SpeedController driveTrainDriveLeftMotorSlave;
    public static SpeedController driveTrainDriveRightMotorMain;
    public static SpeedController driveTrainDriveRightMotorSlave;
    public static Encoder driveTrainDriveLeftEncoder;
    public static Encoder driveTrainDriveRightEncoder;
    public static Gyro driveTrainGyro;
    public static AnalogChannel driveTrainRangeFinder;
    public static Relay driveTrainShifter;
    public static AnalogChannel driveTrainLeftLineReader;
    public static AnalogChannel driveTrainRightLineReader;
    public static SpeedController shooterShooterMotor;
    public static DigitalInput shooterShooterHallEffectForward;
    public static DigitalInput shooterShooterHallEffectBack;
    public static AnalogChannel shooterShooterPot;
    public static Relay shooterShooterRelease;
    public static Encoder shooterShooterEncoder;
    public static SpeedController intakeIntakeTopMotor;
    public static SpeedController intakeIntakeBottomMotor;
    public static Relay intakeIntakeTopPiston;
    public static Relay intakeIntakeBottomPiston;
    public static DigitalInput intakeIntakeBallDetector;
    public static SpeedController tilterTiltMotor;
    public static AnalogChannel tilterTiltPot;
    public static Compressor compressorCompressor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainDriveLeftMotorMain = new Victor(1, 1);
        LiveWindow.addActuator("DriveTrain", "DriveLeftMotorMain", (Victor) driveTrainDriveLeftMotorMain);

        driveTrainDriveLeftMotorSlave = new Victor(1, 2);
        LiveWindow.addActuator("DriveTrain", "DriveLeftMotorSlave", (Victor) driveTrainDriveLeftMotorSlave);

        driveTrainDriveRightMotorMain = new Victor(1, 3);
        LiveWindow.addActuator("DriveTrain", "DriveRightMotorMain", (Victor) driveTrainDriveRightMotorMain);

        driveTrainDriveRightMotorSlave = new Victor(1, 4);
        LiveWindow.addActuator("DriveTrain", "DriveRightMotorSlave", (Victor) driveTrainDriveRightMotorSlave);

        driveTrainDriveLeftEncoder = new Encoder(1, 1, 1, 2, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "DriveLeftEncoder", driveTrainDriveLeftEncoder);
        driveTrainDriveLeftEncoder.setDistancePerPulse(1.0);
        driveTrainDriveLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainDriveLeftEncoder.start();
        driveTrainDriveRightEncoder = new Encoder(1, 3, 1, 4, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "DriveRightEncoder", driveTrainDriveRightEncoder);
        driveTrainDriveRightEncoder.setDistancePerPulse(1.0);
        driveTrainDriveRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        driveTrainDriveRightEncoder.start();
        driveTrainGyro = new Gyro(1, 1);
        LiveWindow.addSensor("DriveTrain", "Gyro", driveTrainGyro);
        driveTrainGyro.setSensitivity(0.007);
        driveTrainRangeFinder = new AnalogChannel(1, 2);
        LiveWindow.addSensor("DriveTrain", "RangeFinder", driveTrainRangeFinder);

        driveTrainShifter = new Relay(1, 3);
        LiveWindow.addActuator("DriveTrain", "Shifter", driveTrainShifter);

        driveTrainLeftLineReader = new AnalogChannel(1, 5);
        LiveWindow.addSensor("DriveTrain", "LeftLineReader", driveTrainLeftLineReader);

        driveTrainRightLineReader = new AnalogChannel(1, 6);
        LiveWindow.addSensor("DriveTrain", "RightLineReader", driveTrainRightLineReader);

        shooterShooterMotor = new Victor(1, 5);
        LiveWindow.addActuator("Shooter", "ShooterMotor", (Victor) shooterShooterMotor);

        shooterShooterHallEffectForward = new DigitalInput(1, 5);
        LiveWindow.addSensor("Shooter", "ShooterHallEffectForward", shooterShooterHallEffectForward);

        shooterShooterHallEffectBack = new DigitalInput(1, 6);
        LiveWindow.addSensor("Shooter", "ShooterHallEffectBack", shooterShooterHallEffectBack);

        shooterShooterPot = new AnalogChannel(1, 3);
        LiveWindow.addSensor("Shooter", "ShooterPot", shooterShooterPot);

        shooterShooterRelease = new Relay(1, 4);
        LiveWindow.addActuator("Shooter", "ShooterRelease", shooterShooterRelease);

        shooterShooterEncoder = new Encoder(1, 7, 1, 8, false, EncodingType.k4X);
        LiveWindow.addSensor("Shooter", "ShooterEncoder", shooterShooterEncoder);
        shooterShooterEncoder.setDistancePerPulse(1.0);
        shooterShooterEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
        shooterShooterEncoder.start();
        intakeIntakeTopMotor = new Victor(1, 6);
        LiveWindow.addActuator("Intake", "IntakeTopMotor", (Victor) intakeIntakeTopMotor);

        intakeIntakeBottomMotor = new Victor(1, 7);
        LiveWindow.addActuator("Intake", "IntakeBottomMotor", (Victor) intakeIntakeBottomMotor);

        intakeIntakeTopPiston = new Relay(1, 1);
        LiveWindow.addActuator("Intake", "IntakeTopPiston", intakeIntakeTopPiston);

        intakeIntakeBottomPiston = new Relay(1, 2);
        LiveWindow.addActuator("Intake", "IntakeBottomPiston", intakeIntakeBottomPiston);

        intakeIntakeBallDetector = new DigitalInput(1, 10);
        LiveWindow.addSensor("Intake", "IntakeBallDetector", intakeIntakeBallDetector);

        tilterTiltMotor = new Victor(1, 8);
        LiveWindow.addActuator("Tilter", "TiltMotor", (Victor) tilterTiltMotor);

        tilterTiltPot = new AnalogChannel(1, 4);
        LiveWindow.addSensor("Tilter", "TiltPot", tilterTiltPot);

        compressorCompressor = new Compressor(1, 9, 1, 5);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
