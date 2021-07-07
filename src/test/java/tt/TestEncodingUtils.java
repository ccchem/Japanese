package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import ek.util.EncodingUtils;

public class TestEncodingUtils
{

    public static void main(String[] args) throws Exception
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
