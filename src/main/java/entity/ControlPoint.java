package entity;

/**
 * Created by Alexander on 15.02.2017.
 */
public class ControlPoint implements Point {

    private final double x;
    private final double y;

    public ControlPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
