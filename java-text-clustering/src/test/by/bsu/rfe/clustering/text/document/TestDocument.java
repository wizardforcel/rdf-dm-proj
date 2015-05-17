package test.by.bsu.rfe.clustering.text.document;


import by.bsu.rfe.clustering.text.ir.Document;

public class TestDocument {


    public void testTermCount() {
        Document testDoc = new Document("testDoc");
        String[] terms = { "data", "data", "clustering", "kmeans", "kmeans", "kmeans" };

        for (String term : terms) {
            testDoc.addTerm(term);
        }

    }

}
