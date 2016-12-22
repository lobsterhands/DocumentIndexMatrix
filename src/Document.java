/**
 * Created by Lyle on 12/22/2016.
 */

public class Document {
    int id;
    String message;

    Document(int id, String msg) {
        this.id = id;
        this.message = msg;
    }

    String getMessage() {
        return message;
    }
}
