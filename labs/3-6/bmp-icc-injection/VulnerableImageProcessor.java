import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * An image processor that processes any image format supported by ImageIO.
 * VULNERABILITY: Guideline 3-6 - Improper handling of untrusted BMP files.
 */
public class VulnerableImageProcessor {

    public void processImage(File imageFile) {
        try {
            System.out.println("[Vulnerable] Processing image: " + imageFile.getName());

            // VULNERABILITY: ImageIO.read() will attempt to resolve external ICC profiles
            // referenced in a BMP header, potentially leaking file existence or triggering
            // UNC access.
            BufferedImage img = ImageIO.read(imageFile);

            if (img != null) {
                System.out.println("   -> Successfully loaded " + img.getWidth() + "x" + img.getHeight() + " image.");
            } else {
                System.out.println("   -> Could not decode image.");
            }
        } catch (IOException e) {
            System.out.println("   -> Error reading image: " + e.getMessage());
        }
    }
}
