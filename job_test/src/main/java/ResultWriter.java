import java.util.ArrayList;

public class ResultWriter {
    private String outputFileName;
    private String patternFileName;
    private ArrayList<CountPattern> countPatternList;
    private ArrayList<StringPattern> stringPatternList;
    private TextReaderWriter textReaderWriter = new TextReaderWriter();

//--------------------------------------------------------------------------------------------------------------------------------------------------
// args: names of pattern and output files, list of patterns that were defined in "" ("smt") and list of patterns that was defined by number(01h2)
//--------------------------------------------------------------------------------------------------------------------------------------------------
    ResultWriter(String outputFileName, String patternFileName, ArrayList<CountPattern> countPatternList, ArrayList<StringPattern> stringPatternList)
    {
        this.outputFileName = outputFileName;
        this.patternFileName = patternFileName;
        this.countPatternList = countPatternList;
        this.stringPatternList = stringPatternList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// read pattern file, then found that pattern in list and write to output file a string with pattern and number of words
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean writeResult() {
        if(!textReaderWriter.initTextReader(patternFileName))
            return false;
        if(!textReaderWriter.initTextWriter(outputFileName))
            return false;

        while (textReaderWriter.hasNextString()) {
            String temp = textReaderWriter.readOneString();
            if (temp.indexOf("\"") == -1) // pattern hasn't got a "
            {//count pattern
                for (CountPattern countPattern : countPatternList) {
                    if(countPattern.getPatternStringForm().equals(temp))
                    {
                        textReaderWriter.writeText(temp + " " + countPattern.getCountWords());
                    }
                }
            } else {// string pattern
                String stringWithoutBraces = temp.substring(1, temp.length() - 1);
                for (StringPattern stringPattern : stringPatternList) {
                    if(stringPattern.getPattern().equals(stringWithoutBraces))
                    {
                        textReaderWriter.writeText(temp + " " + stringPattern.getCountWords());
                    }
                }

            }
        }
        textReaderWriter.closeScanner();
        textReaderWriter.closeWriter();
        return true;
        }

}
