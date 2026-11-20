package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp
public class Teleop extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        double intakePwr, turretPwr;
        turretPwr = 0;
        intakePwr = 1.0;

        waitForStart();

        // Control loop
        while (opModeIsActive()) {
            if (gamepad1.a) {
                turretPwr = 1.0;
            } else if (gamepad1.b) {
                turretPwr = -1.0;
            }

            double drive = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double twist = -gamepad1.right_stick_x;
            robot.drive.setDriveInputs(drive, strafe, twist);

            if (gamepad1.x) robot.shooter.spinUp(1);
            else robot.shooter.stop();

            robot.setPushPower(turretPwr, intakePwr);
            robot.periodic();
        }
    }
}