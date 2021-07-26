package ek.wadoku;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import com.beaglebuddy.mp3.MP3;


public class AudioTitleExtractor
{

    public static void main(String[] args) throws Exception
    {
        Files.list(Path.of("/ws4/Accent/audio/")).forEach(p -> {
            File file = p.toFile();
            String name = file.getName();
            if(name.endsWith(".mp3"))
            {
                String id = name.substring(0, name.length()-4);
                String title = getTitle(file);
                System.out.println(id + ";" + title);
            }
        });
    }

    
    private static String getTitle(File file) throws RuntimeException
    {
        try
        {
            MP3 mp3 = new MP3(file);
            return mp3.getTitle();
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
