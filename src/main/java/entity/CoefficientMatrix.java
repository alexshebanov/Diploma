package entity;

/**
 * Created by Alexander on 23.02.2017.
 */
public class CoefficientMatrix {
    private double[][] a;
    private double[] rightPart;
    private double[][] fullMatrix;

    public CoefficientMatrix(VortexSurface surface) {
        int size = surface.vortexPoints().size();

        a = new double[size + 1][size + 1];
        setA(size, surface);

        rightPart = new double[size + 1];
        setRightPart(size, surface);

        fullMatrix = new double[size + 1][size + 2];
        setFullMatrix(size);

    }

    public CoefficientMatrix(double[][] matrix, double[] rightPart) {
        this.a = matrix;
        this.rightPart = rightPart;
        int size = matrix.length;
        this.fullMatrix = new double[size][size + 1];
        setFullMatrix(size - 1);
    }

    private void setA (int size, VortexSurface surface) {

        // i - number of control point, j - number of vortex point

        for (int i = 0; i < size; i++) {
            SpeedDependencies dependencies = new SpeedDependencies(surface.controlPoints().get(i), surface);
            for (int j = 0; j < size; j++) {
                a[i][j] = dependencies.uSpeedFromEachVortex()[j] * surface.cosnx().get(i)
                        + dependencies.vSpeedFromEachVortex()[j] * surface.cosny().get(i);
            }
        }
        for (int j = 0; j < size; j++) {
            a[size][j] = 1;
        }
        for (int i = 0; i < size; i++) {
            a[i][size] = 1;
        }
        a[size][size] = 1;
    }

    private void setRightPart(int size, VortexSurface surface) {
        for (int i = 0; i < size; i++) {
            rightPart[i] = - surface.cosnx().get(i);
        }
        rightPart[size] = 0;
    }

    private void setFullMatrix (int size) {
        for (int i = 0; i < size + 1; i++)
            for (int j = 0; j < size + 1; j++)
                fullMatrix[i][j] = a[i][j];
        for (int i = 0; i < size + 1; i++)
            fullMatrix[i][size + 1] = rightPart[i];
    }

    public double[][] matrix() {
        return this.fullMatrix;
    }

    public double[] rightPart() {
        return rightPart;
    }
}
