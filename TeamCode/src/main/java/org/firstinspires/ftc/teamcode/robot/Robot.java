package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;

public class Robot {
    private HardwareMap hwMap = null;
    public MecanumDrive drive = null;
    public Shooter shooter = null;
    public Turret turret = null;

    private CRServo intakeLeft, intakeRight;

    public Robot() {}

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        intakeLeft = ahwMap.get(CRServo.class, "servo_intake_left");
        intakeRight = ahwMap.get(CRServo.class, "servo_intake_right");

        intakeLeft.setDirection(DcMotor.Direction.FORWARD);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);

        this.hwMap = ahwMap;

        this.drive = new MecanumDrive();
        this.shooter = new Shooter();
        this.turret = new Turret();

        this.drive.init(this.hwMap);
        this.shooter.init(this.hwMap);
        this.turret.init(this.hwMap);
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
        intakeLeft.setPower(intakePwr);
        intakeRight.setPower(intakePwr);
    }
}
