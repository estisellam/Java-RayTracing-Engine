package geometries;
import primitives.*;
import java.util.List;
/**
 * interface to represent an intersectable object
 */
public interface Intersectable
{
    /**
     * function to find intersections with a ray
     * @param ray the ray to find intersections with
     * @return a list of intersection points
     */
    List <Point> findIntersections(Ray ray);
}
