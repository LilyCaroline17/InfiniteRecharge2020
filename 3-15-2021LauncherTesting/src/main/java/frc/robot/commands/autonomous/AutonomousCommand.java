package frc.robot.commands.autonomous;

import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.AdjustLauncherCommand;
import frc.robot.commands.ChangeLauncherSpeedCommand;
import frc.robot.commands.DriveSoloCommand;
import frc.robot.commands.HopperCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.UpperLauncherSubsystem;
import frc.robot.subsystems.LowerLauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class AutonomousCommand extends CommandGroupBase {
   private DriveSubsystem drive;
   private UpperLauncherSubsystem mUpperLauncherSubsystem;
   private LowerLauncherSubsystem mLowerLauncherSubsystem;
   private LimeLightSubsystem lime;
   private HopperSubsystem hopperSubsystem;
   private DriveSoloCommand solo;
   private CommandGroupBase auto;
   private boolean done=false;
   private Timer time=new Timer();
   public AutonomousCommand(DriveSubsystem drive, UpperLauncherSubsystem upperLauncherSubsystem, LowerLauncherSubsystem lowerLauncherSubsystem, LimeLightSubsystem lime, HopperSubsystem hopperSubsystem) {      
      this.drive = drive;
      this.mUpperLauncherSubsystem = upperLauncherSubsystem;
      this.mLowerLauncherSubsystem = lowerLauncherSubsystem;
      this.lime=lime;
      this.hopperSubsystem=hopperSubsystem;
      addRequirements(drive);
      addRequirements(mUpperLauncherSubsystem);
      addRequirements(lime);
      addRequirements(hopperSubsystem);
      solo=new DriveSoloCommand(drive, lime, 0.5, 0.5, 60);
      addCommands(new ChangeLauncherSpeedCommand(3000,mUpperLauncherSubsystem),solo,new AdjustLauncherCommand(mUpperLauncherSubsystem, mLowerLauncherSubsystem, lime),new WaitCommand(4),new HopperCommand(hopperSubsystem, Constants.HOPPER_SPEED));
   }

   public void inititialize(){
   }

   @Override
   public void addCommands(Command... commands) {
      auto=sequence(commands);
   }
   public void execute(){
      if(!done){
         auto.schedule();
         done=true;
      }
   }
   public boolean isFinished(){
      return done;
   }
   public void end(boolean interrupted){
      done=false;
   }

 }