package xp.pan.dahua;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Test0908_1 {
    public static void main(String[] args) {

    }

    public float getAvgTravel(int beginAddress, int endAddress, List<ShootCar> shootCars) {
        return (float) shootCars.stream()
                .filter(c -> c.adrid == beginAddress || c.adrid == endAddress)
                .collect(Collectors.groupingBy(c->c.carNum))
                .values()
                .stream()
                .mapToLong(this::computeTravelTime)
                .average()
                .orElse(0.0f);
    }

    private long computeTravelTime(List<ShootCar> shootCars) {
        return Math.abs(shootCars.get(0).shootTime.getTime() - shootCars.get(1).shootTime.getTime());
    }

    static class ShootCar{
        private int adrid;
        private String carNum;
        private Date shootTime;

    }
}
