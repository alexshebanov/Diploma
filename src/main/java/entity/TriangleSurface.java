package entity;

import java.util.ArrayList;
import java.util.List;

import others.*;

public class TriangleSurface implements VortexSurface {

    private List<VortexPoint> vortexPoints;
    private List<ControlPoint> controlPoints;
    private List<Double> cosnx;
    private List<Double> cosny;

    public TriangleSurface(Triangle triangle) {
        vortexPoints = new ArrayList<>();
        controlPoints = new ArrayList<>();
        for (int i = 0; i < triangle.getPoints().size() - 1; i++) {
            others.Point point = triangle.getPoints().get(i);
            if (i % 2 == 0) {
                vortexPoints.add(new VortexPoint(point.getX(), point.getY()));
            } else {
                controlPoints.add(new ControlPoint(point.getX(), point.getY()));
            }
        }
        int thirdPart = (triangle.getPoints().size() - 1) / 3;
        vortexPoints.get(0).setSharp(true);
        vortexPoints.get(thirdPart).setSharp(true);
        vortexPoints.get(thirdPart * 2 / 3).setSharp(true);
        setCos();
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

    @Override
    public List<VortexPoint> vortexPoints() {
        return this.vortexPoints;
    }

    @Override
    public List<ControlPoint> controlPoints() {
        return this.controlPoints;
    }

    @Override
    public List<Double> cosnx() {
        return this.cosnx;
    }

    @Override
    public List<Double> cosny() {
        return this.cosny;
    }
}
