package geometries;

import primitives.Point;
import primitives.Vector;
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
     * Test method for {@link geometries.Tube#Tube(primitives.Point, primitives.Vector, double)}.
     */
    @Test
    void testConstructor() {
        //=============TC01: Valid tube creation =============//
        Point center = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);
        double radius = 5;
        Tube tube = new Tube(center, direction, radius);
        assertNotNull(tube, "Tube should be created successfully.");
    }

    /**
     * Test method for edge cases in {@link geometries.Tube#Tube(primitives.Point, primitives.Vector, double)}.
     */
    @Test
    void testEdgeCases()
    {
        Point center = new Point(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);
        double radius = 0;

        //=============TC01: Tube with zero radius =============//
        assertThrows(IllegalArgumentException.class, () -> new Tube(center, direction, radius), "Error: radius should be greater than zero.");
    }
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partition Tests ==============
        // TC01: Normal of a simple tube
        Point axisPoint = new Point(0, 0, 0);  // Point on the axis of the tube
        Vector axisDirection = new Vector(0, 0, 1);  // Direction of the tube's axis
        double radius = 1;
        Tube tube = new Tube(axisPoint, axisDirection, radius);
        assertDoesNotThrow(() -> tube.getNormal(axisPoint), "No exception expected for getNormal method on Tube");
        Vector result = tube.getNormal(axisPoint);
        assertEquals(1, result.length(), DELTA, "Tube normal is not a unit vector");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Tube normal should be orthogonal to the axis direction");

    }


}
