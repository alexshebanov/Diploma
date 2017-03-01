package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 15.02.2017.
 */
public class Rectangular implements VortexSurface {

    private List<VortexPoint> vortexPoints;
    private List<ControlPoint> controlPoints;
    private List<Double> cosnx;
    private List<Double> cosny;


    public Rectangular(int n) {

        vortexPoints = new ArrayList<VortexPoint>();
        controlPoints = new ArrayList<ControlPoint>();

        double delta = 1. / (2 * n);

        vortexPoints.add(new VortexPoint(0, 0 + 0 * 1. / n, true));
        controlPoints.add(new ControlPoint(0, delta));
        for (int i = 1; i < n; i++) {
            vortexPoints.add(new VortexPoint(0, 0 + i * 1. / n));
            controlPoints.add(new ControlPoint(0, delta + i * 2. * delta));
        }

        vortexPoints.add(new VortexPoint(0 + 0 * 1. / n, 1, true));
        controlPoints.add(new ControlPoint(delta, 1.));
        for (int i = 1; i < n; i++) {
            vortexPoints.add(new VortexPoint(0 + i * 1. / n, 1));
            controlPoints.add(new ControlPoint(delta + i * 2. * delta, 1));
        }

        vortexPoints.add(new VortexPoint(1, 1 - 0 * 1. / n, true));
        controlPoints.add(new ControlPoint(1, 1 - delta));
        for (int i = 1; i < n; i++) {
            vortexPoints.add(new VortexPoint(1, 1 - i * 1. / n));
            controlPoints.add(new ControlPoint(1, 1 - delta - i * 2. * delta));
        }

        vortexPoints.add(new VortexPoint(1 - 0 * 1. / n, 0, true));
        controlPoints.add(new ControlPoint(1 - delta, 0));
        for (int i = 1; i < n; i++) {
            vortexPoints.add(new VortexPoint(1 - i * 1. / n, 0));
            controlPoints.add(new ControlPoint(1 - delta - i * 2. * delta, 0));
        }

        setCos();
    }

    public List<VortexPoint> vortexPoints() {
        return vortexPoints;
    }

    public List<ControlPoint> controlPoints() {
        return controlPoints;
    }

    private void setCos() {
        this.cosnx = new ArrayList<Double>();
        this.cosny = new ArrayList<Double>();

        double[] hk = new double[controlPoints.size()];
        double[] hi = new double[vortexPoints.size()];

        for (int i = 1; i < vortexPoints.size(); i++) {
            hi[i] = Math.sqrt(Math.pow(vortexPoints.get(i).x() - vortexPoints.get(i - 1).x(), 2)
                    + Math.pow(vortexPoints.get(i).y() - vortexPoints.get(i - 1).y(), 2));
            hk[i] = Math.sqrt(Math.pow(controlPoints.get(i).x() - controlPoints.get(i - 1).x(), 2)
                    + Math.pow(controlPoints.get(i).y() - controlPoints.get(i - 1).y(), 2));
        }

        for (int i = 1; i < vortexPoints.size(); i++) {
            cosnx.add(-(vortexPoints.get(i).y() - vortexPoints.get(i - 1).y()) / hi[i]);
            cosny.add((vortexPoints.get(i).x() - vortexPoints.get(i - 1).x()) / hi[i]);
        }
        cosnx.add(-(vortexPoints.get(0).y() - vortexPoints.get(vortexPoints.size() - 1).y())
                / Math.sqrt(Math.pow(vortexPoints.get(0).x() - vortexPoints.get(vortexPoints.size() - 1).x(), 2)
                + Math.pow(vortexPoints.get(0).y() - vortexPoints.get(vortexPoints.size() - 1).y(), 2)));
        cosny.add((vortexPoints.get(0).x() - vortexPoints.get(vortexPoints.size() - 1).x())
                / Math.sqrt(Math.pow(vortexPoints.get(0).x() - vortexPoints.get(vortexPoints.size() - 1).x(), 2)
                + Math.pow(vortexPoints.get(0).y() - vortexPoints.get(vortexPoints.size() - 1).y(), 2)));
    }

    public List<Double> cosnx() {
        return cosnx;
    }

    public List<Double> cosny() {
        return cosny;
    }
}
