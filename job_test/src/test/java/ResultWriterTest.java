import junit.framework.TestCase;

public class ResultWriterTest extends TestCase {
    private String fileOutputName = "src/main/resources/textOutput";
    private String fileInputName = "src/main/resources/textInput";
    private String patternFileName = "src/main/resources/patternInput";
    private PatternReader patternReader = new PatternReader(patternFileName);
    private CountPatternCounter countPatternCounter;
    private StringPatternCounter stringPatternCounter;
    private ResultWriter resultWriter;
    public ResultWriterTest()
    {
        patternReader.read();
        countPatternCounter = new CountPatternCounter(patternReader.getCountPatternList(), fileInputName);
        stringPatternCounter = new StringPatternCounter(patternReader.getStringPatternList(), fileInputName);
        countPatternCounter.count();
        stringPatternCounter.count();
    }
    public void testWriteResult() {
        resultWriter = new ResultWriter(fileOutputName, patternFileName, countPatternCounter.getCountPatternList(), stringPatternCounter.getStringPatternList());
        assertTrue(resultWriter.writeResult());
    }
}