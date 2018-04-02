import entity.*;
import entity.Point;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander on 15.02.2017.
 */
public class PlotBuilder {
    public static double delta;
    VortexSurface rectangularAndTracks;
    Rectangular rectangular;
    double boundaryThickness;


    public JFreeChart chart(int vortexPointsCount, double deltaT, int tCount, double boundaryThickness) {
        delta = deltaT;
        double U0 = 1;
        this.boundaryThickness = boundaryThickness;

        rectangular = new Rectangular(vortexPointsCount);
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

        Iterator pointsIterator = rectangular.vortexPoints().iterator();
        while (pointsIterator.hasNext()) {
            VortexPoint point = (VortexPoint) pointsIterator.next();
            if (point.isSharp()) pointsIterator.remove();
        }

        //Выносим вихри на пограничный слой
        trackList.get(0).getStartPoint().setX(trackList.get(0).getStartPoint().x() - boundaryThickness);
        trackList.get(0).getStartPoint().setY(trackList.get(0).getStartPoint().y() - boundaryThickness);
        trackList.get(1).getStartPoint().setX(trackList.get(1).getStartPoint().x() - boundaryThickness);
        trackList.get(1).getStartPoint().setY(trackList.get(1).getStartPoint().y() + boundaryThickness);
        trackList.get(2).getStartPoint().setX(trackList.get(2).getStartPoint().x() + boundaryThickness);
        trackList.get(2).getStartPoint().setY(trackList.get(2).getStartPoint().y() + boundaryThickness);
        trackList.get(3).getStartPoint().setX(trackList.get(3).getStartPoint().x() + boundaryThickness);
        trackList.get(3).getStartPoint().setY(trackList.get(3).getStartPoint().y() - boundaryThickness);


        rectangularAndTracks = new SurfaceAndTracks(rectangular);
        List<VortexPoint> newPoints = new ArrayList<VortexPoint>();
        double startRightPart[] = coefficientMatrix.rightPart().clone();

        for (int t = 0; t < tCount; t++) {
            for (Track track : trackList) {
                VortexPoint currentPoint = track.points().get(track.points().size() - 1);
                SpeedDependencies dependencies = new SpeedDependencies(currentPoint, rectangularAndTracks);
                double u = dependencies.u() + U0;
                double v = dependencies.v();
                double xNew = currentPoint.x() + u * deltaT;
                double yNew = currentPoint.y() + v * deltaT;
                if (xNew < 1 + boundaryThickness && xNew > 0 - boundaryThickness &&
                        yNew < 1 + boundaryThickness && yNew > 0 - boundaryThickness) {
                    double x = currentPoint.x();
                    double y = currentPoint.y();
                    double deltaX = u * deltaT;
                    double deltaY = v * deltaT;
                    if ((x < (0 - boundaryThickness)) && (y < (0 - boundaryThickness))) {
                        xNew = x - deltaX;
                        yNew = y - deltaY;
                    }
                    if ((x < (0 - boundaryThickness)) && ((y > (0 - boundaryThickness)) && (y < (1 + boundaryThickness)))) {
                        xNew = xNew - deltaX;
                    }
                    if ((x < (0 - boundaryThickness)) && (y > 1 + boundaryThickness)) {
                        xNew = x - deltaX;

                        yNew = y - deltaY;
                    }
                    if ((y > (1 + boundaryThickness)) && ((x > (0 - boundaryThickness)) && (x < (1 + boundaryThickness)))) {
                        yNew = yNew - deltaY;
                    }
                    if (x > (1 + boundaryThickness) && y > (1 + boundaryThickness)) {
                        xNew = x - deltaX;
                        yNew = y - deltaY;
                    }
                    if ((x > (1 + boundaryThickness)) && ((y > (0 - boundaryThickness)) && (y < (1 + boundaryThickness)))) {
                        xNew = xNew - deltaX;
                    }
                    if ((x > (1 + boundaryThickness)) && (y < (0 - boundaryThickness))) {
                        xNew = x - deltaX;
                        yNew = y - deltaY;
                    }
                    if (((x > (0 - boundaryThickness)) && (x < (1 + boundaryThickness))) && (y < (0 - boundaryThickness))) {
                        yNew = yNew - deltaY;
                    }
                }
                // end of validation
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

        try {
            speedVectorField();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        XYSeries rectSeries = new XYSeries("rectangular", false);
        rectSeries.add(0, 0);
        rectSeries.add(0, 1);
        rectSeries.add(1, 1);
        rectSeries.add(1, 0);
        rectSeries.add(0, 0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(rectSeries);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setBaseLinesVisible(false);
        renderer.setSeriesLinesVisible(4, true);
        renderer.setSeriesPaint(3, Color.MAGENTA);
        renderer.setSeriesPaint(4, Color.BLACK);

        JFreeChart chart = ChartFactory.createScatterPlot("", "", "", dataset);
        final XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setRange(-5, 5);
        plot.getRangeAxis().setRange(-5, 5);
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        return chart;
    }

    private void speedVectorField() throws IOException {
        double a = -1, b = 6, c = -1, d = 2;
        double N = 70;
        double deltaX = (b - a) / N, deltaY = (d - c) / N;
        FileWriter output = new FileWriter(new File("result.txt"));
        output.write("ListVectorPlot[{");

        for ( int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double x = a + i * deltaX;
                double y = c + j * deltaY;
                if (x >= 0 && x <= 1 && y >= 0 && y <= 1) {
                    output.write(",{{" + x + "," + y + "},{" + "0,0" + "}}");
                }
                SpeedDependencies speed = new SpeedDependencies(new ControlPoint(x, y), rectangularAndTracks);
                output.write(",{{" + x + "," + y + "},{" + (speed.u() + 1.) + "," + speed.v() + "}}");
            }
        }
        output.write("}]");
        output.close();

    }
}
