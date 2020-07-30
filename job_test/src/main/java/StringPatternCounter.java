import java.util.ArrayList;

public class StringPatternCounter extends Counter{
    private ArrayList<StringPattern> stringPatternList;
    private TextReaderWriter textReaderWriter = new TextReaderWriter();

//--------------------------------------------------------------------------------------------------------------------------------------------------
// args: stringPatternList - list of the patterns that were defined in "", fileInput - name of the input file
//--------------------------------------------------------------------------------------------------------------------------------------------------
    StringPatternCounter(ArrayList<StringPattern> stringPatternList, String fileInput)
    {
        super(fileInput);
        this.stringPatternList = stringPatternList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<StringPattern> getStringPatternList() {
        return stringPatternList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// count words for all patterns that were defined in ""
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean count()
    {
        if(!textReaderWriter.initTextReader(fileName))
            return false;

        while(textReaderWriter.hasNextString()) {
            String temp = textReaderWriter.readOneString();
            String[] wordsTable = temp.split(" ");
            for (int i = 0; i < wordsTable.length; i ++) // go throw words in a 1 line
            {
                for (StringPattern stringPattern : stringPatternList) { // go throw patterns
                    if(containsPattern(stringPattern.getPattern(), wordsTable[i]))
                    {
                        stringPattern.incrementCountWords();
                    }
                }
            }
        }
        return true;
    }
}
