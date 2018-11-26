package app;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> data = new LinkedList<>();
		System.out.println("Program is running");
        EnglishNumberParserTask task1 = new EnglishNumberParserTask(data);
        DeleteNumberTask task2 = new DeleteNumberTask(data);
        Thread thread1 = new Thread(task1, "EnglishNumberParserThread");
        Thread thread2 = new Thread(task2, "DeleteNumberThread");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
