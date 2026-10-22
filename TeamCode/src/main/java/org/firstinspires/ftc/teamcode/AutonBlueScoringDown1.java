package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auton Blue Down", group = "Autons")
public class AutonBlueScoringDown1 extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 0.5;
    int hiveTipped = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();

        //drive to the other side
        imuDrive(imuSpeed, 90, 0);
        sleep(250);
        //turn to face hive
        imuTurn(imuSpeed, 180);

        //shoot 4 preloaded pollen
        sleep(1000);

        //head back to the start
        imuDrive(imuSpeed, 95, 0);
        sleep(250);
        //turn and drive to collect pollen from garden
        imuTurn(imuSpeed, 90);
        imuStrafe(imuSpeed, -4, 0, 30);
        sleep(250);
        //intake on
        imuDrive(imuSpeed, 50, 0);
        sleep(250);
        //intake off
        imuDrive(imuSpeed, -50, 0);

        }

    }
