package xp.PB.CVTE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther Peng
 * @date 2020/9/23 - 16:24
 */
//每秒执行一次，队列娶一个元素，高可用，两个任务 一个挂另外一个运行 不能同时，
    //一秒执行一次 全局队列模拟任务队列

class Print{
    public Lock lock = new ReentrantLock();
    public Condition condition1 = lock.newCondition();
    public Condition condition2 = lock.newCondition();
    public int num = 1;

    public void threadOnePrint() {
        lock.lock();
        try {
            while(num != 1){
                condition1.await();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"-1");
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void threadTwoPrint() {
        lock.lock();
        try {
            while(num != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"-2");
            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class PrintTwo implements Runnable{
//    public test t = new test();


    @Override
    public synchronized void run() {

        while(!test.queue.isEmpty()){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            System.out.println(Thread.currentThread().getName()+"-"+ test.queue.remove() );
//            throw new RuntimeException("yunxinshiyic");
        }

    }
}
public class test {
    public static Queue<Integer> queue  = new LinkedList<>();

    public static void main(String[] args) {
        for(int i =0;i<20;i++){
            queue.add(i);
        }

//        Print print = new Print();
//        new Thread(()->{
//            for (int i=1;i<10;i++){
//                print.threadOnePrint();
//            }
//        },"One").start();
//        new Thread(()->{
//            for (int i=1;i<10;i++){
//                print.threadTwoPrint();
//            }
//        },"Two").start();

        PrintTwo printTwo = new PrintTwo();
//        for(int  i =1;i<20;i++){
//            new Thread(printTwo,String.valueOf(i)).start();
//        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));
        for (int i =0;i<50;i++){
            threadPoolExecutor.execute(printTwo);
        }

    }
}
