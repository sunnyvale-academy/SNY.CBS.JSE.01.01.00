import java.io.*;
import java.util.zip.*;

public class VulnerableZipService {
    /**
     * Decompresses a zip file into a target directory.
     * VULNERABILITY: Does not check for the size of the extracted data or the
     * number of entries.
     */
    public void unzip(InputStream zipStream, File targetDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File outputFile = new File(targetDir, entry.getName());

                // VULNERABILITY: No check for path traversal here (Guideline 0-4),
                // but let's focus on Guideline 1-1 (DoS).

                if (entry.isDirectory()) {
                    outputFile.mkdirs();
                } else {
                    outputFile.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[8192];
                        int len;
                        // VULNERABILITY: Will continue writing until disk is full or memory exhausted
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}
