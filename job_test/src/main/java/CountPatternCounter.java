import java.util.ArrayList;
import java.util.HashMap;

public class CountPatternCounter extends Counter{
    private ArrayList<String> wordsList = new ArrayList<>();
    private HashMap<Character, ArrayList<Integer>> symbolCountTable = new HashMap<>();
    private ArrayList<CountPattern> countPatternList;
    private TextReaderWriter textReaderWriter = new TextReaderWriter();

//--------------------------------------------------------------------------------------------------------------------------------------------------
// args: list of count patterns (o1k2), input file name
//--------------------------------------------------------------------------------------------------------------------------------------------------
    CountPatternCounter(ArrayList<CountPattern> countPatternList, String fileName)
    {
        super(fileName);
        this.countPatternList = countPatternList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// found unique letters from patterns, save them as keys
//--------------------------------------------------------------------------------------------------------------------------------------------------
    protected void calculateSymbolCountTableKeys()
    {
        for (CountPattern countPattern : countPatternList) {
            for (Character ch : countPattern.getPattern().keySet()) {
                if(!symbolCountTable.containsKey(ch))
                {
                    symbolCountTable.put(ch, new ArrayList<>());
                }
            }
        }
    }


//--------------------------------------------------------------------------------------------------------------------------------------------------
// write a table where for every key (its a letter from some pattern) we have a list of indexes of words that have such letter.
// By that index we can found a word in the wordList
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean calculateSymbolCountTable() {

        calculateSymbolCountTableKeys();
        if(!textReaderWriter.initTextReader(fileName))
            return false;

        while(textReaderWriter.hasNextString()) {
            String temp = textReaderWriter.readOneString();
            String[] wordsTable = temp.split(" ");
            for (int i = 0; i < wordsTable.length; i ++) // go throw words in a 1 line
            {
                for(Character ch : symbolCountTable.keySet())
                {
                    if(containsPattern(ch.toString(), wordsTable[i]))
                    {
                        int index = wordsList.indexOf(wordsTable[i]);
                        if(index < 0)
                        {
                            index = wordsList.size();
                            wordsList.add(wordsTable[i]);
                        }

                        symbolCountTable.get(ch).add(index);
                    }
                }
            }

        }
        textReaderWriter.closeScanner();
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// count words for all patterns that were defined by numbers (o2i3)
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean count()
    {
        if(!calculateSymbolCountTable())
        {
            return false;
        }
        ArrayList<ArrayList<Integer>> wordsWithNCharsList = new ArrayList<ArrayList<Integer>>();
        for (CountPattern countPattern : countPatternList) {
            for (Character ch : countPattern.getPattern().keySet()) {
                ArrayList<Integer> wordsWithNChars = foundNRepeats(ch,  countPattern.getPattern().get(ch), symbolCountTable.get(ch));
                wordsWithNCharsList.add(wordsWithNChars);
            }
            // join all chars from the pattern
            ArrayList<Integer> tempList = wordsWithNCharsList.get(0);
            for (int i = 1; i < wordsWithNCharsList.size(); i ++) {
                tempList = joinArrays(tempList, wordsWithNCharsList.get(i));
            }
            countPattern.setCountWords(tempList.size());
            tempList.clear();
            wordsWithNCharsList.clear();
        }

        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// make a list with elements that are in both given arrays
//--------------------------------------------------------------------------------------------------------------------------------------------------
    protected ArrayList<Integer> joinArrays(ArrayList<Integer> arr1, ArrayList<Integer> arr2)
    {
        ArrayList<Integer> joinedArray = new ArrayList<Integer>();
        for (Integer elem : arr1) {
            if(arr2.indexOf(elem) != -1)
            {
                joinedArray.add(elem);
            }
        }
        return joinedArray;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// return a list with indexes of words that have letter ch count times
//--------------------------------------------------------------------------------------------------------------------------------------------------
    protected ArrayList<Integer> foundNRepeats(Character ch, Integer count, ArrayList<Integer> arr)
    {
        if(count == 1)
        {
            return arr;
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i ++)
        {
            int counter = 0, fromIndex = 0;
            fromIndex = wordsList.get(arr.get(i)).indexOf(ch, fromIndex);
            while(fromIndex != -1)
            {
                counter ++;
                if(fromIndex + 1 >= wordsList.get(arr.get(i)).length())
                    break;
                fromIndex = wordsList.get(arr.get(i)).indexOf(ch, fromIndex + 1);

            }
            if(counter >= count)
                result.add(arr.get(i));

        }
       return result;
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
    public ArrayList<String> getWordsList() {
        return wordsList;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public HashMap<Character, ArrayList<Integer>> getSymbolCountTable() {
        return symbolCountTable;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public void clearSymbolCountTable()
    {
        symbolCountTable.clear();
    }
}
