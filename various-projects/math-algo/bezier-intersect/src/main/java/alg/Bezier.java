/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package alg;

import java.util.Vector;

import org.jfree.data.xy.XYSeries;

/**
 * 
 */
public class Bezier {
	private int div = 1000;
	private static Vector<Bezier> temp; // pt subdiv recursiva
	private XYSeries controlPoints;
	private XYSeries bezierCurve;

	public Bezier(String bezierName) {
		controlPoints = new XYSeries(bezierName + " Control Points", false);
		bezierCurve = new XYSeries(bezierName + " Curve", false);
	}

	public XYSeries getControlPoints() {
		return controlPoints;
	}

	public XYSeries getBezierCurve() {
		if (controlPoints.toArray()[0].length > 1) {
			bezierCurve.clear();
			double t;
			for (int i = 0; i < div; i++) {
				t = i * ((double) 1 / div);
				double[] XY = deCast(controlPoints.toArray(), t);
				bezierCurve.add(XY[0], XY[1]);
			}
		}
		return bezierCurve;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}

	private static double[] deCast(double[][] b, double t) {

		int n = b[0].length - 1;
		for (int r = 1; r <= n; r++) {
			for (int i = 0; i <= (n - r); i++) {
				b[0][i] = (1 - t) * b[0][i] + t * b[0][i + 1];
				b[1][i] = (1 - t) * b[1][i] + t * b[1][i + 1];
			}
		}
		double[] rez = new double[2];
		rez[0] = b[0][0];
		rez[1] = b[1][0];
		return rez;

	}

	public static Bezier[] subdiv(Bezier bz, double alpha) {
		double[][] b = bz.getControlPoints().toArray();
		double[][] d = new double[b.length][b[0].length];
		double[][] c = new double[b.length][b[0].length];
		for (int k = 0; k < b.length; k++) {
			System.arraycopy(b[k], 0, d[k], 0, b[k].length);
			System.arraycopy(b[k], 0, c[k], 0, b[k].length);
			reverse(c[k]);
		}

		int n = b[0].length - 1;
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i <= n - k; i++) {
				d[0][i] = (1 - alpha) * d[0][i] + alpha * d[0][i + 1];
				d[1][i] = (1 - alpha) * d[1][i] + alpha * d[1][i + 1];

				c[0][i] = alpha * c[0][i] + (1 - alpha) * c[0][i + 1];
				c[1][i] = alpha * c[1][i] + (1 - alpha) * c[1][i + 1];
			}
		}

		Bezier[] ret = new Bezier[2];
		Bezier bezier1 = new Bezier(bz.getControlPoints().getDescription()
				+ "s1");
		Bezier bezier2 = new Bezier(bz.getControlPoints().getDescription()
				+ "s2");
		ret[0] = bezier1;
		ret[1] = bezier2;
		for (int i = 0; i < n + 1; i++) {
			bezier1.getControlPoints().add(d[0][i], d[1][i]);
			bezier2.getControlPoints().add(c[0][i], c[1][i]);
		}
		return ret;
	}

	public Bezier[] subdiv(double alpha) {
		double[][] b = getControlPoints().toArray();
		double[][] d = new double[b.length][b[0].length];
		double[][] c = new double[b.length][b[0].length];
		for (int k = 0; k < b.length; k++) {
			System.arraycopy(b[k], 0, d[k], 0, b[k].length);
			System.arraycopy(b[k], 0, c[k], 0, b[k].length);
			reverse(c[k]);
		}

		int n = b[0].length - 1;
		for (int k = 1; k <= n; k++) {
			for (int i = 0; i <= n - k; i++) {
				d[0][i] = (1 - alpha) * d[0][i] + alpha * d[0][i + 1];
				d[1][i] = (1 - alpha) * d[1][i] + alpha * d[1][i + 1];

				c[0][i] = alpha * c[0][i] + (1 - alpha) * c[0][i + 1];
				c[1][i] = alpha * c[1][i] + (1 - alpha) * c[1][i + 1];
			}
		}

		Bezier[] ret = new Bezier[2];
		Bezier bezier1 = new Bezier(getControlPoints().getDescription() + "s1");
		Bezier bezier2 = new Bezier(getControlPoints().getDescription() + "s2");
		ret[0] = bezier1;
		ret[1] = bezier2;
		for (int i = 0; i < n + 1; i++) {
			bezier1.getControlPoints().add(d[0][i], d[1][i]);
			bezier2.getControlPoints().add(c[0][i], c[1][i]);
		}
		return ret;
	}

	public static Vector<Bezier> subdivizare(Bezier bz, int nr, int nivel) {
		if (nivel == 0)
			temp = new Vector<Bezier>(nr);
		Bezier[] ccdd = subdiv(bz, 0.5);
		Bezier cc = ccdd[0];
		Bezier dd = ccdd[1];
		if (nivel < nr) {
			nivel++;

			subdivizare(cc, nr, nivel);
			subdivizare(dd, nr, nivel);
		} else {
			temp.add(cc);
			temp.add(dd);
		}

		return temp;

	}

	public static void reverse(double[] c) {
		int left = 0; // index of leftmost element
		int right = c.length - 1; // index of rightmost element

		while (left < right) {
			// exchange the left and right elements
			double temp = c[left];
			c[left] = c[right];
			c[right] = temp;

			// move the bounds toward the center
			left++;
			right--;
		}
	}// endmethod reverse

	public static final double roundDouble(double d, int places) {
		if(d==0.0)
			return d;
		return Math.round(d * Math.pow(10, places))
				/ Math.pow(10, places);
	}
	 public static final double roundD(double d) {
	        return roundDouble(d, 1);
	    }

}
