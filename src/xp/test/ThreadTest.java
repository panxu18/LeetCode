package xp.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    public static void main(String args[]){
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Extend from Thread");
            }
        };
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        });
        t2.start();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("Callable");
            return "Success";
        });
        Thread t3 = new Thread(futureTask);
        t3.start();
        try {
            futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
