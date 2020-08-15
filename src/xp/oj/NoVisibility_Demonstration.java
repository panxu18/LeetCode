package xp.oj;

public class NoVisibility_Demonstration extends Thread{
    boolean keepRunning = true;
    volatile int va = 0;
    public static void main(String[] args) throws InterruptedException {
        NoVisibility_Demonstration t = new NoVisibility_Demonstration();
        t.start();
        System.out.println("start: " + t.keepRunning);
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("end: " +t.keepRunning);
    }
    public void run() {
        int x = 1;
        while (keepRunning) {
            x++;

        }
    }
}
