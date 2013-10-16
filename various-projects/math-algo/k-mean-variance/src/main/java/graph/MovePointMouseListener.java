/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;

import alg.KMeans;

/**
 * 
 */
public class MovePointMouseListener implements MouseListener,
		MouseMotionListener {
	private boolean button3 = false;
	private boolean pointDat;
	private XYDataItem currentPoint;
	private ChartPanel chartPanel;
	private ValueMarker domainMarker = new ValueMarker(0);
	private ValueMarker rangeMarker = new ValueMarker(0);
	private KMeans dataPoints;
	CircleDrawer circleDrawer = new CircleDrawer(Color.red, new BasicStroke(
			1.0F), null);
	XYDrawableAnnotation circleAnnotation;

	public MovePointMouseListener(ChartPanel chartPanel, KMeans dataPoints) {
		this.dataPoints = dataPoints;
		domainMarker.setPaint(Color.gray);
		rangeMarker.setPaint(Color.gray);
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		e.consume();
		if (e.getButton() == MouseEvent.BUTTON3) {
			pointDat = false;
			button3 = true;
			Point2D p = chartPanel.translateScreenToJava2D(e.getPoint());
			Rectangle2D plotArea = chartPanel.getScreenDataArea();
			XYPlot plot = chartPanel.getChart().getXYPlot(); // your plot
			double chartX = plot.getDomainAxis().java2DToValue(
					KMeans.roundDouble(p.getX(), 2), plotArea,
					plot.getDomainAxisEdge());
			double chartY = plot.getRangeAxis().java2DToValue(
					KMeans.roundDouble(p.getY(), 2), plotArea,
					plot.getRangeAxisEdge());
			ValueAxis xAxis = plot.getDomainAxis();
			ValueAxis yAxis = plot.getRangeAxis();
			if (chartX >= xAxis.getLowerBound()
					&& chartX <= xAxis.getUpperBound()
					&& chartY >= yAxis.getLowerBound()
					&& chartY <= yAxis.getUpperBound()) {

				chartX = KMeans.roundD(chartX);
				chartY = KMeans.roundD(chartY);

				@SuppressWarnings("unchecked")
				List<XYDataItem> dataList1 = dataPoints.getData().getItems();
				currentPoint = new XYDataItem(chartX, chartY);
				Point2D.Double point;
				if ((point = containPoint(dataList1, new Point2D.Double(
						currentPoint.getXValue(), currentPoint.getYValue()))) != null) {
					pointDat = true;
					if (circleAnnotation != null)
						plot.removeAnnotation(circleAnnotation);
					circleAnnotation = new XYDrawableAnnotation(point.getX(),
							point.getY(), 11D, 11D, circleDrawer);
					plot.addAnnotation(circleAnnotation);
					currentPoint = new XYDataItem(point.getX(), point.getY());

				} else {
					currentPoint = null;
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */

	public void mouseReleased(MouseEvent e) {
		e.consume();
		if (e.getButton() == MouseEvent.BUTTON3) {
			XYPlot plot = chartPanel.getChart().getXYPlot();
			plot.removeDomainMarker(domainMarker);
			plot.removeRangeMarker(rangeMarker);
			if (circleAnnotation != null)
				plot.removeAnnotation(circleAnnotation);
			button3 = false;
			if (currentPoint != null) {

				Point2D p = chartPanel.translateScreenToJava2D(e.getPoint());

				Rectangle2D plotArea = chartPanel.getScreenDataArea();
				double chartX = plot.getDomainAxis().java2DToValue(p.getX(),
						plotArea, plot.getDomainAxisEdge());
				double chartY = plot.getRangeAxis().java2DToValue(p.getY(),
						plotArea, plot.getRangeAxisEdge());

				ValueAxis xAxis = plot.getDomainAxis();
				ValueAxis yAxis = plot.getRangeAxis();
				if (chartX >= xAxis.getLowerBound()
						&& chartX <= xAxis.getUpperBound()
						&& chartY >= yAxis.getLowerBound()
						&& chartY <= yAxis.getUpperBound()) {
					chartX = KMeans.roundD(chartX);
					chartY = KMeans.roundD(chartY);
					if (pointDat) {
						double[][] cp = dataPoints.getData().toArray();
						dataPoints.getData().clear();
						for (int i = 0; i < cp[0].length; i++) {
							if (cp[0][i] == currentPoint.getXValue()
									&& cp[1][i] == currentPoint.getYValue()) {
								dataPoints.getData().add(chartX, chartY);
							} else
								dataPoints.getData().add(cp[0][i], cp[1][i]);
						}
					}

				}

			}
		}
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
		if (button3) {

			Point2D p = chartPanel.translateScreenToJava2D(e.getPoint());

			Rectangle2D plotArea = chartPanel.getScreenDataArea();
			XYPlot plot = chartPanel.getChart().getXYPlot();// your plot
			double chartX = plot.getDomainAxis().java2DToValue(p.getX(),
					plotArea, plot.getDomainAxisEdge());
			double chartY = plot.getRangeAxis().java2DToValue(p.getY(),
					plotArea, plot.getRangeAxisEdge());
			ValueAxis xAxis = plot.getDomainAxis();
			ValueAxis yAxis = plot.getRangeAxis();
			if (chartX >= xAxis.getLowerBound()
					&& chartX <= xAxis.getUpperBound()
					&& chartY >= yAxis.getLowerBound()
					&& chartY <= yAxis.getUpperBound()) {

				plot.removeDomainMarker(domainMarker);
				plot.removeRangeMarker(rangeMarker);

				domainMarker.setValue(chartX);
				rangeMarker.setValue(chartY);

				plot.addDomainMarker(domainMarker);
				plot.addRangeMarker(rangeMarker);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public Point2D.Double containPoint(List<XYDataItem> list,
			Point2D.Double point) {
		for (XYDataItem xyItem : list) {
			if (point.distance(new Point2D.Double(xyItem.getXValue(), xyItem
					.getYValue())) < 0.2)
				return new Point2D.Double(xyItem.getXValue(),
						xyItem.getYValue());
		}
		return null;
	}

}
