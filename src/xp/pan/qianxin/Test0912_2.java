package xp.pan.qianxin;

public class Test0912_2 {

    public static void main(String[] args) {
        System.out.println(getMaxArea(0,0,1,1,9,9,10,10)); // 81
        System.out.println(getMaxArea(4,1,6,9,1,4,9,6)); // 16
        System.out.println(getMaxArea(0,0, 0, 0, 0, 0, 0, 0)); // 0
    }
    public static int getMaxArea (int x1, int y1, int x2, int y2, int x3,
                           int y3, int x4, int y4) {
        // write code here
        int ans = 0;
        Rec rec1 = new Rec(x1, y1, x2, y2);
        Rec rec2 = new Rec(x3, y3, x4, y4);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (rec1.contains(i, j) || rec2.contains(i, j)) {
                    continue;
                }
                for (int k = i + 1; k < 11; k++) {
                    for (int l = j + 1; l < 11; l++) {
                        if (rec1.overlay(i, j, k, l) || rec2.overlay(i, j, k, l)) {
                            continue;
                        }
                        ans = Math.max(Rec.area(i, j, k, l), ans);
                    }
                }
            }
        }
        return ans;

    }

    static class Rec {
        int x1, y1, x2, y2;

        public Rec(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean overlay(int x1, int y1, int x2, int y2) {
            int zx = Math.abs(this.x1 + this.x2 - x1 - x2);
            int zy = Math.abs(this.y1 + this.y2 - y1 - y2);
            int x = Math.abs(this.x1 - this.x2) + Math.abs(x1 - x2);
            int y = Math.abs(this.y1 - this.y2) + Math.abs(y1 - y2);
            return zx < x && zy < y;
        }

        public boolean contains(int x, int y) {
            return (x > this.x1 && x < this.x2) && (y > this.y1 && y < this.y2);
        }

        public static int area(int x1, int y1, int x2, int y2) {
            return (x2 - x1) * (y2 - y1);
        }
    }


}
