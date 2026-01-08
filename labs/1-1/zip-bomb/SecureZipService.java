import java.io.*;
import java.util.zip.*;

public class SecureZipService {
    // Limits to prevent Denial of Service
    private static final int MAX_ENTRIES = 100; // Max number of files inside the zip
    private static final long MAX_SIZE = 10 * 1024 * 1024; // 10MB total uncompressed size
    private static final double MAX_RATIO = 100.0; // Max compression ratio (uncompressed/compressed)

    /**
     * Decompresses a zip file into a target directory with security checks.
     */
    public void unzip(InputStream zipStream, File targetDir, long compressedSize) throws IOException {
        int entries = 0;
        long totalSize = 0;

        try (ZipInputStream zis = new ZipInputStream(zipStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                entries++;
                if (entries > MAX_ENTRIES) {
                    throw new IOException("Too many entries in zip file.");
                }

                File outputFile = new File(targetDir, entry.getName());

                // SECURE: Also check for path traversal (best practice)
                String canonicalTarget = targetDir.getCanonicalPath();
                String canonicalOutput = outputFile.getCanonicalPath();
                if (!canonicalOutput.startsWith(canonicalTarget + File.separator)) {
                    throw new IOException("Entry is outside of target directory: " + entry.getName());
                }

                if (entry.isDirectory()) {
                    outputFile.mkdirs();
                } else {
                    outputFile.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[8192];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            totalSize += len;

                            // SECURE: Check total uncompressed size
                            if (totalSize > MAX_SIZE) {
                                throw new IOException("Zip file is too large when decompressed.");
                            }

                            // SECURE: Check compression ratio
                            if (compressedSize > 0) {
                                double ratio = (double) totalSize / compressedSize;
                                if (ratio > MAX_RATIO) {
                                    throw new IOException("Compression ratio too high (potential zip bomb).");
                                }
                            }

                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}
