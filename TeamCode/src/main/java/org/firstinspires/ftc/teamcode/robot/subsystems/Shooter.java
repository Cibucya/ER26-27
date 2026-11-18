package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    private DcMotorEx shooter = null;

    public Shooter() {}

    public void init(HardwareMap ahwMap) {
        shooter = ahwMap.get(DcMotorEx.class, "shooter");
        shooter.setDirection(DcMotor.Direction.FORWARD);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.reset();
    }

    public void setPower(double power) {
        shooter.setPower(power);
    }

    public void reset() {
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
