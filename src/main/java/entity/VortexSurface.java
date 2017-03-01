package entity;

import java.util.List;

/**
 * Created by Alexander on 15.02.2017.
 */
public interface VortexSurface {

    List<VortexPoint> vortexPoints();

    List<ControlPoint> controlPoints();

    List<Double> cosnx();

    List<Double> cosny();

}
