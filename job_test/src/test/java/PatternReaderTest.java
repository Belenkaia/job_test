import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;

public class PatternReaderTest extends TestCase {

    private String patternFileName = "src/main/resources/patternInput";
    private PatternReader patternReader = new PatternReader(patternFileName);
    public void testReadFinished() {

        assertTrue(patternReader.read());
    }
    public void testReadCountPattern() {
        if(patternReader.read())
        {
            ArrayList<CountPattern> countPatternList = patternReader.getCountPatternList();
/*
            for (CountPattern countPattern : countPatternList) {
                System.out.println( countPattern.getPatternStringForm());
                for (Character ch : countPattern.getPattern().keySet()) {
                    System.out.println("test:: " + ch.toString() + " = " + countPattern.getPattern().get(ch));
                }

            }*/

            assertTrue(countPatternList.get(0).getPatternStringForm().equalsIgnoreCase("о2"));
            assertTrue(countPatternList.get(1).getPatternStringForm().equals("т1о1"));

            assertTrue(countPatternList.get(0).getPattern().get(new Character('о')) == 2);
            assertTrue(countPatternList.get(1).getPattern().get(new Character('т')) == 1);
            assertTrue(countPatternList.get(1).getPattern().get(new Character('о')) == 1);
            assertTrue(countPatternList.get(2).getPattern().get(new Character('ф')) == 2);
            assertTrue(countPatternList.get(3).getPattern().get(new Character('1')) == 1);
        }
        else
            assertTrue(false);
    }

    public void testReadStringPattern() {
        if(patternReader.read())
        {
            ArrayList<StringPattern> stringPatternList = patternReader.getStringPatternList();

            /*for (StringPattern stringPattern : stringPatternList) {
                System.out.println(stringPattern.getPattern());

            }*/
            assertTrue(stringPatternList.size() > 0);
            assertTrue(stringPatternList.get(0).getPattern().equalsIgnoreCase(new String("ми")));
            assertTrue(stringPatternList.get(1).getPattern().equalsIgnoreCase(new String("йо")));
        }
        else
            assertTrue(false);
    }
}