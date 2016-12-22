/**
 * Created by Lyle on 12/22/2016.
 */
import java.util.SortedSet;
import java.util.TreeSet;

public class Document {
    int id;
    String message;
    String messageLower;
    String[] messageWords;
    SortedSet<String> wordSet = new TreeSet<>();
    Matrix matrix;

    Document(int id, String msg) {
        this.id = id;
        this.message = msg;

        messageLower = message.toLowerCase();
        messageWords = messageLower.split("\\W+");

        for (String w : messageWords) {
            wordSet.add(w);
        }
    }

    String getMessage() {
        return message;
    }

    String[] getMessageWords() {
        return messageWords;
    }

    SortedSet getWordSet() {
        return wordSet;
    }

    void buildMatrix(SortedSet allWords) {
        matrix = new Matrix(1, allWords.size());
        for (int i = 0; i < allWords.size(); i++) {
            if (wordSet.contains(allWords.toArray()[i])) {
                matrix.setElementAt(0, i, true);
            } else {
                matrix.setElementAt(0, i, false);
            }
        }
    }

    Matrix getMatrix() {
        return matrix;
    }
}
