package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 25.02.2017.
 */
public class SurfaceAndTracks implements VortexSurface {

    private List<VortexPoint> vortexPoints;
    private List<ControlPoint> controlPoints;
    private List<Double> cosnx;
    private List<Double> cosny;

    public SurfaceAndTracks(VortexSurface basicSurface) {
        this.vortexPoints = new ArrayList<VortexPoint>(basicSurface.vortexPoints());
        this.controlPoints = new ArrayList<ControlPoint>(basicSurface.controlPoints());
        this.cosnx = new ArrayList<Double>(basicSurface.cosnx());
        this.cosny = new ArrayList<Double>(basicSurface.cosny());
    }

    public List<VortexPoint> vortexPoints() {
        return vortexPoints;
    }

    public List<ControlPoint> controlPoints() {
        return controlPoints;
    }

    public List<Double> cosnx() {
        return cosnx;
    }

    public List<Double> cosny() {
        return cosny;
    }
}
