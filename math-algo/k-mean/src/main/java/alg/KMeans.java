/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package alg;

import gui.Printer;

import java.awt.geom.Point2D;
import java.util.HashMap;

import org.jfree.data.xy.XYSeries;

/**
 * 
 */
public class KMeans {
	private XYSeries data;
	private XYSeries centroideInitiale;
	private HashMap<Point2D, XYSeries> clusters;
	private Point2D[] centroids;
	private int k;

	public KMeans() {
		data = new XYSeries("Date initiale", false);
		centroideInitiale = new XYSeries("Centroide initiale", false);
		clusters = new HashMap<Point2D, XYSeries>();
	}

	public XYSeries getData() {
		return data;
	}

	public XYSeries getCentroideInitiale() {
		return centroideInitiale;
	}

	public Point2D[] getCentroids() {
		return centroids;
	}

	public HashMap<Point2D, XYSeries> getClusters() {
		return clusters;
	}

	public static final double roundDouble(double d, int places) {
		if (d == 0.0)
			return d;
		return Math.round(d * Math.pow(10, places))
				/ Math.pow(10, places);
	}

	public static final double roundD(double d) {
		return roundDouble(d, 1);
	}

	// K-Means Alg.

	public void startAlg() {
		clusters.clear();
		double[][] xyPoints = data.toArray();
		int len = xyPoints[0].length;
		Printer.print("len = " + len);
		double[][] xyCentroideInitiale = centroideInitiale.toArray();
		k = xyCentroideInitiale[0].length;
		if (len > 2 && k > 0) {
			int pasi = 0;
			Printer.print("k = " + k);
			centroids = new Point2D.Double[k];
			// calculare centroide initiale
			for (short i = 0; i < k; i++) {
				Point2D.Double p = new Point2D.Double(
						xyCentroideInitiale[0][i], xyCentroideInitiale[1][i]);
				Printer.print("Initial m" + i + "= " + p.getX() + " , "
						+ p.getY());
				clusters.put(p, new XYSeries("cluster", false));
				centroids[i] = p;
			}

			do {
				Printer.print("Pas " + pasi++);
				impartireInClustere(xyPoints);
				calculareCentroide();
			} while (stopKMeans());
		}
	}

	// conditia de oprire
	private boolean stopKMeans() {
		Object[] centrNext = clusters.keySet().toArray();
		boolean centrMiscat = false;
		for (int i = 0; i < centrNext.length; i++) {
			if (roundDouble(
					Math.abs(centroids[i].distance((Point2D) centrNext[i])), 2) > 0) {
				centrMiscat = true;
			}
			centroids[i] = (Point2D) centrNext[i];
		}
		return centrMiscat;

	}

	// pas 1 impartire in clustere
	private void impartireInClustere(double[][] xyP) {
		Object[] keys = clusters.keySet().toArray();
		Object[] values = clusters.values().toArray();
		for (int i = 0; i < clusters.size(); i++) {
			((XYSeries) values[i]).clear();
		}
		for (int i = 0; i < xyP[0].length; i++) {
			int c = 0; // cluster initial
			// distanta initiala la m din primul cluster;
			double d = Math.abs(((Point2D) keys[0]).distance(xyP[0][i],
					xyP[1][i]));
			for (int j = 1; j < clusters.size(); j++) {
				Point2D m = (Point2D) keys[j];
				double dnext = Math.abs(m.distance(xyP[0][i], xyP[1][i]));
				if (dnext < d) {
					d = dnext;
					c = j;
				}
			}
			((XYSeries) values[c]).add(xyP[0][i], xyP[1][i]);
		}
	}

	// pas 2 calculare centroide
	private double calcCentroide(double[] d) {
		int len = d.length;
		if (len == 1) {
			return d[0];
		} else if (len == 0) {
			return 0;
		} else {
			double s = 0;
			for (double val : d) {
				s += val;
			}
			return s / len;
		}
	}

	private void calculareCentroide() {
		Object[] keys = clusters.keySet().toArray();
		Object[] values = clusters.values().toArray();
		for (int i = 0; i < clusters.size(); i++) {
			Point2D m = (Point2D) keys[i];
			double[][] xyCluster = ((XYSeries) values[i]).toArray();
			m.setLocation(calcCentroide(xyCluster[0]),
					calcCentroide(xyCluster[1]));
			Printer.print("m" + i + "= " + m.getX() + " , " + m.getY());
		}
	}

}
