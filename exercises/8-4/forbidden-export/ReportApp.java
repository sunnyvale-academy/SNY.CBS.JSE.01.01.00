import java.io.*;

public class ReportApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 8-4: The Forbidden Export ---");

        try {
            // 1. Analyst creates a report
            System.out.println("[1] Setting role to ANALYST and creating report...");
            AccessControl.setCurrentRole("ANALYST");
            SensitiveReport report = new SensitiveReport("Q4 Financials", "Losses are mounting.");

            // 2. Export (serialize) the report
            System.out.println("[2] Exporting (serializing) report...");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(report);
            oos.close();

            // 3. Unauthorized user (GUEST) tries to import it
            System.out.println("[3] Setting role to GUEST...");
            AccessControl.setCurrentRole("GUEST");

            System.out.println("[4] Attempting to import (deserialize) report as GUEST...");
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            SensitiveReport importedReport = (SensitiveReport) ois.readObject();

            System.out.println("\n[5] LEAK DETECTED!");
            System.out.println("Report: " + importedReport);
            System.out.println("Data: " + importedReport.getData());

            System.out.println("\nALERT: Sensitive report leaked to unauthorized role! (Vulnerable)");

        } catch (SecurityException e) {
            System.out.println("\nRESULT: Action blocked as expected! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nERROR: " + e);
        }
    }
}
