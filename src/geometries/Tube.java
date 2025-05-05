package geometries;
import primitives.*;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;
import static primitives.Util.alignZero;

/**
 * class to represent a tube
 *  @author esti
 */
public class Tube extends RadialGeometry {
   /**
    * ray of the main axis of the tube
    */
   protected final Ray mainAxis;

   /**
    * constructor
    * @param x - radius
    * @param y - ray
    */
   public Tube(double x, Ray y) {
      super(x);
        if (y == null)
             throw new IllegalArgumentException("Error: ray cannot be null.");
        mainAxis =y;
   }

   /**
    * get normal to the tube
    * @param
    * @return
    */
   @Override
   public Vector getNormal(Point p) {
      Point p0 = mainAxis.getHead();
      Vector va = mainAxis.getDirection();
      Vector delta = p.subtract(p0);
      double t = alignZero(delta.dotProduct(va));
      Point o = isZero(t) ? p0 : p0.add(va.scale(t));
      Vector normal = p.subtract(o);

      if (isZero(normal.lengthSquared())) {
         throw new IllegalArgumentException("Point cannot be on tube axis");
      }

      return normal.normalize();
   }

   @Override
   public List<Point> findIntersections(Ray ray) {
      Point p = ray.getHead();
      Vector v = ray.getDirection();
      Point p0 = mainAxis.getHead();
      Vector va = mainAxis.getDirection();

      // check if ray starts on the axis and goes in the same direction
      if (p.equals(p0)) {
         double angleCos = v.normalize().dotProduct(va.normalize());
         if (isZero(Math.abs(angleCos) - 1)) {
            return null;
         }
      }

      Vector delta = null;
      try {
         delta = p.subtract(p0);
      } catch (IllegalArgumentException e) {
         // p == p0
      }

      double vVa = v.dotProduct(va);
      Vector vOrtho = null;
      try {
         vOrtho = isZero(vVa) ? v : v.subtract(va.scale(vVa));
      } catch (IllegalArgumentException e) {
         return null;
      }

      if (vOrtho == null || isZero(vOrtho.lengthSquared())) {
         return null;
      }

      Vector dOrtho = null;
      if (delta != null) {
         double dVa = delta.dotProduct(va);
         try {
            dOrtho = isZero(dVa) ? delta : delta.subtract(va.scale(dVa));
         } catch (IllegalArgumentException e) {
            return null;
         }
      }

      double a = alignZero(vOrtho.lengthSquared());
      double b = dOrtho == null ? 0 : alignZero(2 * vOrtho.dotProduct(dOrtho));
      double c = dOrtho == null ? -radius * radius : alignZero(dOrtho.lengthSquared() - radius * radius);

      double d = alignZero(b * b - 4 * a * c);
      if (d < 0) {
         return null;
      }

      double sqrtD = Math.sqrt(d);
      double t1 = alignZero((-b - sqrtD) / (2 * a));
      double t2 = alignZero((-b + sqrtD) / (2 * a));

      List<Point> result = new ArrayList<>();
      if (alignZero(t1) > 0) result.add(ray.getPoint(t1));
      if (alignZero(t2) > 0 && !isZero(t1 - t2)) result.add(ray.getPoint(t2));


      return result.isEmpty() ? null : result;
   }


}
