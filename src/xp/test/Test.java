package xp.test;

import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test{

    public static void main(String[] args) {
        List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
        System.out.println(MapReduceer.mapReduce(list, i->String.format("[%d]", i), String::concat));
    }
}

class MapReduceer<E, R> extends CountedCompleter<R> {
    private final Function<E, R> mapper;
    private final BiFunction<R, R, R> reducer;
    private final List<E> list;
    private final int lo, hi;
    MapReduceer<E,R> sibling;
    private R result;
    public MapReduceer(MapReduceer<E,R> fa,  Function<E, R> mapper, BiFunction<R,R,R> reducer, List<E> list, int lo, int hi) {
        this.mapper = mapper;
        this.reducer = reducer;
        this.list = list;
        this.lo = lo;
        this.hi = hi;
    }

    @Override
    public R getRawResult() {
        return result;
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        MapReduceer<E, R> child = (MapReduceer<E, R>)caller;
        MapReduceer<E, R> sib = child.sibling;
        // 合并子任务结果，只有两个子任务
        if (sib == null || sib.result == null)
            result = child.result;
        else
            result = reducer.apply(child.result, sib.result);
        System.out.println(result);
    }

    @Override
    public void compute() {
        if (hi - lo >= 2) {
//            System.out.println(String.format("%d, %d", lo, hi));
            int mid = (hi + lo) >> 1;
            addToPendingCount(1);
            MapReduceer<E,R> left = new MapReduceer<E, R>(this, mapper, reducer, list, lo, mid);
            MapReduceer<E,R> right = new MapReduceer<E, R>(this, mapper, reducer, list, mid, hi);
            left.sibling = right;
            right.sibling = left;
            right.fork();
            left.compute();
        } else {
            if (hi > lo) {
                result = mapper.apply(list.get(lo));
            }
            tryComplete();
        }
    }

    public static <E, R> R mapReduce(List<E> list, Function<E, R> mapper, BiFunction<R, R, R> reducer) {
        return new MapReduceer<E, R>(null, mapper, reducer,list, 0, list.size()).invoke();
    }
}