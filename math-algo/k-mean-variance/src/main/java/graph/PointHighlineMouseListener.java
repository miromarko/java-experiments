/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package graph;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;

import alg.KMeans;

/**
 * 
 */
public class PointHighlineMouseListener implements MouseMotionListener {
	ValueMarker domainMarker = new ValueMarker(0);
	ValueMarker rangeMarker = new ValueMarker(0);
	ChartPanel chartPanel;
	JLabel infoLabel;

	public PointHighlineMouseListener(ChartPanel chartPanel, JLabel infoLabel) {
		this.chartPanel = chartPanel;
		this.infoLabel = infoLabel;
		infoLabel.setText("");
		domainMarker.setPaint(Color.gray);
		rangeMarker.setPaint(Color.gray);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */

	public void mouseDragged(MouseEvent e) {
		e.consume();

		Point2D p = chartPanel.translateScreenToJava2D(e.getPoint());

		Rectangle2D plotArea = chartPanel.getScreenDataArea();
		XYPlot plot = chartPanel.getChart().getXYPlot(); // your plot
		double chartX = plot.getDomainAxis().java2DToValue(p.getX(), plotArea,
				plot.getDomainAxisEdge());
		double chartY = plot.getRangeAxis().java2DToValue(p.getY(), plotArea,
				plot.getRangeAxisEdge());
		ValueAxis xAxis = plot.getDomainAxis();
		ValueAxis yAxis = plot.getRangeAxis();
		if (chartX >= xAxis.getLowerBound() && chartX <= xAxis.getUpperBound()
				&& chartY >= yAxis.getLowerBound()
				&& chartY <= yAxis.getUpperBound()) {

			plot.removeDomainMarker(domainMarker);
			plot.removeRangeMarker(rangeMarker);

			domainMarker.setValue(chartX);
			rangeMarker.setValue(chartY);
			infoLabel.setText("    (x,y)= " + KMeans.roundD(chartX) + " , "
					+ KMeans.roundD(chartY));

			plot.addDomainMarker(domainMarker);
			plot.addRangeMarker(rangeMarker);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e) {
		mouseDragged(e);

	}

	public void removeMarker() {
		chartPanel.getChart().getXYPlot().removeDomainMarker(domainMarker);
		chartPanel.getChart().getXYPlot().removeRangeMarker(rangeMarker);
		infoLabel.setText("");

	}

}
