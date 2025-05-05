package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Cylinder} class.
 * @author esti
 */
class CylinderTests {
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Cylinder#Cylinder(primitives.Ray, double, double)}.
     */
    @Test
    void testConstructor() {
        // TC01: Valid cylinder creation with axis ray, radius, and height
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 2;
        double height = 5;
        Cylinder cylinder = new Cylinder(axisRay, radius, height);
    }

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
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

        // =============== Boundary Values Tests ==================

        // TC02: Normal of a point at the center of the bottom base
        Point bottomCenter = new Point(0, 0, 0);
        result = cylinder.getNormal(bottomCenter);
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

        // TC06: Point very close to the axis, checking numerical stability
        Point closeToAxis = new Point(0.000001, 0, 2);
        result = cylinder.getNormal(closeToAxis);
        assertEquals(1, result.length(), DELTA, "Normal should be unit vector near the axis");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Normal should be orthogonal to the axis near the axis");
    }

    /**
     * Test method for {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
     */

    @Test
    void testFindIntersections() {
        Cylinder cyl = new Cylinder(
                new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)),
                1,
                5
        );

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray outside and does not intersect (0 points)
        assertNull(cyl.findIntersections(
                new Ray(new Point(3, 3, 2), new Vector(1, 0, 0))
        ), "TC01: Ray completely outside and away from cylinder");

        // TC02: Ray crosses the side (2 points)
        Ray ray2 = new Ray(new Point(2, 0, 2.5), new Vector(-1, 0, 0));
        List<Point> result2 = cyl.findIntersections(ray2);
        assertEquals(2, result2.size(), "TC02: Ray crosses the side");
        assertTrue(result2.get(0).distance(ray2.getHead()) < result2.get(1).distance(ray2.getHead()));

        // TC03: Ray starts inside and hits the side (1 point)
        assertEquals(1, cyl.findIntersections(
                new Ray(new Point(0.5, 0, 2.5), new Vector(1, 0, 0))
        ).size(), "TC03: Ray starts inside");

        // TC04: Ray starts beyond the cylinder and goes away (0 points)
        assertNull(cyl.findIntersections(
                new Ray(new Point(0, 0, 10), new Vector(0, 0, 1))
        ), "TC04: Ray starts beyond and goes away");

        // =============== Boundary Values Tests ==================

        // TC05: Ray is tangent to the side surface and goes into the cylinder (1 point)
        assertEquals(1, cyl.findIntersections(
                new Ray(new Point(1, -1, 2.5), new Vector(0, 1, 0))
        ).size(), "TC05: Tangent to side");

        // TC06: Ray starts on surface and goes inward (1 point)
        assertEquals(1, cyl.findIntersections(
                new Ray(new Point(1, 0, 2.5), new Vector(-1, 0, 0))
        ).size(), "TC06: Starts on surface inward");

        // TC07: Ray intersects both the bottom base and the cylinder side
        Ray rayBottomAndSide = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        List<Point> intersections = cyl.findIntersections(rayBottomAndSide);
        assertNotNull(intersections, "TC07: Ray should intersect the cylinder");
        assertEquals(2, intersections.size(), "TC07: Expected 2 intersections (bottom base and side)");

        // TC08: Ray intersects both the top base and the side
        Ray rayTopAndSide = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        List<Point> intersections2 = cyl.findIntersections(rayTopAndSide);
        assertNotNull(intersections2, "TC08: Ray should intersect the cylinder");
        assertEquals(2, intersections2.size(), "TC08: Expected 2 intersections (top base and side)");

        // TC09: Ray through both bases (2 points)
        Ray ray9 = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));
        List<Point> result9 = cyl.findIntersections(ray9);
        assertEquals(2, result9.size(), "TC09: Through both bases");
        assertTrue(result9.get(0).getZ() < result9.get(1).getZ());

        // TC10: Ray along the axis (2 points)
        assertEquals(2, cyl.findIntersections(
                new Ray(new Point(0, 0, -2), new Vector(0, 0, 1))
        ).size(), "TC10: Along axis");

        // TC11: Ray parallel to axis, inside (2 points)
        assertEquals(2, cyl.findIntersections(
                new Ray(new Point(0.5, 0, -1), new Vector(0, 0, 1))
        ).size(), "TC11: Parallel to axis inside");

        // TC12: Ray parallel to axis, outside (0 points)
        assertNull(cyl.findIntersections(
                new Ray(new Point(2, 0, 2), new Vector(0, 0, 1))
        ), "TC12: Parallel to axis outside");

        // TC13: Ray hits the edge (between side and base)
        Ray rayEdge = new Ray(new Point(0.5, -1, 0), new Vector(0, 1, 0));
        List<Point> result = cyl.findIntersections(rayEdge);
        assertNotNull(result, "TC13: Ray should intersect cylinder edge");
        assertEquals(2, result.size(), "TC13: Expected 2 intersection points (side and base)");

    }

}




