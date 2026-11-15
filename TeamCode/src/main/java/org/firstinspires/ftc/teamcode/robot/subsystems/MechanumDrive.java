package org.firstinspires.ftc.teamcode.robot.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MechanumDrive {
    // Drive motors
    private DcMotorEx frontLeft  = null;
    private DcMotorEx frontRight = null;
    private DcMotorEx backLeft   = null;
    private DcMotorEx backRight  = null;

    public MechanumDrive() {}

    /**
     * Initializes the base motors.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        // TODO: change when motors are installed and driver station is set up
        frontLeft  = ahwMap.get(DcMotorEx.class, "front_left_drive");
        frontRight = ahwMap.get(DcMotorEx.class, "front_right_drive");
        backLeft   = ahwMap.get(DcMotorEx.class, "right_left_drive");
        backRight  = ahwMap.get(DcMotorEx.class, "right_right_drive");

        // TODO: change when motors are installed
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        reset();
    }

    /**
     * Resets encoders
     */
    public void reset() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Converts drive, strafe, and twist values to motor outputs
     * @param drive  front and back
     * @param strafe left and right
     * @param twist  rotating the whole chassis
     */
    void drive(double drive, double strafe, double twist) {
        double[] speeds = {
                (drive + strafe + twist),
                (drive - strafe - twist),
                (drive - strafe + twist),
                (drive + strafe - twist)
        };

        double max = Math.abs(speeds[0]);
        for (int i = 0; i < speeds.length; ++i) {
            double speed = Math.abs(speeds[i]);
            if (max < speed) max = speed;
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; ++i) {
                speeds[i] /= max;
            }
        }

        frontLeft.setPower(speeds[0]);
        frontRight.setPower(speeds[1]);
        backLeft.setPower(speeds[2]);
        backRight.setPower(speeds[3]);
    }
}
