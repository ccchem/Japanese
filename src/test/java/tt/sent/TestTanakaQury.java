package tt.sent;

import java.nio.file.Path;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.NIOFSDirectory;


public class TestTanakaQury
{

    public static void main(String[] args) throws Exception
    {
        NIOFSDirectory dir = new NIOFSDirectory(Path.of("/data/tanaka"));
        DirectoryReader rd = DirectoryReader.open(dir);
        
        IndexSearcher searcher = new IndexSearcher(rd);
        
        StandardAnalyzer analyzer = new StandardAnalyzer();
        
        Query q = new QueryParser("en", analyzer).parse("\"It took her\"");
        
        //int count = searcher.count(q);
        //System.out.println(count);
        
        TopDocs docs = searcher.search(q, 20);
        System.out.println(docs.totalHits.value);
        
        for(ScoreDoc sdoc: docs.scoreDocs)
        {
            Document doc = searcher.doc(sdoc.doc);
            System.out.println(doc.get("jp") + "   " + doc.get("en"));
        }
        
        
        rd.close();
        
        
    }

}
