package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Tube} class.
 */
class TubeTests
{
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
        // TC01: Normal of a simple tube
        Point axisPoint = new Point(0, 0, 0);  // Point on the axis of the tube
        Vector axisDirection = new Vector(0, 0, 1);  // Direction of the tube's axis
        double radius = 1;
        Ray ray = new Ray(axisPoint, axisDirection);
        Tube tube = new Tube(radius, ray);
        assertDoesNotThrow(() -> tube.getNormal(axisPoint), "No exception expected for getNormal method on Tube");
        Vector result = tube.getNormal(axisPoint);
        assertEquals(1, result.length(), DELTA, "Tube normal is not a unit vector");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Tube normal should be orthogonal to the axis direction");

        // ============ Equivalence Partition Tests ==============
        // TC02: Test case where the connection between the point and the axis creates a right angle
        Point pointAtRightAngle = new Point(1, 0, 0); // This should create a right angle with the axis
        assertDoesNotThrow(() -> tube.getNormal(pointAtRightAngle), "No exception expected for getNormal method at right angle");
        Vector resultAtRightAngle = tube.getNormal(pointAtRightAngle);
        assertEquals(1, resultAtRightAngle.length(), DELTA, "Tube normal is not a unit vector at right angle point");
        assertEquals(0d, resultAtRightAngle.dotProduct(axisDirection), DELTA, "Tube normal should be orthogonal to the axis direction at right angle point");
    }
}
