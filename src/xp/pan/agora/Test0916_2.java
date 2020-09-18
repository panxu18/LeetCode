package xp.pan.agora;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class Test0916_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] arr = in.nextLine().split("");
        Map<String, Integer> countMap = Arrays.stream(arr)
                .collect(toMap(Function.identity(), (val)-> 1, (oldVal, newVal) -> ++oldVal));
        Arrays.stream(arr)
                .filter(s -> countMap.get(s) == 1)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
