/**
 * @author	Miroslav MARKO
 * @date	Feb 9, 2010
 */
package gui;

import graph.CircleDrawer;
import graph.DrawBezierMouseListener;
import graph.MovePointMouseListener;
import graph.PointHighlineMouseListener;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.MenuElement;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import alg.KMeans;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar mnuBar = null;
	private JCheckBoxMenuItem mchkControlPolygon = null;
	private JToggleButton btnDrawBezier1 = null;
	private JToggleButton btnDrawBezier2 = null;
	private DrawBezierMouseListener drawBezierMouseListener1;
	private DrawBezierMouseListener drawBezierMouseListener2;
	private PointHighlineMouseListener pointHighlineMouseListener;
	private MovePointMouseListener movePointMouseListener;
	private ChartPanel chartPanel;
	private JFreeChart chart;
	private XYSeriesCollection dataset;
	private XYPlot xyplot;
	private XYLineAndShapeRenderer xylineandshaperenderer;
	private JLabel infoLabel;
	private JLabel lblPoints;
	private KMeans kMeans;
	private JButton btnBezierIntersect;
	CircleDrawer circleDrawer = new CircleDrawer(Color.MAGENTA,
			new BasicStroke(2.0F), null);
	private ArrayList<XYDrawableAnnotation> clustersMeans;

	/**
	 * This method initializes mnuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getMnuBar() {
		if (mnuBar == null) {
			mnuBar = new JMenuBar();
			mnuBar.add(getBtnDrawBezier1());
			mnuBar.add(getBtnDrawBezier2());
			mnuBar.add(getBtnBezierIntersect());
			mnuBar.add(infoLabel);
			mnuBar.add(Box.createHorizontalGlue());
			JMenu graphOptions = new JMenu("Graph Options");
			graphOptions.add(getMchkControlPolygon());
			mnuBar.add(graphOptions);
			mnuBar.add(getChartPanel().getPopupMenu());
			JPopupMenu popUp = getChartPanel().getPopupMenu();
			for (MenuElement mElement : popUp.getSubElements()) {
				graphOptions.add((JMenuItem) mElement);

			}
		}
		return mnuBar;
	}

	private ChartPanel getChartPanel() {
		if (chartPanel == null) {
			chartPanel = new ChartPanel(getChart());
			// disamble zoom
			chartPanel.setDomainZoomable(false);
			chartPanel.setRangeZoomable(false);
			chartPanel.setBounds(new Rectangle(300, 300));

		}
		return chartPanel;
	}

	private JButton getBtnBezierIntersect() {

		if (btnBezierIntersect == null) {
			btnBezierIntersect = new JButton("K-Means");
			btnBezierIntersect.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					getBtnDrawBezier1().setSelected(false); // finish drawing
															// bezir
					getBtnDrawBezier2().setSelected(false);

					removeIntersectMarkers();
					XYDrawableAnnotation circleAnnotation;
					clustersMeans.clear();
					clustersMeans.clear();
					kMeans.startAlg();

					Object[] serii = kMeans.getClusters().values().toArray();
					dataset.removeAllSeries();
					for (int i = 0; i < serii.length; i++) {
						dataset.addSeries((XYSeries) serii[i]);
					}

					for (Point2D p : kMeans.getCentroids()) {
						circleAnnotation = new XYDrawableAnnotation(p.getX(), p
								.getY(), 13D, 13D, circleDrawer);
						clustersMeans.add(circleAnnotation);
						getChart().getXYPlot().addAnnotation(circleAnnotation);

					}

				}
			});
		}
		return btnBezierIntersect;
	}

	private JToggleButton getBtnDrawBezier1() {
		if (btnDrawBezier1 == null) {
			btnDrawBezier1 = new JToggleButton("Date Initiale");
			btnDrawBezier1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					removeIntersectMarkers();
					if (!btnDrawBezier1.isSelected()
							&& !btnDrawBezier2.isSelected()) {
						getChartPanel()
								.addMouseListener(movePointMouseListener);
						getChartPanel().addMouseMotionListener(
								movePointMouseListener);
					}

					if (btnDrawBezier1.isSelected()) {
						kMeans.getData().clear();
						if (btnDrawBezier2.isSelected()) {
							btnDrawBezier2.setSelected(false);
						}
						getChartPanel().addChartMouseListener(
								drawBezierMouseListener1);
						getChartPanel().addMouseMotionListener(
								pointHighlineMouseListener);
						getChartPanel().removeMouseListener(
								movePointMouseListener);
						getChartPanel().removeMouseMotionListener(
								movePointMouseListener);
					} else {
						getChartPanel().removeChartMouseListener(
								drawBezierMouseListener1);
						getChartPanel().removeMouseMotionListener(
								pointHighlineMouseListener);
						pointHighlineMouseListener.removeMarker();

					}

				}
			});
		}
		return btnDrawBezier1;
	}

	private JToggleButton getBtnDrawBezier2() {
		if (btnDrawBezier2 == null) {
			btnDrawBezier2 = new JToggleButton("Centroide");
			btnDrawBezier2.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					removeIntersectMarkers();
					if (!btnDrawBezier1.isSelected()
							&& !btnDrawBezier2.isSelected()) {
						getChartPanel()
								.addMouseListener(movePointMouseListener);
						getChartPanel().addMouseMotionListener(
								movePointMouseListener);
					}
					if (btnDrawBezier2.isSelected()) {
						if (btnDrawBezier1.isSelected()) {
							btnDrawBezier1.setSelected(false);
						}
						kMeans.getCentroideInitiale().clear();
						getChartPanel().addChartMouseListener(
								drawBezierMouseListener2);
						getChartPanel().addMouseMotionListener(
								pointHighlineMouseListener);
						getChartPanel().removeMouseListener(
								movePointMouseListener);
						getChartPanel().removeMouseMotionListener(
								movePointMouseListener);
					} else {
						getChartPanel().removeChartMouseListener(
								drawBezierMouseListener2);
						getChartPanel().removeMouseMotionListener(
								pointHighlineMouseListener);
						pointHighlineMouseListener.removeMarker();
					}

				}
			});
		}
		return btnDrawBezier2;
	}

	private JFreeChart getChart() {
		if (chart == null) {
			chart = ChartFactory.createScatterPlot("K-Means", "X", "Y",
					dataset, PlotOrientation.VERTICAL, true, true, false);

		}
		return chart;
	}

	private XYPlot getXYPlot() {
		if (xyplot == null) {
			xyplot = (org.jfree.chart.plot.XYPlot) getChart().getPlot();
			eqScale();
			xyplot.setDomainCrosshairVisible(false);
			xyplot.setRangeCrosshairVisible(false);

			xyplot.setDomainPannable(false);
			xyplot.setRangePannable(false);

			xylineandshaperenderer = new XYLineAndShapeRenderer();
			for (int i = 0; i < 1000; i++) {
				xylineandshaperenderer.setSeriesLinesVisible(i, false);
				xylineandshaperenderer.setSeriesShapesVisible(i, true);
			}

			xylineandshaperenderer
					.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
			xylineandshaperenderer.setDefaultEntityRadius(6);
			xylineandshaperenderer.setSeriesStroke(2, new BasicStroke(2.5F, 1,
					1));
			xylineandshaperenderer.setSeriesStroke(3, new BasicStroke(2.5F, 1,
					1));
			xyplot.setRenderer(xylineandshaperenderer);
		}
		return xyplot;
	}

	/**
	 * This method initializes mPreferences
	 * 
	 * @return javax.swing.JMenu
	 */

	/**
	 * This method initializes mchkControlPolygon
	 * 
	 * @return javax.swing.JCheckBoxMenuItem
	 */
	private JCheckBoxMenuItem getMchkControlPolygon() {
		if (mchkControlPolygon == null) {
			mchkControlPolygon = new JCheckBoxMenuItem();
			mchkControlPolygon.setText("Control Lines Visible");
			mchkControlPolygon.setSelected(false);
			setControlPolygonVisible(false);
			mchkControlPolygon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (mchkControlPolygon.isSelected()) {
						setControlPolygonVisible(true);
					} else {
						setControlPolygonVisible(false);
					}
				}
			});
		}
		return mchkControlPolygon;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
	}

	private void initDataSet() {
		dataset = new XYSeriesCollection();
		dataset.addSeries(kMeans.getData());
		dataset.addSeries(kMeans.getCentroideInitiale());
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		clustersMeans = new ArrayList<XYDrawableAnnotation>();
		infoLabel = new JLabel("");
		kMeans = new KMeans();
		initDataSet();
		getXYPlot(); // initialize xyPlot from getter
		drawBezierMouseListener1 = new DrawBezierMouseListener(getChartPanel(),
				kMeans.getData());
		drawBezierMouseListener2 = new DrawBezierMouseListener(getChartPanel(),
				kMeans.getCentroideInitiale());
		pointHighlineMouseListener = new PointHighlineMouseListener(
				getChartPanel(), infoLabel);
		movePointMouseListener = new MovePointMouseListener(chartPanel, kMeans);
		this.setSize(600, 540);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(getMnuBar());
		this.setTitle(" Proiect Data Mining K-Means Alg.:: Miroslav MARKO");
		getContentPane().setLayout(new BorderLayout());
		lblPoints = new JLabel("");
		getContentPane().add(getChartPanel(), BorderLayout.CENTER);
		getContentPane().add(lblPoints, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void eqScale() {
		double min = -10;
		double max = 10;
		// min = controlPoints.getMinX();
		// if (controlPoints.getMinY() < min) {
		// min = controlPoints.getMinY();
		// }
		// max = controlPoints.getMaxX();
		// if (controlPoints.getMaxY() > max) {
		// max = controlPoints.getMaxY();
		// }
		min = min - 1;
		max = max + 1;
		NumberAxis xAxis = (NumberAxis) xyplot.getDomainAxis();
		NumberAxis yAxis = (NumberAxis) xyplot.getRangeAxis();
		xAxis.setAutoRange(false);
		yAxis.setAutoRange(false);
		xAxis.setLowerBound(min);
		xAxis.setUpperBound(max);
		yAxis.setLowerBound(min);
		yAxis.setUpperBound(max);

	}

	private void setControlPolygonVisible(boolean visible) {
		for (int i = 0; i < 1000; i++) {
			xylineandshaperenderer.setSeriesLinesVisible(i, visible);
		}
	}

	public void removeIntersectMarkers() {
		for (XYAnnotation an : clustersMeans) {
			getChart().getXYPlot().removeAnnotation(an);
		}
		clustersMeans.clear();
		lblPoints.setText("");
		
		dataset.removeAllSeries();
		dataset.addSeries(kMeans.getData());
		dataset.addSeries(kMeans.getCentroideInitiale());
		
	}

}
