// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2.Version;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;

public class PixySubsystem extends SubsystemBase {
  boolean _isVisionEnded = false;
  private Pixy2 _pixy2;
  private Pixy2CCC _pixyCCC;
  //private Pixy2Video _pixyVideo = _pixy2.getVideo();
  /** Creates a new PixySubsystem. */
  public PixySubsystem() {
    _pixy2= Pixy2.createInstance(LinkType.I2C);
    _pixyCCC = _pixy2.getCCC(); 
    int pixyError = _pixy2.init();
    System.out.println("Pixy Error Code :" + pixyError);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void resetVisionEnd() {
    _isVisionEnded = false;
  }

  public void setVisionEnd() {
    _isVisionEnded = true;
  }

  public boolean getVisionEnd() {
    return _isVisionEnded;
  }

  public String getVersion() {
    String versionStr = "Pixy Not Found!!!";
    try {
      Version version = _pixy2.getVersionInfo();

      if (version != null) {
        version.print();
        versionStr = version.toString();
      }
    }
    catch (Exception ex) {
      System.out.println("Exception in getVersion:" + ex.toString());
    }
    return versionStr;
  }

  public void getTargetDistance() {
    if (_pixyCCC.getBlocks() >= 0) {
      ArrayList<Block> blocks = _pixyCCC.getBlockCache();
      System.out.println("Getting Blocks : " + blocks.size());
      for (Block block : blocks) {
       // if (block.getSignature() == Constants.FUEL_CELL) {
            SmartDashboard.putNumber("FuelCell Signature", block.getSignature());
            SmartDashboard.putNumber("X", block.getX());
            SmartDashboard.putNumber("Y", block.getY());
            SmartDashboard.putNumber("Width", block.getWidth());
            SmartDashboard.putNumber("Height", block.getHeight());
            SmartDashboard.putNumber("Camera Angle", block.getAngle());
        //}
      }
    }
  }
}
