import java.util.Objects;

public class Present implements Comparable<Present>{
    private static final int MIN_SIZE = 1;
    private static final int MIN_AGE = 1;
    private static final int MAX_AGE = 99;
    
    private String content;
    private int minAge;
    private int maxAge;
    private int size;
    private String  recipient;

    public Present(String content, int size) {
        this.setContent(content);
        this.setSize(size);
        this.setMinAge(MIN_AGE);
        this.setMaxAge(MAX_AGE);
        this.setRecipient("");
    }

    public Present(String content, int size, String recipient) {
        this(content, size);
        this.setRecipient(recipient);
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        if(minAge < this.getMaxAge() && minAge >= MIN_AGE){
            this.minAge = minAge;
        }
        else {
            this.minAge = Math.max(MIN_AGE, this.getMinAge());
        }
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        if(maxAge > MIN_AGE && maxAge >= this.getMinAge()){
            this.maxAge = Math.min(maxAge, MAX_AGE);
        }
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = Math.max(MIN_SIZE, size);
    }

    public String getRecipient() {
        return recipient;
    }

    private void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        StringBuilder sizeChar = new StringBuilder();
        for (int i = 0; i < this.getSize(); i++) {
            sizeChar.append("=");
        }
        
        //"[=]* Nice Watch (1+) for Mom"
        if(!Objects.equals(this.getRecipient(), "")){
            if (this.getMaxAge() == 99){
                return String.format("[%s]* %s (%d+) for %s", sizeChar, this.getContent(), this.getMinAge(), this.getRecipient());
            }
            return String.format("[%s]* %s (%d-%d) for %s", sizeChar, this.getContent(), this.getMinAge(), this.getMaxAge(), this.getRecipient());
        }
        //"[===]* Catan Junior (6-10)"
        if(this.getMaxAge() == 99){
            return String.format("[%s]* %s (%d+)", sizeChar, this.getContent(), this.getMinAge());    
        }
        return String.format("[%s]* %s (%d-%d)", sizeChar, this.getContent(), this.getMinAge(), this.getMaxAge());
    }

    @Override
    public int compareTo(Present other) {
        return Integer.compare(this.getSize(), other.getSize());
    }
}
