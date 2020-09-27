package ek.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class EncodingUtils
{
    public static void eucjp_to_utf8(File src, File target) throws Exception
    {
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(src), "EUC-JP"));
        FileWriter wr = new FileWriter(target);
        
        int count = 0;
        String line;

        while((line = rd.readLine()) != null)
        {
            wr.write(line);
            wr.write("\n");
            count++;
        }
        
        rd.close();
        wr.close();

        System.out.println("Wrote " + count + " lines");
    }
}
