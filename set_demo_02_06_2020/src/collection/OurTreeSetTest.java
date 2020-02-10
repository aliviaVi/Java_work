package collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class OurTreeSetTest {
    OurTreeSet<Integer> ourTreeSet=new OurTreeSet<>();

    @Test
    public void containsTrue(){
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);

        assertTrue(ourTreeSet.contains(1));
    }
    @Test
    public void containsFalse(){
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        assertFalse(ourTreeSet.contains(5));
    }

    @Test
    public void sizeTrue(){
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        ourTreeSet.add(4);
        assertEquals(3, ourTreeSet.size);
    }
    @Test
    public void sizeFalse(){
        ourTreeSet.add(1);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        assertNotEquals(7, ourTreeSet.size);
    }
    @Test
    public void removeTrue(){
        ourTreeSet.add(10);
        ourTreeSet.add(3);
        ourTreeSet.add(4);
        ourTreeSet.add(12);
        ourTreeSet.add(23);
        ourTreeSet.add(16);
        ourTreeSet.add(11);
        ourTreeSet.add(17);
        assertTrue(ourTreeSet.remove(10));
    }
    @Test
    public void remove_False(){
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


}
