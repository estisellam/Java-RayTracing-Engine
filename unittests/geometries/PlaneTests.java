package geometries;

import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Plane} class.
 */
class PlaneTests {
    private static final double DELTA = 0.000001;

    /**
     * Test constructor with three points.
     */
    @Test
    void testConstructorWithThreePoints() {
        // ============ EP: Three non-collinear points ============ //
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        assertDoesNotThrow(() -> new Plane(p1, p2, p3), "Plane should be created with valid non-collinear points.");

        // ============ Error case: Collinear points ============ //
        Point collinear = new Point(2, 0, 0); // lies on the line between p1 and p2
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, collinear),
                "Should throw an exception when points are collinear.");
    }

    /**
     * Test edge cases for constructor with three points.
     */
    @Test
    void testConstructorEdgeCases() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(1, 2, 3); // same as p1
        Point p3 = new Point(4, 5, 6);

        // ============ BVA: Two identical points ============ //
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3),
                "Should throw an exception when two points are identical.");

        // ============ BVA: All points are identical ============ //
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p1, p1),
                "Should throw an exception when all points are identical.");
    }

    /**
     * Test constructor with point and normal vector.
     */
    @Test
    void testConstructorWithPointAndVector() {
        // ============ EP: Valid point and normal vector ============ //
        Point p = new Point(0, 0, 0);
        Vector normal = new Vector(1, 0, 0);
        assertDoesNotThrow(() -> new Plane(p, normal),
                "Plane should be created with a valid point and normal vector.");
    }

    /**
     * Test getNormal method for correctness.
     */
    @Test
    void testGetNormal() {
        // ============ EP: Normal vector is perpendicular to plane vectors ============ //
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, p2, p3);

        // Expect the normal to be orthogonal to both vectors formed by the plane points
        Vector normal = plane.getNormal(p1);
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);

        assertEquals(0, normal.dotProduct(v1), DELTA, "Normal should be perpendicular to first edge.");
        assertEquals(0, normal.dotProduct(v2), DELTA, "Normal should be perpendicular to second edge.");
        assertEquals(1, normal.length(), DELTA, "Normal should be a unit vector.");
    }
}
