public interface ChristmasSubject {
    void registerObserver(ChristmasObserver christmasObserver);
    
    void unregisterObserver(ChristmasObserver christmasObserver);
}
