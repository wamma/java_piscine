package ex01;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("사용법: java Program <inputFile1> <inputFile2>");
            return;
        }

        String inputFile1 = args[0];
        String inputFile2 = args[1];

        try {
            // 단어 추출 및 빈도 계산
            Map<String, Integer> freqMap1 = getWordFrequencies(inputFile1);
            Map<String, Integer> freqMap2 = getWordFrequencies(inputFile2);

            // 사전 생성
            Set<String> dictionary = new TreeSet<>();
            dictionary.addAll(freqMap1.keySet());
            dictionary.addAll(freqMap2.keySet());

            // dictionary.txt 파일 생성
            writeDictionary(dictionary, "dictionary.txt");

            // 벡터 생성
            List<Integer> vectorA = new ArrayList<>();
            List<Integer> vectorB = new ArrayList<>();

            for (String word : dictionary) {
                vectorA.add(freqMap1.getOrDefault(word, 0));
                vectorB.add(freqMap2.getOrDefault(word, 0));
            }

            // 코사인 유사도 계산
            double similarity = calculateCosineSimilarity(vectorA, vectorB);
            System.out.printf("유사성 = %.2f%n", similarity);

        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 파일에서 단어를 추출하고 빈도를 계산하는 메서드
    private static Map<String, Integer> getWordFrequencies(String filePath) throws IOException {
        Map<String, Integer> freqMap = new HashMap<>();
        File file = new File(filePath);
        long fileSizeInBytes = file.length();
        long maxSize = 10 * 1024 * 1024; // 10MB

        if (fileSizeInBytes > maxSize) {
            throw new IOException("파일 크기가 10MB를 초과합니다: " + filePath);
        }

        // 파일을 한 줄씩 읽음
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase(); // 소문자로 변환
                StringBuilder wordBuilder = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (Character.isLetter(c)) {
                        wordBuilder.append(c);
                    } else {
                        if (wordBuilder.length() > 0) {
                            String word = wordBuilder.toString();
                            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                            wordBuilder.setLength(0); // 단어 초기화
                        }
                    }
                }
                // 줄 끝에서 단어가 끝났을 경우 처리
                if (wordBuilder.length() > 0) {
                    String word = wordBuilder.toString();
                    freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        return freqMap;
    }

    // 사전을 파일로 쓰는 메서드
    private static void writeDictionary(Set<String> dictionary, String dictFilePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(dictFilePath))) {
            for (String word : dictionary) {
                writer.write(word + " ");
            }
        }
    }

    // 코사인 유사도를 계산하는 메서드
    private static double calculateCosineSimilarity(List<Integer> vectorA, List<Integer> vectorB) {
        if (vectorA.size() != vectorB.size()) {
            throw new IllegalArgumentException("벡터의 크기가 일치하지 않습니다.");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.size(); i++) {
            dotProduct += vectorA.get(i) * vectorB.get(i);
            normA += Math.pow(vectorA.get(i), 2);
            normB += Math.pow(vectorB.get(i), 2);
        }

        if (normA == 0.0 || normB == 0.0) {
            return 0.0; // 벡터 중 하나가 모두 0이면 유사성은 0
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
