import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * VULNERABLE CLASS: UserAccount
 * Guidelines:
 * - ACCESS-1 (4-1): Public fields for balance and password.
 * - MUTABLE-1 (6-1): Uses java.util.Date which is mutable.
 * - MUTABLE-2 (6-2): Returns internal mutable reference.
 */
public class UserAccount {
    public String username;
    public String password; // Should be private/hashed
    public long balance; // Should be private
    public Date lastLogin; // Mutable value type
    public List<String> roles; // Mutable collection

    public UserAccount(String username, String password, long initialBalance) {
        this.username = username;
        this.password = password;
        this.balance = initialBalance;
        this.lastLogin = new Date();
        this.roles = new ArrayList<>();
        this.roles.add("USER");
    }

    // Vulnerable: Returns internal reference to mutable Date object
    public Date getLastLogin() {
        return lastLogin;
    }

    // Vulnerable: Returns internal reference to mutable List
    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserAccount{username='" + username + "', balance=" + balance + "}";
    }
}
