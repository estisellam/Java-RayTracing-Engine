package geometries;
import primitives.*;

/**
 * abstract class to represent a geometry
 */
public abstract class Geometry implements Intersectable
{
   /**
    * abstract function to get normal
    * @param p point on the geometry
    * @return normal vector
    */
   public abstract Vector getNormal(Point p);
}
