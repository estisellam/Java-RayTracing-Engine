package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for {@link geometries.Tube} class.
 *  @author esti
 */
class TubeTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Tube#Tube(double, Ray)}.
     */
    @Test
    void testConstructor() {
        //=============TC01: Valid tube creation =============//
        Point center = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);
        double radius = 5;
        Ray ray = new Ray(center, direction);
        Tube tube = new Tube(radius, ray);
        assertNotNull(tube, "Tube should be created successfully.");
    }

    /**
     * Test method for edge cases in {@link geometries.Tube#Tube(double, Ray)}.
     */
    @Test
    void testEdgeCases() {
        Point center = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);
        double radius = 0;
        Ray ray = new Ray(center, direction);

        //=============TC01: Tube with zero radius =============//
        assertThrows(IllegalArgumentException.class, () -> new Tube(radius, ray), "Error: radius should be greater than zero.");
    }

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partition Tests ==============

        Point axisPoint = new Point(0, 0, 0);  // Point on the axis of the tube
        Vector axisDirection = new Vector(0, 0, 1);  // Direction of the tube's axis
        double radius = 1;
        Ray ray = new Ray(axisPoint, axisDirection);
        Tube tube = new Tube(radius, ray);

        // TC01: Point on the surface of the tube at a right angle to the axis
        Point pointAtRightAngle = new Point(1, 0, 0); // Should be on the surface
        assertDoesNotThrow(() -> tube.getNormal(pointAtRightAngle),
                "No exception expected for getNormal method at right angle");
        Vector resultAtRightAngle = tube.getNormal(pointAtRightAngle);
        assertEquals(1, resultAtRightAngle.length(), DELTA, "Tube normal is not a unit vector at right angle point");
        assertEquals(0d, resultAtRightAngle.dotProduct(axisDirection), DELTA,
                "Tube normal should be orthogonal to the axis direction at right angle point");

        // TC02: Point on the surface of the tube at a general position
        Point generalPoint = new Point(1, 0, 5); // Same distance from axis, but higher along z-axis
        assertDoesNotThrow(() -> tube.getNormal(generalPoint),
                "No exception expected for getNormal at a general surface point");
        Vector normalGeneral = tube.getNormal(generalPoint);
        assertEquals(1, normalGeneral.length(), DELTA, "Tube normal is not a unit vector at general point");
        assertEquals(0d, normalGeneral.dotProduct(axisDirection), DELTA,
                "Tube normal should be orthogonal to the axis direction at general point");
    }


    /**
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Tube tube = new Tube(1d, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray outside and not intersecting the tube (0 points)
        assertNull(tube.findIntersections(new Ray(new Point(2, 0, 1), new Vector(0, 0, 1))),
                "TC01: Ray completely outside tube");

        // TC02: Ray crosses the tube (2 points)
        Ray ray2 = new Ray(new Point(-2, 0.5, 1), new Vector(1, 0, 0));
        List<Point> result2 = tube.findIntersections(ray2);
        assertNotNull(result2, "TC02: Ray crosses the tube");
        assertEquals(2, result2.size(), "TC02: Should be 2 intersection points");

        // TC03: Ray starts inside the tube and exits (1 point)
        Ray ray3 = new Ray(new Point(0.5, 0, 1), new Vector(1, 0, 0));
        List<Point> result3 = tube.findIntersections(ray3);
        assertNotNull(result3, "TC03: Ray starts inside the tube");
        assertEquals(1, result3.size(), "TC03: Should be 1 intersection point");

        // =============== Boundary Values Tests ==================

        // TC10: Ray tangent to the tube - no intersection
        Ray ray10 = new Ray(new Point(1, -1, 1), new Vector(0, 1, 0));
        List<Point> result10 = tube.findIntersections(ray10);
        assertNotNull(result10, "TC10: Ray tangent to tube");
        assertEquals(1, result10.size(), "TC10: Should return 1 point for tangent ray");

        // TC11: Ray goes through the center of tube's section - 2 points
        Ray ray11 = new Ray(new Point(-2, 0, 1), new Vector(1, 0, 0));
        List<Point> result11 = tube.findIntersections(ray11);
        assertNotNull(result11, "TC11: Ray through tube center");
        assertEquals(2, result11.size(), "TC11: Should be 2 intersection points");

        // TC12: Ray starts on the surface and goes inside - 1 point
        Ray ray12 = new Ray(new Point(1, 0, 1), new Vector(-1, 0, 0));
        List<Point> result12 = tube.findIntersections(ray12);
        assertNotNull(result12, "TC12: Ray starts on surface and goes inward");
        assertEquals(1, result12.size(), "TC12: Should be 1 intersection point");

        // TC13: Ray starts on the surface and goes outward - 0 points
        Ray ray13 = new Ray(new Point(1, 0, 1), new Vector(1, 0, 0));
        assertNull(tube.findIntersections(ray13), "TC13: Ray starts on surface and goes outward");

        // TC14: Ray starts at the center of the tube - 2 points
        Ray ray14 = new Ray(new Point(-2, 0, 1), new Vector(1, 0, 0));
        List<Point> result = tube.findIntersections(ray14);
        assertNotNull(result, "TC14: Ray starts at axis and goes outward");
        assertEquals(2, result.size(), "TC14: Should find 2 intersection points");

        // TC15: Ray parallel to tube axis and outside - no intersection
        Ray ray15 = new Ray(new Point(2, 0, 0), new Vector(0, 0, 1));
        assertNull(tube.findIntersections(ray15), "TC15: Ray parallel and outside");

        // TC16: Ray parallel to tube axis and inside - no intersection
        Ray ray16 = new Ray(new Point(0.5, 0, 0), new Vector(0, 0, 1));
        assertNull(tube.findIntersections(ray16), "TC16: Ray parallel and inside");

        // TC17: Ray starts on axis, directed outward perpendicularly (2 points)
        Ray ray17 = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        List<Point> result17 = tube.findIntersections(ray17);
        assertNotNull(result17, "TC17: Ray from axis outward");
        assertEquals(1, result17.size(), "TC17: Expected 1 intersection point");
    }



}
