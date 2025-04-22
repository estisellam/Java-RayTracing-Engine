package geometries;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GeometriesTests
{
    @Test
    public void testFindIntersections()
    {
        Sphere sphere = new Sphere(new Point(0, 0, 3), 1); // Should have 2 intersections
        Plane plane = new Plane(new Point(0, 0, 4), new Vector(0, 0, 1)); // Should have 1 intersection
        Triangle triangle = new Triangle(new Point(-1, -1, 2), new Point(1, -1, 2), new Point(0, 1, 2)); // Should have 1 intersection

        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));

        // ============ Boundary Value Tests ==============

        // TC01: Empty collection - no intersections
        Geometries geoEmpty = new Geometries();
        assertNull(geoEmpty.findIntersections(ray), "Empty collection should return null");

        // TC02: No shape is intersected
        Geometries geoNone = new Geometries(
                (Intersectable) new Sphere(new Point(0, 0, -5), 1),
                (Intersectable) new Plane(new Point(0, 0, -1), new Vector(0, 0, -1)),
                (Intersectable) new Triangle(new Point(-2, -2, -1), new Point(-1, -2, -1), new Point(-1.5, -1, -1))
        );
        assertNull(geoNone.findIntersections(ray), "Ray should not intersect any geometry");

        // TC03: Only one shape is intersected (sphere)
        Geometries geoOne = new Geometries(
                (Intersectable) sphere,
                (Intersectable) new Plane(new Point(0, 0, -1), new Vector(0, 1, 0)),
                (Intersectable) new Triangle(new Point(2, 2, 2), new Point(3, 2, 2), new Point(2.5, 3, 2))
        );
        List<Point> resultOne = geoOne.findIntersections(ray);
        assertNotNull(resultOne, "One geometry should be hit");
        assertEquals(2, resultOne.size(), "Expected 2 points from sphere");

        // ============ Equivalence Partition Tests ==============

        // TC04: Some shapes are intersected (sphere and plane)
        Geometries geoSome = new Geometries((Intersectable) sphere, (Intersectable) plane,
                (Intersectable) new Triangle(new Point(2, 2, 6), new Point(3, 2, 6), new Point(2.5, 3, 6)));
        List<Point> resultSome = geoSome.findIntersections(ray);
        assertNotNull(resultSome, "Some geometries should be hit");
        assertEquals(3, resultSome.size(), "Expected 2 from sphere and 1 from plane");

        // TC05: All shapes are intersected
        Geometries geoAll = new Geometries((Intersectable) sphere, (Intersectable) plane, (Intersectable) triangle);
        List<Point> resultAll = geoAll.findIntersections(ray);
        assertNotNull(resultAll, "All geometries should be hit");
        assertEquals(4, resultAll.size(), "Expected 2 from sphere, 1 from plane, 1 from triangle");
    }


}
