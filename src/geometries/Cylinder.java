package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent a cylinder, which is a tube with a finite height.
 *  @author esti
 */
public class Cylinder extends Tube {
   private final double height; // The height of the cylinder

   /**
    * Constructor with ray, radius, and height.
    *
    * @param axisRay The central axis of the cylinder.
    * @param radius  The radius of the cylinder.
    * @param height  The height of the cylinder.
    */
   public Cylinder(Ray axisRay, double radius, double height) {
      super(radius, axisRay);
      this.height = height;
   }

   /**
    * Getter for the height of the cylinder.
    *
    * @return The height of the cylinder.
    */
   public double getHeight() {
      return height;
   }

   /**
    * Get the normal vector at a given point on the cylinder.
    * @param point point to get the normal at
    * @return
    */
   @Override
   public Vector getNormal(Point point) {
      Point p0 = mainAxis.getHead(); // Base center
      Vector dir = mainAxis.getDirection();

      double t;
      try {
         Vector v = point.subtract(p0); // Vector from base center to the point
         t = alignZero(v.dotProduct(dir));
      } catch (IllegalArgumentException e) {
         return dir.scale(-1); // Point is at the base center
      }

      if (isZero(t)) // Point is on the bottom base
         return dir.scale(-1);
      if (isZero(t - height))
         return dir;

      // Point is on the side surface
      Point o = mainAxis.getPoint(t);
      return point.subtract(o).normalize();
   }

 
   @Override
   public List<Point> findIntersections(Ray ray) {
      Point p = ray.getHead();
      Vector v = ray.getDirection();
      Point p0 = mainAxis.getHead();
      Vector va = mainAxis.getDirection();

      List<Point> result = new LinkedList<>();

      // 1. Intersections with the tube's side
      List<Point> tubePoints = super.findIntersections(ray);
      if (tubePoints != null) {
         for (Point pt : tubePoints) {
            double t = alignZero(pt.subtract(p0).dotProduct(va));
            if (alignZero(t) >= 0 && alignZero(t - height) <= 0) {
               result.add(pt);
            }
         }
      }

      double nv = va.dotProduct(v); // Dot product of axis direction and ray direction
      if (!isZero(nv)) {
         // 2. Intersection with the bottom base
         Vector u = p0.subtract(p); // Vector from ray origin to base center
         double t1 = alignZero(va.dotProduct(u) / nv);
         if (alignZero(t1) >= 0) {
            Point q1 = ray.getPoint(t1);
            try {
               Vector vec = q1.subtract(p0); // Vector from base center to intersection
               if (alignZero(vec.lengthSquared() - radius * radius) <= 0) {
                  result.add(q1);
               }
            } catch (IllegalArgumentException e) {
               result.add(q1); // Point is exactly at the center
            }
         }

         // 3. Intersection with the top base
         Point top = p0.add(va.scale(height));
         Vector uTop = top.subtract(p);
         double t2 = alignZero(va.dotProduct(uTop) / nv);
         if (t2 > 0) {
            Point q2 = ray.getPoint(t2); //
            try {
               Vector vec = q2.subtract(top); // Vector from top base center to intersection
               if (alignZero(vec.lengthSquared() - radius * radius) <= 0) {
                  // Prevent duplicates
                  if (result.stream().noneMatch(pnt -> pnt.equals(q2))) {
                     result.add(q2);
                  }
               }
            } catch (IllegalArgumentException e) {
               result.add(q2); // Point is exactly at the center
            }
         }
      }

      if (result.isEmpty()) {
         return null; // No intersections
      }

      // Sort the intersection points by distance from the ray origin
      result.sort(Comparator.comparingDouble(pnt -> pnt.distance(p)));
      return result;
   }
}