package ex00;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        int count = 50;

        // 명령어 인자 처리
        for (String arg : args) {
            if (arg.startsWith("--count=")) { // 수정된 부분
                try {
                    count = Integer.parseInt(arg.split("=")[1]);
                } catch(NumberFormatException e) {
                    System.out.println("Invalid count value. Using default 50.");
                }
            }
        }

        // "Egg" 스레드 생성 및 시작
        Thread eggThread = new Thread(new EggRunnable(count));
        eggThread.start();
        Thread henThread = new Thread(new HenRunnable(count));
        henThread.start();

        // 두 스레드가 완료될 때까지 대기
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }

        // "Human" 메시지 출력
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}

class EggRunnable implements Runnable {
    private int count;

    public EggRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Egg");
        }
    }
}

class HenRunnable implements Runnable {
    private int count;

    public HenRunnable(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    }
}