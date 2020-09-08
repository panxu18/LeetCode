package xp.DL.腾讯;

import java.util.PriorityQueue;

public class t4 {
    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(((o1, o2) -> o2.compareTo(o1)));
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
    int count = 0;

    public void Insert(Integer num) {
        count++;
        leftMaxHeap.add(num);
        rightMinHeap.add(leftMaxHeap.poll());
    }

    public Double GetMedian() {
        return (double) rightMinHeap.peek();
    }
}
