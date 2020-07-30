import java.util.logging.Logger;

public abstract class Counter {
    protected String fileName = null;
    public abstract boolean count();

//--------------------------------------------------------------------------------------------------------------------------------------------------
//
//--------------------------------------------------------------------------------------------------------------------------------------------------
    public Counter(String fileInput)
    {
        this.fileName = fileInput;
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
// returns true if the word contains the pattern
//--------------------------------------------------------------------------------------------------------------------------------------------------
    protected boolean containsPattern(String pattern, String word)
    {
        if(word.indexOf(pattern) != -1)
        {
            return true;
        }
        return false;
    }
}
