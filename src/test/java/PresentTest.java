import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PresentTest {
    @Test
    void testGettersSettersAndConstructor() {
        Present present = new Present("Catan Junior", 3);
        assertEquals("Catan Junior", present.getContent());
        assertEquals(3, present.getSize());
        assertEquals(1, present.getMinAge());
        assertEquals(99, present.getMaxAge());
        assertEquals("", present.getRecipient());

        present.setMinAge(6);
        present.setMaxAge(10);

        assertEquals(6, present.getMinAge());
        assertEquals(10, present.getMaxAge());

        present.setMinAge(5);
        assertEquals(5, present.getMinAge());

        present.setMinAge(11);
        assertEquals(5, present.getMinAge());

        present.setMinAge(7);
        assertEquals(7, present.getMinAge());

        present.setMinAge(0);
        assertEquals(7, present.getMinAge());

        present.setMinAge(-1);
        assertEquals(7, present.getMinAge());

        present.setMinAge(1);
        assertEquals(1, present.getMinAge());

        present.setMaxAge(18);
        assertEquals(18, present.getMaxAge());

        present.setMinAge(11);
        assertEquals(11, present.getMinAge());

        present.setMaxAge(10);
        assertEquals(18, present.getMaxAge());

        present.setMaxAge(99);
        assertEquals(99, present.getMaxAge());

        present.setMaxAge(100);
        assertEquals(99, present.getMaxAge());

        present = new Present("Lego Technic Truck", 0);
        assertEquals("Lego Technic Truck", present.getContent());
        assertEquals(1, present.getSize());
        assertEquals("", present.getRecipient());

        present = new Present("Nice Watch", -1, "Mom");
        assertEquals("Nice Watch", present.getContent());
        assertEquals(1, present.getSize());
        assertEquals("Mom", present.getRecipient());

        present = new Present("Video Game", 2, "Cousin");
        assertEquals("Video Game", present.getContent());
        assertEquals(2, present.getSize());
        assertEquals("Cousin", present.getRecipient());
    }

    @Test
    void testComparator() {
        Present smallestPresent = new Present("Nice Watch", 1, "Mom");

        assertEquals(true, smallestPresent instanceof Comparable<Present>);

        Present smallPresent = new Present("Electric Shaver", 2, "Dad");
        Present middlePresent = new Present("Catan", 3);
        Present largePresent = new Present("Nintendo Console", 4);
        Present hugePresent = new Present("Bike", 5);

        LinkedList<Present> presents = new LinkedList<>();
        presents.add(smallestPresent);
        presents.add(smallPresent);
        presents.add(middlePresent);
        presents.add(largePresent);
        presents.add(hugePresent);

        Collections.shuffle(presents);

        Collections.sort(presents);

        assertEquals(smallestPresent, presents.get(0));
        assertEquals(smallPresent, presents.get(1));
        assertEquals(middlePresent, presents.get(2));
        assertEquals(largePresent, presents.get(3));
        assertEquals(hugePresent, presents.get(4));
    }

    @Test
    void testToString() {
        Present catan = new Present("Catan Junior", 3);

        assertEquals("[===]* Catan Junior (1+)", catan.toString());

        catan.setMinAge(6);
        catan.setMaxAge(10);

        assertEquals("[===]* Catan Junior (6-10)", catan.toString());

        catan.setMinAge(7);
        catan.setMaxAge(3);

        assertEquals("[===]* Catan Junior (7-10)", catan.toString());

        catan.setMaxAge(99);
        catan.setMinAge(11);

        assertEquals("[===]* Catan Junior (11+)", catan.toString());

        Present watch = new Present("Nice Watch", 0, "Mom");
        assertEquals("[=]* Nice Watch (1+) for Mom", watch.toString());

        watch.setMaxAge(98);
        assertEquals("[=]* Nice Watch (1-98) for Mom", watch.toString());

        Present bike = new Present("Mountain Bike", 7);
        bike.setMinAge(14);
        assertEquals("[=======]* Mountain Bike (14+)", bike.toString());
    }
}