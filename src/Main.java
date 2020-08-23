import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.printf("now: %d\n", now.getTime());
        Date copy = (Date) now.clone();
        copy.setDate(10);
        System.out.printf("now: %d\n", now.getTime());
        System.out.printf("copy: %d\n", copy.getTime());
    }
}