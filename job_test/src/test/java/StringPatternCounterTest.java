import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;

public class StringPatternCounterTest extends TestCase {
    private String fileInputName = "src/main/resources/textInput";
    private String patternFileName = "src/main/resources/patternInput";
    private PatternReader patternReader = new PatternReader(patternFileName);
    public void testCountFinished() {
        patternReader.read();
        StringPatternCounter stringPatternCounter = new StringPatternCounter(patternReader.getStringPatternList(), fileInputName);
        assertTrue(stringPatternCounter.count());
    }

    public void testCount() {
        patternReader.read();
        ArrayList<StringPattern> stringPatternList = patternReader.getStringPatternList();
        StringPatternCounter stringPatternCounter = new StringPatternCounter(stringPatternList, fileInputName);
        if(stringPatternCounter.count())
        {
        /*    for (StringPattern stringPattern : stringPatternList) {
                System.out.println(stringPattern.getPattern() + " : " + stringPattern.getCountWords());

            }*/
            assertTrue((stringPatternList.get(0).getPattern().equalsIgnoreCase("ми")) && (stringPatternList.get(0).getCountWords() == 0));
            assertTrue((stringPatternList.get(1).getPattern().equalsIgnoreCase("йо")) && (stringPatternList.get(1).getCountWords() == 1));
        }
    }
}