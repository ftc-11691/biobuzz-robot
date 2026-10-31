package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.LynxModuleImuType;

import org.firstinspires.ftc.teamcode.hardware.camera;


@Autonomous(name = "Auton Red Scoring Down Help", group = "Autons")
public class AutonRedScoringDownHelp extends AutonBase {
    // Robot starts with back against center of blue depot
    camera cam;

    double imuSpeed = 0.5;

    int hiveTipped = 0;

    //where we are according to the checkTags method
    int AprilPosition = 2;

    @Override
    public void runOpMode() {
        initialize();
        cam = new camera(theHardwareMap, this);
        waitForStart();
        cam.start();


        //wait to see if other team tipped hive
        encoderDrive(imuSpeed, 5, 0);

        sleep(10000);

        //output of 1 means visible, 0 means not
        hiveTipped = cam.checkTags(AprilPosition);

        //if we can see it, success
        if (hiveTipped == 1) {
            //shoot preloaded pollen
            sleep(5000);
            imuDrive(imuSpeed, 7, 0);
            imuStrafe(imuSpeed, 11, 0, 30);
            imuDrive(imuSpeed, -6, 0);


        }
    }
}
