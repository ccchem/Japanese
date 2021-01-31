package tt.sent;

import java.io.File;
import java.nio.file.Path;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ek.sent.TanakaParser;


public class TestTanaka
{
    private static class LuceneCB implements TanakaParser.Callback
    {
        private IndexWriter writer;
        private int count = 0;
        
        public LuceneCB() throws Exception
        {
            Directory dir = FSDirectory.open(Path.of("/data/tanaka"));
            IndexWriterConfig config = new IndexWriterConfig();
            writer = new IndexWriter(dir, config);
        }
        
        
        @Override
        public boolean onRecord(String id, String jp, String en) throws Exception
        {
            count++;
            
            Document doc = new Document();
            doc.add(new StringField("id", id, Field.Store.YES));
            doc.add(new StringField("jp", jp, Field.Store.YES));
            doc.add(new TextField("en", en, Field.Store.YES));

            Term pk = new Term("id", id);
            writer.updateDocument(pk, doc);
            
            if(count % 1000 == 0) 
            {
                writer.commit();
                System.out.println("Wrote " + count);
            }
            
            return true;
        }
        
        
        public void close() throws Exception
        {
            writer.commit();
            writer.close();            
        }
    }

    
    public static void main(String[] args) throws Exception
    {
        File file = new File("D:\\Jap\\Dic\\examples.utf");
        LuceneCB cb = new LuceneCB();
        
        TanakaParser.parse(file, cb);
        cb.close();
    }

}
