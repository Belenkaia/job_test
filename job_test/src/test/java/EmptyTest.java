import junit.framework.TestCase;

public class EmptyTest extends TestCase {
    private PatternReader patternReader;
    private CountPatternCounter countPatternCounter;
    private StringPatternCounter stringPatternCounter;
    private ResultWriter resultWriter;

    public void testEmptyPatternFile() {
        assertTrue(initTest("src/main/resources/Output", "src/main/resources/textInput", "src/main/resources/patternEmpty"));
    }

    public void testEmptyInputFile() {
        assertTrue(initTest("src/main/resources/output", "src/main/resources/textEmpty", "src/main/resources/patternInput"));

    }

    public void testEmptyInputAndPatternFiles() {
        assertTrue(initTest("src/main/resources/output", "src/main/resources/textEmpty", "src/main/resources/patternEmpty"));
    }
    private boolean initTest(String fileOutputName, String fileInputName, String patternFileName)
    {
        patternReader = new PatternReader(patternFileName);
        patternReader.read();
        countPatternCounter = new CountPatternCounter(patternReader.getCountPatternList(), fileInputName);
        stringPatternCounter = new StringPatternCounter(patternReader.getStringPatternList(), fileInputName);
        countPatternCounter.count();
        stringPatternCounter.count();
        resultWriter = new ResultWriter(fileOutputName, patternFileName, countPatternCounter.getCountPatternList(), stringPatternCounter.getStringPatternList());
        return resultWriter.writeResult();
    }
}