package primitives;

/**
 * Class Point is the basic class representing a point of Euclidean geometry
 * in Cartesian 3-Dimensional coordinate system.
 * @author Dan Zilberstein
 */
public class Point {
   /** Center of coordinates point */
   public static final Point ZERO = new Point(Double3.ZERO);

   /** Triad of (x,y,z) coordinates of the point */
   protected final Double3   xyz;

   /**
    * 3D point constructor by x, y and z coordinate values
    * @param x - coordinate value
    * @param y - coordinate value
    * @param z - coordinate value
    */
   public Point(double x, double y, double z) { xyz = new Double3(x, y, z); }

   /**
    * 3D point constructor by coordinate values triad
    * @param coords - coordinate values' triad
    */
   Point(Double3 coords) { this.xyz = coords; }

   /**
    * x coordinate value getter
    * @return value of the coordinate
    */
   public double x() { return xyz.d1(); }

   /**
    * y coordinate value getter
    * @return value of the coordinate
    */
   public double y() { return xyz.d2(); }

   /**
    * z coordinate value getter
    * @return value of the coordinate
    */
   public double z() { return xyz.d3(); }

   /**
    * coordinates getter
    * @return the coordinates triad
    */
   public Double3 xyz() { return xyz; }

   /**
    * Adds a given vector v&#x20D7; to this point (P&#x2081;) resulting in a new
    * point
    * @param  v vector to be added
    * @return   new point P&#x2081;+v&#x20D7;
    */
   public Point add(Vector v) { return new Point(xyz.add(v.xyz)); }

   /**
    * Subtracts a point (P&#x2082;) from this point (P&#x2081;) resulting in a
    * vector from a given point to this point
    * @param  p other point to be subtracted (P&#x2082;)
    * @return   the result vector of P&#x2081;-P&#x2082;
    */
   public Vector subtract(Point p) { return new Vector(xyz.subtract(p.xyz)); }

   /**
    * Calculates squared distance between this point (P&#x2081;) and another point
    * (P&#x2082;)
    * @param  p other point
    * @return   |P&#x2081;P&#x2082;|&#xB2;
    */
   public double distanceSquared(Point p) {
      double dx = xyz.d1() - p.xyz.d1();
      double dy = xyz.d2() - p.xyz.d2();
      double dz = xyz.d3() - p.xyz.d3();
      return dx * dx + dy * dy + dz * dz;
   }

   /**
    * Calculates distance between this point (P&#x2081;) and another point
    * (P&#x2082;)
    * @param  p other point
    * @return   |P&#x2081;P&#x2082;|&#xB2;
    */
   public double distance(Point p) { return Math.sqrt(distanceSquared(p)); }

   @Override
   public String toString() { return "" + xyz; }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      return (obj instanceof Point other) && xyz.equals(other.xyz);
   }

   @Override
   public int hashCode() { return xyz.hashCode(); }
}
