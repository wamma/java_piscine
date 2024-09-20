package ex00;

import java.io.*;
import java.util.*;

public class FileParsing {
    private final String signatureFile;
    private final Map<String, String> signatures = new HashMap<>();

    public FileParsing(String signatureFile) {
        this.signatureFile = signatureFile;
    }

    public void loadSignatures() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(signatureFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    signatures.put(parts[1].replaceAll("\\s", ""), parts[0]);
                }
            }
        }
    }

    public String processFile(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8];
            int bytesRead = fis.read(buffer);

            if (bytesRead > 0) {
                StringBuilder hex = new StringBuilder();
                for (int i = 0; i < bytesRead; i++) {
                    hex.append(String.format("%02X", buffer[i]));
                }

                for (Map.Entry<String, String> entry : signatures.entrySet()) {
                    if (hex.toString().startsWith(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing file " + filePath + ": " + e.getMessage());
        }
        return null;
    }
}