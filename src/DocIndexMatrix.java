/**
 * Created by Lyle on 12/22/2016.
 */

import java.util.*;

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
        displayDocMessages(docs);

        System.out.printf("%nDocument Word Sets:%n");
        displayDocumentWordSets(docs);

        SortedMap<String, Integer> mapWordSet = createMapOfAllWords(docs);
        displayMapKeys(mapWordSet);

        Matrix documentTermMatrix;
        documentTermMatrix = createDocumentTermMatrix(docs, mapWordSet);
        System.out.printf("%nDocument Term Matrix:%n");
        displayMatrix(documentTermMatrix);

        String wordToSearch = "am";

        if (mapWordSet.containsKey(wordToSearch)) {
            System.out.println("Found: '" + wordToSearch + "' at index: " + mapWordSet.get(wordToSearch));
        } else {
            System.out.println("'" + wordToSearch + "' not found.");
        }

        System.out.println("Which documents have this word:");
        wordToSearch = "name";

        if (mapWordSet.containsKey(wordToSearch)) {
            int c = mapWordSet.get(wordToSearch);
            String binaryString = "";
            for (int r = 0; r < documentTermMatrix.getNumRows(); r++) {
                binaryString += documentTermMatrix.getElementAt(r, c);
            }

            for (int i = 0; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '1') {
                    System.out.printf("Found '%s' in Document %s: %s%n", wordToSearch, docs[i].getId(), docs[i].getMessage());
                }
            }

        } else {
            System.out.println("'" + wordToSearch + "' not found.");
        }

    }

    private static Matrix createDocumentTermMatrix(Document[] docs, SortedMap<String, Integer> map) {
        Matrix matrix = new Matrix(docs.length, map.size());

        for (int r = 0; r < docs.length; r++) {
            SortedSet<?> docWordSet = docs[r].getWordSet();

            for (int c = 0; c < docWordSet.size(); c++) {
                String str = docWordSet.toArray()[c].toString();
                if (map.containsKey(str)) {
                    matrix.setElementAt(r, map.get(str), 1);
                } else {
                    matrix.setElementAt(r, c, 0);
                }
            }
        }
        return matrix;
    }

    private static SortedMap createMapOfAllWords(Document[] docs) {
        SortedMap<String, Integer> mapWordSet = new TreeMap<>();
        SortedSet<String> sortedSet = new TreeSet<>();

        for (Document d : docs) {
            sortedSet.addAll(d.getWordSet());
        }

        int value = 0;
        for(String key : sortedSet) {
            mapWordSet.put(key, value);
            value++;
        }

        return mapWordSet;
    }

    private static void displayDocumentWordSets(Document[] docs) {
        for(Document d : docs) {
            System.out.printf("Document %s: ", d.getId() );
            System.out.printf("%-9s%n", d.getWordSet().toString());
        }
    }

    private static void displayMatrix(Matrix m) {
        int numRows = m.getNumRows();
        int numCols = m.getNumCols();

        for (int r = 0; r < numRows; r++) {
            System.out.printf("Document %s: ", (r+1));
            for (int c = 0; c < numCols; c++) {
                System.out.printf("%-9s", m.getElementAt(r, c));
            }
            System.out.println();
        }
    }

    private static void displayDocMessages(Document[] docs) {
        for(Document d : docs) {
            System.out.printf("Document %s: ", d.getId());
            System.out.printf("%-9s%n", d.getMessage());
        }
    }

    private static void displayMapKeys(Map<String, Integer> map) {
        Set<String> keys = map.keySet();
        System.out.printf("Map keys:   ");
        for (String key : keys) {
            System.out.printf("%-9s", key);
        }
    }

    private static void displayMapKeyVal(Map<String, Integer> map) {
        Set<String> keys = map.keySet();
        System.out.printf("%nMap contains:%nKey\t\t\t\tValue%n");
        System.out.printf("---------------------------%n");
        for (String key : keys) {
            System.out.printf("%-9s%10s%n", key, map.get(key));
        }
    }
}
