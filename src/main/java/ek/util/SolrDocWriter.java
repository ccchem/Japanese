package ek.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringEscapeUtils;


public class SolrDocWriter implements Closeable
{
    private Writer writer;

    
    public SolrDocWriter(File outFile) throws IOException
    {
        writer = new FileWriter(outFile);
        writer.append("<add>\n");
    }

    
    @Override
    public void close() throws IOException
    {
        writer.append("</add>\n");
        writer.close();
    }

    
    public void beginDoc() throws IOException
    {
        writer.append("<doc>\n");
    }

    
    public void endDoc() throws IOException
    {
        writer.append("</doc>\n");
    }

    
    public void writeField(String key, String value) throws IOException
    {
        if(value == null) return;
        
        writer.write("  <field name=\"");
        writer.write(key);
        writer.write("\">");
        StringEscapeUtils.escapeXml(writer, value);
        writer.write("</field>\n");
    }

}
