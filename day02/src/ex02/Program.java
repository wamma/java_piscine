package ex02;

import java.io.File;

public class Program {
    private static File currentDirectory;
    public static void main(String[] args) {
        if (args.length > 0) {
            currentDirectory = new File(args[0]);
        } else {
            currentDirectory = new File("."); // 현재 디렉토리
        }
        if (!currentDirectory.isDirectory()) {
            System.err.println("올바른 디렉토리가 아닙니다.");
                return;
        }
    }
}
