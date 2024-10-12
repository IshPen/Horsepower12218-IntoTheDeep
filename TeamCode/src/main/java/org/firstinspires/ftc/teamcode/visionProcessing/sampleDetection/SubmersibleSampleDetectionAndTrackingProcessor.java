package org.firstinspires.ftc.teamcode.visionProcessing.sampleDetection;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubmersibleSampleDetectionAndTrackingProcessor extends OpenCvPipeline {

    private HashMap<Integer, RotatedRect> trackedBlocks = new HashMap<>();
    private int blockIdCounter = 0;

    // Variables to store the latest frame with drawing
    private Mat processedFrame = new Mat();

    // Method to process each frame
    @Override
    public Mat processFrame(Mat input) {
        // Convert the input frame to HSV color space
        Mat hsv = new Mat();
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_RGB2HSV);

        // Threshold for yellow color (HSV range)
        Mat yellowMask = new Mat();
        Scalar lowerYellow = new Scalar(20, 100, 100);
        Scalar upperYellow = new Scalar(30, 255, 255);
        Core.inRange(hsv, lowerYellow, upperYellow, yellowMask);

        // Find contours of yellow blocks
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(yellowMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Clear the tracked blocks and re-track based on detected contours
        trackedBlocks.clear();
        trackBlocks(contours, input);

        // Return the frame after drawing tracked block information on it
        input.copyTo(processedFrame);  // Copy the input to a new Mat for drawing
        drawTrackedBlocks(processedFrame);  // Draw rectangles on the frame
        return processedFrame;  // Return the processed frame
    }

    // Method to track blocks and assign unique IDs
    private void trackBlocks(List<MatOfPoint> contours, Mat frame) {
        for (MatOfPoint contour : contours) {
            // Filter by area to detect valid block contours
            if (Imgproc.contourArea(contour) > 100) {
                // Get the minimum bounding rectangle with rotation
                RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

                // Assign block a unique identifier
                int blockId = blockIdCounter++;
                trackedBlocks.put(blockId, rotatedRect);
            }
        }
    }

    // Method to draw tracked blocks onto the frame
    private void drawTrackedBlocks(Mat frame) {
        for (int blockId : trackedBlocks.keySet()) {
            RotatedRect rect = trackedBlocks.get(blockId);
            Point[] rectPoints = new Point[4];
            rect.points(rectPoints);

            // Draw the rotated rectangle on the frame
            for (int i = 0; i < 4; i++) {
                Imgproc.line(frame, rectPoints[i], rectPoints[(i + 1) % 4], new Scalar(0, 255, 0), 2);
            }

            // Draw the block ID and rotation angle on the frame
            Imgproc.putText(frame, "ID: " + blockId, rect.center, Imgproc.FONT_HERSHEY_SIMPLEX, 0.6, new Scalar(255, 0, 0), 2);
            Imgproc.putText(frame, "Angle: " + rect.angle, new Point(rect.center.x, rect.center.y + 20), Imgproc.FONT_HERSHEY_SIMPLEX, 0.6, new Scalar(255, 255, 255), 2);
        }
    }

    // Method to get processed frame for visual output in the simulator (if needed)
    public Mat getProcessedFrame() {
        return processedFrame;
    }
}
