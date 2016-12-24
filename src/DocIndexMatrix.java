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

//        simpleRetrieval("name", mapWordSet, docs, documentTermMatrix);
//        simpleRetrieval("fox", mapWordSet, docs, documentTermMatrix);

        simpleRetrievalAnd("fox is brown", mapWordSet, docs, documentTermMatrix);
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

    private static void simpleRetrieval(String searchWord, SortedMap<String, Integer> map, Document[] docs, Matrix matrix) {
        System.out.printf("%nWhich documents have this word: '%s'%n", searchWord);

        if (map.containsKey(searchWord)) {
            int c = map.get(searchWord);
            String binaryString = "";
            for (int r = 0; r < matrix.getNumRows(); r++) {
                binaryString += matrix.getElementAt(r, c);
            }

            for (int i = 0; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '1') {
                    System.out.printf("Found '%s' in Document %s: %s%n", searchWord, docs[i].getId(), docs[i].getMessage());
                }
            }

        } else {
            System.out.println("'" + searchWord + "' not found.");
        }
    }

    private static void simpleRetrievalAnd(String searchPhrase, SortedMap<String, Integer> map, Document[] docs, Matrix matrix) {
        System.out.printf("%nWhich documents have this word: '%s'%n", searchPhrase);
        String[] searchWords = searchPhrase.split("\\W+");
        int[] searchWordWeights = new int[searchWords.length];
        int[] docWeights = new int[docs.length];

        int wordIndex = 0;
        for(String word : searchWords) {
            int wordWeight = 0;

            if (map.containsKey(word)) {
                int c = map.get(word);
                String binaryString = "";
                for (int r = 0; r < matrix.getNumRows(); r++) {
                    binaryString += matrix.getElementAt(r, c);
                }

                for (int i = 0; i < binaryString.length(); i++) {
                    if (binaryString.charAt(i) == '1') {
                        System.out.printf("Found '%s' in Document %s: %s%n", word, docs[i].getId(), docs[i].getMessage());
                        docWeights[docs[i].getId() - 1]++;
                        wordWeight++;
                    }
                }
                searchWordWeights[wordIndex] = wordWeight;
                wordIndex++;
            } else {
                System.out.println("'" + word + "' not found.");
            }
        }

        for (int i = 0; i < searchWordWeights.length; i++) {
            System.out.println("Word: " + searchWords[i] + "; Weight: " + searchWordWeights[i]);
        }

        int maxWeight = 0;
        int docId = 0;
        for (int i = 0; i < docWeights.length; i++) {
            System.out.printf("Document %s weight: %s%n", docs[i].getId(), docWeights[i]);
            if (docWeights[i] > maxWeight) {
                maxWeight = docWeights[i];
                docId = docs[i].getId();
            }
        }
        System.out.println("Max weight: " + maxWeight + " goes to Document " + docId);
    }


}
