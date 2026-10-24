package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

@Autonomous(name = "Auton Pinpoint", group = "Autons")
public class AutonPinPoint extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 0.5;
    int hiveTipped = 1;

    @Override
    public void runOpMode() {
        initialize();


//        //drive to the other side
//        imuDrive(imuSpeed, 90, 0);
//        sleep(250);
//        //turn to face hive
//        imuTurn(imuSpeed, 180);
//
//        //shoot 4 preloaded pollen
//        sleep(1000);
//
//        //head back to the start
//        imuDrive(imuSpeed, 95, 0);
//        sleep(250);
//        //turn and drive to collect pollen from garden
//        imuTurn(imuSpeed, 90);
//        imuStrafe(imuSpeed, -4, 0, 30);
//        sleep(250);
//        //intake on
//        imuDrive(imuSpeed, 50, 0);
//        sleep(250);
//        //intake off
//        imuDrive(imuSpeed, -50, 0);
        GoBildaPinpointDriver pinpoint;


            pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
            waitForStart();
            while (opModeIsActive()) {
                pinpoint.getEncoderX();
//                int leftTicks = pinpoint.getLeftEncoder().getCurrentPosition();
//                int rightTicks = pinpoint.getRightEncoder().getCurrentPosition();
//                int centerTicks = pinpoint.getCenterEncoder().getCurrentPosition();
//                telemetry.addData("Left Encoder", leftTicks);
//                telemetry.addData("Right Encoder", rightTicks);
//                telemetry.addData("Center Encoder", centerTicks);
                telemetry.update();
            }
        }
    }

