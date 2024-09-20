package ex04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    private static final int MAX_CHAR_CODE = 65535;
    private static final int MAX_TOP_CHARS = 10;
    private static final int MAX_HEIGHT = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        short[] charCounts = getCharOccurrences(input);
        char[] topChars = getTopChars(charCounts);
        printHistogram(topChars, charCounts);
    }

    // 입력된 문자열에서 각 문자의 빈도수를 계산하는 메서드
    private static short[] getCharOccurrences(String input) {
        short[] counts = new short[MAX_CHAR_CODE];
        for (char c : input.toCharArray()) {
            counts[c]++;
        }
        return counts;
    }

    // 빈도수가 높은 상위 문자를 추출하는 메서드
    private static char[] getTopChars(short[] charCounts) {
        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < charCounts.length; i++) {
            if (charCounts[i] > 0) {
                charList.add((char) i);
            }
        }

        // 빈도수 내림차순 및 사전순 정렬
        charList.sort((a, b) -> {
            int freqCompare = Short.compare(charCounts[b], charCounts[a]);
            if (freqCompare == 0) {
                return Character.compare(a, b);
            }
            return freqCompare;
        });

        // 상위 문자를 배열로 변환
        int topCharCount = Math.min(MAX_TOP_CHARS, charList.size());
        char[] topChars = new char[topCharCount];
        for (int i = 0; i < topCharCount; i++) {
            topChars[i] = charList.get(i);
        }
        return topChars;
    }

    // 히스토그램을 출력하는 메서드
    private static void printHistogram(char[] topChars, short[] charCounts) {
        int maxCount = charCounts[topChars[0]];
        int maxHeight = Math.min(maxCount, MAX_HEIGHT);
        int totalLines = maxHeight + 2; // 히스토그램 높이 + 빈도수 라인 + 문자 라인

        // 빈도수 스케일링
        int[] scaledCounts = new int[topChars.length];
        for (int i = 0; i < topChars.length; i++) {
            if (maxCount <= MAX_HEIGHT) {
                scaledCounts[i] = charCounts[topChars[i]];
            } else {
                scaledCounts[i] = (int) Math.ceil((double) charCounts[topChars[i]] * MAX_HEIGHT / maxCount);
            }
        }

        System.out.println();

        // 히스토그램 출력
        for (int row = 0; row < totalLines; row++) {
            for (int col = 0; col < topChars.length; col++) {
                if (topChars[col] != 0) {
                    if (row + scaledCounts[col] + 2 == totalLines) {
                        // 빈도수 출력
                        System.out.printf("%3d", charCounts[topChars[col]]);
                    } else if (row == totalLines - 1) {
                        // 문자 출력
                        System.out.printf("%3c", topChars[col]);
                    } else if (row + scaledCounts[col] >= maxHeight) {
                        // 히스토그램 막대 출력
                        System.out.printf("%3c", '#');
                    } else {
                        // 공백 출력
                        System.out.printf("   ");
                    }
                    // 열 사이의 공백 추가
                    if (col != topChars.length - 1 && topChars[col + 1] != 0) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}