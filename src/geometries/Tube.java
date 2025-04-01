package geometries;

import java.util.List;

import static primitives.Util.alignZero;

import primitives.*;

/** Tube class represents Euclidean infinite cylinder in 3D Cartesian coordinate
 * system represented by its central ray and radius
 * @author Dan */
public class Tube extends RadialGeometry {
   /** Ray describing the central tube axis */
   protected final Ray axis;

   /** Tube constructor given its radius and its central ray
    * @param radius of the tube
    * @param ray    central axis ray */
   public Tube(double radius, Ray ray) {
      super(radius);
      axis = ray;
   }

   @Override
   public Vector getNormal(Point point) {
      // projection of P-O on the ray:
      double t = point.subtract(axis.origin()).dotProduct(axis.direction());
      return point.subtract(axis.getPoint(t)).normalize();
   }

}
