package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 25.02.2017.
 */
public class Track {

    private VortexPoint startPoint;
    private List<VortexPoint> points;

    public Track(VortexPoint startPoint) {
        this.startPoint = startPoint;
        points = new ArrayList<VortexPoint>();
        points.add(this.startPoint);
    }

    public List<VortexPoint> points() {
        return this.points;
    }

}
