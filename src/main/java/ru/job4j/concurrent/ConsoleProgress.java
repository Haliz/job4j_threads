package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] symbol = {'|', '/', '—', '\\'};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                for (int i = 0; i < symbol.length; i++) {
                    System.out.print("\r load: " + symbol[i]);
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }
}
