/**
 * Created by Lyle on 12/22/2016.
 */
public class DocIndexMatrix {
    public static void main(String[] args) {

        Document doc1 = new Document(1, "My name is Lyle, and I am cool.");
        Document doc2 = new Document(1, "My name is Bob, and I am awesome.");
        Document[] docs = new Document[2];
        docs[0] = doc1;
        docs[1] = doc2;

        for (int i = 0; i < docs.length; i++) {
            String message = docs[i].getMessage();
            String messageLower = message.toLowerCase();
            String[] messageWords = messageLower.split("\\W+");
            System.out.printf("Document %d: %s%n", (i+1), message);

            System.out.printf("Document %d words:%n", (i+1));
            for (int j = 0; j < messageWords.length; j++) {
                System.out.printf("%s%n", messageWords[j]);
            }
        }
    }
}
