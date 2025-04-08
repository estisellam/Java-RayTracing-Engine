package primitives;

public class Vector extends Point
{
    /** X axis unit vector */
    public static final Vector AXIS_X  = new Vector(1, 0, 0);
    /** Y axis unit vector */
    public static final Vector AXIS_Y  = new Vector(0, 1, 0);
    /** Z axis unit vector */
    public static final Vector AXIS_Z  = new Vector(0, 0, 1);
    /** X axis opposite unit vector */
    public static final Vector MINUS_X = new Vector(-1, 0, 0);
    /** Y axis opposite unit vector */
    public static final Vector MINUS_Y = new Vector(0, -1, 0);
    /** Z axis opposite unit vector */
    public static final Vector MINUS_Z = new Vector(0, 0, -1);

   /**
    * Vector constructor by 3 coordinate values
    * @param coordinate
    */
   public Vector(Double3 coordinate)
   {
      super(coordinate);
      if(coordinate.equals(Double3.ZERO))
      {
         throw new IllegalArgumentException(" zero vector not allowed");
      }
   }

   /**
    * vector constuctor by 3 points
    * @param x
    * @param y
    * @param z
    */

   public Vector(double x, double y, double z)
   {
      super(x, y, z);
      if(coordinate.equals(Double3.ZERO)){
         throw new IllegalArgumentException("zero vector not allowed");
      }
   }

   /**
    * add function between two vectors
    * @param vector
    * @return
    */
   @Override
   public Vector add(Vector vector)
   {
      return new Vector(coordinate.add(vector.coordinate));

   }

   /**
    * This function returns a new vector that is the result of multiplying the current vector by a scalar
    * @param scalar the scalar to multiply the vector by
    * @return A new Vector object.
    */
   public Vector scale(double scalar)
   {
      return new Vector(coordinate.scale(scalar));

   }

   /**
    * This function calculates the dot product of two vectors.
    * @param vector
    * @return
    */
   public double dotProduct(Vector vector)
   {
      return coordinate.d1() * vector.coordinate.d1() + coordinate.d2() * vector.coordinate.d2() + coordinate.d3() * vector.coordinate.d3();
   }

   /**
    * This function calculates the cross product of two vectors.
    * @param vector
    * @return
    */
   public Vector crossProduct(Vector vector)
   {
      double x = coordinate.d2() * vector.coordinate.d3() - coordinate.d3() * vector.coordinate.d2();
      double y = coordinate.d3() * vector.coordinate.d1() - coordinate.d1() * vector.coordinate.d3();
      double z = coordinate.d1() * vector.coordinate.d2() - coordinate.d2() * vector.coordinate.d1();
      return new Vector(new Double3(x, y, z));
   }

   public double lengthSquared()
   {
      double u1 =coordinate.d1();
      double u2 = coordinate.d2();
      double u3 = coordinate.d3();
      return u1 * u1 + u2 * u2 + u3 * u3;
   }

   public double length()
   {
      return Math.sqrt(lengthSquared());
   }

   /**
    * func for normalize vector
    * @return
    */
   public Vector normalize()
   {
      double length = length();
      if (length == 0)
      {
         throw new IllegalStateException("Cannot normalize a zero-length vector");
      }
      return scale(1 / length);
   }

   /**
    * This function returns a string representation of the vector.
    * @return
    */

   @Override
   public String toString()
   {
      return "Vector " +super.toString();
   }

   /**
    * This function checks if two vectors are equal.
    * @param obj
    * @return
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      else
      {
         return obj instanceof Vector && super.equals(obj);
      }
   }

   /**
    * this function returns the hash code of the vector
    * @return
    */
   @Override
   public int hashCode()
   {
      return super.hashCode();
   }
}
