package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "TeleopBlue")
public class TeleopBlue extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        int minTagId = 38;
        int maxTagId = 45;

        robot.webcam.setTagIdRange(minTagId, maxTagId);
        double intakePwr = 1;

        waitForStart();

        // Control loop
        while (opModeIsActive()) {
            robot.webcam.updateDetecting();
            boolean turretLeft = gamepad1.left_trigger_pressed;
            boolean turretRight = gamepad1.right_trigger_pressed;

            if (gamepad1.y && robot.webcam.isTagVisible()) robot.turret.startTracking();
            else if (turretLeft == turretRight) robot.turret.stopTracking();
            else robot.turret.spinUp(turretLeft ? -1 : 1);

            double drive = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double twist = -gamepad1.right_stick_x;
            robot.drive.setDriveInputs(drive, strafe, twist);

            if (gamepad1.x) robot.shooter.spinUp(intakePwr);
            else robot.shooter.stop();

            robot.intake.spinUp(1.0);
            robot.periodic();
        }
    }
}