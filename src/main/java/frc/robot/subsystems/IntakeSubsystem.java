/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class IntakeSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX intake;
  public WPI_TalonSRX deploy;
  private Encoder deployEncoder;
  private DigitalInput dio;
  private TalonSRXConfiguration intakeSettings;
  //Rotations SHOULD be changed depending on desired height
  private double rotations = -1.95;
  private double dropRotations = -1.25;
  //Error range for middle to prevent oscillations
  private double error = 0.05;
  private double timeStarted = 0.0;
  private double timeElapsed = 3.0;
  private double currentTime;
  
  public IntakeSubsystem() {
    deploy=new WPI_TalonSRX(Constants.INTAKEDEPLOY);
    deployEncoder=new Encoder(Constants.INTAKE1, Constants.INTAKE2);
    deployEncoder.setDistancePerPulse(1.0/512.0);
    deployEncoder.reset();
    deployEncoder.setMaxPeriod(4);
    dio=new DigitalInput(Constants.ISWITCH);
    intake=new WPI_TalonSRX(Constants.INTAKE);
    intakeSettings=new TalonSRXConfiguration();
    intakeSettings.peakCurrentLimit= 5;
    intakeSettings.continuousCurrentLimit=5;
    intakeSettings.peakCurrentDuration=0;
    intake.configAllSettings(intakeSettings);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //Drop and raise the intake to load in balls
  //Three different heights: home, up, and down
  public boolean deployIntake(int direction){
    //Check if method has been called before
    if (timeStarted == 0.0){
      timeStarted = Timer.getFPGATimestamp();
    }
    /*
    if (direction == -1){
      deploy.set(direction*.5);
      System.out.println("Direction is -1");
    }
    else if (direction == 1){
      deploy.set(direction*.5);
      System.out.println("Direction is 1");
    }*/

    currentTime = Timer.getFPGATimestamp();
    if (currentTime - timeStarted >= timeElapsed){
      System.out.println("Time Elapsed is " + (currentTime-timeStarted) + " seconds");
      timeStarted = 0.0;
      deploy.set(0);
      return true;
    }

    double distance=deployEncoder.getDistance();
    //System.out.println("Distance is " + distance);
    if(!dio.get()){
      resetEncoder();
    }
    //Bring all the way down
    if (direction==-1){
      if(distance>rotations){
        deploy.set(direction*.5);
      }
      else{
        deploy.set(0);
        timeStarted = 0.0;
      }//*/
      return (distance)<=rotations;
    }
    //Bring all the way up
    else{
    //else if (direction==1){
      if(distance<0){
        deploy.set(direction*.5);
      }
      else{
        deploy.set(0);
        timeStarted = 0.0;
      }
      return distance>=0;
    }
    //Bring to middle position
    /*else{
      if(distance>rotations-dropRotations){
        deploy.set(-0.5);
        return false;
      }
      else if (distance<rotations-dropRotations){
        deploy.set(0.5);
        return false;
      }
      else{
        deploy.set(0);
        return true;
      }
    }*/
  }

  public void periodic(){
    SmartDashboard.putNumber("deploy distance", deployEncoder.getDistance());
    SmartDashboard.putNumber("deploy count", deployEncoder.get());
    SmartDashboard.putBoolean("deployswitch", dio.get());
  }
  public void runIntake(double speed){
    intake.set(speed);
  }
  public void resetEncoder(){
    deployEncoder.reset();
    intake.set(0);
  }
  public boolean getSwitch(){
    return dio.get();
  }
}
