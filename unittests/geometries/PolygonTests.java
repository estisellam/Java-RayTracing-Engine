package geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.*;

/**
 * Testing Polygons
 * @author Dan
 */
class PolygonTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class,
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Polygon normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                    "Polygon normal is not orthogonal to one of the edges");
    }
    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        // Polygon: a convex quadrilateral in the XY plane
        Polygon polygon = new Polygon(
                new Point(0, 0, 0),
                new Point(2, 0, 0),
                new Point(2, 2, 0),
                new Point(0, 2, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the polygon inside (1 point)
        Ray ray1 = new Ray(new Point(1, 1, 1), new Vector(0, 0, -1));
        List<Point> result1 = polygon.findIntersections(ray1);
        assertNotNull(result1, "Ray intersects polygon");
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 1, 0)), result1, "Wrong intersection point");

        // TC02: Ray intersects the plane but outside the polygon (0 points)
        Ray ray2 = new Ray(new Point(3, 3, 1), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray2), "Ray misses the polygon");

        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray parallel to polygon plane
        // TC11: Ray is parallel and outside the polygon (0 points)
        Ray ray11 = new Ray(new Point(0, 0, 1), new Vector(1, 0, 0));
        assertNull(polygon.findIntersections(ray11), "Parallel ray outside polygon");

        // TC12: Ray is parallel and inside the polygon plane (0 points)
        Ray ray12 = new Ray(new Point(1, 1, 0), new Vector(1, 0, 0));
        assertNull(polygon.findIntersections(ray12), "Ray in polygon plane");

        // **** Group 2: Ray orthogonal to the polygon plane
        // TC21: Ray starts before the plane (1 point)
        Ray ray21 = new Ray(new Point(1, 1, 1), new Vector(0, 0, -1));
        List<Point> result21 = polygon.findIntersections(ray21);
        assertNotNull(result21, "Orthogonal ray from above");
        assertEquals(1, result21.size(), "Wrong number of points");

        // TC22: Ray starts in the polygon plane (0 points)
        Ray ray22 = new Ray(new Point(1, 1, 0), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray22), "Ray starts in polygon plane");

        // TC23: Ray starts after the polygon plane (0 points)
        Ray ray23 = new Ray(new Point(1, 1, -1), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray23), "Ray starts below polygon");

        // **** Group 3: Ray starts inside the polygon (but not orthogonal or parallel)
        // TC31: Ray starts inside and points up (0 points)
        Ray ray31 = new Ray(new Point(1, 1, 0), new Vector(0, 0, 1));
        assertNull(polygon.findIntersections(ray31), "Ray from inside pointing up");

        // **** Group 4: Ray starts at reference point of polygon (0 points)
        // TC41: Ray starts exactly at one polygon vertex
        Ray ray41 = new Ray(new Point(0, 0, 0), new Vector(0, 0, -1));
        assertNull(polygon.findIntersections(ray41), "Ray from polygon vertex");

    }

}
