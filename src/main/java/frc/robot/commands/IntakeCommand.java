package frc.robot.commands;

import frc.robot.subsystems.IntakeWheelsSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase{
    private IntakeWheelsSubsystem intake;
    private double speed;
    private boolean fin = false;
    public  IntakeCommand(IntakeWheelsSubsystem subsystem, double speed) {
        this.intake=subsystem;
        this.speed=speed;
        addRequirements(intake);
    }
    public void execute(){
        this.intake.runIntake(speed);
        fin=true;
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interupted){
        
    }
}