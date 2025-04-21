package geometries;
import primitives.*;
import java.util.List;

/**
 * class to represent a triangle
 */
public class Triangle extends Polygon
{
    /**
     * constructor with 3 points for a triangle
     * @param x
     * @param y
     * @param z
     */
    public Triangle(Point x, Point y, Point z)
    {
        super(x, y, z);
    }

    /**
     * func for find intersections when given a ray
     * @param ray the ray to find intersections with
     * @return list of points of intersection or null if no intersection
     */
    @Override
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }
}