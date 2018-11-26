package app;

import java.util.List;

public class DeleteNumberTask implements Runnable {

    private final List<Integer> data;

    public DeleteNumberTask(List<Integer> data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            deleteMinNumber();
        }
    }

    private void deleteMinNumber() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (data) {
            if (!data.isEmpty()) {
                int min = data.get(0);
                for (int value : data) {
                    if (min > value) {
                        min = value;
                    }
                }
                data.remove(new Integer(min));
                System.out.println(min);
            }
        }
    }
}
