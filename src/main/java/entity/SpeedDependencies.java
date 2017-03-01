package entity;

/**
 * Created by Alexander on 19.02.2017.
 */
public class SpeedDependencies {
    private double u;
    private double v;
    private double[] uSpeedFromEachVortex;
    private double[] vSpeedFromEachVortex;

    public SpeedDependencies(Point point, VortexSurface surface) {
        int n = surface.vortexPoints().size();
        uSpeedFromEachVortex = new double[n];
        vSpeedFromEachVortex = new double[n];
        double[] r2 = new double[n];

        for (int i = 0; i < n; i++) {
            r2[i] = Math.pow(point.y() - surface.vortexPoints().get(i).y(), 2)
                    + Math.pow(point.x() - surface.vortexPoints().get(i).x(), 2);
        }

        for (int i = 0; i < n; i++) {
            uSpeedFromEachVortex[i] = 1
                    / (2 * Math.PI) * (point.y() - surface.vortexPoints().get(i).y()) / r2[i];
            vSpeedFromEachVortex[i] = - 1
                    / (2 * Math.PI) * (point.x() - surface.vortexPoints().get(i).x()) / r2[i];
        }

        this.u = 0;
        this.v = 0;
        for (int i = 0; i < n; i++) {
            if (r2[i] == 0) continue;
            u += (surface.vortexPoints().get(i).gamma() * uSpeedFromEachVortex[i]);
            v += (surface.vortexPoints().get(i).gamma() * vSpeedFromEachVortex[i]);
        }

    }

    public double u() {
        return this.u;
    }

    public double v() {
        return this.v;
    }

    public double[] uSpeedFromEachVortex() {
        return uSpeedFromEachVortex;
    }

    public double[] vSpeedFromEachVortex() {
        return vSpeedFromEachVortex;
    }
}
