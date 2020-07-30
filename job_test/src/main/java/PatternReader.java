import java.util.ArrayList;
import java.util.HashMap;

public class PatternReader {
    private String fileName = null;
    private ArrayList<CountPattern> countPatternList = new ArrayList<CountPattern>();
    private ArrayList<StringPattern> stringPatternList = new ArrayList<StringPattern>();
    private TextReaderWriter textReaderWriter = new TextReaderWriter();

//--------------------------------------------------------------------------------------------------------------------------------------------------
// args: name of the pattern file
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public PatternReader(String fileName)
    {
        this.fileName = fileName;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// read from pattern file. divide patterns into 2 groups: string ("smt") and count(o1j2), create 2 lists of these groups
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean read()
    {
        if(!textReaderWriter.initTextReader(fileName))
            return false;
        while(textReaderWriter.hasNextString())
        {
            String temp = textReaderWriter.readOneString();
            if(temp.indexOf("\"") == -1) // pattern hasn't got a "
            {//count pattern
                Character ch = null;
                CountPattern countPattern = new CountPattern();
                countPattern.setPatternStringForm(temp);

                char[] dividedPattern = temp.toCharArray();
                HashMap<Character, Integer> patternMap = new HashMap<Character, Integer>();
                for(int i = 0; i < dividedPattern.length; i += 2)
                {
                    ch = new Character(dividedPattern[i]);
                    if(Character.isDigit(dividedPattern[i + 1]))
                    {
                        Integer count = Integer.parseInt(String.valueOf(dividedPattern[i + 1]));
                        patternMap.put(ch, patternMap.getOrDefault(ch, 0) + count); // o1o1 == o2
                    }
                }
                countPattern.setPattern(patternMap);
                countPatternList.add(countPattern);
            }
            else
            { //string pattern
                StringPattern newPattern = new StringPattern();
                String stringWithoutBraces = temp.substring(1, temp.length() - 1);
                newPattern.setPattern(stringWithoutBraces);
                stringPatternList.add(newPattern);
            }
        }
        textReaderWriter.closeScanner();
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<CountPattern> getCountPatternList() {
        return countPatternList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<StringPattern> getStringPatternList() {
        return stringPatternList;
    }
}
