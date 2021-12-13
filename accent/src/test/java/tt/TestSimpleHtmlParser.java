package tt;

import ek.ja.accent.etl.DataExtractor;

public class TestSimpleHtmlParser
{
    public static void main(String[] args) throws Exception
    {
        String html = "<a href=\"download_count/download.php?download=25\"><span class=\"a1\">あ</span><span class=\"a2\">しおと</span></a><script type=\"text/javascript\" src=\"download_count/download.php?dsp_count=25\"></script> / <a href=\"download_count/download.php?download=26\"><span class=\"a1\">あ</span><span class=\"a2\">しお</span><span class=\"a3\">と</span></a><script type=\"text/javascript\" src=\"download_count/download.php?dsp_count=26\"></script> (足音)　　　[アクセント投票実施中]";
    
        String str = DataExtractor.clean(html);
        System.out.println(str);
    }
}
