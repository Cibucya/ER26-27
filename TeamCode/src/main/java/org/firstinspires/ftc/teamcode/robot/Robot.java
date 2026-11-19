package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.MechanumDrive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;

public class Robot {
    private HardwareMap hwMap = null;
    public MechanumDrive drive = null;
    public Shooter shooter = null;

    private CRServo turretAxis, intakeLeft, intakeRight;


    public Robot() {}

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        turretAxis = ahwMap.get(CRServo.class, "servo_turret_axis");
        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");


        turretAxis.setDirection(CRServo.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);

        this.hwMap = ahwMap;

        this.drive = new MechanumDrive();
        this.shooter = new Shooter();

        this.drive.init(this.hwMap);
        this.shooter.init(this.hwMap);
    }

    /**
     * Called once per control loop iteration
     * Handles robot behaviour and updates
     */
    public void periodic() {
        this.drive.periodic();
        this.shooter.periodic();
    }

    public void setPushPower(double turretPwr, double intakePwr){
        turretAxis.setPower(turretPwr);
        intakeLeft.setPower(intakePwr);
        intakeRight.setPower(intakePwr);
    }

}
