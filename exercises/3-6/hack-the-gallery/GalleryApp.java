import java.io.File;
import java.io.IOException;

/**
 * Driver app for Exercise 3-6: Hack the Gallery.
 */
public class GalleryApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 3-6: Hack the Gallery ---");

        VulnerableGallery gallery = new VulnerableGallery();

        // 1. Attacker crafts a JPEG with a metadata comment:
        // Color-Profile-Reference: /etc/passwd
        File attackerJpeg = new File("vacation.jpg");
        String maliciousRef = "/etc/passwd";

        System.out.println("\n[Action] Attacker uploads 'vacation.jpg' with reference to /etc/passwd");
        gallery.processJpeg(attackerJpeg, maliciousRef);

        // 2. Attacker tries a non-existent file to see the difference
        String missingRef = "/secret/non_existent_file.key";
        System.out.println("\n[Action] Attacker uploads 'sunset.jpg' with reference to " + missingRef);
        gallery.processJpeg(attackerJpeg, missingRef);

        System.out
                .println("\n[Result] If you see 'Invalid ICC Profile Data', you successfully probed the file system!");
    }
}
