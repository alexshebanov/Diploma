import entity.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 15.02.2017.
 */
public class Application {


    public JFreeChart chart(int vortexPointsCount, double deltaT, int tCount) {

        double U0 = 1;

        Rectangular rectangular = new Rectangular(vortexPointsCount);
        CoefficientMatrix coefficientMatrix = new CoefficientMatrix(rectangular);
        double[][] startMatrix = coefficientMatrix.matrix();

        SOLE sole = new SOLE(startMatrix);
        for (int i = 0; i < rectangular.vortexPoints().size(); i++) {
            rectangular.vortexPoints().get(i).setGamma(sole.result()[i]);
        }

        List<Track> trackList = new ArrayList<Track>();
        for (VortexPoint point : rectangular.vortexPoints()) {
            if (point.isSharp()) {
                trackList.add(new Track(point));
            }
        }

        VortexSurface rectangularAndTracks = new SurfaceAndTracks(rectangular);
        List<VortexPoint> newPoints = new ArrayList<VortexPoint>();
        double startRightPart[] = coefficientMatrix.rightPart().clone();

        for (int t = 0; t < tCount; t++) {
            for (Track track: trackList) {
                VortexPoint currentPoint = track.points().get(track.points().size() - 1);
                SpeedDependencies dependencies = new SpeedDependencies(currentPoint, rectangularAndTracks);
                double u = dependencies.u() + U0;
                double v = dependencies.v();
                double xNew = currentPoint.x() + u * deltaT;
                double yNew = currentPoint.y() + v * deltaT;
                VortexPoint newPoint = new VortexPoint(xNew, yNew, currentPoint.gamma());
                track.points().add(newPoint);
                newPoints.add(newPoint);
            }
            rectangularAndTracks.vortexPoints().addAll(newPoints);

            double rightPart[] = startRightPart.clone();
            for (int i = 0; i < rectangular.controlPoints().size(); i++) {
                ControlPoint controlPoint = rectangular.controlPoints().get(i);
                SpeedDependencies newDependencies = new SpeedDependencies(controlPoint, rectangularAndTracks);
                for (int j = rectangular.vortexPoints().size(); j < rectangularAndTracks.vortexPoints().size(); j++) {
                    double a = newDependencies.uSpeedFromEachVortex()[j] * rectangular.cosnx().get(i)
                            + newDependencies.vSpeedFromEachVortex()[j] * rectangular.cosny().get(i);
                    double summat = a * rectangularAndTracks.vortexPoints().get(j).gamma();
                    rightPart[i] -= summat;
                }
            }


            CoefficientMatrix newCoefficientMatrix = new CoefficientMatrix(startMatrix, rightPart);
            SOLE sole1 = new SOLE(newCoefficientMatrix.matrix());
            coefficientMatrix = newCoefficientMatrix;
            for (int i = 0; i < rectangular.vortexPoints().size(); i++) {
                rectangular.vortexPoints().get(i).setGamma(sole1.result()[i]);
            }
        }


        //Потом сделать по нормальному
        JFrame window = new JFrame("Модель отрывного обтекания квадрата");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        XYSeries series1 = new XYSeries("1", false);
        Track track1 = trackList.get(0);
        for (VortexPoint point : track1.points()) {
            series1.add(point.x(), point.y());
        }

        XYSeries series2 = new XYSeries("2", false);
        Track track2 = trackList.get(1);
        for (VortexPoint point : track2.points()) {
            series2.add(point.x(), point.y());
        }

        XYSeries series3 = new XYSeries("3", false);
        Track track3 = trackList.get(2);
        for (VortexPoint point : track3.points()) {
            series3.add(point.x(), point.y());
        }

        XYSeries series4 = new XYSeries("4", false);
        Track track4 = trackList.get(3);
        for (VortexPoint point : track4.points()) {
            series4.add(point.x(), point.y());
        }

        XYSeries rectSeries = new XYSeries("r", false);
        rectSeries.add(0,0);
        rectSeries.add(0,1);
        rectSeries.add(1,1);
        rectSeries.add(1,0);
        rectSeries.add(0,0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(rectSeries);

        JFreeChart chart = ChartFactory.createScatterPlot("","","", dataset);
        window.setContentPane(new ChartPanel(chart));
        final XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        window.setBackground(new Color(255, 255, 255));
//        window.setVisible(true);
        return chart;
    }

}
