package tt.trans;

import java.io.File;

import ek.trans.J4yToWord;


public class TestJ4y
{
    private File dataFile;
    private File wordFile;
    
    
    public TestJ4y(String baseDir, String dataDir)
    {
        File dir = new File(baseDir, dataDir);
        
        dataFile = new File(dir, dataDir + ".txt");
        wordFile = new File(dir, dataDir + ".docx");
    }
    
    
    public void process() throws Exception
    {
        J4yToWord proc = new J4yToWord();
        proc.process(dataFile, wordFile);
    }
    
    
    public static void main(String[] args) throws Exception
    {
        TestJ4y main = new TestJ4y("D:\\Jap\\j4y", "n5g-made");
        main.process();
    }


}
