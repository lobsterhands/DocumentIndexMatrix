/**
 * Created by Lyle on 12/22/2016.
 */

import java.util.SortedSet;
import java.util.TreeSet;

public class DocIndexMatrix {
    public static void main(String[] args) {

        Document doc1 = new Document(1, "My name is Lyle, and I am cool.");
        Document doc2 = new Document(1, "My name is Bob, and I am awesome.");
        Document[] docs = new Document[2];
        docs[0] = doc1;
        docs[1] = doc2;

        SortedSet<String> wordSet = new TreeSet<>();

        for (int i = 0; i < docs.length; i++) {
            String[] messageWords = docs[i].getMessageWords();
            for(String w : messageWords) {
                wordSet.add(w);
            }
        }

        for(Document d : docs) {
            d.buildMatrix(wordSet);
            for (int i = 0; i < d.matrix.getColNumber(); i++) {
                System.out.println(d.getMatrix().getElementAt(0, i));
            }
        }
    }
}
