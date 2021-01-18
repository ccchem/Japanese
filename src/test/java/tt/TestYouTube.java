package tt;

import java.io.File;

import ek.trans.YouTubeTranscriptParser;

public class TestYouTube
{
    private static class TextCB implements YouTubeTranscriptParser.Callback
    {
        @Override
        public void onTimestamp(String ts)
        {
            System.out.println(ts);
        }

        @Override
        public void onText(String text)
        {
            System.out.println(text);
            System.out.println();
        }
    }
    
    
    public static void main(String[] args) throws Exception
    {
        YouTubeTranscriptParser pp = new YouTubeTranscriptParser();
        pp.parse(new File("/tmp/YTTrans.txt"), new TextCB());
    }

}
