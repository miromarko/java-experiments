/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package graph;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;

import alg.KMeans;

/**
 * 
 */
public class DrawBezierMouseListener implements ChartMouseListener {
	private ChartPanel chartPanel = null;
	XYSeries data;

	public DrawBezierMouseListener(ChartPanel chartPanel, XYSeries data) {
		this.chartPanel = chartPanel;
		this.data = data;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jfree.chart.ChartMouseListener#chartMouseClicked(org.jfree.chart.
	 * ChartMouseEvent)
	 */
	public void chartMouseClicked(ChartMouseEvent e) {
		e.getTrigger().consume();
		Point2D p = chartPanel.translateScreenToJava2D(e.getTrigger()
				.getPoint());
		Rectangle2D plotArea = chartPanel.getScreenDataArea();
		XYPlot plot = chartPanel.getChart().getXYPlot();
		double chartX = plot.getDomainAxis().java2DToValue(
				KMeans.roundDouble(p.getX(), 2), plotArea,
				plot.getDomainAxisEdge());
		double chartY = plot.getRangeAxis().java2DToValue(
				KMeans.roundDouble(p.getY(), 2), plotArea,
				plot.getRangeAxisEdge());
		ValueAxis xAxis = plot.getDomainAxis();
		ValueAxis yAxis = plot.getRangeAxis();

		if (chartX >= xAxis.getLowerBound() && chartX <= xAxis.getUpperBound()
				&& chartY >= yAxis.getLowerBound()
				&& chartY <= yAxis.getUpperBound()) {

			chartX = KMeans.roundD(chartX);
			chartY = KMeans.roundD(chartY);
			data.add(chartX, chartY);
			// recalculate kmeans

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.jfree.chart.ChartMouseListener#chartMouseMoved(org.jfree.chart.
	 * ChartMouseEvent)
	 */
	public void chartMouseMoved(ChartMouseEvent e) {
		// TODO Auto-generated method stub

	}

}
