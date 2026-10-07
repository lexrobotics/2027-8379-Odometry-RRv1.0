package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

// Un-comment this import when you are back at school with the OTOS sensor:
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;

@TeleOp(name = "Testbed Diagnostic (Home)", group = "Test")
public class TestbedDiagnostic extends LinearOpMode {

    // Actuators on home testbed
    private DcMotor testMotor;
    private Servo servo2;
    private CRServo servo1;
    // OTOS Sensor declaration (Commented out for home testing)
    // private SparkFunOTOS otos;

    @Override
    public void runOpMode() throws InterruptedException {

        // 1. Initialize Testbed Actuators
        testMotor = hardwareMap.get(DcMotor.class, "test_motor");
        servo1 = hardwareMap.get(CRServo.class, "servo_1");
        servo2 = hardwareMap.get(Servo.class, "servo_2");

        /* --- UNCOMMENT AT SCHOOL FOR OTOS SENSOR ---
        otos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        otos.setLinearUnit(DistanceUnit.INCH);
        otos.setAngularUnit(AngleUnit.DEGREES);
        otos.calibrateImu();
        otos.resetTracking();
        -------------------------------------------- */

        telemetry.addData("Status", "Initialized! Press Play.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Motor Control via Gamepad 1 Left Stick Y
            double motorPower = -gamepad1.left_stick_y;
            testMotor.setPower(motorPower);

            // Servo Controls via Gamepad Buttons
            if (gamepad1.a) {
                servo1.setPower(1.0);
                servo2.setPosition(1.0);
            } else if (gamepad1.b) {
                servo1.setPower(-1.0);
                servo2.setPosition(0.0);
            }
            else if (gamepad1.x) {
                servo1.setPower(0.0);
                servo2.setPosition(0.5);
            }

            // Motor and Servo Telemetry Output
            telemetry.addData("--- HOME TESTBED ACTUATORS ---", "");
            telemetry.addData("Motor Power", "%.2f", motorPower);
            telemetry.addData("Motor Encoder Position", testMotor.getCurrentPosition());
            telemetry.addData("Servo 1 Position", "%.2f", servo1.getPower());
            telemetry.addData("Servo 2 Position", "%.2f", servo2.getPosition());

            /* --- UNCOMMENT AT SCHOOL FOR OTOS TELEMETRY ---
            SparkFunOTOS.Pose2D pose = otos.getPosition();
            telemetry.addData("--- OTOS POSITION ---", "");
            telemetry.addData("X (Inches)", "%.2f", pose.x);
            telemetry.addData("Y (Inches)", "%.2f", pose.y);
            telemetry.addData("Heading (Degrees)", "%.2f", pose.h);
            ------------------------------------------------ */

            telemetry.update();
        }
    }
}
