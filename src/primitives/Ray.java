package primitives;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in
 * Cartesian 3-Dimensional coordinate system.<br>
 * A ray consists of those points on a line passing through a given point and
 * proceeding indefinitely, starting at the given point, in one direction only
 * along the line<br>
 * Ray direction is stored as a unit vector providing both line's direction and
 * the part of the line whose points belong to the ray
 * @author Dan Zilberstein
 */
public class Ray {
   /** Ray head point P&#x2080; */
   private final Point  origin;

   /** Ray direction vector v&#x302; */
   private final Vector direction;

   /** Inverted ray direction */
   private final Vector inverted;

   /**
    * Ray constructor by ray beginning point and its direction
    * @param p ray beginning point
    * @param v ray direction vector
    */
   public Ray(Point p, Vector v) {
      origin    = p;
      direction = v.normalize();
      inverted  = new Vector(1 / direction.x(), 1 / direction.y(), 1 / direction.z());
   }

   /** The distance of moving the ray head point when required */
   private static final double EPSILON = 0.1;

   /**
    * Ray constructor by ray beginning point and its direction, while the
    * beginning
    * point is moved by DELTA along the line defined by vector n and according to
    * the direction of the ray
    * @param p ray beginning point
    * @param v ray direction vector
    * @param n line vector for point p movement
    */
   public Ray(Point p, Vector v, Vector n) {
      origin    = p.add(n.scale(n.dotProduct(v) >= 0 ? EPSILON : -EPSILON));
      direction = v.normalize();
      inverted  = new Vector(1 / direction.x(), 1 / direction.y(), 1 / direction.z());
   }

   /**
    * /** Getter of ray beginning point
    * @return ray beginning point
    */
   public Point origin() { return origin; }

   /**
    * Getter of ray direction
    * @return direction vector (unit vector)
    */
   public Vector direction() { return direction; }

   /**
    * Getter of inverted direction
    * @return inverted direction vector
    */
   public Vector inverted() { return inverted; }

   /**
    * Get point on ray at a distance from ray's head
    * @param  t distance from ray head
    * @return   the point
    */
   public Point getPoint(double t) {
      try {
         return origin.add(direction.scale(t));
      } catch (IllegalArgumentException ignore) {
         return origin;
      }
   }


    @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      return (obj instanceof Ray other) && origin.equals(other.origin) && direction.equals(other.direction);
   }

   @Override
   public int hashCode() { return origin.hashCode() + direction.hashCode(); }

   @Override
   public String toString() { return "Ray: P0" + origin + "->" + direction; }

}
