import java.util.ArrayList;

public class StringToIntConversion {

    public StringToIntConversion(){

    }

    public ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for(String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue.trim()));
            } catch(NumberFormatException nfe) {

                System.out.println("NumberFormat, Parsing failed! " + stringValue + " can not be an integer");
            }
        }
        return result;
    }
}
