package unittests;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Ray class
 */
public class RayTests {

    /**
     * Test method for Ray.getPoint(double distance)
     */
    @Test
    public void testGetPoint()
    {
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Positive distance
        assertEquals(new Point(1, 2, 5), ray.getPoint(2), "Ray.getPoint() failed for positive distance");

        // TC02: Negative distance
        assertEquals(new Point(1, 2, 1), ray.getPoint(-2), "Ray.getPoint() failed for negative distance");

        // =============== Boundary Values Tests ==================

        // TC11: Zero distance
        assertEquals(new Point(1, 2, 3), ray.getPoint(0), "Ray.getPoint() failed for zero distance");
    }
}
