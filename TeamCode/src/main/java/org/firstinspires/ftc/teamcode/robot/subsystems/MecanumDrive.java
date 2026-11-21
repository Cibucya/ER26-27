package org.firstinspires.ftc.teamcode.robot.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class MecanumDrive implements Subsystem {
    private final List<DcMotorEx> motors = new ArrayList<>();

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
        backLeft   = ahwMap.get(DcMotorEx.class, "back_left_drive");
        backRight  = ahwMap.get(DcMotorEx.class, "back_right_drive");

        motors.clear();
        motors.addAll(List.of(frontLeft, frontRight, backLeft, backRight));

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
        for (DcMotorEx motor : motors) motor.setPower(0);
    }

    public void resetEncoders() {
        for (DcMotorEx motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    private void setBrakeMode(DcMotor.ZeroPowerBehavior behavior) {
        for (DcMotorEx motor : motors) motor.setZeroPowerBehavior(behavior);
    }

    private void setRunMode(DcMotor.RunMode mode) {
        for (DcMotorEx motor : motors) motor.setMode(mode);
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
            for (int i = 0; i < speeds.length; ++i)
                speeds[i] /= max;
        }

        for (int i = 0; i < motors.size() && i < speeds.length; ++i)
            motors.get(i).setPower(speeds[i]);
    }
}
