package xp.oj;

public class COWListTest {

    public static void main(String args[]) throws InterruptedException {
//        CopyOnWriteArrayList<Integer> COWList =
//                IntStream.iterate(0,a->a+1)
//                        .limit(10)
//                        .boxed()
//                        .collect(toCollection(CopyOnWriteArrayList::new));
//        Thread thread1 = new Thread(() -> COWList.remove(5));
//        Thread thread2 = new Thread(() -> {
//            // 1、通过迭代器遍历
//            for(Integer val : COWList)
//                System.out.println(val);
//            ///2、通过索引遍历
//            for (int i = 0; i < 10; i++)
//                System.out.println(COWList.get(i));
//        });
//        thread2.start();
//        thread1.start();
//        thread1.join();
//        thread2.join();
        Integer i = 20;
        Integer j = 20;
        System.out.println(i==j);

    }
}
