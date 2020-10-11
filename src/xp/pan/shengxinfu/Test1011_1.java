package xp.pan.shengxinfu;

public class Test1011_1 {

    public int solve(int x) {
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 - C/x0);
            if (Math.abs(xi - x0) < 0.00001) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
}
