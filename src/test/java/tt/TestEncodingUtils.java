package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class TestEncodingUtils
{

    public static void main(String[] args) throws Exception
    {
        File src = new File("/ws4/Dic/RAD/kradfile2");
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(src), "SHIFT-JIS"));
        
        FileWriter writer = new FileWriter("/ws4/Dic/RAD/kradfile2.txt", Charset.forName("utf-8"));
        
        String line;
        while((line = rd.readLine()) != null)
        {
            writer.write(line);
            writer.write("\n");
        }
        
        writer.close();
        rd.close();

    }
    
    
    public static void euc() throws Exception
    {
        //File src = new File("/ws4/Jap/Dic/edict2.txt");
        //File tgt = new File("/tmp/edict2-utf8.txt");
        
        //EncodingUtils.eucjp_to_utf8(src, tgt);
        
        
        File src = new File("/tmp/Verb.csv");
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(src), "EUC-JP"));
        
        String line = rd.readLine();
        System.out.println(line);
        
        rd.close();
        
        
    }

}
