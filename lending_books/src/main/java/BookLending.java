import java.util.ArrayList;

public class BookLending {

    static StringToIntConversion stringToIntConversion = new StringToIntConversion();

    public static double calculateMean(ArrayList<String> rating) {

        double sum = 0.0;

        ArrayList<Integer> intArray = stringToIntConversion.getIntegerArray(rating);

        for (int intArr: intArray
             ) {
            sum += intArr;
        }
        return sum/intArray.size();
    }



}
