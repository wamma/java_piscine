package ex00;

import java.io.*;
import java.util.Scanner;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) {
        String signatureFilePath = Paths.get("src", "ex00", "signatures.txt").toString();
        File signatureFile = new File(signatureFilePath);

        if (!signatureFile.exists()) {
            System.out.println("Error: signatures.txt file not found.");
            System.out.println("Expected location: " + signatureFile.getAbsolutePath());
            return;
        }

        FileParsing parser = new FileParsing(signatureFilePath);

        try {
            parser.loadSignatures();
        } catch (IOException e) {
            System.out.println("Error loading signatures file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (Scanner scanner = new Scanner(System.in);
             FileWriter writer = new FileWriter("result.txt")) {

            while (true) {
                String filePath = scanner.nextLine();

                if (filePath.equals("42")) {
                    break;
                }

                String fileType = parser.processFile(filePath);
                if (fileType != null) {
                    writer.write(fileType + "\n");
                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }
            }
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}