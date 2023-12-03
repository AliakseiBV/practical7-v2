import org.junit.Assert;
import org.junit.Test;
import java.io.FileNotFoundException;
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
        private static final String RESULT = "sum = ";
        private static final String ERROR = "error-lines = ";

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

        int tailIndex = 1;
        int errors = 0;
        String value = "value";
        Result result = new Result();

        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            Pattern pat = Pattern.compile(KEY_REG_EXP);
            Matcher mat = pat.matcher(key);


            if(mat.matches()){

                String iString = mat.group(tailIndex);
                String jString = rb.getString(key).trim();

                Matcher iMatcher = pat.matcher(iString);
                Pattern pattern = Pattern.compile(NUM_REG_EXP);
                Matcher jMatcher = pattern.matcher(jString);

                if(iMatcher.matches() && jMatcher.matches()){
                    String valueIJ = value + iString + jString;

                    try {
                        System.out.println(valueIJ);

                    }catch (IndexOutOfBoundsException | MissingResourceException e){
                        errors++;
                    }
                }else {
                    errors++;
                }
            }
        }
        return result;
    }
    private double DELTA = 0.000001;


    @Test
    public void testMainScenario1() throws FileNotFoundException {
        Result result1 = getResult("/home/ave/university-2/practical7-v2/src/in1");
        Assert.assertEquals(3, result1.getErrorLines());
        Assert.assertEquals(8.24, result1.getSum(), DELTA);
    }

    @Test
    public void testMainScenario2() throws FileNotFoundException {
        Result result1 = getResult("/home/ave/university-2/practical7-v2/src/in2");
        Assert.assertEquals(9, result1.getErrorLines());
        Assert.assertEquals(30.242, result1.getSum(), DELTA);
    }

    @Test
    public void testMainScenario3() throws FileNotFoundException {
        Result result1 = getResult("/home/ave/university-2/practical7-v2/src/in3");
        Assert.assertEquals(0, result1.getErrorLines());
        Assert.assertEquals(1.9, result1.getSum(), DELTA);
    }



}
