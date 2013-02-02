// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package org.usfirst.frc100.Robot2013;
import org.usfirst.frc100.Robot2013.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick driverLeft;
    public JoystickButton creepButton;
    public JoystickButton preciseTurnButton;
    public JoystickButton drivingModeButton;
    public JoystickButton highGearButton;
    public JoystickButton lowGearButton;
    public Joystick driverRight;
    public JoystickButton raiseElevatorButton;
    public JoystickButton lowerElevatorButton;
    public JoystickButton climbButton;
    public JoystickButton runIntakeButton;
    public JoystickButton tiltClimberArmButton;
    public JoystickButton frisbeeConveyorBeltToggleButton;
    public JoystickButton shootButton;
    public Joystick manipulator;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        manipulator = new Joystick(3);
        
        shootButton = new JoystickButton(manipulator, 7);
        shootButton.whenPressed(new Shoot());
        frisbeeConveyorBeltToggleButton = new JoystickButton(manipulator, 6);
        frisbeeConveyorBeltToggleButton.whenPressed(new ToggleFrisbeeConveyorBelt());
        tiltClimberArmButton = new JoystickButton(manipulator, 5);
        tiltClimberArmButton.whenPressed(new TiltClimberArm());
        runIntakeButton = new JoystickButton(manipulator, 4);
        runIntakeButton.whileHeld(new RunIntake());
        climbButton = new JoystickButton(manipulator, 3);
        climbButton.whenPressed(new Climb());
        lowerElevatorButton = new JoystickButton(manipulator, 2);
        lowerElevatorButton.whileHeld(new LowerElevator());
        raiseElevatorButton = new JoystickButton(manipulator, 1);
        raiseElevatorButton.whileHeld(new RaiseElevator());
        driverRight = new Joystick(2);
        
        lowGearButton = new JoystickButton(driverRight, 5);
        lowGearButton.whenPressed(new LowGear());
        highGearButton = new JoystickButton(driverRight, 4);
        highGearButton.whenPressed(new HighGear());
        drivingModeButton = new JoystickButton(driverRight, 3);
        drivingModeButton.whileHeld(new UsePreciseTankDrive());
        preciseTurnButton = new JoystickButton(driverRight, 2);
        preciseTurnButton.whileHeld(new UsePreciseTankDrive());
        creepButton = new JoystickButton(driverRight, 1);
        creepButton.whileHeld(new CreepForwardOrBackward());
        driverLeft = new Joystick(1);
        
	    
        // SmartDashboard Buttons
        SmartDashboard.putData("CreepForwardOrBackward", new CreepForwardOrBackward());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("TeleopDrive", new TeleopDrive());
        SmartDashboard.putData("Shoot", new Shoot());
        SmartDashboard.putData("RunIntake", new RunIntake());
        SmartDashboard.putData("UsePreciseTankDrive", new UsePreciseTankDrive());
        SmartDashboard.putData("HighGear", new HighGear());
        SmartDashboard.putData("LowGear", new LowGear());
        SmartDashboard.putData("TiltClimberArm", new TiltClimberArm());
        SmartDashboard.putData("ToggleFrisbeeConveyorBelt", new ToggleFrisbeeConveyorBelt());
        SmartDashboard.putData("Climb", new Climb());
        SmartDashboard.putData("RaiseElevator", new RaiseElevator());
        SmartDashboard.putData("LowerElevator", new LowerElevator());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverLeft() {
        return driverLeft;
    }
    public Joystick getDriverRight() {
        return driverRight;
    }
    public Joystick getManipulator() {
        return manipulator;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
