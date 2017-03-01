package entity;

/**
 * Created by Alexander on 23.02.2017.
 */
public class SOLE {
    private double[][] matrix;
    private double[] result;

    public SOLE(double[][] matrix) {
        this.matrix = matrix;
        solveByGaussMethod();
    }

    private void solveByGaussMethod() {
        double[][] matrix = this.matrix.clone();
        for (int i = 0; i < this.matrix.length; i++) {
            matrix[i] = this.matrix[i].clone();
        }
        int n = matrix.length;
        int m = n + 1;

        double tmp;
        double[] xx = new double[n];
        int i, j, k;

        for (i = 0; i < n; i++) {
            tmp = matrix[i][i];
            for (j = n; j >= i; j--)
                matrix[i][j] /= tmp;
            for (j = i + 1; j < n; j++) {
                tmp = matrix[j][i];
                for (k = n; k >= i; k--)
                    matrix[j][k] -= tmp * matrix[i][k];
            }
        }

        xx[n - 1] = matrix[n - 1][n];
        for (i = n - 2; i >= 0; i--) {
            xx[i] = matrix[i][n];
            for (j = i + 1; j < n; j++) xx[i] -= matrix[i][j] * xx[j];
        }

        this.result = xx;
    }

    public double[] result() {
        return result;
    }
}
