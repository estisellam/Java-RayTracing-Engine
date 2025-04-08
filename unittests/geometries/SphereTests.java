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
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partition Tests ==============
        // TC01: Normal of a simple sphere
        Point center = new Point(0, 0, 0);
        double radius = 1;
        Sphere sphere = new Sphere(center, radius);

        // Ensure no exception is thrown when calling getNormal
        assertDoesNotThrow(() -> sphere.getNormal(center), "No exception expected for getNormal method on Sphere");

        // Getting the normal vector and validating its properties
        Vector result = sphere.getNormal(center);

        // Normal should be a unit vector
        assertEquals(1, result.length(), DELTA, "Sphere normal is not a unit vector");

        // The normal vector should point outward, which means its dot product with a zero vector should be 0
        assertEquals(0d, result.dotProduct(center.subtract(center)), DELTA, "Sphere normal should point outward");
    }
}
