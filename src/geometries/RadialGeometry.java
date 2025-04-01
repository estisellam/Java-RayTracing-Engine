package geometries;

import java.util.List;

import static primitives.Util.*;

import primitives.Ray;

/** Abstract class for rounded geometries */
public abstract class RadialGeometry extends Geometry {
   /** Radius of the sphere */
   protected final double radius;
   /** Squared radius of the sphere */
   protected final double rSquared;

   /** Constructor initializing the radius of a rounded geometry
    * @param radius the radius */
   protected RadialGeometry(double radius) {
      this.radius   = radius;
      this.rSquared = radius * radius;
   }

   /** Radius getter
    * @return the radius */
   public double getRadius() { return radius; }

}
