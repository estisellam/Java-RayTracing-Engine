package geometries;

import primitives.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable
{

    // List of intersectable geometries - initialized to empty linked list
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor - creates an empty collection
     */
    public Geometries()
    {
        // No need  do anything (list already initialized)
    }

    /**
     * Constructor with geometries to add at creation
     * @param geometries one or more intersectable geometries
     */
    public Geometries(Intersectable... geometries)
    {
        add(geometries);
    }

    /**
     * Add one or more geometries to the collection
     * @param geometries one or more intersectable geometries
     */
    public void add(Intersectable... geometries)
    {
        this.geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * Find all intersection points of the given ray with the geometries in the collection
     * @param ray the ray to intersect with
     * @return list of all intersection points, or null if none
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geo : geometries) {
            List<Point> tempList = geo.findIntersections(ray);
            if (tempList != null) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                intersections.addAll(tempList);
            }
        }
        return intersections;
    }
}
