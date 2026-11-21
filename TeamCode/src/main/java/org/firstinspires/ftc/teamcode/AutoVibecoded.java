package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Robot;

@Autonomous
public class AutoVibecoded extends LinearOpMode {

    // Tune these two values on the field so the robot clears the starting line cleanly
    private static final double DRIVE_POWER = 0.45;
    private static final double DRIVE_SECONDS = 1.2;

    @Override
    public void runOpMode() {
        Robot robot = new Robot();
        robot.init(hardwareMap);

        ElapsedTime timer = new ElapsedTime();

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Drive Power", DRIVE_POWER);
        telemetry.addData("Drive Time (s)", DRIVE_SECONDS);
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            timer.reset();

            // Drive forward out of the starting zone
            while (opModeIsActive() && timer.seconds() < DRIVE_SECONDS) {
                robot.drive.setDriveInputs(DRIVE_POWER, 0.0, 0.0);
                robot.periodic();

                telemetry.addData("Status", "Driving Out");
                telemetry.addData("Elapsed (s)", "%.2f / %.2f", timer.seconds(), DRIVE_SECONDS);
                telemetry.update();
            }

            // Stop driving once out of the starting area
            robot.drive.setDriveInputs(0.0, 0.0, 0.0);
            robot.periodic();

            telemetry.addData("Status", "Complete");
            telemetry.update();

            while (opModeIsActive()) {
                robot.periodic();
                idle();
            }
        }

        robot.stop();
    }
}
