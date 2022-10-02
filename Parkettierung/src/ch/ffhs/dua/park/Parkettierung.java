package ch.ffhs.dua.park;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

public class Parkettierung {

    public static void main(String[] args) {
        System.out.println(anzahlParkettierungen(2, 2));
    }

    private Parkettierung() {
    }

    public static BigInteger anzahlParkettierungen(int m, int n) {
        if ((m * n) == 1) return BigInteger.ONE;

        return BigInteger.valueOf(calcPossibilities(m, n));
    }

    // Diese HashMap kann zum Caching verwendet werden
    private static final HashMap<String, BigInteger> CACHE = new HashMap<>();

    private static int calcPossibilities(int m, int n) {
        for (int i = 0; i <= m; i++) {
            int step = 0;
            while (step <= n) {
                int temp = 0;


            }

        }
    }

    public static BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number.longValue(); factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }
}
