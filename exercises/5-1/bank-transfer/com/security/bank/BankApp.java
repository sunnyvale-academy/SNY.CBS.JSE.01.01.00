package com.security.bank;

/**
 * Main application to demonstrate the bank transfer vulnerability.
 */
public class BankApp {
    public static void main(String[] args) {
        VulnerableBankService service = new VulnerableBankService();

        System.out.println("--- Normal Transfer ---");
        TransferRequest normal = new TransferRequest("ACC-USER-123", "ACC-USER-456", 50);
        service.processTransfer(normal);

        System.out.println("\n--- ATTACK: Negative Amount Transfer ---");
        // Attacker transfers -$10,000 from their account to the vault.
        // fromBalance - (-10000) = fromBalance + 10000 (Added to attacker)
        // toBalance + (-10000) = toBalance - 10000 (Subtracted from vault)
        TransferRequest exploit = new TransferRequest("ACC-USER-123", "ACC-VAULT-999", -10000);
        service.processTransfer(exploit);
    }
}
