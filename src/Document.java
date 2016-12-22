/**
 * Created by Lyle on 12/22/2016.
 */

public class Document {
    int id;
    String message;
    String messageLower;
    String[] messageWords;

    Document(int id, String msg) {
        this.id = id;
        this.message = msg;

        messageLower = message.toLowerCase();
        messageWords = messageLower.split("\\W+");
    }

    String getMessage() {
        return message;
    }

    String getMessageLower() {
        return messageLower;
    }

    String[] getMessageWords() {
        return messageWords;
    }
}
