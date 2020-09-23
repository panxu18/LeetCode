package xp.pan.qunar;

import java.util.*;
import java.util.stream.Collectors;

public class Test0923_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Poke> pokeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pokeList.add(new Poke(in.next()));
        }
        Pipeline pipeline = new Pipeline();
        pipeline.addLast(new Handler10Goals());
        pipeline.addLast(new Handler9Goals());
        pipeline.addLast(new Handler1Goals());
        System.out.println(pipeline.start(pokeList));
    }

    static class Poke {
        static TreeMap<String, Integer> map;

        static {
            TreeMap<String, Integer> tempMap = new TreeMap<>();
            String[] strings = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
            for (int i = 0; i < strings.length; i++) {
                tempMap.put(strings[i], i + 1);
            }
            map = tempMap;
        }

        String type;
        int val;

        public Poke(String str) {
            this.type = str.substring(0, 1);
            this.val = map.get(str.substring(1));
        }

        public String getType() {
            return type;
        }

        public int getVal() {
            return val;
        }
    }

    interface Handler {
        boolean check(List<Poke> list);

        String result();
    }

    static class Pipeline {
        ArrayList<Handler> handlers = new ArrayList<>();

        String start(List<Poke> pokeList) {
            for (Handler h : handlers) {
                if (h.check(pokeList)) {
                    return h.result();
                }
            }
            return null;
        }

        void addLast(Handler handler) {
            handlers.add(handler);
        }
    }

    static class Handler10Goals implements Handler {

        @Override
        public boolean check(List<Poke> list) {
            return list.stream().collect(Collectors.groupingBy(Poke::getType, Collectors.toList()))
                    .values().stream()
                    .map(this::checkShunZi)
                    .filter(Boolean::booleanValue)
                    .findAny()
                    .orElse(false);
        }

        private boolean checkShunZi(List<Poke> list) {
            if (list.size() < 5) {
                return false;
            }
            Set<Integer> set = list.stream().map(p -> p.val).sorted().collect(Collectors.toSet());
            int[] checkList = {1, 13, 12, 11, 10};
            for (int i = 0; i < checkList.length; i++) {
                if (!set.contains(checkList[i])) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String result() {
            return "HuangJiaTongHuaShun";
        }
    }

    static class Handler9Goals implements Handler {

        @Override
        public boolean check(List<Poke> list) {
            if (list.size() >= 5) {
                return true;
            }
            return false;

        }

        @Override
        public String result() {
            return "TongHuaShun";
        }
    }

    static class Handler1Goals implements Handler {

        @Override
        public boolean check(List<Poke> list) {
            return true;
        }

        @Override
        public String result() {
            return "LiangDui";
        }
    }


}
