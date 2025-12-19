import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SantasWorkshopTest {
    @Test
    void testCreateFromString() {
        PresentFactory presentFactory = new SantasWorkshop();

        Present present = presentFactory.createFromString("[====]* Skateboard (8-30) for Bart");
        assertEquals(4, present.getSize());
        assertEquals("Skateboard", present.getContent());
        assertEquals(8, present.getMinAge());
        assertEquals(30, present.getMaxAge());
        assertEquals("Bart", present.getRecipient());

        present = presentFactory.createFromString("[=]* Pacifier (1-3)");
        assertEquals(1, present.getSize());
        assertEquals("Pacifier", present.getContent());
        assertEquals(1, present.getMinAge());
        assertEquals(3, present.getMaxAge());
        assertEquals("", present.getRecipient());

        present = presentFactory.createFromString("[====]* Saxophone (6+) for Lisa");
        assertEquals(4, present.getSize());
        assertEquals("Saxophone", present.getContent());
        assertEquals(6, present.getMinAge());
        assertEquals(99, present.getMaxAge());
        assertEquals("Lisa", present.getRecipient());

        present = presentFactory.createFromString("[=====]* Santas Little Helper (1+)");
        assertEquals(5, present.getSize());
        assertEquals("Santas Little Helper", present.getContent());
        assertEquals(1, present.getMinAge());
        assertEquals(99, present.getMaxAge());
        assertEquals("", present.getRecipient());

        present = presentFactory.createFromString("[==]* Anti Aging Creme For Marge");
        assertEquals(null, present);

        present = presentFactory.createFromString("Invisibility (10+)");
        assertEquals(null, present);
    }
}