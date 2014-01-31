//ready, unless more prefs are needed
package org.usfirst.frc100.Robot2014;

/**
 * This class stores the values of the robot's preferences.
 */
public class Preferences {

    // DriveTrain modes
    public static int autoMode = 1;
    public static boolean tankDriveMode = true; // false = arcadeDrive
    public static boolean slaveDriveDefaultEnabled = true; // whether slave drive is activated by default
    // DriveTrain PID
    public static boolean driveTrainTuningMode = true;
    public static double driveEncoderToInchRatio = 38.2; // encoder / ratio = inches
    public static double driveGyroToDegreeRatio = 1; // gyro / ratio = degrees
    public static double driveStraight_kP = 0.15; // error * kP = motor speed
    public static double autoTurn_kP = 0.15; // error * kP = motor speed
    public static double driveDistBuffer = 4; // inches robot can be off by
    public static double driveAngleBuffer = 4; // degrees robot can be off by
    public static double autoDriveDelay = 10; // time to wait to make sure robot has stopped moving
    // Distance sensing
    public static double ultraInitialStopDistance = 120; // inches away from target that we want to stop
    public static double ultraActualStopDistance = 100;
    public static double ultraAcceptableSpike = 20;
    public static int inPositionCounter = 7;
    public static int ultraActualShootDistance = 80;
    // LineReader
    public static int lowerLimit = 900;
    public static int upperLimit = 910;
    
    // Tilter PID
    public static boolean tilterTuningMode = false;
    public static double tilterPotToDegreeRatio = .27; // pot / ratio = degrees
    public static double tilterPotOffsetDegrees = -1481.5; // degrees + offset = angle
    public static double tilterAngleBuffer = 8; // degrees tilter can be off by
    public static double tilter_kP = -0.02; // error * kP = motor speed, neg if tilter motor reversed (positive=down)
    // Tilter angles (in degrees)
    public static double shootHighAngle = 180;
    public static double shootLowAngle = 90;
    public static double shootTrussAngle = 0;
    public static double intakeAngle = 270;
    public static double stowedAngle = 0; // THIS MUST BE CHANGED! IT IS NOT ACCURATE AT ALL!!!!!!!!!!!!!!!!!!!!!
    public static double shootQuickAngle = 0;
    
    // Shooter tuning
    public static boolean shooterTuningMode = false;
    public static double shooterPotToInchRatio = 1; // pot / ratio = inches
    public static double shooterPotOffsetInches = 0; // inches + offset = position
    public static double shooterEncoderToInchRatio = 1; // encoder / ratio = inches
    public static double shooterDistanceBuffer = 0; // inches shooter can be off by
    public static double shooterPullBackSpeed = .5;
    public static double shooterPullForwardSpeed = .2;
    // Shooter positions (in inches)
    public static double shootHighPosition = 0;
    public static double shootLowPosition = 0;
    public static double shootTrussPosition = 0;
    public static double intakePosition = 0;
    public static double stowedPosition = 0;
    public static double shootQuickPosition = 0;
    
    // RobotMap
    public static boolean hammerHeadRobotMap  = false;
    public static boolean gwrathRobotMap = true;
    
    // Changes preferences to those for hammerhead
    public static void setHammerhead(){
        driveEncoderToInchRatio = 38.2; // encoder / ratio = inches
        driveGyroToDegreeRatio = 1; // gyro / ratio = degrees
        driveStraight_kP = 0.15; // error * kP = motor speed
        autoTurn_kP = 0.15; // error * kP = motor speed
        driveDistBuffer = 4; // inches robot can be off by
        driveAngleBuffer = 4; // degrees robot can be off by
        autoDriveDelay = 10; // time to wait to make sure robot has stopped moving
        ultraInitialStopDistance = 120; // inches away from target that we want to stop
        ultraActualStopDistance = 100;
        ultraAcceptableSpike = 20;
        inPositionCounter = 7;
    }

    // Changes preferences to those for gwrath
    public static void setGwrath() {
        driveEncoderToInchRatio = 18.3; // encoder / ratio = inches
        driveGyroToDegreeRatio = 1; // gyro / ratio = degrees
        driveStraight_kP = 0.15; // error * kP = motor speed
        autoTurn_kP = 0.1; // error * kP = motor speed
        driveDistBuffer = 4; // inches robot can be off by
        driveAngleBuffer = 0; // degrees robot can be off by
        autoDriveDelay = 10; // time to wait to make sure robot has stopped moving
        ultraInitialStopDistance = 120; // inches away from target that we want to stop
        ultraActualStopDistance = 100;
        ultraAcceptableSpike = 20;
        inPositionCounter = 7;
    }
}
