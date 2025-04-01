package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link primitives.Vector} class
 */
class VectorTests {

    /**
     * Test method for {@link primitives.Vector#add(Vector)}
     */
    @Test
    void testAdd() {
        // ============ TC01: Test vector addition ============= //
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        Vector v3 = v1.add(v2);
        Vector expected = new Vector(5, 7, 9);
        assertEquals(expected, v3, "add() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        // ============ TC01: Test vector scaling ============= //
        Vector v1 = new Vector(1, 2, 3);
        double scalar = 2;
        Vector v2 = v1.scale(scalar);
        Vector expected = new Vector(2, 4, 6);
        assertEquals(expected, v2, "scale() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}
     */
    @Test
    void testDotProduct() {
        // ============ TC01: Test dot product calculation ============= //
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, -5, 6);
        double result = v1.dotProduct(v2);
        double expected = 1 * 4 + 2 * (-5) + 3 * 6;
        assertEquals(expected, result, "dotProduct() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {
        // ============ TC01: Test cross product calculation ============= //
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        Vector v3 = v1.crossProduct(v2);
        Vector expected = new Vector(-3, 6, -3);
        assertEquals(expected, v3, "crossProduct() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // ============ TC01: Test squared length calculation ============= //
        Vector v1 = new Vector(1, 2, 3);
        double result = v1.lengthSquared();
        double expected = 1 * 1 + 2 * 2 + 3 * 3;
        assertEquals(expected, result, "lengthSquared() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#length()}
     */
    @Test
    void testLength() {
        // ============ TC01: Test length calculation ============= //
        Vector v1 = new Vector(0, 3, 4);
        double result = v1.length();
        double expected = 5;
        assertEquals(expected, result, "length() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}
     */
    @Test
    void testNormalize() {
        // ============ TC01: Test vector normalization ============= //
        Vector v1 = new Vector(0, 3, 4);
        Vector v2 = v1.normalize();
        double expectedLength = 1;
        assertEquals(expectedLength, v2.length(), "normalize() wrong result: length is not 1");
    }
}