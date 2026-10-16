package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.LynxModuleImuType;

@Autonomous(name = "Auton Blue Down", group = "Autons")
public class AutonBlueDown extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 0.5;
    int hiveTipped = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        imuDrive(imuSpeed, 90, 0);
        sleep(250);
        imuTurn(imuSpeed, 180);

        //shoot 4 preloaded pollen
        sleep(1000);

        imuDrive(imuSpeed, 95, 0);
        sleep(250);
        imuTurn(imuSpeed, 90);
        imuStrafe(imuSpeed, -4, 0, 30);
        sleep(250);
        imuDrive(imuSpeed, 50, 0);
        sleep(250);
        imuDrive(imuSpeed, -50, 0);

        }

    }
