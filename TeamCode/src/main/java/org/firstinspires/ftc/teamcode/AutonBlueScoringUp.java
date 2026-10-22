package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.camera;


@Autonomous(name = "Auton Blue Up", group = "Autons")
public class AutonBlueScoringUp extends AutonBase {
    // Robot starts with back against center of blue depot
    camera cam;

    double imuSpeed = 1;
    int hiveTipped = 0;

    @Override
    public void runOpMode() {
        initialize();
        cam = new camera(theHardwareMap, this);
        waitForStart();
        cam.start();

        //shoot all four preloaded pollen, (5 seconds)
        hiveTipped = cam.checkTags(1);



        if (hiveTipped == 1) {
            //move over to flower to collect pollen
            imuTurn(imuSpeed, 90);
            sleep(500);
            imuDrive(imuSpeed, 20, 0);
            //turn on intake to collect pollen from flower
            //turn off intake
            sleep(1000);
            imuDrive(imuSpeed, -20, 0);
        }
        else {
            //go to garden to collect pollen
            imuTurn(imuSpeed, -90);
            imuDrive(imuSpeed, 20, 0);
            //turn on intake
            imuDrive(imuSpeed, 10, 0);
            //turn off intake
            imuDrive(imuSpeed, -30, 0);

        }




    }
}