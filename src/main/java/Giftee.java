import java.util.HashMap;
import java.util.HashSet;

public class Giftee implements ChristmasObserver {
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 99;

    private String name;
    private int age;
    private HashSet<Present> presents;

    public Giftee(String name, int age) {
        this.setName(name);
        this.setAge(age);
        this.presents = new HashSet<>();
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
        if(age < MIN_AGE){
            this.age = MIN_AGE;
        }
        else if (age > MAX_AGE){
            this.age = MAX_AGE;
        }
    }

    public int getPresentCount() {
        return this.presents.size();
    }
    
    public boolean hasPresent(Present present) {
        return this.presents.contains(present);
    }

    @Override
    public void gift(Present present) {
        if(present == null) {
            return;
        }
        // wenn an anderen adressiert
        if(!present.getRecipient().isEmpty() && !present.getRecipient().equals(this.name)) {
            return;
        }
        // alles andere valid sobald alter passt
        if(present.getMinAge() <= this.age && present.getMaxAge() >= this.age) {
            this.presents.add(present);
        }
    }
}
