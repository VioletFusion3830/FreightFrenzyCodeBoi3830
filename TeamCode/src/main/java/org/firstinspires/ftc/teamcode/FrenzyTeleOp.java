package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;


    @TeleOp(name="FrenzyTeleOp", group="Linear Opmode")
    public class FrenzyTeleOp extends LinearOpMode  {

        // Declare OpMode members.
        private ElapsedTime runtime = new ElapsedTime();

        //Open = Closed and Closed = Open
        boolean bababooey = false;
        FrenzyHardware robot = new FrenzyHardware();

        @Override
        public void runOpMode() {
            boolean intakeSwitch = false;

            robot.init(hardwareMap);


            /*robot.armLift.setPower(0.4);
            robot.armLift.setTargetPosition(robot.armLift.getCurrentPosition());
            robot.armLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            // Wait for the game to start (driver presses PLAY)
            waitForStart();
            runtime.reset();

            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                // GamePad 1

                double drive = gamepad1.left_stick_y;
                double turn = -gamepad1.right_stick_x * 1;
                double strafe = -gamepad1.left_stick_x;

                if (gamepad1.dpad_down) {
                    drive += 0.4;
                } else if (gamepad1.dpad_up) {
                    drive -= 0.4;
                }
                if (gamepad1.dpad_left) {
                    strafe += 0.4;

                } else if (gamepad1.dpad_right) {
                    strafe -= 0.4;
                }

                if(gamepad2.dpad_left){
                    robot.quack.setPower(-.9);
                    robot.quack.setTargetPosition(-100);
                }
                if(gamepad2.dpad_right){
                    robot.quack.setPower(.9);
                    robot.quack.setTargetPosition(100);
                }

                /*robot.fr.setPower(Range.clip(gamepad1.left_stick_y-gamepad1.left_stick_x,-1.0, 1.0));saZXWQDsazWXQ
                robot.fl.setPower(Range.clip(gamepad1.right_stick_y+gamepad1.left_stick_x,-1.0,1.0));
                robot.rr.setPower(Range.clip(gamepad1.left_stick_y-gamepad1.left_stick_x,-1.0,1.0));
                robot.rl.setPower(Range.clip(gamepad1.left_stick_y+gamepad1.left_stick_x,-1.0, 1.0));*/
                telemetry.addData("drive power",drive);
                telemetry.addData("turn power",turn);
                telemetry.addData("strafe power",strafe);
                double scale = Math.max(Math.abs(drive - turn - strafe), Math.max(Math.abs(drive + turn + strafe), Math.max(Math.abs(drive - turn + strafe), Math.abs(drive + turn - strafe))));
                if(scale > 1) {
                    robot.frPower = Range.clip(-(drive - turn - strafe) / scale, -1, 1);
                    robot.flPower = Range.clip(-(drive + turn + strafe) / scale, -1, 1);
                    robot.rrPower = Range.clip(-(drive - turn + strafe) / scale, -1, 1);
                    robot.rlPower = Range.clip(-(drive + turn - strafe) / scale, -1, 1);
                }
                else {
                    robot.frPower = Range.clip((drive - turn - strafe), -1, 1);
                    robot.flPower = Range.clip((drive + turn + strafe), -1, 1);
                    robot.rrPower = Range.clip((-drive - turn + strafe), -1, 1);
                    robot.rlPower = Range.clip((-drive + turn - strafe), -1, 1);
                }



                // Send calculated Power to wheels
                if ( robot.fr != null ) {
                    robot.fr.setPower(robot.frPower);
                }
                if (robot.fl != null) {
                    robot.fl.setPower(robot.flPower);
                }
                if (robot.rr != null){
                    robot.rr.setPower(robot.rrPower);
                }
                if (robot.rl != null) {
                    robot.rl.setPower(robot.rlPower);
                }
                if (robot.quack != null)  {
                    robot.quack.setPower(robot.quackPower);
                }


                telemetry.update();





                // GamePad 2
                telemetry.addData("position", robot.armLift.getCurrentPosition());

                if (gamepad2.left_stick_y > 0.5) {
                    robot.armLift.setPower(0.3);
                   // robot.armLift.setTargetPosition(-31);
                }else if (gamepad2.left_stick_y < -0.5) {
                    robot.armLift.setPower(-0.3);
                   // robot.armLift.setTargetPosition(-1658);
                }else{
                    robot.armLift.setPower(0);
                }

                if(gamepad2.x && !bababooey){
                    robot.claw.setPosition(0.5);
                    bababooey = true;
                }
                if(!gamepad2.x ){
                    robot.claw.setPosition(0);
                    bababooey = false;
                }

                telemetry.update();

// wow wow

            }
        }

    }