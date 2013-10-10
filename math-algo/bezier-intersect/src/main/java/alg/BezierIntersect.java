/**
 * @author	Miroslav MARKO
 * @date	Feb 10, 2010
 */
package alg;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 */
public class BezierIntersect {

	public static SortedSet<Point2D.Double> intersect(Bezier c1, Bezier c2) {

		SortedSet<Point2D.Double> intersectPoints = new TreeSet<Point2D.Double>(
				new Comparator<Point2D.Double>() {

					public int compare(Point2D.Double o1, Point2D.Double o2) {
						if (o1.distance(o2)<0.11)
							return 0;
						if (o1.getX() < o2.getX())
							return -1;
						else
							return 1;
					}
				});
		if (c1.getControlPoints().toArray().length < 2
				|| c2.getControlPoints().toArray().length < 2)
			return intersectPoints;

		LinkedList<Bezier> curbe1 = new LinkedList<Bezier>();
		LinkedList<Bezier> curbe1temp = new LinkedList<Bezier>();
		curbe1.add(c1);

		LinkedList<Bezier> curbe2 = new LinkedList<Bezier>();
		LinkedList<Bezier> curbe2temp = new LinkedList<Bezier>();
		curbe2.add(c2);
		while (!curbe1.isEmpty() && !curbe2.isEmpty()) {
			// verific cond intersectie
			curbe1temp.clear();
			curbe2temp.clear();

			for (Bezier curba2 : curbe2) {
				for (Bezier curba1 : curbe1) {
					if (intersectCond(curba1, curba2)) {
						if (!curbe1temp.contains(curba1))
							curbe1temp.add(curba1);
						if (!curbe2temp.contains(curba2))
							curbe2temp.add(curba2);

					}

				}
			}
			curbe1.clear();
			curbe2.clear();
			curbe1.addAll(curbe1temp);
			curbe2.addAll(curbe2temp);
			// subdivizez
			curbe1temp.clear();
			curbe2temp.clear();
			for (Bezier curba1 : curbe1) {
				Point2D.Double pt = rectangle2Point(polygon2Rectangle(curba1));
				if (pt != null) {
					intersectPoints.add(pt);
				} else {
					// Bezier[] bz = curba1.subdiv(0.5);
					// curbe1temp.add(bz[0]);
					// curbe1temp.add(bz[1]);
					curbe1temp.addAll(Bezier.subdivizare(curba1, 9, 0));
				}

			}
			for (Bezier curba2 : curbe2) {
				Point2D.Double pt = rectangle2Point(polygon2Rectangle(curba2));
				if (pt != null) {
					intersectPoints.add(pt);
				} else {
					curbe2temp.addAll(Bezier.subdivizare(curba2, 9, 0));
					// Bezier[] bz = curba2.subdiv(0.5);
					// curbe2temp.add(bz[0]);
					// curbe2temp.add(bz[1]);
				}

			}
			curbe1.clear();
			curbe2.clear();
			curbe1.addAll(curbe1temp);
			curbe2.addAll(curbe2temp);
			// ------------------
		}
		
		return intersectPoints;

	}

	public static Rectangle2D.Double polygon2Rectangle(Bezier bezier) {
		double[][] controlP = bezier.getControlPoints().toArray();
		Arrays.sort(controlP[0]);
		Arrays.sort(controlP[1]);
		double xmin = controlP[0][0];
		double ymax = controlP[1][controlP[1].length - 1];
		double w = controlP[0][controlP[0].length - 1] - controlP[0][0];
		double h = controlP[1][controlP[1].length - 1] - controlP[1][0];
		Rectangle2D.Double rectange = new Rectangle2D.Double(xmin, ymax, w, h);

		return rectange;

	}

	public static Point2D.Double rectangle2Point(Rectangle2D.Double rectangle) {
		if (rectangle.getWidth() < 0.1 && rectangle.getHeight() < 0.1) {
			return new Point2D.Double(Bezier.roundD(rectangle.getX()),
					Bezier.roundD(rectangle.getY()));
		} else
			return null;
	}

	public static String printIntersectionPoints(
			Collection<Point2D.Double> points) {
		StringBuilder stP = new StringBuilder();
		for (Point2D.Double point : points) {
			stP.append("[x,y]=" + point.getX() + " , " + point.getY() + "  \n");
		}
		System.out.println(stP);
		return stP.toString();

	}

	public static boolean intersectCond(Bezier b1, Bezier b2) {
		Rectangle2D.Double rec1 = polygon2Rectangle(b1);
		Rectangle2D.Double rec2 = polygon2Rectangle(b2);
		return rec1.intersects(rec2);

	}


}
