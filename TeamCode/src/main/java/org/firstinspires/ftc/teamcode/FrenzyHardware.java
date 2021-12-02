package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

    /**************************************************************************************************/

//@Config
    public class FrenzyHardware



    {

        /* Public OpMode members. */
        public DcMotor fr;
        public double frPower = 0;

        public DcMotor fl;
        public double flPower = 0;

        public DcMotor rr;
        public double rrPower = 0;

        public DcMotor rl;
        public double rlPower = 0;

        public DcMotor quack;
        public double quackPower = 0;

        public DcMotor armLift;
        public double armLiftPower = 0;

        public Servo claw;

        public Servo tubeBois;

        public Servo outake;

        public BNO055IMU imu;

        public double motorSpeed = 0;
        public double intake = 0;

        public double strafe = 0;
        public double leftStrafe = 0;
        public double rightStrafe = 0;

        /**********************************************************************************************/

        /* local OpMode members.
        HardwareMap hwMap           =  null;
        public ElapsedTime  = new void ElapsedTime();

        int pos = 0;
         int pos1 = lift.getCurrentPosition();
         int cPos = pos;
        DigitalChannel digitalRed;
        */


        public FrenzyHardware(){

        }

        public void init(HardwareMap hardwareMap) {

            try {
                fr = hardwareMap.dcMotor.get("fr");
                fr.setPower(frPower);
                fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            } catch (Exception a) {
                fr = null;
            }

            try {
                fl = hardwareMap.dcMotor.get("fl");
                fl.setPower(flPower);
                fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                fl.setDirection(DcMotor.Direction.REVERSE);
            } catch (Exception a) {
                fl = null;
            }

            try {
                rr = hardwareMap.dcMotor.get("rr");
                rr.setPower(rrPower);
                rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            } catch (Exception a) {
                rr = null;
            }

            try {
                rl = hardwareMap.dcMotor.get("rl");
                rl.setPower(rlPower);
                rl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rl.setDirection(DcMotor.Direction.REVERSE);
            } catch (Exception a) {
                rl = null;
            }

            try {
                quack = hardwareMap.dcMotor.get("quack");
                quack.setPower(quackPower);
                quack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                quack.setDirection(DcMotor.Direction.REVERSE);
            } catch (Exception a){
                quack = null;
            }

            try {
                armLift = hardwareMap.dcMotor.get("armLift");
                armLift.setPower(armLiftPower);
                armLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            } catch (Exception a){
                quack = null;
            }

            try {
                claw = hardwareMap.servo.get("claw");
            } catch (Exception a){
                claw = null;
            }


            try {
                outake = hardwareMap.servo.get("outake");
            } catch (Exception a){
                outake = null;
            }

            try {
                tubeBois = hardwareMap.servo.get("tubeBois");
            } catch (Exception a){
                tubeBois = null;
            }


            try {
                BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
                parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
                parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
                parameters.calibrationDataFile = "BNO055IMUCalibration.json";
                parameters.loggingEnabled      = true;
                parameters.loggingTag          = "IMU";
                parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

                imu = hardwareMap.get(BNO055IMU.class, "imu 1");
                imu.initialize(parameters);
            } catch (Exception a ){
                imu = null;
            }

        }
        public void setDrivePower(double frontLeft, double frontRight, double rearLeft, double rearRight){
            if (fl != null) {
                fl.setPower(frontLeft);
            }
            if (fr != null) {
                fr.setPower(frontRight);
            }
            if (rl != null) {
                rl.setPower(rearLeft);
            }
            if (rr != null) {
                rr.setPower(rearRight);
            }
        }

        public double getAngle(){
            if (imu != null) {
                return imu.getAngularOrientation().firstAngle;
            }
            return Double.NaN;
        }

        public void setMotorSpeed(double speed) {
            if (fl != null) {
                fl.setPower(speed);
            }
            if (fr != null) {
                fr.setPower(speed);
            }
            if (rl != null) {
                rl.setPower(speed);
            }
            if (rr != null) {
                rr.setPower(speed);
            }
        }

        public void setLeftStrafe( double power){

                strafe += power;    //was.3
                //int maxspeed = 0;
                //frPower = Range.clip(-strafe, maxspeed, maxspeed);
                //flPower = Range.clip(+strafe, maxspeed, maxspeed);
                //rrPower = Range.clip(+strafe, maxspeed, maxspeed);
                //rlPower = Range.clip(-strafe, maxspeed, maxspeed);


                if (fl != null) {
                    fl.setPower(flPower);
                }
                if (fr != null) {
                    fr.setPower(frPower);
                }
                if (rl != null) {
                    rl.setPower(rlPower);
                }
                if (rr != null) {
                    rr.setPower(rrPower);
                }
            }



        public void setRightStrafe(double power) {
            strafe += power; //was .3
            //int maxspeed = 0;
            //frPower = Range.clip(+strafe, -maxspeed, maxspeed);
            //flPower = Range.clip(-strafe, -maxspeed, maxspeed);
            //rrPower = Range.clip(-strafe, -maxspeed, maxspeed);
            //rlPower = Range.clip(+strafe, -maxspeed, maxspeed);


            if (fl != null) {
                fl.setPower(flPower);
            }
            if (fr != null) {
                fr.setPower(frPower);
            }
            if (rl != null) {
                rl.setPower(rlPower);
            }
            if (rr != null) {
                rr.setPower(rrPower);
            }

        }

    }
