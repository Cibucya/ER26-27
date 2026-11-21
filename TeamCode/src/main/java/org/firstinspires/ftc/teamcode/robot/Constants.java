package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class Constants {
    private Constants() {}

    public static final class Drive {
        public static final String FRONT_LEFT_NAME = "front_left_drive";
        public static final String FRONT_RIGHT_NAME = "front_right_drive";
        public static final String BACK_LEFT_NAME = "back_left_drive";
        public static final String BACK_RIGHT_NAME = "back_right_drive";

        public static final DcMotorSimple.Direction FRONT_LEFT_DIR = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction FRONT_RIGHT_DIR = DcMotorSimple.Direction.REVERSE;
        public static final DcMotorSimple.Direction BACK_LEFT_DIR = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction BACK_RIGHT_DIR = DcMotorSimple.Direction.REVERSE;
    }

    public static final class Shooter {
        public static final String POLLEN_MOTOR_NAME = "shooter_pollen";
        public static final String NECTAR_MOTOR_NAME = "shooter_nectar";
        public static final DcMotorSimple.Direction DIRECTION_POLLEN = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction DIRECTION_NECTAR = DcMotorSimple.Direction.FORWARD;
        public static final double DEFAULT_POWER = 0.85;
    }

    public static final class Intake {
        public static final String LIFT_SERVO_NAME = "servo_intake_lift";
        public static final String FRONT_SERVO_NAME = "servo_intake_front";
        public static final CRServo.Direction LIFT_DIR = CRServo.Direction.FORWARD;
        public static final CRServo.Direction FRONT_DIR = CRServo.Direction.FORWARD;
        public static final double INTAKE_POWER = 1.0;
        public static final double OUTTAKE_POWER = -1.0;
    }
}
