/**
 * @author	Miroslav MARKO
 * @date	Feb 9, 2010
 */
package gui;

import graph.CircleDrawer;
import graph.DrawMouseListener;
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
	private JToggleButton btnDraw = null;
	private DrawMouseListener drawMouseListener;
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
	private JButton btnKMeans;
	CircleDrawer circleDrawer = new CircleDrawer(Color.MAGENTA,
			new BasicStroke(2.0F), null);
	private ArrayList<XYDrawableAnnotation> cluseterMeans;

	/**
	 * This method initializes mnuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private JMenuBar getMnuBar() {
		if (mnuBar == null) {
			mnuBar = new JMenuBar();
			mnuBar.add(getBtnDraw());
			mnuBar.add(getBtnKMeans());
			mnuBar.add(infoLabel);
			mnuBar.add(Box.createHorizontalGlue());
			JMenu graphOptions = new JMenu("Graph Options");
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

	private JButton getBtnKMeans() {

		if (btnKMeans == null) {
			btnKMeans = new JButton("K-Means");
			btnKMeans.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					getBtnDraw().setSelected(false); // finish drawing

					removeIntersectMarkers();
					XYDrawableAnnotation circleAnnotation;
					cluseterMeans.clear();
					cluseterMeans.clear();
					kMeans.startAlg();
					
					Object[] serii = kMeans.getClusters().values().toArray();
					dataset.removeAllSeries();
					for(int i=0;i<serii.length;i++){
						dataset.addSeries((XYSeries)serii[i]);
					}

					for (Point2D p : kMeans.getCentroids()) {
						circleAnnotation = new XYDrawableAnnotation(p.getX(), p
								.getY(), 13D, 13D, circleDrawer);
						cluseterMeans.add(circleAnnotation);
						getChart().getXYPlot().addAnnotation(circleAnnotation);

					}

				}
			});
		}
		return btnKMeans;
	}

	private JToggleButton getBtnDraw() {
		if (btnDraw == null) {
			btnDraw = new JToggleButton("Deseneaza puncte");
			btnDraw.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					removeIntersectMarkers();
					if (!btnDraw.isSelected()) {
						getChartPanel()
								.addMouseListener(movePointMouseListener);
						getChartPanel().addMouseMotionListener(
								movePointMouseListener);
					}

					if (btnDraw.isSelected()) {
						kMeans.getData().clear();
						getChartPanel()
								.addChartMouseListener(drawMouseListener);
						getChartPanel().addMouseMotionListener(
								pointHighlineMouseListener);
						getChartPanel().removeMouseListener(
								movePointMouseListener);
						getChartPanel().removeMouseMotionListener(
								movePointMouseListener);
					} else {
						getChartPanel().removeChartMouseListener(
								drawMouseListener);
						getChartPanel().removeMouseMotionListener(
								pointHighlineMouseListener);
						pointHighlineMouseListener.removeMarker();

					}

				}
			});
		}
		return btnDraw;
	}

	private JFreeChart getChart() {
		if (chart == null) {
			chart = ChartFactory.createScatterPlot(
					"KMeans", "X", "Y", dataset,
					PlotOrientation.VERTICAL, true, true, false);

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

			for(int i=0;i<100;i++){
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
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
		cluseterMeans = new ArrayList<XYDrawableAnnotation>();
		infoLabel = new JLabel("");
		kMeans = new KMeans();
		initDataSet();
		getXYPlot(); // initialize xyPlot from getter
		drawMouseListener = new DrawMouseListener(getChartPanel(), kMeans);
		pointHighlineMouseListener = new PointHighlineMouseListener(
				getChartPanel(), infoLabel);
		this.setSize(600, 540);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(getMnuBar());
		this.setTitle(" Proiect Data Mining | K-Means Alg. :: Miroslav MARKO");
		getContentPane().setLayout(new BorderLayout());
		lblPoints = new JLabel("");
		getContentPane().add(getChartPanel(), BorderLayout.CENTER);
		getContentPane().add(lblPoints, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void eqScale() {
		double min = 1;
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

	public void removeIntersectMarkers() {
		for (XYAnnotation an : cluseterMeans) {
			getChart().getXYPlot().removeAnnotation(an);
		}
		cluseterMeans.clear();
		lblPoints.setText("");
	}

}
