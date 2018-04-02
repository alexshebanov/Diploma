package others;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    private double length;
    private double rotationAngle;
    private List<Point> points;

    Triangle(double sideLength) {
        this.length = sideLength;
        this.rotationAngle = 0;
        this.points = new ArrayList<>(3);
        final Point firstPoint = new Point(-length / 2.0, -length * Math.sqrt(3) / 6.0);
        this.points.add(firstPoint);
        this.points.add(new Point(0, length * Math.sqrt(3) / 2.0));
        this.points.add(new Point(length / 2.0, -length * Math.sqrt(3) / 6.0));
        this.points.add(firstPoint);
    }

    void discretize(final int n) {
        assert n >= 0;
        this.rotate(-this.rotationAngle);
        final List<Point> newPoints = new ArrayList<>(3 * n);
        final int pointsInTriangle = this.points.size();
        for (int i = 0; i < pointsInTriangle - 1; i++) {
            final Point first = this.points.get(i);
            final Point last = this.points.get(i + 1);
            newPoints.add(first);
            final double xLength = (last.getX() - first.getX()) / n;
            final double yLength = (last.getY() - first.getY()) / n;
            for (int j = 0; j < n - 1; j++) {
                final double prevPointX = newPoints.get(newPoints.size() - 1).getX();
                final double prevPointY = newPoints.get(newPoints.size() - 1).getY();
                newPoints.add(new Point(prevPointX + xLength, prevPointY + yLength));
            }
        }
        newPoints.add(this.points.get(this.points.size() - 1));
        this.points = new ArrayList<>(newPoints);
        this.rotate(this.rotationAngle);
    }

    void rotate(double phi) {
        for (Point p : this.points) {
            p.rotateAroundZ(phi);
        }
        this.rotationAngle = phi;
    }

    void rotateWithoutLastPoint(double phi) {
        for (int i = 0; i < points.size() - 1; i++) {
            points.get(i).rotateAroundZ(phi);
        }
        this.rotationAngle = phi;
    }

    double getLength() {
        return length;
    }

    double getRotationAngle() {
        return rotationAngle;
    }

    public List<Point> getPoints() {
        return points;
    }
}
