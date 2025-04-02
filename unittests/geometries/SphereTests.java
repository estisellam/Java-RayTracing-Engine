package geometries;

import primitives.Point;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link geometries.Sphere} class.
 */
class SphereTests {

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Sphere#Sphere(primitives.Point, double)}.
     */
    @Test
    void testConstructor() {
        //=============TC01: Valid sphere creation =============//
        Point center = new Point(0, 0, 0);
        double radius = 5;
        Sphere sphere = new Sphere(center, radius);
        assertNotNull(sphere, "Sphere should be created successfully.");
    }

    /**
     * Test method for edge cases in {@link geometries.Sphere#Sphere(primitives.Point, double)}.
     */
    @Test
    void testEdgeCases()
    {
        Point center = new Point(0, 0, 0);
        double radius = 0;

        //=============TC01: Sphere with zero radius =============//
        assertThrows(IllegalArgumentException.class, () -> new Sphere(center, radius), "Error: radius should be greater than zero.");
    }
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partition Tests ==============
        // TC01: Normal of a simple sphere
        Point center = new Point(0, 0, 0);
        double radius = 1;
        Sphere sphere = new Sphere(center, radius);
        assertDoesNotThrow(() -> sphere.getNormal(center), "No exception expected for getNormal method on Sphere");
        Vector result = sphere.getNormal(center);
        assertEquals(1, result.length(), DELTA, "Sphere normal is not a unit vector");
        assertEquals(0d, result.dotProduct(center.subtract(center)), DELTA, "Sphere normal should point outward");
    }


}
