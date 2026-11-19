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
        waitForStart();

        // Control loop
        while (opModeIsActive()) {
            double intakePwr, turretPwr, shooterPwr;
            shooterPwr = 0;
            turretPwr = 0;

            intakePwr = 1.0;

            if (gamepad1.a) {
                turretPwr = 1.0;
            } else if (gamepad1.b) {
                turretPwr = -1.0;

            } else if (gamepad1.x) {
                shooterPwr = 1.0;
            }
            robot.setPushPower(turretPwr, intakePwr);

            robot.periodic();
        }
    }
}