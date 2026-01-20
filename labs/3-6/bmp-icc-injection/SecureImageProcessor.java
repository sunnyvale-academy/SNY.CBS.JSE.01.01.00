import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * A secure image processor that enforces format restrictions.
 * SECURE: Follows Guideline 3-6 by avoiding untrusted BMP files.
 */
public class SecureImageProcessor {

    public void processImage(File imageFile) {
        try {
            System.out.println("[Secure] Processing image: " + imageFile.getName());

            // SECURE: Identify the format BEFORE parsing the whole image.
            String format = getImageFormat(imageFile);

            if ("bmp".equalsIgnoreCase(format)) {
                // Guideline 3-6: Avoid any BMP files from untrusted sources.
                System.out.println(
                        "   -> [SECURITY BLOCK] BMP format is prohibited for untrusted uploads due to ICC profile risks.");
                return;
            }

            if (format == null) {
                System.out.println("   -> [ERROR] Unknown or unsupported image format.");
                return;
            }

            // If it's a safe format (e.g., PNG/JPEG), proceed.
            BufferedImage img = ImageIO.read(imageFile);
            System.out.println(
                    "   -> Successfully loaded " + format + " (" + img.getWidth() + "x" + img.getHeight() + ").");

        } catch (IOException e) {
            System.out.println("   -> Error reading image: " + e.getMessage());
        }
    }

    private String getImageFormat(File file) throws IOException {
        try (ImageInputStream iis = ImageIO.createImageInputStream(file)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                return reader.getFormatName();
            }
        }
        return null;
    }
}
