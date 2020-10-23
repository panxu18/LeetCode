package xp.pan.xiecheng;

import java.util.HashMap;
import java.util.Scanner;

public class Test1013_3 {
    static int validateArrayUsages(String[] lines) {
        HashMap<String, Integer> arrayMap = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            int idx1 = lines[i].indexOf('[');
            String name = lines[i].substring(0, idx1);
            int idx2 = lines[i].indexOf(']');
            int size = Integer.parseInt(lines[i].substring(idx1 + 1, idx2));
            if (lines[i].contains("=")) {
                if (arrayMap.get(name) <= size) {
                    return i + 1;
                }
                int idx3 = lines[i].indexOf('=');
                int idx4 = lines[i].lastIndexOf('[');
                int idx5 = lines[i].lastIndexOf(']');
                if (idx4 > idx3) {
                    name = lines[i].substring(idx3 + 1, idx4);
                    size = Integer.parseInt(lines[i].substring(idx4 + 1, idx5));
                    if (arrayMap.get(name) <= size) {
                        return i + 1;
                    }
                }
            } else {
                arrayMap.put(name, size);
            }
        }
        return 0;

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _lines_size = 0;
        _lines_size = Integer.parseInt(in.nextLine().trim());
        String[] _lines = new String[_lines_size];
        String _lines_item;
        for(int _lines_i = 0; _lines_i < _lines_size; _lines_i++) {
            try {
                _lines_item = in.nextLine();
            } catch (Exception e) {
                _lines_item = null;
            }
            _lines[_lines_i] = _lines_item;
        }

        res = validateArrayUsages(_lines);
        System.out.println(String.valueOf(res));

    }
}
