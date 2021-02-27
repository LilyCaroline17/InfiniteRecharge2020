/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeWheelsSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeWheelsSubsystem.
   */
  private WPI_TalonSRX intake;
  private TalonSRXConfiguration intakeSettings;

  public IntakeWheelsSubsystem() {
    intake=new WPI_TalonSRX(Constants.INTAKE);
    intakeSettings=new TalonSRXConfiguration();
    intakeSettings.peakCurrentLimit= 5;
    intakeSettings.continuousCurrentLimit=5;
    intakeSettings.peakCurrentDuration=0;
    intake.configAllSettings(intakeSettings);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void runIntake(double speed){
    intake.set(speed);
  }
}
