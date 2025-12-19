import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GifteeTest {
    @Test
    void testGettersSettersAndConstructor() {
        Giftee bart = new Giftee("Bart", 10);
        assertEquals("Bart", bart.getName());
        assertEquals(10, bart.getAge());
        assertEquals(0, bart.getPresentCount());
        assertEquals(true, bart instanceof ChristmasObserver);

        bart.setAge(11);
        assertEquals(11, bart.getAge());

        bart.setAge(-1);
        assertEquals(1, bart.getAge());

        bart.setAge(12);
        assertEquals(12, bart.getAge());

        bart.setAge(0);
        assertEquals(1, bart.getAge());

        bart.setAge(13);
        assertEquals(13, bart.getAge());

        bart.setAge(100);
        assertEquals(99, bart.getAge());

        Giftee abe = new Giftee("Abe", 101);
        assertEquals("Abe", abe.getName());
        assertEquals(99, abe.getAge());
        assertEquals(0, abe.getPresentCount());

        Giftee maggie = new Giftee("Maggie", 0);
        assertEquals("Maggie", maggie.getName());
        assertEquals(1, maggie.getAge());
        assertEquals(0, maggie.getPresentCount());
    }

    @Test
    void testGift() {
        Present catan = new Present("Catan Junior", 3);
        catan.setMinAge(6);
        catan.setMaxAge(10);

        Present skateboard = new Present("Skateboard", 4, "Bart");
        skateboard.setMinAge(8);

        Present saxophone = new Present("Saxophone", 4, "Lisa");

        Present videogame = new Present("Bonestorm", 2, "Bart");
        videogame.setMinAge(18);

        Present pacifier = new Present("Pacifier", 1);
        pacifier.setMaxAge(3);

        Present slingshot = new Present("Slingshot", 2, "Bart");

        Giftee bart = new Giftee("Bart", 10);
        assertEquals(0, bart.getPresentCount());
        assertEquals(false, bart.hasPresent(catan));
        assertEquals(false, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(catan);

        assertEquals(1, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(false, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(skateboard);

        assertEquals(2, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(saxophone);

        assertEquals(2, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(videogame);

        assertEquals(2, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(pacifier);

        assertEquals(2, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(slingshot));

        bart.gift(slingshot);

        assertEquals(3, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(true, bart.hasPresent(slingshot));

        bart.gift(skateboard);

        assertEquals(3, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(true, bart.hasPresent(slingshot));
    }
}