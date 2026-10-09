package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.LynxModuleImuType;

@Autonomous(name = "Auton Test", group = "Autons")
public class AutonTest extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        for (int i = 1; i <= 4; i++) {
            imuStrafe(0.5, 50, 0, 30);
            imuStrafe(0.5, -50, 0, 30);
        }


    }
}