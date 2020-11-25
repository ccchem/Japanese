package tt;

import java.io.File;

import ek.util.EncodingUtils;

public class TestEncodingUtils
{

    public static void main(String[] args) throws Exception
    {
        File src = new File("/ws4/Jap/Dic/edict2.txt");
        File tgt = new File("/tmp/edict2-utf8.txt");
        
        EncodingUtils.eucjp_to_utf8(src, tgt);
    }

}
