import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

public class CountPatternCounterTest extends TestCase {

    private String fileInputName = "src/main/resources/textInput";
    private String patternFileName = "src/main/resources/patternInput";
    private PatternReader patternReader = new PatternReader(patternFileName);
    private CountPatternCounter countPatternCounter;

    public CountPatternCounterTest()
    {
        patternReader.read();
        countPatternCounter = new CountPatternCounter(patternReader.getCountPatternList(), fileInputName);
    }
    public void testCalculateSymbolCountTableKeys() {
        ArrayList<Character> testAnswer = new ArrayList<>();
        testAnswer.add(new Character('о'));
        testAnswer.add(new Character('т'));
        testAnswer.add(new Character('ф'));
        testAnswer.add(new Character('1'));
        testAnswer.add(new Character('й'));

        countPatternCounter.calculateSymbolCountTableKeys();
        HashMap<Character, ArrayList<Integer>> symbolCountTable = countPatternCounter.getSymbolCountTable();
        for (Character character : symbolCountTable.keySet()) {
            assertTrue(testAnswer.contains(character));
        }
        for (Character character : testAnswer) {
            assertTrue(symbolCountTable.keySet().contains(character));
        }
        countPatternCounter.clearSymbolCountTable();
    }

    public void testCalculateSymbolCountTable() {
        assertTrue(countPatternCounter.calculateSymbolCountTable());
        HashMap<Character, ArrayList<Integer>> testAnswers = new HashMap<>();
        ArrayList<Integer> oList = new ArrayList<>();
        oList.add(1);
        oList.add(3);
        oList.add(4);
        oList.add(5);
        oList.add(7);
        oList.add(8);
        oList.add(10);
        testAnswers.put(new Character('о'), oList);

        ArrayList<Integer> tList = new ArrayList<>();
        tList.add(0);
        tList.add(3);
        tList.add(4);
        tList.add(7);
        testAnswers.put(new Character('т'), tList);

        ArrayList<Integer> fList = new ArrayList<>();
        fList.add(6);
        testAnswers.put(new Character('ф'), fList);

        ArrayList<Integer> oneList = new ArrayList<>();
        oneList.add(9);
        testAnswers.put(new Character('1'), oneList);

        ArrayList<Integer> ioList = new ArrayList<>();
        ioList.add(1);
        ioList.add(2);
        ioList.add(5);
        ioList.add(8);
        testAnswers.put(new Character('й'), ioList);
        HashMap<Character, ArrayList<Integer>> result = countPatternCounter.getSymbolCountTable();

        for (Character character : result.keySet()) {
            for(int i = 0; i < result.get(character).size(); i ++)
            {
                assertTrue(testAnswers.get(character).contains(result.get(character).get(i)));
            }
        }
        for(Character character : testAnswers.keySet())
        {
            for(int i = 0; i < testAnswers.get(character).size(); i ++)
            {
                assertTrue(result.get(character).contains(testAnswers.get(character).get(i)));
            }
        }
        countPatternCounter.clearSymbolCountTable();
    }

    public void testCount() {
        assertTrue(countPatternCounter.count());
        ArrayList<CountPattern> countPatternList = countPatternCounter.getCountPatternList();
        for (CountPattern countPattern : countPatternList) {
            if(countPattern.getPatternStringForm().equalsIgnoreCase("о2"))
            {
                assertTrue(countPattern.getCountWords() == 2);
            }
            if(countPattern.getPatternStringForm().equalsIgnoreCase("т1о1"))
            {
                assertTrue(countPattern.getCountWords() == 3);
            }
            if(countPattern.getPatternStringForm().equalsIgnoreCase("ф2"))
            {
                assertTrue(countPattern.getCountWords() == 0);
            }
            if(countPattern.getPatternStringForm().equalsIgnoreCase("11"))
            {
                assertTrue(countPattern.getCountWords() == 1);
            }
            if(countPattern.getPatternStringForm().equalsIgnoreCase("й1о1"))
            {
                assertTrue(countPattern.getCountWords() == 3);
            }
        }
    }

    public void testJoinArrays() {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        arr1.add(1);
        arr1.add(2);
        arr1.add(3);
        arr1.add(4);

        arr2.add(1);
        arr2.add(3);
        arr2.add(5);
        arr2.add(6);
        arr2.add(7);

        ArrayList<Integer> result = countPatternCounter.joinArrays(arr1, arr2);
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertTrue(result.size() == 2);
    }

    public void testFoundNRepeats() {
        countPatternCounter.calculateSymbolCountTable();
        ArrayList<Integer> answerO2 = new ArrayList<>();
        answerO2.add(4);
        answerO2.add(8);

        Character oCh = new Character('о');
        ArrayList<Integer> wordsO = countPatternCounter.getSymbolCountTable().get(oCh);
        ArrayList<Integer> resultO1 = countPatternCounter.foundNRepeats(oCh, 1, wordsO);
        assertEquals(resultO1, wordsO);

        ArrayList<Integer> resultO2 = countPatternCounter.foundNRepeats(oCh, 2, wordsO);

        for (Integer res : resultO2) {
            assertTrue(answerO2.contains(res));
        }
        for(Integer answ : answerO2)
        {
            assertTrue(resultO2.contains(answ));
        }
        countPatternCounter.clearSymbolCountTable();
    }

}