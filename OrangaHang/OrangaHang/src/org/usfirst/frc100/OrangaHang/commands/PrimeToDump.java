//PrimeToDump turns on the shooter wheels while it is held down.
//It takes around one second of the wheels to get up to the proper speed for dumping.
//The shooter motors will stop when the button is released.

package org.usfirst.frc100.OrangaHang.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Team100
 */
public class PrimeToDump extends CommandBase {

    public PrimeToDump() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putNumber("Setpoint", 0.0);
        shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooter.setSetpoint(SmartDashboard.getNumber("Setpoint", 0.0));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
