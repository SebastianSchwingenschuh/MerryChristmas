import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ChristmasTree implements ChristmasSubject {
    private List<ChristmasObserver> observers;
    private PriorityQueue<Present> presents;
    private int size;

    public ChristmasTree(int size) {
        observers = new ArrayList<ChristmasObserver>();
        presents = new PriorityQueue<>();
        this.setSize(size);
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = Math.max(size, 5);
    }


    public int getPresentCount() {
        if (presents == null) {
            return 0;
        }
        return presents.size();
    }

    public int getTotalPresentSize() {
        if (presents == null) {
            return 0;
        }
        int total = 0;
        for (Present present : presents) {
            total += present.getSize();
        }
        return total;
    }

    public boolean hidePresent(Present present) {
        if (present == null) {
            return false;
        }
        if (!(presents == null) && presents.contains(present)) {
            throw new ChristmasException("Somebody already hid this present!");
        }
        if (getTotalPresentSize() + present.getSize() > getSize()) {
            return false;
        }
        presents.add(present);
        return true;
    }

    public void unwrapPresent() {
        Present unwrappedPresent = presents.poll();
        for(ChristmasObserver observer : observers) {
            observer.gift(unwrappedPresent);
        }
    }

    public void hidePresentsFromWishlist(String filePath, PresentFactory presentFactory) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Present newPresent = presentFactory.createFromString(line);
                hidePresent(newPresent);
            }
        }catch (IOException e){
            throw new ChristmasException("Exception occurred while reading the file!", e);
        }
    }

    @Override
    public void registerObserver(ChristmasObserver christmasObserver) {
        observers.add(christmasObserver);
    }

    @Override
    public void unregisterObserver(ChristmasObserver christmasObserver) {
        observers.remove(christmasObserver);
    }
}
