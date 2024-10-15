package org.firstinspires.ftc.teamcode.autonomous.testPathing;

import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

public class CONTAINERAutonomousTestPaths {
    // "v2 -- 10/13/24 PedroPathing 3-Sample LeftSide Auton"
    public static PathChain testLeftSideAutonPath() {
        PathBuilder builder = new PathBuilder();

        builder
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(8.250, 84.000, Point.CARTESIAN),
                                new Point(25.075, 83.625, Point.CARTESIAN),
                                new Point(26.957, 125.062, Point.CARTESIAN),
                                new Point(40.607, 120.796, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 2
                        new BezierCurve(
                                new Point(40.607, 120.796, Point.CARTESIAN),
                                new Point(18.427, 126.938, Point.CARTESIAN),
                                new Point(11.943, 130.009, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed(true)
                .addPath(
                        // Line 3
                        new BezierCurve(
                                new Point(11.943, 130.009, Point.CARTESIAN),
                                new Point(21.498, 128.815, Point.CARTESIAN),
                                new Point(37.024, 130.692, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 4
                        new BezierLine(
                                new Point(37.024, 130.692, Point.CARTESIAN),
                                new Point(11.261, 133.763, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed(true)
                .addPath(
                        // Line 5
                        new BezierCurve(
                                new Point(11.261, 133.763, Point.CARTESIAN),
                                new Point(27.981, 130.521, Point.CARTESIAN),
                                new Point(39.583, 135.981, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .addPath(
                        // Line 6
                        new BezierLine(
                                new Point(39.583, 135.981, Point.CARTESIAN),
                                new Point(14.673, 136.322, Point.CARTESIAN)
                        )
                )
                .setTangentHeadingInterpolation()
                .setReversed(true);
        return builder.build();
    }
}

