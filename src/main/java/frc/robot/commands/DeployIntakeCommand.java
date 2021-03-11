package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DeployIntakeCommand extends CommandBase{
    private IntakeSubsystem intake;
    private int speed;
    private boolean fin = false;
    public  DeployIntakeCommand(IntakeSubsystem subsystem, int speed) {
        intake=subsystem;
        this.speed=speed;
        addRequirements(intake);
    }
    public void execute(){
        //System.out.println("executing...");
        fin=intake.deployIntake(speed);
        //System.out.println(fin);
    }
    @Override
    public boolean isFinished() {
        //System.out.println("IS " + fin);
        return fin;
    }
    @Override
    public void end(boolean interupted){
        //intake.resetEncoder();
        intake.deploy.set(0);
        //System.out.println(fin);
        //System.out.println("Interupted?" + interupted);
    }
}