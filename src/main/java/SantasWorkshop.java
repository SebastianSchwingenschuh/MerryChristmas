import java.util.Objects;

public class SantasWorkshop implements PresentFactory {

    @Override
    public Present createFromString(String s) {
        //Present present = presentFactory.createFromString("[====]* Skateboard (8-30) for Bart");
        // get size
        if(!s.contains("=") || !s.contains("(") || !s.contains(")")) {
            return null;
        }
        int size = s.lastIndexOf("=");
        String[] words = s.split(" ");
        String content = getStrBetween(s, "*", "(").trim(); // falls mehr wörter

        Present newPresent;
        if(Objects.equals(words[words.length - 2], "for")){
            newPresent = new Present(content, size, words[words.length - 1]);
        }
        else {
            newPresent = new Present(content, size);
        }

        String ageStr = getStrBetween(s, "(", ")");
        if(ageStr.contains("+")){
            ageStr = ageStr.replace("+", "");   //+ löschen
            int minAge = Integer.parseInt(ageStr);
            newPresent.setMinAge(minAge);
            return  newPresent;
        }
        else {
            String[] ageStrSplit = ageStr.split("-");
            int minAge = Integer.parseInt(ageStrSplit[0]);
            newPresent.setMinAge(minAge);

            int maxAge = Integer.parseInt(ageStrSplit[1].replace("+", ""));
            newPresent.setMaxAge(maxAge);
        }
        return newPresent;
    }

    private String getStrBetween(String s ,String a, String b) {
        int start = s.indexOf(a);
        int end = s.indexOf(b);
        String res = s.substring(start + 1, end);

        return res;
    }


}
