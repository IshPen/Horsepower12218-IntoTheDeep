package org.firstinspires.ftc.teamcode.autonomous.testPathing;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="TestLeftSideAutonPath")
public class testLeftSideAutonPath extends OpMode {
    private Telemetry telemetryA;

    private Follower follower;

    PathChain testleftSideAutonPath = autonomousTestPathingPathContainer.testLeftSideAutonPath();


    @Override
    public void init() {
        follower = new Follower(hardwareMap);

        follower.followPath(testleftSideAutonPath);

        telemetryA = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetryA.addLine("This will run in a roughly circular shape of radius "
                + ", starting on the right-most edge. So, make sure you have enough "
                + "space to the left, front, and back to run the OpMode.");
        telemetryA.update();
    }

    @Override
    public void loop() {
        follower.update();
        if (follower.atParametricEnd()) {
            follower.followPath(testleftSideAutonPath);
        }

        follower.telemetryDebug(telemetryA);
    }
}
