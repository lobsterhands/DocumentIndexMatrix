/**
 * Created by Lyle on 12/22/2016.
 */
import java.util.SortedSet;
import java.util.TreeSet;

public class Document {
    private static int docId;
    private int id;
    private String message;
    private SortedSet<String> wordSet = new TreeSet<>();

    Document(String msg) {
        this.id = ++docId;
        this.message = msg;

        String messageLower = message.toLowerCase();
        String[] messageWords = messageLower.split("\\W+");

        for (String w : messageWords) {
            wordSet.add(w);
        }
    }

    String getMessage() {
        return message;
    }

    SortedSet getWordSet() {
        return wordSet;
    }

    int getId() {
        return id;
    }
}
