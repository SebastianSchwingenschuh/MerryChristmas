import java.util.HashMap;
import java.util.HashSet;

public class Giftee implements ChristmasObserver {
    private String name;
    private int age;
    private HashSet<Present> presents;

    public Giftee(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPresentCount() {
        return this.presents.size();
    }
    
    public boolean hasPresent(Present present) {
        return this.presents.contains(present);
    }

    @Override
    public void gift(Present present) {
        
    }
}
