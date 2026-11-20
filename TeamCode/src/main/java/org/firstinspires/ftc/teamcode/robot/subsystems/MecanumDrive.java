package org.firstinspires.ftc.teamcode.robot.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive implements Subsystem {
    // Drive motors
    private DcMotorEx frontLeft  = null;
    private DcMotorEx frontRight = null;
    private DcMotorEx backLeft   = null;
    private DcMotorEx backRight  = null;

    private double driveInput =  0;
    private double strafeInput = 0;
    private double twistInput =  0;

    public MecanumDrive() {}

    /**
     * Initializes the base motors.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    @Override
    public void init(HardwareMap ahwMap) {
        frontLeft  = ahwMap.get(DcMotorEx.class, "front_left_drive");
        frontRight = ahwMap.get(DcMotorEx.class, "front_right_drive");
        backLeft   = ahwMap.get(DcMotorEx.class, "right_left_drive");
        backRight  = ahwMap.get(DcMotorEx.class, "right_right_drive");

        // TODO: Update when tested
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        setBrakeMode(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoders();
    }

    /**
     * Sets operator's input
     * @param drive
     * @param strafe
     * @param twist
     */
    public void setDriveInputs(double drive, double strafe, double twist) {
        this.driveInput = drive;
        this.strafeInput = strafe;
        this.twistInput = twist;
    }

    /**
     * Called once per control loop iteration
     * Handles drive-related calculations and updates
     */
    @Override
    public void periodic() {
        applyMecanumPower(driveInput, strafeInput, twistInput);
    }

    /**
     * Resets drive inputs and stops the robot
     */
    @Override
    public void stop() {
        driveInput = 0;
        strafeInput = 0;
        twistInput = 0;
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void setBrakeMode(DcMotor.ZeroPowerBehavior behavior) {
        frontLeft.setZeroPowerBehavior(behavior);
        frontRight.setZeroPowerBehavior(behavior);
        backLeft.setZeroPowerBehavior(behavior);
        backRight.setZeroPowerBehavior(behavior);
    }

    /**
     * Converts drive, strafe, and twist values to motor outputs
     * @param drive  front and back
     * @param strafe left and right
     * @param twist  rotating the whole chassis
     */
    void applyMecanumPower(double drive, double strafe, double twist) {
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
