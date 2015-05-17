package test.by.bsu.rfe.clustering.text.document;

import java.io.File;
import java.io.IOException;



import test.by.bsu.rfe.clustering.app.util.CSVDataSetExporter;
import by.bsu.rfe.clustering.text.data.DocumentDataSet;
import by.bsu.rfe.clustering.text.ir.Document;
import by.bsu.rfe.clustering.text.ir.DocumentCollection;
import by.bsu.rfe.clustering.text.vsm.DocumentVSMGenerator;
import by.bsu.rfe.clustering.text.vsm.TFIDF;

public class TestDocumentCollection  {


    public void testTermCount() {
        String[][] terms = { { "document", "collection", "data", "text" }, { "clustering", "text" },
                { "text", "data", "point", "java", "java" } };

        DocumentCollection collection = new DocumentCollection();
        int ordinal = 1;

        for (String[] document : terms) {
            Document newDoc = new Document("doc_" + ordinal++);

            for (String term : document) {
                newDoc.addTerm(term);
            }

            collection.addDocument(newDoc);
        }



        DocumentVSMGenerator vsm = new TFIDF();
        DocumentDataSet dataSet = vsm.createVSM(collection);

        try {
            CSVDataSetExporter.export(dataSet, new File("tmp/testDS.csv"));
        }
        catch (IOException e) {
            // nobody cares
        }
    }
}
