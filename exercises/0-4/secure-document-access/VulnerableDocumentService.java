import java.util.HashMap;
import java.util.Map;

/**
 * VulnerableDocumentService.java
 * 
 * This class demonstrates a Trust Boundary violation (Guideline 0-4).
 * It trusts the documentId provided by the user without validating
 * if the user has the authority to access that specific document.
 */
public class VulnerableDocumentService {

    private static class Document {
        String id;
        String ownerId;
        String content;

        Document(String id, String ownerId, String content) {
            this.id = id;
            this.ownerId = ownerId;
            this.content = content;
        }
    }

    private Map<String, Document> documentRepo = new HashMap<>();

    public VulnerableDocumentService() {
        // Mock data
        documentRepo.put("doc123", new Document("doc123", "userA", "Secret Salary Data"));
        documentRepo.put("doc456", new Document("doc456", "userB", "Public Holiday List"));
    }

    /**
     * Fetches a document for a user.
     * 
     * VULNERABLE: This method accepts a userId (from session) and a documentId (from request),
     * but it fails to establish a trust boundary between users. It assumes that if a 
     * documentId is requested, it must be valid for that user.
     *
     * @param userId The ID of the user requesting the document.
     * @param documentId The ID of the document to fetch.
     * @return The document content.
     */
    public String getDocument(String userId, String documentId) {
        System.out.println("Processing request for User: " + userId + ", Document: " + documentId);
        
        Document doc = documentRepo.get(documentId);
        
        if (doc == null) {
            return "Document not found.";
        }

        // VULNERABILITY: No ownership check. 
        // User A can access User B's "Secret Salary Data" by just providing "doc123".
        return doc.content;
    }

    public static void main(String[] args) {
        VulnerableDocumentService service = new VulnerableDocumentService();
        
        // Scenario: Alice (userA) is logged in.
        String loggedInUser = "userB"; // Bob is logged in
        
        // Bob tries to access his own document
        System.out.println("Bob's access: " + service.getDocument(loggedInUser, "doc456"));
        
        // Bob tries to access Alice's document (IDOR)
        System.out.println("Bob's malicious access: " + service.getDocument(loggedInUser, "doc123"));
    }
}
