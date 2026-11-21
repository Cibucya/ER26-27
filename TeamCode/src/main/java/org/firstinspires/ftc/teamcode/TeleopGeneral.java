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

            double pollenPower = gamepad1.left_trigger;
            double nectarPower = gamepad1.right_trigger;
            if (pollenPower > 0) robot.shooter.spinUpPollen(pollenPower);
            else robot.shooter.stopNectar();
            if (nectarPower > 0) robot.shooter.spinUpNectar(nectarPower);
            else robot.shooter.stopPollen();

            if (gamepad1.x) robot.intake.spinUp(1.0);
            else if (gamepad1.y) robot.intake.spinUp(-1.0);
            else robot.intake.stop();

            robot.periodic();
        }
    }
}
