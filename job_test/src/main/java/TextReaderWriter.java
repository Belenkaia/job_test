import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextReaderWriter {

    private static Logger logger = Logger.getLogger(PatternReader.class.getName());
    private Scanner scanner;
    private FileWriter writer = null;

//--------------------------------------------------------------------------------------------------------------------------------------------------
// create file descriptor, init Reader, catch FileNotFoundException
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean initTextReader(String fileName)
    {
        File file = new File(fileName);
        if(!file.exists())
        {
            logger.log(Level.WARNING, "Cant open" + fileName +" file!");
            return false;
        }
        scanner = null;
        try {
            FileReader fileReader = new FileReader(file);
            scanner = new Scanner(fileReader);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.toString());
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// returns one string that was ridden
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public String readOneString()
    {
        String temp = scanner.nextLine();
        return temp;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// check an existing of next line
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean hasNextString()
    {
        return scanner.hasNext();
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// close the scanner that was opened in initTextReader
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public void closeScanner()
    {
        scanner.close();
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// create file descriptor, init Writer
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean initTextWriter(String outputFileName)
    {
        File file = new File(outputFileName);
        try {
            writer = new FileWriter(file, false);
        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
            return false;
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// write one string from argument (text) to the file that was given as an argument in the initTextReader(), catch IOException
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public void writeText(String text)
    {
        try {
            writer.write(text);
            writer.append('\n');
            writer.flush();
        } catch (IOException e) {
            logger.log(Level.WARNING, "cant write string " + e.toString());
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// close Writer and catch IOException
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public void closeWriter()
    {
        try {
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "cant close writer " + e.toString());
        }
    }
}
