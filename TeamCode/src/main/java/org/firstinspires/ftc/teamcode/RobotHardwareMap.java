package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.vision.VisionPortal;

public class RobotHardwareMap {

    private LinearOpMode opMode = null;
    public HardwareMap baseHMap;

    public VoltageSensor controlHubBatteryVoltage;
    public VoltageSensor expansionHubBatteryVoltage;

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

    private VisionPortal visionPortal;

    boolean controlHubBatteryVoltageEnabled = true;
    boolean expansionHubBatteryVoltageEnabled = true;

    public RobotHardwareMap(HardwareMap baseHMap, LinearOpMode opmode) {
        this.opMode = opmode;
        this.baseHMap = baseHMap;
    }

    public void initialize() {

        opMode.telemetry.addData("Status", "detecting...");

        controlHubBatteryVoltage = baseHMap.get(VoltageSensor.class, "Control Hub");
        controlHub = baseHMap.get(LynxModule.class, "Control Hub");
        expansionHubBatteryVoltage = baseHMap.tryGet(VoltageSensor.class, "Expansion Hub 2");
        expansionHub = baseHMap.tryGet(LynxModule.class, "Expansion Hub 2");

        backLeftMotor = baseHMap.get(DcMotorEx.class, "RL");
        backRightMotor = baseHMap.get(DcMotorEx.class, "RR");
        frontLeftMotor = baseHMap.get(DcMotorEx.class, "FL");
        frontRightMotor = baseHMap.get(DcMotorEx.class, "FR");

        //intakeMotorFront = baseHMap.tryGet(DcMotorEx.class, "IF");
        outtakeMotorBack1 = baseHMap.tryGet(DcMotorEx.class, "BRO");
        outtakeMotorBack2 = baseHMap.tryGet(DcMotorEx.class, "BLO");
        clawRotator = baseHMap.tryGet(DcMotorEx.class, "clawRotator");
        armMotor = baseHMap.tryGet(DcMotorEx.class, "armMotor");

        LED1Green = baseHMap.tryGet(DigitalChannel.class, "LED1Green");
        LED1Red = baseHMap.tryGet(DigitalChannel.class, "LED1Red");
        LED2Green = baseHMap.tryGet(DigitalChannel.class, "LED2Green");
        LED2Red = baseHMap.tryGet(DigitalChannel.class, "LED2Red");

        LeftTransferServo = baseHMap.tryGet(CRServo.class, "LTS");
        RightTransferServo = baseHMap.tryGet(CRServo.class, "RTS");
        CenterTransferServo = baseHMap.tryGet(CRServo.class, "CTS");

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

    public boolean hasIntake() {
        return intakeMotorFront != null;
    }

    public boolean hasOuttake() {
        return outtakeMotorBack1 != null && outtakeMotorBack2 != null;
    }

    public boolean hasTransfer() {
        return LeftTransferServo != null && RightTransferServo != null && CenterTransferServo != null;
    }

    public boolean hasArm() {
        return armMotor != null && clawRotator != null;
    }

    public boolean hasLEDs() {
        return LED1Green != null && LED1Red != null && LED2Green != null && LED2Red != null;
    }
}