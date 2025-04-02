package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Cylinder} class.
 */
class CylinderTests
{
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Cylinder#Cylinder(primitives.Ray, double, double)}.
     */
    @Test
    void testConstructor()
    {
        //=============TC01: Valid cylinder creation =============//
        Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double radius = 2;
        double height = 5;
        Cylinder cylinder = new Cylinder(axisRay, radius, height);
        assertNotNull(cylinder, "Cylinder should be created successfully.");
    }
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partition Tests ==============
        // TC01: Normal of a simple cylinder
        Point axisPoint = new Point(0, 0, 0);
        Vector axisDirection = new Vector(0, 0, 1);
        double radius = 1;
        double height = 5;
        Cylinder cylinder = new Cylinder(new Ray(axisPoint, axisDirection), radius, height);
        assertDoesNotThrow(() -> cylinder.getNormal(axisPoint), "No exception expected for getNormal method on Cylinder");
        Vector result = cylinder.getNormal(axisPoint);
        assertEquals(1, result.length(), DELTA, "Cylinder normal is not a unit vector");
        assertEquals(0d, result.dotProduct(axisDirection), DELTA, "Cylinder normal should be orthogonal to the axis");
    }


}
