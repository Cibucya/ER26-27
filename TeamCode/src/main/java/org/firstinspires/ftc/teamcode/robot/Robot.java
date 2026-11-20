package org.firstinspires.ftc.teamcode.robot;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.robot.subsystems.Turret;
import org.firstinspires.ftc.teamcode.robot.subsystems.Webcam;

public class Robot {
    private HardwareMap hwMap = null;
    public MecanumDrive drive = null;
    public Shooter shooter = null;
    public Turret turret = null;
    public Intake intake = null;

    public Webcam webcam = null;

    public Robot() {}

    /**
     * Initialize the robot hardware.
     * @param ahwMap The HardwareMap from the OpMode.
     */
    public void init(HardwareMap ahwMap) {
        if (ahwMap == null) {
            throw new IllegalArgumentException("Robot: Hardware map not initialized.");
        }

        this.hwMap = ahwMap;

        this.drive = new MecanumDrive();
        this.shooter = new Shooter();
        this.turret = new Turret();
        this.intake = new Intake();
        this.webcam = new Webcam();

        this.drive.init(this.hwMap);
        this.shooter.init(this.hwMap);
        this.turret.init(this.hwMap);
        this.intake.init(this.hwMap);
        this.webcam.init(this.hwMap);
    }

    /**
     * Called once per control loop iteration
     * Handles robot behaviour and updates
     */
    public void periodic() {
        this.drive.periodic();
        this.shooter.periodic();
        this.turret.periodic();
        this.intake.periodic();
        this.webcam.periodic();
    }

}
