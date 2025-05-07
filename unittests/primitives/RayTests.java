package unittests;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

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

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partition Test ==============
        // TC01: Middle point is closest
        Point p1 = new Point(3, 0, 0);
        Point p2 = new Point(1, 0, 0); // closest
        Point p3 = new Point(5, 0, 0);
        assertEquals(p2, ray.findClosestPoint(List.of(p1, p2, p3)), "TC01: wrong closest point");

        // =============== Boundary Values Tests ==================
        // TC02: List is empty
        assertNull(ray.findClosestPoint(List.of()), "TC02: should return null for empty list");

        // TC03: First point is closest
        assertEquals(p2, ray.findClosestPoint(List.of(p2, p1, p3)), "TC03: wrong closest point when first");

        // TC04: Last point is closest
        assertEquals(p2, ray.findClosestPoint(List.of(p1, p3, p2)), "TC04: wrong closest point when last");
    }
}
