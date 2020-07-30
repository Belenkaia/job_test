import java.util.Scanner;

public class Main {
    private static CountPatternCounter countPatternCounter;
    private static StringPatternCounter stringPatternCounter;
    private static ResultWriter resultWriter;

        public static void main(String[] args)
        {
            String inputFileName;
            String outputFileName;
            String patternFileName;

            Scanner input = new Scanner(System.in);
            System.out.println("Write text file");
            inputFileName = input.nextLine();
            System.out.println("Write pattern file");
            patternFileName = input.nextLine();
            System.out.println("Write output file name");
            outputFileName = input.nextLine();

            PatternReader patternReader = new PatternReader(patternFileName);
            patternReader.read();
            countPatternCounter = new CountPatternCounter(patternReader.getCountPatternList(), inputFileName);
            stringPatternCounter = new StringPatternCounter(patternReader.getStringPatternList(), inputFileName);
            countPatternCounter.count();
            stringPatternCounter.count();
            resultWriter = new ResultWriter(outputFileName, patternFileName, countPatternCounter.getCountPatternList(), stringPatternCounter.getStringPatternList());
            if(resultWriter.writeResult())
                System.out.println(outputFileName + " file was written");
            else
            {
                System.out.println("Something goes wrong( ");
            }

        }

}
