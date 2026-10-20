package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auton Blue Down no Help", group = "Autons")
public class AutonBlueScoringDown2 extends AutonBase {
    // Robot starts with back against center of blue depot

    double imuSpeed = 0.5;
    int hiveTipped = 1;

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();



    }

}
