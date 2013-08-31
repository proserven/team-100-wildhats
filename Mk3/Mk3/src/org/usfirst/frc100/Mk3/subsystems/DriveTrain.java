package org.usfirst.frc100.Mk3.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc100.Mk3.RobotMap;
import org.usfirst.frc100.Mk3.commands.Drive;

/**
 *
 * @author Team100
 */
public class DriveTrain extends Subsystem implements SubsystemControl {
    
    //Robot parts
    private final Talon rightMotor = RobotMap.driveRightMotor;
    private final Talon leftMotor = RobotMap.driveLeftMotor;
    private final Encoder rightEncoder = RobotMap.driveRightEncoder;
    private final Encoder leftEncoder = RobotMap.driveLeftEncoder;
    private final Gyro gyro = RobotMap.driveGyro;
    private final RobotDrive robotDrive = RobotMap.driveRobotDrive;
    //Preferences defaults
    private final boolean kDefaultReverseDirection = false;//need to change this for comp bot b/c now driving "backward"
    private final double kDefaultShootLimitVoltage = 1.12;//was 1.2 for comp bot
    private final double kDefaultQuickTurnProportion = 0.011;
    private final double kDefaultQuickTurnDeadband = 0.19;
    //Other
    private boolean hasBeenAchieved = false;
    private double encoderVal;
    private double encoderErr;
    private double distOut;
    private double gyroErr;
    private double L_encoderVal;
    private double R_encoderVal;

    //starts encoders
    public DriveTrain() {
        leftEncoder.start();
        rightEncoder.start();
        gyro.reset();
        Preferences p = Preferences.getInstance();
        if (!p.containsKey("DriveTrainShootLimitVoltage")) {
            p.putDouble("DriveTrainShootLimitVoltage", kDefaultShootLimitVoltage);
        }
        if (!p.containsKey("DriveTrainQuickTurnP")) {
            p.putDouble("DriveTrainQuickTurnP", kDefaultQuickTurnProportion);
        }
        if (!p.containsKey("DriveTrainQuickTurnDeadband")) {
            p.putDouble("DriveTrainQuickTurnDeadband", kDefaultQuickTurnDeadband);
        }
        if (!p.containsKey("DriveTrainReverseDirection")) {
            p.putBoolean("DriveTrainReverseDirection", kDefaultReverseDirection);
        }
        if (!p.containsKey("LeftEncoderRatio")) {
            p.putDouble("LeftEncoderRatio", 233.0);
        }
        if (!p.containsKey("RightEncoderRatio")) {
            p.putDouble("RightEncoderRatio", 158.0);
        }
        if (!p.containsKey("Encoder_kP")) {
            p.putDouble("Encoder_kP", 0.5);
        }
        if (!p.containsKey("OutputMin")) {
            p.putDouble("OutputMin", .2);
        }
        if (!p.containsKey("DistBuffer")) {
            p.putDouble("DistBuffer", 0.06);
        }
        if (!p.containsKey("Gyro_kP")) {
            p.putDouble("Gyro_kP", 1 / 18);
        }
        if (!p.containsKey("AngleBuffer")) {
            p.putDouble("AngleBuffer", 4.5);
        }
        if (!p.containsKey("AutoDist_0")) {
            p.putDouble("AutoDist_0", 0.0);
        }
        if (!p.containsKey("AutDist_1")) {
            p.putDouble("AutoDist_1", 0.0);
        }
        if (!p.containsKey("AutoDist_2")) {
            p.putDouble("AutoDist_2", 0.0);
        }
        if (!p.containsKey("AutoDist_3")) {
            p.putDouble("AutoDist_3", 0.0);
        }
    }//end constructor

    //creates a new Drive
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }//end initDefaultCommand

    public void tankDrive(double leftSpeed, double rightSpeed) {
        Preferences p = Preferences.getInstance();
        final boolean kReverseDirection = p.getBoolean("DriveTrainReverseDirection", false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, kReverseDirection);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, kReverseDirection);
        if(kReverseDirection){
            robotDrive.tankDrive(rightSpeed, leftSpeed);
            return;
        }
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }//end tankDrive

    //basic arcadeDrive: y=forward/backward speed, x=left/right speed
    public void arcadeDrive(double y, double x) {
        Preferences p = Preferences.getInstance();
        final boolean kReverseDirection = p.getBoolean("DriveTrainReverseDirection", false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, kReverseDirection);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, kReverseDirection);
        if(kReverseDirection){
            robotDrive.arcadeDrive(x, y);
            return;
        }
        robotDrive.arcadeDrive(y, x);
    }// end arcadeDrive

    public void resetGyro() {
        gyro.reset();
    }//end resetGyro

    public double getGyro() {
        return gyro.getAngle();
    }//end getGyro

    public void resetRangefinder() {
        hasBeenAchieved = false;
    }//end resetRangefinder

    public boolean quickTurn(double setpoint) {
        // Proportional quickturn algorithm
        Preferences p = Preferences.getInstance();
        final double kP = p.getDouble("DriveTrainQuickTurnP", 0.0);
        final double kDB = p.getDouble("DriveTrainQuickTurnDeadband", 0.0);
        double angle = -getGyro(); // -driveTrain.getGyro() b/c the gyro is upsidedown
        double error = setpoint - angle;
        double twist = error * kP;
        double magnitude = Math.abs(twist);
        if (magnitude < kDB) {
            twist = kDB * (twist > 0.0 ? 1.0 : -1.0);
        } else if (magnitude > 1.0) {
            twist = (twist > 0.0 ? 1.0 : -1.0);
        }

        SmartDashboard.putNumber("DriveTrainGyro", angle);

        leftMotor.set(-twist);
        rightMotor.set(twist);

        // Done once we pass the setpoint
        return Math.abs(angle) >= Math.abs(setpoint);
    }//end quickTurn

    public boolean driveStraight(double dist) {
        Preferences p = Preferences.getInstance();
        L_encoderVal = leftEncoder.get() / p.getDouble("LeftEncoderRatio", 0.0); // converts encoder value to feet
        R_encoderVal = rightEncoder.get() / p.getDouble("RightEncoderRatio", 0.0); // converts encoder value to feet
        encoderVal = (L_encoderVal + R_encoderVal) / 2; // averages out encoder values
        encoderErr = dist - encoderVal;
        distOut = encoderErr * p.getDouble("Encoder_kP", 0.0) + (Math.abs(dist) / dist) * p.getDouble("OutputMin", 0.0); // Encoder kP = 0.5; abs(x)/x returns sign of x; 0.2 is the min. magnitude
        gyroErr = gyro.getAngle(); // Setpoint is always 0
        if (Math.abs(encoderErr) < p.getDouble("DistBuffer", 0.0) && Math.abs(gyroErr) < p.getDouble("AngleBuffer", 0.0)) {
            leftEncoder.reset();
            rightEncoder.reset();
            return true; // returns true when robot gets to its goal
        } else {
            robotDrive.arcadeDrive(distOut, gyroErr * p.getDouble("gyro_kP", 0.0)); // Gyro kP = 1/18.0; Arcade Drive uses reversed rotate values (neg. goes Left / pos. goes Right)
            return false; // returns false if robot still hasn't reached its goal yet
        }
    }//end driveStraight

    public void disable() {
        leftMotor.set(0.0);
        rightMotor.set(0.0);
        rightEncoder.reset();
        leftEncoder.reset();
    }//end disable

    public void enable() {
        rightEncoder.reset();
        leftEncoder.reset();
        rightEncoder.start();
        leftEncoder.start();
    }//end enable
}//end DriveTrain
