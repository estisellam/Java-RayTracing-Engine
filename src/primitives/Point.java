package primitives;

/**
 * class to represent a point in 3D space
 */
public class Point
{
   /**
    * coordinate of the point in 3d
    */
   protected final Double3 coordinate;
   /**
    * point at origin
    */
   public static final Point ZERO = new Point(0, 0, 0);

   /**
    * constructor with 3 points
    * @param x
    * @param y
    * @param z
    */
   public Point(double x, double y, double z)
   {
      coordinate = new Double3(x, y, z);
   }

   /**
    * get point from double3
    * @param x
    */
   public Point(Double3 x)
   {
      coordinate = x;
   }
   public double getX()
    {
        return coordinate.d1();
    }
    public double getY()
    {
        return coordinate.d2();
    }
    public double getZ()
    {
        return coordinate.d3();
    }

   /**
    * get coordinate of the point
    * @return
    */
    public Double3 getCoordinate()
    {
        return coordinate;
    }

   /**
    * vector between two points
    * @param a
    * @return
    */
   public Vector subtract(Point a)
   {
      Double3 d=this.coordinate.subtract(a.coordinate);
      return new Vector(d.d1(), d.d2(), d.d3());
   }
   /**
    * add a vector to point
    */
   public Point add(Vector a)
   {
      Double3 d=this.coordinate.add(a.coordinate);
      return new Point(d.d1(), d.d2(), d.d3());
   }

   /**
    * distance squared between two points
    * @param a - point
    * @return  distance squared
    */
   public double distanceSquared(Point a)
   {
      double x= this.coordinate.d1()-a.coordinate.d1();
      double y= this.coordinate.d2()-a.coordinate.d2();
      double z= this.coordinate.d3()-a.coordinate.d3();
      return x*x+y*y+z*z;
   }

   /**
    * distance between two points
    * @param a
    * @return
    */
   public double distance(Point a)
   {
      return Math.sqrt(this.distanceSquared(a));
   }

   /**
    * function to check if two points are equal
    * @param obj
    * @return
    */

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof Point point)) return false;
      return coordinate.equals(point.coordinate);
   }

   /**
    * function to get hash code of the point
    * @return
    */
   @Override
    public int hashCode()
   {
        return coordinate.hashCode();
   }


   /**
    * ovveride function to print point
    * @return
    */

   @Override
   public String toString()
   {
      return "Point{" + "coordinate=" + coordinate + '}';
   }

}
