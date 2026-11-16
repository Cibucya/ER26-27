package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class Teleop extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
       robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            double intakePwr, turretPwr;
            intakePwr = 1.0;

            if (gamepad1.a) {
                turretPwr = 1.0;
            } else if (gamepad1.b) {
                turretPwr = -1.0;
            }

            else {
                turretPwr = 0.0;
            }
            robot.setPushPower(turretPwr, intakePwr);
        }
    }
}