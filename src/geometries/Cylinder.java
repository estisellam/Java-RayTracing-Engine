package geometries;

import static primitives.Util.*;

import primitives.*;

/** Cylinder class represents Euclidean cylinder in 3D Cartesian coordinate
 * system represented by its central ray beginning at its base, its radius and
 * its height
 * @author Dan */
public class Cylinder extends Tube {
   /** The height of the cylinder */
   private final double height;

   /** Cylinder constructor given its central ray beginning at its base, radius and
    * height
    * @param radius of the cylinder
    * @param ray    central axis of the cylinder starting at the 1st base
    * @param height of the cylinder */
   public Cylinder(double radius, Ray ray, double height) {
      super(radius, ray);
      this.height = height;
   }

   /** Height getter
    * @return height value */
   double getHeight() { return height; }

   @Override
   public Vector getNormal(Point point) {
      Point  o = axis.origin();
      Vector v = axis.direction();

      Vector u;
      // projection of P-O on the ray:
      try {
         u = point.subtract(o);
      } catch (IllegalArgumentException ignore) { // P = O
         return v;
      }

      double t = alignZero(u.dotProduct(v));
      // if the point is at a base
      return t == 0 || isZero(height - t) ? v //
            : point.subtract(axis.getPoint(t)).normalize();
   }
}
