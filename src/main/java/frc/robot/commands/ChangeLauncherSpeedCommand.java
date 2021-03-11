
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.UpperLauncherSubsystem;
import frc.robot.subsystems.LowerLauncherSubsystem;
/**
 * changes the setpoint of the launcher PID controller
 */
public class ChangeLauncherSpeedCommand extends CommandBase{
    private boolean fin = false;
    private double setSpeed;
    private UpperLauncherSubsystem mUpperLauncher;
    private LowerLauncherSubsystem mLowerLauncher;
    /**
     * the command to change launcher speed
     * @param speed the speed to adjust to
     * @param launcher the launcher subsystem
     */
    public ChangeLauncherSpeedCommand(double speed, UpperLauncherSubsystem launcher1, LowerLauncherSubsystem launcher2) {
        setSpeed=speed;
        this.mUpperLauncher = launcher1;
        this.mLowerLauncher = launcher2;
        addRequirements(mUpperLauncher);
        addRequirements(mLowerLauncher);
    }
    public void initialize(){

    }
    /**
     * called when the command is scheduled
     */
    public void execute(){
        mUpperLauncher.setSetpoint(setSpeed);
        mLowerLauncher.setSetpoint(setSpeed);
        if(!RobotContainer.coDriverOI.getRawButton(Constants.LEFT_BUMPER)){
            fin=true;
        }
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interrupted) {
        if(RobotContainer.coDriverOI.getRawButtonReleased(Constants.LEFT_BUMPER)){
            mUpperLauncher.setSetpoint(0);
            mLowerLauncher.setSetpoint(0);
        }
    }
}