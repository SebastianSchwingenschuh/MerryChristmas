import java.util.List;
import java.util.PriorityQueue;

public class ChristmasTree implements ChristmasSubject{
    private List<ChristmasObserver> observers;
    private PriorityQueue<Present> presents;
    private int size;

    public ChristmasTree(int size) {
        this.setSize(size);
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }


    public int getPresentCount() {
        return -1;
    }

    public int getTotalPresentSize() {
        return -1;
    }

    public boolean hidePresent(Present present) {
        return false;
    }

    public void unwrapPresent() {
        
    }

    public void hidePresentsFromWishlist(String s, SantasWorkshop santasWorkshop) {
        
    }

    @Override
    public void registerObserver(ChristmasObserver christmasObserver) {
        
    }

    @Override
    public void unregisterObserver(ChristmasObserver christmasObserver) {

    }
}
