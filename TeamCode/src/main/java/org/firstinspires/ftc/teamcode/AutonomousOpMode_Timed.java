/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Autonomous OpMode Timed", group="Linear Opmode")
//@Disabled
public class AutonomousOpMode_Timed extends LinearOpMode {

    //========================================
    // DECLARE OPMODE MEMBERS
    //========================================

    // Misc
    private ElapsedTime runtime = new ElapsedTime();

    // Motors
    private DcMotor leftfront = null;
    private DcMotor leftback = null;
    private DcMotor rightfront = null;
    private DcMotor rightback = null;
    private DcMotor clawmotor = null;
    private DcMotor stickmotor = null;

 /*   // Servos
    private Servo leftStick;
    private Servo rightStick;
    
    double leftStick_position = 0.0;
    double rightStick_position = 0.0;
**/
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //========================================
        // HARDWARE MAPPING
        //========================================

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftfront  = hardwareMap.get(DcMotor.class, "leftfront");
        leftback = hardwareMap.get(DcMotor.class, "leftback");
        rightfront  = hardwareMap.get(DcMotor.class, "rightfront");
        rightback = hardwareMap.get(DcMotor.class, "rightback");
        clawmotor = hardwareMap.get(DcMotor.class, "clawmotor");
        stickmotor = hardwareMap.get(DcMotor.class, "stickmotor");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftfront.setDirection(DcMotor.Direction.REVERSE);
        leftback.setDirection(DcMotor.Direction.REVERSE);
        rightfront.setDirection(DcMotor.Direction.FORWARD);
        rightback.setDirection(DcMotor.Direction.FORWARD);
        clawmotor.setDirection(DcMotor.Direction.FORWARD);
        stickmotor.setDirection(DcMotor.Direction.FORWARD);
        
   /*     //hardware map servos
        leftStick = hardwareMap.servo.get("leftservo");
        rightStick = hardwareMap.servo.get("rightservo");
**/

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //========================================
            // Autonomous Mode
            //========================================
            
            /*
            * We're going to park the robot on the line by timing it
            * */

            // drive forward for 2 seconds
            moveForward();
            sleep(3500);
            
            clawmotor.setPower(1.0);
            sleep(1000);
            
            //change name if we change name on configuration
            //leftStick.setPosition(0.5);
            //sleep(1000);
            //
            //leftStick.setPosition(0.0);
            //sleep(1000);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }

        // Stop the robot after the autonomous period
        leftfront.setPower(0.0);
        leftback.setPower(0.0);
        rightfront.setPower(0.0);
        rightback.setPower(0.0);

    }

    /* Drivetrain helper methods */
    private void moveForward () {
        leftfront.setPower(1.0);
        leftback.setPower(1.0);
        rightfront.setPower(1.0);
        rightback.setPower(1.0);
    }

    private void moveBack () {
        leftfront.setPower(-1.0);
        leftback.setPower(-1.0);
        rightfront.setPower(-1.0);
        rightback.setPower(-1.0);
    }

    private void moveLeft () {
        leftfront.setPower(-1.0);
        leftback.setPower(1.0);
        rightfront.setPower(1.0);
        rightback.setPower(-1.0);
    }

    private void moveRight () {
        leftfront.setPower(1.0);
        leftback.setPower(-1.0);
        rightfront.setPower(-1.0);
        rightback.setPower(1.0);
    }
}
