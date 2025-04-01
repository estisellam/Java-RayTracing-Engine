package geometries;

import primitives.*;

/** Base class for all basic geometries providing mandatory methods
 * @author Dan */
public abstract class Geometry  {

   /** Calculates a unit vector orthogonal to the surface of the geometry body at a
    * given point. Basic assumption - the point lays in the surface
    * @param  point in the surface
    * @return       unit orthogonal vector */
   public abstract Vector getNormal(Point point);
}
