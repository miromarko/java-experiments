package alg;

import java.awt.geom.Point2D;

public class KMeansUtils {
	private static double[] variances(double[] data) {
		double sum = 0;
		double sumsquare = 0;
		int len = data.length;
		double mean = 0;
		double[] variances = new double[len];
		for (int i = 0; i < len; ++i) {
			sum += data[i];
			sumsquare += data[i] * data[i];
			mean = sum / (i + 1);
			variances[i] = sumsquare / (i + 1) - mean * mean;
		}
		return variances;
	}

	public static double getVariance(double[] data) {

		// double[] data = new
		// double[]{0.1,0.2,0.23,0.01,2.01,2.03,2.5,4.04,4,4.3,4.32};
		int len = data.length;
		double[] variances = variances(data);
		return variances[len - 1];
	}

	public double getDistance(Point2D p1, Point2D p2) {
		return Math.abs(p1.distance(p2));
	}
}
