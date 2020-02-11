package collection;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class OurTreeSetTest {
    OurTreeSet<Integer> ourTreeSet = new OurTreeSet<>();

    @Test
    public void containsTrue() {
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);

        assertTrue(ourTreeSet.contains(1));
    }

    @Test
    public void containsFalse() {
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        assertFalse(ourTreeSet.contains(5));
    }

    @Test
    public void sizeTrue() {
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        ourTreeSet.add(4);
        assertEquals(3, ourTreeSet.size());
    }

    @Test
    public void sizeFalse() {
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        assertNotEquals(7, ourTreeSet.size());
    }

    @Test
    public void removeTrue() {
        ourTreeSet.add(10);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        ourTreeSet.add(12);
        ourTreeSet.add(23);
        ourTreeSet.add(16);
        ourTreeSet.add(11);
        ourTreeSet.add(17);
        assertTrue(ourTreeSet.remove(23));
        assertEquals(7, ourTreeSet.size());
    }

    @Test
    public void remove_False() {
        ourTreeSet.add(10);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        ourTreeSet.add(12);
        ourTreeSet.add(23);
        ourTreeSet.add(16);
        ourTreeSet.add(11);
        ourTreeSet.add(17);
        assertFalse(ourTreeSet.remove(-19));
    }

    @Test
    public void removeTrue_one_El() {
        ourTreeSet.add(10);
        assertTrue(ourTreeSet.remove(10));
    }

    @Test
    public void testSize_EmptySet_Zero() {
        assertEquals(0, ourTreeSet.size());
    }

    @Test
    public void testAdd_SetWithOneVertex_OneElement() {
        ourTreeSet.add(5);
        assertEquals(1, ourTreeSet.size());
    }

    @Test
    public void testAdd_SetWithTreeElements_TreeElement() {
        ourTreeSet.add(5);
        ourTreeSet.add(1);
        ourTreeSet.add(2);
        assertEquals(3, ourTreeSet.size());
    }

    @Test
    public void testContains_SetWithTreeElements_True() {
        ourTreeSet.add(5);
        ourTreeSet.add(1);
        ourTreeSet.add(2);
        assertTrue(ourTreeSet.contains(1));
    }

    @org.junit.Test
    public void testContains_SetWithTreeElements_False() {

        ourTreeSet.add(4);
        ourTreeSet.add(5);
        ourTreeSet.add(8);
        assertFalse(ourTreeSet.contains(1));
    }

    @org.junit.Test
    public void testRemove_SetThreeElementsWithoutBothChildrenRemoveLastElement_TwoElements() {
        ourTreeSet.add(10);
        ourTreeSet.add(18);
        ourTreeSet.add(12);
        ourTreeSet.remove(12);
        assertEquals(2, ourTreeSet.size());
        assertFalse(ourTreeSet.contains(5));
    }

    @Test
    public void testRemove_SetThreeElementsWithoutBothChildrenRemoveMiddleElement_TwoElements() {
        ourTreeSet.add(10);
        ourTreeSet.add(18);
        ourTreeSet.add(12);
        ourTreeSet.remove(12);
        assertEquals(2, ourTreeSet.size());
        assertFalse(ourTreeSet.contains(7));
    }

    @Test
    public void testRemove_SetThreeElementsWithoutBothChildrenRemoveFirstElement_TwoElements() {
        ourTreeSet.add(45);
        ourTreeSet.add(24);
        ourTreeSet.add(85);
        ourTreeSet.remove(24);
        assertEquals(2, ourTreeSet.size());
        assertFalse(ourTreeSet.contains(2));
    }

    @Test
    public void testRemove_SetFourElementsWithBothChildrenRemoveLastElement_ThreeElements() {
        ourTreeSet.add(5);
        ourTreeSet.add(45);
        ourTreeSet.add(12);
        ourTreeSet.add(35);
        ourTreeSet.remove(12);
        assertEquals(3, ourTreeSet.size());
        assertFalse(ourTreeSet.contains(4));
    }

    @Test
    public void testRemove_SetFourElementsWithBothChildrenRemoveMiddleElement_ThreeElements() {

        ourTreeSet.add(24);
        ourTreeSet.add(7);
        ourTreeSet.add(11);
        ourTreeSet.add(45);
        ourTreeSet.remove(11);
        assertEquals(3, ourTreeSet.size());
        assertTrue(ourTreeSet.contains(24));
    }

    @Test
    public void testRemove_SetFourElementsWithBothChildrenRemoveFirstElement_ThreeElements() {
        Integer sample01 = 10;
        Integer sample02 = 18;
        Integer sample03 = 19;
        Integer sample04 = 17;
        ourTreeSet.add(sample01);
        ourTreeSet.add(sample02);
        ourTreeSet.add(sample03);
        ourTreeSet.add(sample04);
        ourTreeSet.remove(sample01);
        assertEquals(3, ourTreeSet.size());
        assertFalse(ourTreeSet.contains(sample01));
    }

}



