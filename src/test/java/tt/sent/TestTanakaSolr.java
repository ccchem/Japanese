package tt.sent;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringEscapeUtils;

import ek.sent.TanakaParser;


public class TestTanakaSolr
{

    private static class DuplicatesCB implements TanakaParser.Callback
    {
        private Set<String> ids = new TreeSet<>();
        
        @Override
        public boolean onRecord(String id, String jp, String en) throws Exception
        {
            if(ids.contains(id))
            {
                System.out.println("Duplicate: " + id);
            }
            else
            {
                ids.add(id);
            }
            
            return true;
        }
    }

    
    private static class SolrCB implements TanakaParser.Callback
    {
        private FileWriter writer;
        
        public SolrCB() throws Exception
        {
            writer = new FileWriter("/tmp/tanaka.xml", Charset.forName("UTF-8"));
            writer.write("<add>\n");
        }
        
        public void close() throws Exception
        {
            writer.write("</add>\n");
            writer.close();
        }
        
        @Override
        public boolean onRecord(String id, String ja, String en) throws Exception
        {
            writer.write("<doc>\n");
            writeField("id", id, false);
            writeField("ja", ja, false);
            writeField("en", en, true);
            writer.write("</doc>\n");
            
            return true;
        }

        
        private void writeField(String name, String value, boolean escape) throws Exception
        {
            writer.write("  <field name=\"" + name +"\">");
            if(escape)
            {
                StringEscapeUtils.escapeXml(writer, value);                
            }
            else
            {
                writer.write(value);
            }
            
            writer.write("</field>\n");
        }
    }

    
    private static void getSolrXml() throws Exception
    {
        File file = new File("C:\\ws4\\Jap\\Dic\\examples.utf.txt");
        
        SolrCB cb = new SolrCB();
        TanakaParser.parse(file, cb);
        cb.close();
    }

    
    private static void findDuplicates() throws Exception
    {
        File file = new File("C:\\ws4\\Jap\\Dic\\examples.utf.txt");
        
        DuplicatesCB cb = new DuplicatesCB();
        TanakaParser.parse(file, cb);
    }

    
    public static void main(String[] args) throws Exception
    {
        findDuplicates();
    }

}
