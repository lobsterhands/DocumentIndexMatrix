/**
 * Created by Lyle on 12/22/2016.
 */

import java.util.SortedSet;
import java.util.TreeSet;

public class DocIndexMatrix {
    public static void main(String[] args) {

        Document doc1 = new Document(1, "My name is Lyle, and I am cool.");
        Document doc2 = new Document(2, "My name is Bob, and I am awesome.");
        Document doc3 = new Document(3, "The brown dog jumps over the lazy fox.");
        Document[] docs = new Document[3];
        docs[0] = doc1;
        docs[1] = doc2;
        docs[2] = doc3;

        SortedSet<String> wordSet = new TreeSet<>();

        for (int i = 0; i < docs.length; i++) {
            String[] messageWords = docs[i].getMessageWords();
            for(String w : messageWords) {
                wordSet.add(w);
            }
        }

        Object[] str = wordSet.toArray();
        System.out.printf("%s : ", "All Words");
        for (int i = 0; i < str.length; i++) {
            System.out.printf("%-12s", str[i]);
        }
        System.out.println();

        for(Document d : docs) {
            d.buildMatrix(wordSet);
            System.out.printf("Document %s: ", d.getId());
            for (int i = 0; i < d.matrix.getColNumber(); i++) {
                System.out.printf("%-12s", d.getMatrix().getElementAt(0, i));
            }
            System.out.println();
        }
    }
}
