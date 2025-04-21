package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import java.util.List;

/**
 * class to represent a cylinder
 */
public class Cylinder extends Tube
{
   private final double height;

   /**
    * constructor with ray, radius and height
    *
    * @param axisRay
    * @param radius
    * @param height
    */
      public Cylinder(Ray axisRay, double radius, double height)
   {
      super(radius, axisRay);
      this.height = height;
   }

   /**
    * getter func for hight
    * @return
    */

   public double getHeight()
   {
      return height;
   }

   /**
    * func to get normal
    *
    */
   @Override
   public Vector getNormal(Point point) {
      Point p0 = mainAxis.getHead(); // base center
      Vector dir = mainAxis.getDirection();

      double t;
      try {
         Vector v = point.subtract(p0);
         t = alignZero(v.dotProduct(dir));
      } catch (IllegalArgumentException e) {
         return dir.scale(-1); // point is at base center
      }

      if (isZero(t)) // bottom base
         return dir.scale(-1);
      if (isZero(t - height)) // top base
         return dir;

      // side
      Point o = mainAxis.getPoint(t);
      return point.subtract(o).normalize();
   }

   /**
    * func to get intersections with a ray
    * @param ray the ray to find intersections with
    * @return a list of intersection points or null if no intersections
    */
   @Override
   public List<Point> findIntersections(Ray ray)
   {
      return null;
   }

}





