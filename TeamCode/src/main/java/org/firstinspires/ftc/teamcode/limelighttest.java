package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.camera;

@TeleOp(name = "Limelight Tag Test", group = "Autons")
public class limelighttest extends LinearOpMode {
    @Override
    public void runOpMode() {
        RobotHardwareMap robot = new RobotHardwareMap(hardwareMap, this);
        robot.initialize();
        camera cam = new camera(robot, this);

        waitForStart();
        cam.start();

        while (opModeIsActive()) {
            cam.loop();
            telemetry.update();
        }
    }
}