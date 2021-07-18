package ek.util;

import java.io.Closeable;

public class CloseUtils
{

    public static void close(Closeable obj)
    {
        try
        {
            obj.close();
        }
        catch(Exception ex)
        {
            // Ignore
        }
    }
}
