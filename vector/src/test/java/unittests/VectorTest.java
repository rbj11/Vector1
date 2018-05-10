package unittests;

import datastructure.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTest {

    @Test
    public void defaultCapacityTest() {
        Vector vector = new Vector();
        Assert.assertEquals(10, vector.capacity());
    }

    @Test
    public void correctCapacityTest() {
        Vector vector = new Vector(5);
        Assert.assertEquals(5, vector.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidCapacityTest() {
        new Vector(-5);
    }

    @Test
    public void addTest() {
        Vector vector = new Vector(1);
        vector.add(64);

        Assert.assertEquals(64, vector.get(0));
    }

    @Test
    public void addMiddle() {
        Vector vector = new Vector(3);
        vector.add(1);
        vector.add(3);
        vector.add(1, 2);

        Vector anotherVector = new Vector(3);
        anotherVector.add(1);
        anotherVector.add(2);
        anotherVector.add(3);

        Assert.assertEquals(vector, anotherVector);
    }

    @Test
    public void addEnd() {
        Vector vector = new Vector(1);
        vector.add(1);
        vector.add(2);
        vector.add(1, 5);
        Assert.assertEquals(3, vector.size());
        Assert.assertEquals(4, vector.capacity());
    }

    @Test
    public void removeTest() {
        Vector vector = new Vector(2);
        vector.add(1);
        vector.add(2);

        Assert.assertTrue(vector.remove(2));

        Vector anotherVector = new Vector(2);
        anotherVector.add(1);
        Assert.assertEquals(vector, anotherVector);
    }

    @Test
    public void removeEdgeTest() {
        Vector vector = new Vector(2);
        vector.add(1);
        vector.add(2);

        Assert.assertTrue(vector.remove(1));

        Vector anotherVector = new Vector(2);
        anotherVector.add(2);
        Assert.assertEquals(vector, anotherVector);
    }

    @Test
    public void removeNonExistantTest() {
        Vector vector = new Vector(1);
        vector.add(1);

        Assert.assertFalse(vector.remove(5));
    }

    @Test
    public void removeShrinkTest() {
        Vector vector = new Vector(3);
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);

        Assert.assertEquals(6, vector.capacity());
        Assert.assertTrue(vector.remove(4));
        Assert.assertTrue(vector.remove(3));

        // After last removal, it should shrink the vector
        Assert.assertEquals(3, vector.capacity());
    }

    @Test
    public void clearTest() {
        Vector vector = new Vector(5);
        Vector anotherVector = new Vector(5);
        for (int i = 0; i < 5; i++) {
            vector.add(i);
            anotherVector.add(i);
        }

        Assert.assertEquals(vector, anotherVector);
        vector.clear();
        Assert.assertNotEquals(vector, anotherVector);
        anotherVector = new Vector(5);
        Assert.assertEquals(vector, anotherVector);
    }

    @Test
    public void indexOfTest() {
        Vector vector = new Vector(2);
        vector.add(1);
        vector.add(2);

        Assert.assertEquals(1, vector.indexOf(2));
    }

    @Test
    public void nonExistantIndexTest() {
        Vector vector = new Vector(2);
        vector.add(1);
        vector.add(2);

        Assert.assertEquals(-1, vector.indexOf(5));
    }

    @Test
    public void getTest() {
        Vector vector = new Vector(1);
        vector.add(45);

        Assert.assertEquals(45, vector.get(0));
    }

    @Test
    public void vectorDoublingTest() {
        Vector vector = new Vector(1);
        Assert.assertEquals(1, vector.capacity());

        vector.add(1);
        Assert.assertEquals(1, vector.capacity());

        // Vector should double now..
        vector.add(2);
        Assert.assertEquals(2, vector.capacity());
    }

    @Test
    public void vectorEqualityTest() {
        Vector vector = new Vector(3);
        vector.add(1);
        vector.add(2);
        vector.add(3);

        Vector anotherVector = new Vector(3);
        anotherVector.add(1);
        anotherVector.add(2);
        anotherVector.add(3);

        Assert.assertEquals(vector, anotherVector);

        anotherVector.set(1, 4);
        Assert.assertNotEquals(vector, anotherVector);
    }

    @Test
    public void emptyTest() {
        Vector vector = new Vector();
        Assert.assertTrue(vector.isEmpty());
    }

    @Test
    public void containsTest() {
        Vector vector = new Vector(1);
        vector.add(4);

        Assert.assertTrue(vector.contains(4));
        Assert.assertFalse(vector.contains(14));
    }

}
