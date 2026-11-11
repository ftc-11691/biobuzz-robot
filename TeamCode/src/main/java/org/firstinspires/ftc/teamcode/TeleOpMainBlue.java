package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.hardware.Outtake;
import org.firstinspires.ftc.teamcode.hardware.RobotControlMechanum;
import org.firstinspires.ftc.teamcode.hardware.camera;

@TeleOp
public class TeleOpMainBlue extends AutonBase {

    static final String TAG = "TeleOp Main Blue";
    static final int TAG_GROUP = 1;          // 1 = blue (tags 38-45), 2 = red (tags 30-37)
    static final double STICK_DEADBAND = 0.05; // stick values smaller than this are treated as 0
    static final double AIM_TURN_SPEED = 0.3;  // max turn power used by imuTurn after Aim

    // zeroes out tiny stick values so a drifting stick doesn't move the robot
    private double deadband(double value) {
        return Math.abs(value) < STICK_DEADBAND ? 0 : value;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware (AutonBase.initialize creates theHardwareMap and sets up the IMU)
        initialize();

        RobotControlMechanum robotDrive = new RobotControlMechanum(theHardwareMap, this);
        robotDrive.initialize();

        Outtake outtake = new Outtake(theHardwareMap, this);

        camera Camera = new camera(theHardwareMap, this);

        boolean slowMode = false;
        double lastAimAngle = Double.NaN; // result of the last Aim call (NaN = none yet / no tags seen)

        telemetry.addData("Robot", "Initialized successfully");
        telemetry.update();

        waitForStart();
        Camera.start();

        // gamepads used to detect button presses (current vs. previous loop pass)
        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        // Main loop
        while (opModeIsActive()) {
            Camera.loop();

            // copy over the previous gamepads so we can compare what changed
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            /***************
             * Gamepad 1 - driving
             */
            // mecanum drive - left stick y is negative because up is negative
            double drive = deadband(-1 * gamepad1.left_stick_y);
            double strafe = -1 * gamepad1.left_trigger + gamepad1.right_trigger;
            double twist = deadband(gamepad1.right_stick_x);

            // slow mode speed values
            double speed = slowMode ? 0.45 : 0.9;
            drive *= speed;
            strafe *= speed;
            twist *= speed;

            // left bumper = slow mode on, right bumper = slow mode off
            if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {
                slowMode = true;
            } else if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {
                slowMode = false;
            }

            // hold B = turn toward the goal using the camera
            if (currentGamepad1.b) {
                twist = Camera.Robotallignwithgoal(twist);
            }

            robotDrive.teleOpMechanum(drive, strafe, twist);

            /***************
             * Gamepad 2
             */
            if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up) {
                outtake.increasemotorspeed();
            }
            if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {
                outtake.decreasemotorspeed();
            }

            // right bumper (pressed once) = measure the angle to the center of the tags
            // NOTE: Aim blocks for about a second, then imuTurn blocks until the turn finishes
            if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
                lastAimAngle = Camera.Aim(TAG_GROUP);
                if (!Double.isNaN(lastAimAngle)) {
                    theHardwareMap.chImu.resetYaw();          // make the current heading 0 so the turn is relative
                    imuTurn(AIM_TURN_SPEED, lastAimAngle);    // positive angle = tags to the right = turn right
                }
            }

            /***************
             * Debug telemetry
             */
            telemetry.addData("Stick twist (raw)", gamepad1.right_stick_x);
            telemetry.addData("Final twist", twist);
            telemetry.addData("Drive / Strafe", drive + " / " + strafe);
            telemetry.addData("Slow mode", slowMode);
            telemetry.addData("Last Aim angle (deg)", Double.isNaN(lastAimAngle) ? "none / no tags" : String.format("%.2f", lastAimAngle));
            telemetry.update();
        }
    }
}