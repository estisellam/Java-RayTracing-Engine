package geometries;

import java.util.List;

import static primitives.Util.*;

import primitives.*;

/**
 * Sphere class represents Euclidean sphere in 3D Cartesian coordinate system
 * represented by its center (point) and radius
 * @author Dan
 */
public class Sphere extends RadialGeometry {
   /**
    * Sphere center
    */
   private final Point o;

   /**
    * Sphere constructor by it's center and radius
    * @param radius of the sphere
    * @param center of the sphere
    */
   public Sphere(Point center, double radius) {
      super(radius);
      o = center;
   }

   /**
    * Center getter
    * @return center point
    */
   public Point getO() { return o; }

   @Override
   public Vector getNormal(Point point) {
      // Create vector by connecting from the center to the point and getting unit
      // vector
      return point.subtract(o).normalize();
   }

}
