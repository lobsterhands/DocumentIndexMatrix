/**
 * Created by Lyle on 12/22/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class DocIndexMatrix {
    public static void main(String[] args) {

        Document doc1 = new Document(1, "My name is Lyle, and I am cool.");
        Document doc2 = new Document(1, "My name is Bob, and I am awesome.");
        Document[] docs = new Document[2];
        docs[0] = doc1;
        docs[1] = doc2;

        int nWords = doc1.getMessageWords().length + doc1.getMessageWords().length;
        System.out.printf("nWords: %s%n", nWords);
        List<String> wordList = new ArrayList<>();

        for (int i = 0; i < docs.length; i++) {
            String[] messageWords = docs[i].getMessageWords();
            System.out.printf("Document %d: %s%n", (i+1), docs[i].getMessage());

            for (int j = 0; j < messageWords.length; j++) {
                wordList.add(messageWords[j]);
            }
        }

        System.out.println("All words used: " + wordList.toString());
    }
}
