import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChristmasTreeTest {
    @Test
    void testSizeHideUnwrap() {
        ChristmasTree smallChristmasTree = new ChristmasTree(-1);
        assertEquals(5, smallChristmasTree.getSize());
        assertEquals(0, smallChristmasTree.getPresentCount());
        assertEquals(0, smallChristmasTree.getTotalPresentSize());
        assertEquals(true, smallChristmasTree instanceof ChristmasSubject);

        ChristmasTree anotherSmallChristmasTree = new ChristmasTree(3);
        assertEquals(5, anotherSmallChristmasTree.getSize());
        assertEquals(0, anotherSmallChristmasTree.getPresentCount());
        assertEquals(0, anotherSmallChristmasTree.getTotalPresentSize());

        final ChristmasTree christmasTree = new ChristmasTree(10);
        assertEquals(10, christmasTree.getSize());
        assertEquals(0, christmasTree.getPresentCount());
        assertEquals(0, christmasTree.getTotalPresentSize());

        assertDoesNotThrow(() -> {
            assertEquals(false, christmasTree.hidePresent(null));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(0, christmasTree.getPresentCount());
        assertEquals(0, christmasTree.getTotalPresentSize());

        Present catan = new Present("Catan Junior", 3);
        assertDoesNotThrow(() -> {
            assertEquals(true, christmasTree.hidePresent(catan));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(1, christmasTree.getPresentCount());
        assertEquals(3, christmasTree.getTotalPresentSize());

        ChristmasException e = assertThrows(ChristmasException.class, () -> {
            assertEquals(false, christmasTree.hidePresent(catan));
        });
        assertEquals(RuntimeException.class, e.getClass().getSuperclass());
        assertEquals("Somebody already hid this present!", e.getMessage());

        assertEquals(10, christmasTree.getSize());
        assertEquals(1, christmasTree.getPresentCount());
        assertEquals(3, christmasTree.getTotalPresentSize());

        Present skateboard = new Present("Skateboard", 4);
        assertDoesNotThrow(() -> {
            assertEquals(true, christmasTree.hidePresent(skateboard));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(2, christmasTree.getPresentCount());
        assertEquals(7, christmasTree.getTotalPresentSize());

        Present saxophone = new Present("Saxophone", 4);
        assertDoesNotThrow(() -> {
            assertEquals(false, christmasTree.hidePresent(saxophone));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(2, christmasTree.getPresentCount());
        assertEquals(7, christmasTree.getTotalPresentSize());

        Present videogame = new Present("Bonestorm", 2, "Bart");
        assertDoesNotThrow(() -> {
            assertEquals(true, christmasTree.hidePresent(videogame));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(3, christmasTree.getPresentCount());
        assertEquals(9, christmasTree.getTotalPresentSize());

        Present slingshot = new Present("Slingshot", 2, "Bart");
        assertDoesNotThrow(() -> {
            assertEquals(false, christmasTree.hidePresent(slingshot));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(3, christmasTree.getPresentCount());
        assertEquals(9, christmasTree.getTotalPresentSize());

        Present pacifier = new Present("Pacifier", 1);
        assertDoesNotThrow(() -> {
            assertEquals(true, christmasTree.hidePresent(pacifier));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(4, christmasTree.getPresentCount());
        assertEquals(10, christmasTree.getTotalPresentSize());

        e = assertThrows(ChristmasException.class, () -> {
            assertEquals(false, christmasTree.hidePresent(videogame));
        });
        assertEquals(RuntimeException.class, e.getClass().getSuperclass());
        assertEquals("Somebody already hid this present!", e.getMessage());

        assertEquals(10, christmasTree.getSize());
        assertEquals(4, christmasTree.getPresentCount());
        assertEquals(10, christmasTree.getTotalPresentSize());

        Present bowlingBall = new Present("Bowling Ball", 3);
        assertDoesNotThrow(() -> {
            assertEquals(false, christmasTree.hidePresent(bowlingBall));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(4, christmasTree.getPresentCount());
        assertEquals(10, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(3, christmasTree.getPresentCount());
        assertEquals(9, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(2, christmasTree.getPresentCount());
        assertEquals(7, christmasTree.getTotalPresentSize());

        assertDoesNotThrow(() -> {
            assertEquals(true, christmasTree.hidePresent(bowlingBall));
        });

        assertEquals(10, christmasTree.getSize());
        assertEquals(3, christmasTree.getPresentCount());
        assertEquals(10, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(2, christmasTree.getPresentCount());
        assertEquals(7, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(1, christmasTree.getPresentCount());
        assertEquals(4, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(0, christmasTree.getPresentCount());
        assertEquals(0, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();

        assertEquals(10, christmasTree.getSize());
        assertEquals(0, christmasTree.getPresentCount());
        assertEquals(0, christmasTree.getTotalPresentSize());
    }
    @Test
    void testObservers() {
        Giftee homer = new Giftee("Homer", 39);
        Giftee marge = new Giftee("Marge", 36);
        Giftee bart = new Giftee("Bart", 10);
        Giftee lisa = new Giftee("Lisa", 8);
        Giftee maggie = new Giftee("Maggie", 1);
        Giftee abe = new Giftee("Abe", 83);
        Giftee moe = new Giftee("Moe", 54);

        ChristmasTree christmasTree = new ChristmasTree(35);
        christmasTree.registerObserver(homer);
        christmasTree.registerObserver(marge);
        christmasTree.registerObserver(bart);
        christmasTree.registerObserver(lisa);
        christmasTree.registerObserver(maggie);
        christmasTree.registerObserver(abe);

        christmasTree.registerObserver(moe);
        christmasTree.unregisterObserver(moe); //moe is uninvited again...

        Present catan = new Present("Catan Junior", 3);
        catan.setMinAge(6);
        catan.setMaxAge(10);

        Present skateboard = new Present("Skateboard", 4, "Bart");
        skateboard.setMinAge(8);

        Present saxophone = new Present("Saxophone", 4, "Lisa");
        saxophone.setMinAge(6);

        Present videogame = new Present("Bonestorm", 2, "Bart");
        videogame.setMinAge(18);

        Present pacifier = new Present("Pacifier", 1);
        pacifier.setMaxAge(3);

        Present bowlingBall = new Present("Bowling Ball", 3);
        bowlingBall.setMinAge(12);

        Present dog = new Present("Santas Little Helper", 5);

        Present plushie = new Present("Teddy Bear", 2, "Maggie");

        Present teeth = new Present("False Teeth", 1);
        teeth.setMinAge(60);

        Present beer = new Present("Duff", 6, "Homer");
        beer.setMinAge(21);

        Present elephant = new Present("Stampy", 10, "Bart");

        Present cigarettes = new Present("Laramie Cigarettes", 3, "Patty");
        cigarettes.setMinAge(18);

        assertDoesNotThrow(() -> {
            christmasTree.hidePresent(catan);
            christmasTree.hidePresent(skateboard);
            christmasTree.hidePresent(saxophone);
            christmasTree.hidePresent(videogame);
            christmasTree.hidePresent(pacifier);
            christmasTree.hidePresent(bowlingBall);
            christmasTree.hidePresent(dog);
            christmasTree.hidePresent(plushie);
            christmasTree.hidePresent(teeth);
            christmasTree.hidePresent(beer);
            christmasTree.hidePresent(elephant);
            christmasTree.hidePresent(cigarettes);
        });

        System.out.println(catan);
        System.out.println(skateboard);
        System.out.println(saxophone);
        System.out.println(videogame);
        System.out.println(pacifier);
        System.out.println(bowlingBall);
        System.out.println(dog);
        System.out.println(plushie);
        System.out.println(teeth);
        System.out.println(beer);
        System.out.println(elephant);
        System.out.println(cigarettes);

        assertEquals(35, christmasTree.getSize());
        assertEquals(11, christmasTree.getPresentCount());
        assertEquals(34, christmasTree.getTotalPresentSize());

        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();

        christmasTree.unregisterObserver(abe); //grandpa goes home...

        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();
        christmasTree.unwrapPresent();

        assertEquals(3, homer.getPresentCount());
        assertEquals(false, homer.hasPresent(catan));
        assertEquals(false, homer.hasPresent(skateboard));
        assertEquals(false, homer.hasPresent(saxophone));
        assertEquals(false, homer.hasPresent(videogame));
        assertEquals(false, homer.hasPresent(pacifier));
        assertEquals(true, homer.hasPresent(bowlingBall));
        assertEquals(true, homer.hasPresent(dog));
        assertEquals(false, homer.hasPresent(plushie));
        assertEquals(false, homer.hasPresent(teeth));
        assertEquals(true, homer.hasPresent(beer));
        assertEquals(false, homer.hasPresent(elephant));
        assertEquals(false, homer.hasPresent(cigarettes));

        assertEquals(2, marge.getPresentCount());
        assertEquals(false, marge.hasPresent(catan));
        assertEquals(false, marge.hasPresent(skateboard));
        assertEquals(false, marge.hasPresent(saxophone));
        assertEquals(false, marge.hasPresent(videogame));
        assertEquals(false, marge.hasPresent(pacifier));
        assertEquals(true, marge.hasPresent(bowlingBall));
        assertEquals(true, marge.hasPresent(dog));
        assertEquals(false, marge.hasPresent(plushie));
        assertEquals(false, marge.hasPresent(teeth));
        assertEquals(false, marge.hasPresent(beer));
        assertEquals(false, marge.hasPresent(elephant));
        assertEquals(false, marge.hasPresent(cigarettes));

        assertEquals(3, bart.getPresentCount());
        assertEquals(true, bart.hasPresent(catan));
        assertEquals(true, bart.hasPresent(skateboard));
        assertEquals(false, bart.hasPresent(saxophone));
        assertEquals(false, bart.hasPresent(videogame));
        assertEquals(false, bart.hasPresent(pacifier));
        assertEquals(false, bart.hasPresent(bowlingBall));
        assertEquals(true, bart.hasPresent(dog));
        assertEquals(false, bart.hasPresent(plushie));
        assertEquals(false, bart.hasPresent(teeth));
        assertEquals(false, bart.hasPresent(beer));
        assertEquals(false, bart.hasPresent(elephant));
        assertEquals(false, bart.hasPresent(cigarettes));

        assertEquals(3, lisa.getPresentCount());
        assertEquals(true, lisa.hasPresent(catan));
        assertEquals(false, lisa.hasPresent(skateboard));
        assertEquals(true, lisa.hasPresent(saxophone));
        assertEquals(false, lisa.hasPresent(videogame));
        assertEquals(false, lisa.hasPresent(pacifier));
        assertEquals(false, lisa.hasPresent(bowlingBall));
        assertEquals(true, lisa.hasPresent(dog));
        assertEquals(false, lisa.hasPresent(plushie));
        assertEquals(false, lisa.hasPresent(teeth));
        assertEquals(false, lisa.hasPresent(beer));
        assertEquals(false, lisa.hasPresent(elephant));
        assertEquals(false, lisa.hasPresent(cigarettes));

        assertEquals(3, maggie.getPresentCount());
        assertEquals(false, maggie.hasPresent(catan));
        assertEquals(false, maggie.hasPresent(skateboard));
        assertEquals(false, maggie.hasPresent(saxophone));
        assertEquals(false, maggie.hasPresent(videogame));
        assertEquals(true, maggie.hasPresent(pacifier));
        assertEquals(false, maggie.hasPresent(bowlingBall));
        assertEquals(true, maggie.hasPresent(dog));
        assertEquals(true, maggie.hasPresent(plushie));
        assertEquals(false, maggie.hasPresent(teeth));
        assertEquals(false, maggie.hasPresent(beer));
        assertEquals(false, maggie.hasPresent(elephant));
        assertEquals(false, maggie.hasPresent(cigarettes));

        assertEquals(1, abe.getPresentCount());
        assertEquals(false, abe.hasPresent(catan));
        assertEquals(false, abe.hasPresent(skateboard));
        assertEquals(false, abe.hasPresent(saxophone));
        assertEquals(false, abe.hasPresent(videogame));
        assertEquals(false, abe.hasPresent(pacifier));
        assertEquals(false, abe.hasPresent(bowlingBall));
        assertEquals(false, abe.hasPresent(dog));
        assertEquals(false, abe.hasPresent(plushie));
        assertEquals(true, abe.hasPresent(teeth));
        assertEquals(false, abe.hasPresent(beer));
        assertEquals(false, abe.hasPresent(elephant));
        assertEquals(false, abe.hasPresent(cigarettes));

        assertEquals(0, moe.getPresentCount());
        assertEquals(false, moe.hasPresent(catan));
        assertEquals(false, moe.hasPresent(skateboard));
        assertEquals(false, moe.hasPresent(saxophone));
        assertEquals(false, moe.hasPresent(videogame));
        assertEquals(false, moe.hasPresent(pacifier));
        assertEquals(false, moe.hasPresent(bowlingBall));
        assertEquals(false, moe.hasPresent(dog));
        assertEquals(false, moe.hasPresent(plushie));
        assertEquals(false, moe.hasPresent(teeth));
        assertEquals(false, moe.hasPresent(beer));
        assertEquals(false, moe.hasPresent(elephant));
        assertEquals(false, moe.hasPresent(cigarettes));
    }

    @Test
    void testReadFromNotExistingWishlist() {
        ChristmasTree christmasTree = new ChristmasTree(20);
        ChristmasException e = assertThrows(ChristmasException.class, () -> {
            christmasTree.hidePresentsFromWishlist("input/does_not_exist.txt", new SantasWorkshop());
        });
        assertEquals("Exception occurred while reading the file!", e.getMessage());
        assertEquals(RuntimeException.class, e.getClass().getSuperclass());
        assertEquals(true, e.getCause() instanceof IOException);
    }

    @Test
    void testObserversWithWishlist() {
        Giftee homer = new Giftee("Homer", 39);
        Giftee marge = new Giftee("Marge", 36);
        Giftee bart = new Giftee("Bart", 10);
        Giftee lisa = new Giftee("Lisa", 8);
        Giftee maggie = new Giftee("Maggie", 1);
        Giftee abe = new Giftee("Abe", 83);
        Giftee patty = new Giftee("Patty", 54);

        ChristmasTree christmasTree = new ChristmasTree(35);
        christmasTree.registerObserver(homer);
        christmasTree.registerObserver(marge);
        christmasTree.registerObserver(bart);
        christmasTree.registerObserver(lisa);
        christmasTree.registerObserver(maggie);
        //grandpa abe is not invited this time, instead patty comes...
        christmasTree.registerObserver(patty);

        assertDoesNotThrow(() -> {
            christmasTree.hidePresentsFromWishlist("input/wishlist.txt", new SantasWorkshop());
        });

        assertEquals(35, christmasTree.getSize());
        assertEquals(11, christmasTree.getPresentCount());
        assertEquals(34, christmasTree.getTotalPresentSize());

        while(christmasTree.getPresentCount() > 0) {
            christmasTree.unwrapPresent();
        }

        assertEquals(3, homer.getPresentCount());
        assertEquals(2, marge.getPresentCount());
        assertEquals(3, bart.getPresentCount());
        assertEquals(3, lisa.getPresentCount());
        assertEquals(3, maggie.getPresentCount());
        assertEquals(0, abe.getPresentCount());
        assertEquals(3, patty.getPresentCount());
    }
}