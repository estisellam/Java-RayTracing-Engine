package geometries;
import primitives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for {@link geometries.Triangle} class.
 */
class TriangleTests {

    /**
     * Test method for {@link geometries.Triangle#Triangle(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testConstructor() {
        //=============TC01: Valid triangle creation =============//
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle triangle = new Triangle(p1, p2, p3);
        assertNotNull(triangle, "Triangle not created successfully.");
    }

    /**
     * Test method for edge cases in {@link geometries.Triangle#Triangle(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testCasesForThreePoints() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(1, 2, 3);
        Point p3 = new Point(4, 5, 6);

        //=============TC01: Two points are identical =============//
        assertThrows(IllegalArgumentException.class, () -> new Triangle(p1, p2, p3), "Error: two points are identical.");

        //=============TC02: All points are identical =============//
        assertThrows(IllegalArgumentException.class, () -> new Triangle(p1, p1, p1), "Error: all points are identical.");
    }
    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        // Triangle: in the XY plane
        Triangle triangle = new Triangle(
                new Point(0, 0, 0),
                new Point(2, 0, 0),
                new Point(1, 2, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects inside the triangle (1 point)
        Ray ray1 = new Ray(new Point(1, 1, 1), new Vector(0, 0, -1));
        List<Point> result1 = triangle.findIntersections(ray1);
        assertNotNull(result1, "Ray intersects triangle");
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 1, 0)), result1, "Wrong intersection point");

        // TC02: Ray intersects the plane but outside the triangle (0 points)
        Ray ray2 = new Ray(new Point(2, 2, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray2), "Ray misses the triangle");

        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray intersects the plane at the triangle edge
        // TC11: Ray intersects exactly on the edge (0 points)
        Ray ray11 = new Ray(new Point(1, 0, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray11), "Ray hits triangle edge");

        // TC12: Ray intersects exactly on the vertex (0 points)
        Ray ray12 = new Ray(new Point(0, 0, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray12), "Ray hits triangle vertex");

        // TC13: Ray intersects just outside edge (0 points)
        Ray ray13 = new Ray(new Point(-0.1, 0, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray13), "Ray just outside triangle");

        // **** Group 2: Ray is parallel to triangle plane
        // TC21: Ray is parallel and above (0 points)
        Ray ray21 = new Ray(new Point(0, 0, 1), new Vector(1, 0, 0));
        assertNull(triangle.findIntersections(ray21), "Parallel ray above triangle");

        // TC22: Ray is in the same plane (0 points)
        Ray ray22 = new Ray(new Point(1, 1, 0), new Vector(1, 0, 0));
        assertNull(triangle.findIntersections(ray22), "Ray in triangle plane");

        // **** Group 3: Ray orthogonal to triangle
        // TC31: Ray starts before the triangle (1 point)
        Ray ray31 = new Ray(new Point(1, 1, 1), new Vector(0, 0, -1));
        List<Point> result31 = triangle.findIntersections(ray31);
        assertNotNull(result31, "Orthogonal ray from above");
        assertEquals(1, result31.size(), "Wrong number of points");

        // TC32: Ray starts on the triangle plane (0 points)
        Ray ray32 = new Ray(new Point(1, 1, 0), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray32), "Ray starts in triangle plane");

        // TC33: Ray starts after the triangle plane (0 points)
        Ray ray33 = new Ray(new Point(1, 1, -1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray33), "Ray starts below triangle");

        // **** Group 4: Ray starts at triangle vertex (0 points)
        Ray ray41 = new Ray(new Point(0, 0, 0), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray41), "Ray starts at triangle vertex");
    }



}
