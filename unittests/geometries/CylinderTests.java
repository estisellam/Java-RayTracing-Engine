package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for {@link geometries.Cylinder} class.
 */
class CylinderTests
{
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Cylinder#Cylinder(primitives.Ray, double, double)}.
     */
    @Test
    void testConstructor()
    {
        // TC01: Valid cylinder creation with axis ray, radius, and height
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 2;
        double height = 5;
        Cylinder cylinder = new Cylinder(axisRay, radius, height);
    }

    @Test
    void testGetNormal_EquivalencePartitions()
    {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Normal of a point on the side surface of the cylinder
        Point axisPoint = new Point(0, 0, 0);
        Vector axisDirection = new Vector(0, 0, 1);
        double radius = 1;
        double height = 5;
        Cylinder cylinder = new Cylinder(new Ray(axisPoint, axisDirection), radius, height);

        Point sidePoint = new Point(1, 0, 2);
        Vector result = cylinder.getNormal(sidePoint);
        assertEquals(1, result.length(), DELTA, "Cylinder normal is not a unit vector");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Cylinder normal should be orthogonal to the axis");
    }

    @Test
    void testGetNormal_BoundaryValues()
    {
        // =============== Boundary Values Tests ==================

        Point axisPoint = new Point(0, 0, 0);
        Vector axisDirection = new Vector(0, 0, 1);
        double radius = 1;
        double height = 5;
        Cylinder cylinder = new Cylinder(new Ray(axisPoint, axisDirection), radius, height);

        // TC02: Normal of a point at the center of the bottom base
        Point bottomCenter = new Point(0, 0, 0);
        Vector result = cylinder.getNormal(bottomCenter);
        assertEquals(axisDirection.scale(-1), result, "Normal should point opposite to axis direction at bottom base center");

        // TC03: Normal of a point at the center of the top base
        Point topCenter = new Point(0, 0, height);
        result = cylinder.getNormal(topCenter);
        assertEquals(axisDirection, result, "Normal should point in axis direction at top base center");

        // TC04: Normal of a point on the edge between bottom base and side
        Point edgeBottom = new Point(1, 0, 0);
        result = cylinder.getNormal(edgeBottom);
        assertEquals(axisDirection.scale(-1), result, "Normal at edge with bottom base should match bottom normal");

        // TC05: Normal of a point on the edge between top base and side
        Point edgeTop = new Point(1, 0, height);
        result = cylinder.getNormal(edgeTop);
        assertEquals(axisDirection, result, "Normal at edge with top base should match top normal");
    }

    @Test
    void testGetNormal_EdgeCase()
    {
        // TC06: Point very close to the axis, checking numerical stability
        Point axisPoint = new Point(0, 0, 0);
        Vector axisDirection = new Vector(0, 0, 1);
        double radius = 2;
        double height = 5;
        Cylinder cylinder = new Cylinder(new Ray(axisPoint, axisDirection), radius, height);

        Point closeToAxis = new Point(0.000001, 0, 2);
        Vector result = cylinder.getNormal(closeToAxis);
        assertEquals(1, result.length(), DELTA, "Normal should be unit vector near the axis");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Normal should be orthogonal to the axis near the axis");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(new Point(0, 0, 1), new Point(1, 0, 1), new Point(0, 1, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray from outside, not parallel, hits the plane (1 point)
        Ray ray1 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> result1 = plane.findIntersections(ray1);
        assertNotNull(result1, "Ray should hit the plane");
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 0, 1)), result1, "Wrong intersection point");

        // TC02: Ray from outside, not parallel, misses the plane (0 points)
        Ray ray2 = new Ray(new Point(0, 0, 2), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray2), "Ray should miss the plane");

        // =============== Boundary Values Tests ==================

        // **** Group 1: Ray is parallel to plane
        // TC11: Ray is outside the plane (0 points)
        Ray ray11 = new Ray(new Point(0, 0, 2), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray11), "Ray parallel and outside");

        // TC12: Ray is inside the plane (0 points)
        Ray ray12 = new Ray(new Point(0.5, 0.5, 1), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray12), "Ray parallel and in plane");

        // **** Group 2: Ray is orthogonal to plane
        // TC21: Ray starts before the plane (1 point)
        Ray ray21 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        List<Point> result21 = plane.findIntersections(ray21);
        assertNotNull(result21, "Ray orthogonal before plane");
        assertEquals(1, result21.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 0, 1)), result21, "Wrong intersection point");

        // TC22: Ray starts in the plane (0 points)
        Ray ray22 = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray22), "Ray orthogonal in plane");

        // TC23: Ray starts after the plane (0 points)
        Ray ray23 = new Ray(new Point(0, 0, 2), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray23), "Ray orthogonal after plane");

        // **** Group 3: Ray not parallel and not orthogonal, starts in plane (0 points)
        Ray ray3 = new Ray(new Point(0.5, 0.5, 1), new Vector(0, 1, 1));
        assertNull(plane.findIntersections(ray3), "Ray from in plane at angle");

        // **** Group 4: Ray starts at plane's base point (0 points)
        Ray ray4 = new Ray(new Point(0, 0, 1), new Vector(1, 1, 1));
        assertNull(plane.findIntersections(ray4), "Ray from base point at angle");
    }


}
