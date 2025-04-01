package geometries;

import java.util.List;

import static primitives.Util.*;

import primitives.*;

/**
 * Plane class represents Euclidean plane in 3D Cartesian coordinate system
 * represented by a point in the plane and normalized vector orthogonal to the
 * plane (the normal)
 * @author Dan
 */
public class Plane extends Geometry {
   /** A point in the plane - Q&#x2080; */
   private final Point  q;
   /** Normal vector to the plane - n&#x302; */
   private final Vector n;

   /**
    * Plane constructor given three points in the plane. The constructor will
    * calculate normal to the plane and take the 1st point as a point in the plane
    * If some of points' pair are the same or all the points are in the same line -
    * there will be an exception
    * @param  p1                       1st point
    * @param  p2                       2nd point
    * @param  p3                       3rd point
    * @throws IllegalArgumentException if the points are on the same line by any
    *                                  constellation
    */
   public Plane(Point p1, Point p2, Point p3) {
      // if p1=p2 or p1=p3 - an exception will be thrown
      Vector v1 = p1.subtract(p2);
      Vector v2 = p1.subtract(p3);

      // if the points are in the same line - X-product will throw an exception
      n = v1.crossProduct(v2).normalize();
      q = p1;
   }

   /**
    * Plane constructor given a reference point in the plane and a vector
    * orthogonal to the plane. The vector will be normalized (made a unit vector)
    * @param p a point in the plane
    * @param n normal vector
    */
   public Plane(Point p, Vector n) {
      this.n = n.normalize();
      q      = p;
   }

   /**
    * Reference point getter
    * @return reference point
    */
   public Point getPoint() { return q; }

   @Override
   public Vector getNormal(Point p) { return n; }

}
