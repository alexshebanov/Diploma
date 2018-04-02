package entity;

/**
 * Created by Alexander on 15.02.2017.
 */
public class VortexPoint implements Point {

    private double x;
    private double y;
    private boolean isSharp;
    private double gamma = 1;

    public VortexPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public VortexPoint(double x, double y, boolean isSharp) {
        this.x = x;
        this.y = y;
        this.isSharp = isSharp;
    }

    public VortexPoint(double x, double y, double gamma) {
        this.x = x;
        this.y = y;
        this.gamma = gamma;
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

    public boolean isSharp() {
        return isSharp;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double gamma() {
        return this.gamma;
    }

    public void setSharp(boolean sharp) {
        isSharp = sharp;
    }
}
