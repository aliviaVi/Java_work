package collection;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        ourTreeSet.remove(10);
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
        ourTreeSet.remove2(11);
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

    @Test
    public void iterator_test() {
        ourTreeSet.add(3);
        ourTreeSet.add(1);
        ourTreeSet.add(5);
        ourTreeSet.add(2);
        ourTreeSet.add(4);

        List<Integer> exp = Arrays.asList(1, 2, 3, 4, 5);
        OurTreeSetIterator<Integer> act = new OurTreeSetIterator<>(ourTreeSet);
        List<Integer> res = new ArrayList<>();
        while (act.hasNext()) {
            res.add(act.next());

        }
        assertEquals(exp, res);

    }
    @Test
    public void addAll_updates_set_with_element_from_other() {
        OurSet<Integer> set = new OurTreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        OurSet<Integer> otherSet = new OurTreeSet<>();
        otherSet.add(1);
        otherSet.add(4);
        otherSet.add(5);

        Integer[] expected = {1, 2, 3, 4, 5};

        set.addAll(otherSet);

        assertEquals(set.size(), expected.length);
        for (Integer element : expected) {
            assertTrue(set.contains(element));
        }
    }
    @Test
    public void removeAll_removes_elemnts_that_are_in_other_set() {
        OurSet<Integer> set = new OurTreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        OurSet<Integer> otherSet = new OurTreeSet<>();
        otherSet.add(1);
        otherSet.add(2);
        otherSet.add(4);
        otherSet.add(5);

        Integer[] expected = {3};

        set.removeAll(otherSet);

        assertEquals(set.size(), expected.length);
        for (Integer element : expected) {
            assertTrue(set.contains(element));
        }
    }
    @Test
    public void retainAll_keeps_only_elements_that_are_in_other_set() {
        OurSet<Integer> set = new OurTreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        OurSet<Integer> otherSet = new OurTreeSet<>();
        otherSet.add(1);
        otherSet.add(5);
        otherSet.add(4);
        otherSet.add(2);

        Integer[] expected = {1, 2};

        boolean res = set.retainAll(otherSet);

        assertEquals(set.size(), expected.length);
        assertTrue(res);
        for (Integer element : expected) {
            assertTrue(set.contains(element));
        }
    }

    @Test
    public void retainAll_firstSetIsPlacedInOther() {
        OurSet<Integer> set = new OurTreeSet<>();
        set.add(1);
        set.add(2);

        OurSet<Integer> otherSet = new OurTreeSet<>();
        otherSet.add(1);
        otherSet.add(5);
        otherSet.add(4);
        otherSet.add(2);

        Integer[] expected = {1, 2};

        boolean res = set.retainAll(otherSet);

        assertEquals(expected.length, set.size());
        assertFalse(res);
        for (Integer element : expected) {
            assertTrue(set.contains(element));
        }
    }
}



