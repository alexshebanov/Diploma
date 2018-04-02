package others;

import entity.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    final static int tCount = 20;
    final static double deltaT = 0.07;
    final static int U0 = 1;

    public static void main(String[] args) {
        Triangle tr = new Triangle(1);
        tr.discretize(4);
        tr.rotateWithoutLastPoint(Math.PI / 2 * 3);

        VortexSurface surface = new TriangleSurface(tr);

        //START
        CoefficientMatrix coefficientMatrix = new CoefficientMatrix(surface);
        double[][] startMatrix = coefficientMatrix.matrix();

        SOLE sole = new SOLE(startMatrix);
        for (int i = 0; i < surface.vortexPoints().size(); i++) {
            surface.vortexPoints().get(i).setGamma(sole.result()[i]);
        }

        List<Track> trackList = new ArrayList<Track>();
        for (VortexPoint point : surface.vortexPoints()) {
            if (point.isSharp()) {
                trackList.add(new Track(point));
            }
        }

        Iterator pointsIterator = surface.vortexPoints().iterator();
        while (pointsIterator.hasNext()) {
            VortexPoint point = (VortexPoint) pointsIterator.next();
            if (point.isSharp()) pointsIterator.remove();
        }

        SurfaceAndTracks triangleAndTracks = new SurfaceAndTracks(surface);
        List<VortexPoint> newPoints = new ArrayList<VortexPoint>();
        double startRightPart[] = coefficientMatrix.rightPart().clone();

        for (int t = 0; t < tCount; t++) {
            for (Track track : trackList) {
                VortexPoint currentPoint = track.points().get(track.points().size() - 1);
                SpeedDependencies dependencies = new SpeedDependencies(currentPoint, triangleAndTracks);
                double u = dependencies.u() + U0;
                double v = dependencies.v();
                double xNew = currentPoint.x() + u * deltaT;
                double yNew = currentPoint.y() + v * deltaT;


                // end of validation
                VortexPoint newPoint = new VortexPoint(xNew, yNew, currentPoint.gamma());
                track.points().add(newPoint);
                newPoints.add(newPoint);
            }
        }
        triangleAndTracks.vortexPoints().addAll(newPoints);

        double rightPart[] = startRightPart.clone();
        for (int i = 0; i < surface.controlPoints().size(); i++) {
            ControlPoint controlPoint = surface.controlPoints().get(i);
            SpeedDependencies newDependencies = new SpeedDependencies(controlPoint, triangleAndTracks);
            for (int j = surface.vortexPoints().size(); j < triangleAndTracks.vortexPoints().size(); j++) {
                double a = newDependencies.uSpeedFromEachVortex()[j] * surface.cosnx().get(i)
                        + newDependencies.vSpeedFromEachVortex()[j] * surface.cosny().get(i);
                double summat = a * triangleAndTracks.vortexPoints().get(j).gamma();
                rightPart[i] -= summat;
            }
        }


        CoefficientMatrix newCoefficientMatrix = new CoefficientMatrix(startMatrix, rightPart);
        SOLE sole1 = new SOLE(newCoefficientMatrix.matrix());
        coefficientMatrix = newCoefficientMatrix;
        for (int i = 0; i < surface.vortexPoints().size(); i++) {
            surface.vortexPoints().get(i).setGamma(sole1.result()[i]);
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

        XYSeries vortexSeries = new XYSeries("vortex", false);
        XYSeries controlSeries = new XYSeries("control", false);

        for (VortexPoint point : surface.vortexPoints()) {
            vortexSeries.add(point.x(), point.y());
        }

        for (ControlPoint point : surface.controlPoints()) {
            controlSeries.add(point.x(), point.y());
        }

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(vortexSeries);
        collection.addSeries(controlSeries);
        collection.addSeries(series1);
        collection.addSeries(series2);
        collection.addSeries(series3);

        JFreeChart chart = ChartFactory.createScatterPlot("triangle", "x", "y",
                collection);
        chart.getXYPlot().getDomainAxis().setRange(-1, 1);
        chart.getXYPlot().getRangeAxis().setRange(-1, 1);

        JFrame window = new JFrame("MAIN");
        window.setSize(800, 640);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new ChartPanel(chart));
        window.setVisible(true);
    }
    //END


//


}
