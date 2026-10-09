package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/***
 *
 * robot hardware map
 * Dragon$11691
 */
public class RobotHardwareMap {

    //0 - FL
    //1 - FR
    //2 - RL
    //3 - RR
    private LinearOpMode opMode = null;
    public HardwareMap baseHMap;

    public VoltageSensor controlHubBatteryVoltage;
    //public VoltageSensor expansionHubBatteryVoltage;

    public LynxModule controlHub;
    public LynxModule expansionHub;

    public DcMotorEx backLeftMotor;
    public DcMotorEx backRightMotor;
    public DcMotorEx frontLeftMotor;
    public DcMotorEx frontRightMotor;
    public DcMotorEx intakeMotorFront;
    public DcMotorEx outtakeMotorBack1;
    public DcMotorEx outtakeMotorBack2;
    public DcMotorEx clawRotator;
    public DcMotorEx armMotor;
 //   public DcMotorEx lifterMotor;
    public DigitalChannel LED1Green;
    public DigitalChannel LED1Red;
    public DigitalChannel LED2Green;
    public DigitalChannel LED2Red;

    public CRServo LeftTransferServo;
    public CRServo RightTransferServo;
    public CRServo CenterTransferServo;



    public IMU chImu;

    private final int baseResolution_x = 320;
    private final int baseResolution_y = 240;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    boolean controlHubBatteryVoltageEnabled = true;
    boolean expansionHubBatteryVoltageEnabled = true;

    public RobotHardwareMap(HardwareMap baseHMap, LinearOpMode opmode) {

        this.opMode = opmode;
        this.baseHMap = baseHMap;
    }

    public void initialize(){

        opMode.telemetry.addData("Status", "detecting...");

        controlHubBatteryVoltage = baseHMap.get(VoltageSensor.class, "Control Hub");
        //expansionHubBatteryVoltage = baseHMap.get(VoltageSensor.class, "Expansion Hub 2");
        controlHub = baseHMap.get(LynxModule.class, "Control Hub");
        //expansionHub = baseHMap.get(LynxModule.class, "Expansion Hub 2");

        //dc motor vs dc motor ex?
        backLeftMotor = baseHMap.get(DcMotorEx.class, "RL");
        backRightMotor = baseHMap.get(DcMotorEx.class, "RR");
        frontLeftMotor = baseHMap.get(DcMotorEx.class, "FL");
        frontRightMotor = baseHMap.get(DcMotorEx.class, "FR");
        //intakeMotorFront = baseHMap.get(DcMotorEx.class,"IF");
        //outtakeMotorBack1 = baseHMap.get(DcMotorEx.class,"BRO");
        //outtakeMotorBack2 = baseHMap.get(DcMotorEx.class,"BLO");
        //LeftTransferServo = baseHMap.get(CRServo.class,"LTS");
        //RightTransferServo = baseHMap.get(CRServo.class,"RTS");
        //CenterTransferServo = baseHMap.get(CRServo.class,"CTS");
        //Initializes the IMU
        chImu = baseHMap.get(IMU.class, "chImu");

        IMU.Parameters myIMUParamaters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                )
        );
        chImu.initialize(myIMUParamaters);

        opMode.telemetry.addData("Status", "done");
        opMode.telemetry.update();

    }

}
