package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp(name = "General")
public class TeleopGeneral extends LinearOpMode {
    Robot robot = new Robot();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        double intakePwr = 1;

        waitForStart();

        // Control loop
        while (opModeIsActive()) {
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
