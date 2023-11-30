import org.junit.Test;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {

    private static class Result {
        private int errorLines;
        private double sum;
        private /*static final*/ String RESULT = "sum = ";
        private /*static final*/ String ERROR = "error-lines = ";

        public Result(){
            this.errorLines = 0;
            this.sum = 0;
        }
        public Result(int errorLines, double sum){
            this.errorLines = errorLines;
            this.sum = sum;
        }

        public int getErrorLines() {
            return errorLines;
        }

        public double getSum() {
            return sum;
        }


    }

    private static Result getResult(String fileName) throws MissingResourceException{
        Locale locale = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();

        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";


        while(keys.hasMoreElements()){
            String key = keys.nextElement();

            Pattern pat = Pattern.compile(KEY_REG_EXP);
            Matcher mat = pat.matcher(key);

            int tailIndex = 1;

            if(mat.matches()){

                //group???
                String iString = mat.group(tailIndex);
                String jStringgit ;
            }

        }


    }






    @Test
}
