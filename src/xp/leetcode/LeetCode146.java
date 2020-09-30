package xp.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCode146 {
    class LRUCache {
        private LinkedHashMap<Integer, Integer> cache;
        public LRUCache(int capacity) {
            cache = new LinkedHashMap<>(capacity, 0.75f, true) {

                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };

        }

        public int get(int key) {
            Integer res = cache.get(key);
            return res == null ? -1 : res;
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }

    }

}
