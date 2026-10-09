package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.LynxModuleImuType;

@Autonomous(name = "Auton Blue Up", group = "Autons")
public class AutonBlueUp extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 1;
    int hiveTipped = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        //shoot all four preloaded pollen, (5 seconds)


        if (hiveTipped == 1) {
            //move over to flower to collect pollen
            imuTurn(imuSpeed, 90);
            imuDrive(imuSpeed, 20, 0);
            //turn on intake to collect pollen from flower
            //turn off intake
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