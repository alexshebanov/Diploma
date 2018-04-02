package entity;

/**
 * Created by Alexander on 15.02.2017.
 */
public class ControlPoint implements Point {

    private double x;
    private double y;

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

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
}
