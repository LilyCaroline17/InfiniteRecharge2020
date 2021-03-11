/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Relay;

public class LEDSubsystem extends SubsystemBase {
  private Relay led;
  /**
   * Creates a new LEDSubsystem.
   */
  public LEDSubsystem() {
    led = new Relay(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void setLED(Relay.Value val){
    led.set(val);
  }
}
