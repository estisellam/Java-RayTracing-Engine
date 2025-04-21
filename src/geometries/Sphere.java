package geometries;
import primitives.Vector;
import primitives.*;

import java.util.Collections;
import java.util.List;



/**
 * class to represent a sphere
 */
public class Sphere extends RadialGeometry
{
   /**
    * center of the sphere
    */
   private final Point center;

   /**
    * constructor with point, radius and sphere
    * @param center point of the sphere
    * @param radius radius of the sphere
    */
   public Sphere(Point center, double radius)
   {
      super(radius);
      this.center = center;
   }

   /**
    * func get for center
    * @return center
    */
   public Point getCenter()
   {
      return center;
   }

   /**
    * func get for normal
    * @param point point on the sphere
    * @return normal vector
    */
   @Override
   public Vector getNormal(Point point)
   {
      return point.subtract(center).normalize();
   }

   /**
    * toString function
    * @return string representation of the sphere
    */
   @Override
   public String toString()
   {
      return "Sphere:" + "center:" + center + "radius:" + radius;
   }

   /**
    * find intersections with a ray
    * @param ray the ray to find intersections with
    * @return a list of intersection points
    */
   @Override
   public List<Point> findIntersections(Ray ray)
   {
      return null;
   }


}