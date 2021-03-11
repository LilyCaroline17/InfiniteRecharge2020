package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightSubsystem extends SubsystemBase{
    private NetworkTable table=NetworkTableInstance.getDefault().getTable("limelight");;
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry tx = table.getEntry("tx");
    private NetworkTableEntry ta = table.getEntry("ta");
    private double d = 0.0;
    private boolean targetFound = false;

    public LimeLightSubsystem(){
        lightOff();
    }
    public void periodic(){
        dashBoard();
    }

    public void dashBoard(){
        
        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        if (area > 0.0){
            targetFound = true;
        }
        else {
            targetFound = false;
        }
        SmartDashboard.putBoolean("Launch Target", targetFound);
        double tan = Math.tan((y+Constants.ANGLE)*Math.PI/180);
        try {
            //System.out.println("D before is " + d);
            d=(Constants.PORT_HEIGHT-Constants.CAM_HEIGHT)/tan;
            //System.out.println("D after is: " + d);
        } catch (Exception divideByZeroException) {
            d = -1;
            System.out.println("Exception");
        }
        /*System.out.println("X is " + x);
        System.out.println("Y is " + y);
        System.out.println("A is " + area);*/
        

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Distance to target", d);
    }
    public double returnD(){
        //System.out.println("D is " + d);
        return d;
    }
	public double returnTY() {
		return ty.getDouble(0.0);
    }
    public void lightOn(){
        table.getEntry("ledMode").setNumber(3);
    }

    public void lightOff(){
        // CHECK TO ADD LIMELIGHT OFF
        table.getEntry("ledMode").setNumber(1);
    }
}
