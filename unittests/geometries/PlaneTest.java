package geometries;
import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Plane} class
 */
class PlaneTests
{

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}
     */
    @Test
    void testConstructorWithThreePoints()
    {
        // ============ TC01: Valid plane with non-collinear points ============= //
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane plane = new Plane(p1, p2, p3);
        assertNotNull(plane, "Plane should be created with non-collinear points.");

        // ============ TC02: Points are collinear ============= //
        Point p4 = new Point(2, 2, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Plane(p1, p2, p4);
        }, "Expected IllegalArgumentException when points are collinear.");

        // ============ TC03: Points are on the same line  ============= //
        Point p5 = new Point(3, 3, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            new Plane(p1, p2, p5);
        }, "Expected IllegalArgumentException when points are on the same line.");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Vector)}
     */
    @Test
    void testConstructorWithPointAndVector()
    {
        // ============ TC01: Valid plane ============= //
        Point p1 = new Point(0, 0, 0);
        Vector normal = new Vector(1, 0, 0);
        Plane plane = new Plane(p1, normal);
        assertNotNull(plane, "Plane should be created with point and vector.");
    }
}
