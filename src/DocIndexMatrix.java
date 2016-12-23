/**
 * Created by Lyle on 12/22/2016.
 */

import java.util.SortedSet;
import java.util.TreeSet;

public class DocIndexMatrix {
    public static void main(String[] args) {
        Document[] docs = new Document[3];
        Document doc1 = new Document("My name is Lyle, and I am cool.");
        docs[0] = doc1;
        Document doc2 = new Document("My name is Bob, and I am awesome.");
        docs[1] = doc2;
        Document doc3 = new Document("The quick brown fox jumps over the lazy dog.");
        docs[2] = doc3;

        System.out.println("Document Messages:");
        printDocMessages(docs);

        System.out.printf("%nDocument Word Sets:%n");
        printDocumentWordSets(docs);

        SortedSet<String> wordSet = createSetOfWordsFromDocs(docs);
        printWordSet(wordSet);

        if (docs.length > 0) {
            Matrix documentTermMatrix;
            documentTermMatrix = createDocumentTermMatrix(docs, wordSet);
            System.out.printf("%nDocument Term Matrix:%n");
            printMatrix(documentTermMatrix);
        }
    }

    private static Matrix createDocumentTermMatrix(Document[] docs, SortedSet allWords) {
        Matrix matrix = new Matrix(docs.length, allWords.size());

        for (int r = 0; r < docs.length; r++) {
            SortedSet<?> wordSet = docs[r].getWordSet();

            for (int c = 0; c < allWords.size(); c++) {
                if (wordSet.contains(allWords.toArray()[c])) {
                    matrix.setElementAt(r, c, true);
                } else {
                    matrix.setElementAt(r, c, false);
                }
            }
        }
        return matrix;
    }

    private static SortedSet createSetOfWordsFromDocs(Document[] docs) {
        SortedSet<?> wordSet = new TreeSet<>();

        for (Document d : docs) {
            wordSet.addAll(d.getWordSet());
        }
        return wordSet;
    }

    private static void printWordSet(SortedSet set) {
        Object[] obj = set.toArray();
        System.out.printf("%s : ", "All Words");
        for (Object o : obj) {
            System.out.printf("%-9s", o);
        }
        System.out.println();
    }

    private static void printDocumentWordSets(Document[] docs) {
        for(Document d : docs) {
            System.out.printf("Document %s: ", d.getId());
            System.out.printf("%-9s%n", d.getWordSet().toString());
        }
    }

    private static void printMatrix(Matrix m) {
        int numRows = m.getNumRows();
        int numCols = m.getNumCols();

        for (int r = 0; r < numRows; r++) {
            System.out.printf("Document %s: ", r);
            for (int c = 0; c < numCols; c++) {
                System.out.printf("%-9s", m.getElementAt(r, c));
            }
            System.out.println();
        }
    }

    private static void printDocMessages(Document[] docs) {
        for(Document d : docs) {
            System.out.printf("Document %s: ", d.getId());
            System.out.printf("%-9s%n", d.getMessage());
        }
    }
}
