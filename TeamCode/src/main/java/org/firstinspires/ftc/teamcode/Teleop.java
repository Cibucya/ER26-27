package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() {
        CRServo turretAxis = hardwareMap.get(CRServo.class, "servo_turret_axis");
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                turretAxis.setPower(1);
            } else {
                turretAxis.setPower(0);
            }
        }
    }
}