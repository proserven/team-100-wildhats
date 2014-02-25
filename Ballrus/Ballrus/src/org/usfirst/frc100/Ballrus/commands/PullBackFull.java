//untested
package org.usfirst.frc100.Ballrus.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc100.Ballrus.Preferences;
import org.usfirst.frc100.Ballrus.Ballrus;

/**
 * Pulls the shooter all the way back to intake position without tilting. 
 * Command is when pressed.
 */
public class PullBackFull extends Command {
    
    public PullBackFull() {
        requires(Ballrus.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Ballrus.tilter.stop();
        Ballrus.shooter.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Ballrus.shooter.setPosition(Preferences.shooterIntakePullback);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeSinceInitialized() > 5||(timeSinceInitialized()>.1&&Ballrus.shooter.inPosition());
    }

    // Called once after isFinished returns true
    protected void end() {
        Ballrus.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
