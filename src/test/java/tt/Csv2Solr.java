package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.stream.JsonWriter;

import ek.util.SolrDocWriter;


public class Csv2Solr
{

    public static void main(String[] args) throws Exception
    {
        testJson();
    }

    
    public static void testJson() throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader("data/N5-vocab.txt"));
        
        JsonWriter jw = new JsonWriter(new FileWriter("/tmp/N5-vocab.json"));
        jw.setIndent("  ");
        jw.beginArray();
        
        // Skip header
        String line = rd.readLine();

        while((line = rd.readLine()) != null)
        {
            line = line.trim();
            if(line.length() == 0) continue;

            String[] tokens = line.split("\t");
            if(tokens.length == 5)
            {
                jw.beginObject();
                jw.name("id").value(tokens[0].trim());
                jw.name("kana").value(tokens[1].trim());
                jw.name("kanji").value(tokens[2].trim());
                jw.name("type").value(tokens[3].trim());
                jw.name("definition").value(tokens[4].trim());
                jw.endObject();
            }
            else
            {
                System.out.println("[WARN] Invalid record: " + line);
            }
        }

        jw.endArray();
        jw.close();
        rd.close();
    }

    
    public static void testXml() throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader("data/N5-vocab.txt"));

        SolrDocWriter writer = new SolrDocWriter(new File("/tmp/N5-vocab.xml"));
        
        // Skip header
        String line = rd.readLine();

        while((line = rd.readLine()) != null)
        {
            line = line.trim();
            if(line.length() == 0) continue;

            String[] tokens = line.split("\t");
            if(tokens.length == 5)
            {
                writer.beginDoc();
                writer.writeField("id", tokens[0].trim());
                writer.writeField("kana", tokens[1].trim());
                writer.writeField("kanji", tokens[2].trim());
                writer.writeField("type", tokens[3].trim());
                writer.writeField("definition", tokens[4].trim());
                writer.endDoc();
            }
            else
            {
                System.out.println("[WARN] Invalid record: " + line);
            }
        }

        writer.close();
        rd.close();
    }

}
