package ch.ffhs.dua.park;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Parkettierung {

    static int[] rowCount;

    // Diese HashMap kann zum Caching verwendet werden
    private static final HashMap<String, BigInteger> CACHE = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(anzahlParkettierungen(2, 3));
    }

    private Parkettierung() {
    }

    public static BigInteger anzahlParkettierungen(int m, int n) {
        if ((m * n) <= 2) return BigInteger.ONE;
        if ((m * n) == 2) return BigInteger.ONE;

        // Generiere ein neues Array und fülle es mit den standardmässigen spalten
        rowCount = new int[m];
        Arrays.fill(rowCount, n);

        return BigInteger.valueOf((calcPossibility(rowCount)));
    }

    private static int calcPossibility(int[] rowCount) {

        int sum = 0;

        // finde die höchste Anzahl in den spalten
        int index = 0;
        int start = rowCount[0];
        for (int i = 0; i < rowCount.length; i++) {
            if (rowCount[i] > start) {
                index = i;
                start = rowCount[i];
            }
        }

        //wähle grösste Zeile aus und setzte dominostein horizontal. wenn ein solcher stein noch platz hat.
        if (start >= 2) {
            int[] rowCopy = new int[rowCount.length];
            System.arraycopy(rowCount, 0, rowCopy, 0, rowCount.length);
            rowCopy[index] = rowCopy[index] - 2;
            // nur sum +1 wenn nicht der letzte stein ist!
            sum++;
            /**
             * hier sollte die rekusion starten
             */
            sum += calcPossibility(rowCopy);
        }


        //testen ob noch ein weiterer index verfügbar ist
        if ((rowCount.length - 1) > index) {

            // testen ob noch eine gleich lange zeile unterhalb verüfgbar ist
            if (rowCount[index] <= rowCount[index + 1]) {

                if (start >= 1) {

                    //wähle gröste Zeile aus und setzte dominostein vertical.
                    int[] rowCopy2 = new int[rowCount.length];
                    System.arraycopy(rowCount, 0, rowCopy2, 0, rowCount.length);
                    rowCopy2[index] = rowCopy2[index] - 1;
                    // testen ob noch ein weiterer index frei ist
                    if ((rowCopy2.length - 1) > index) {
                        rowCopy2[index + 1] = rowCopy2[index + 1] - 1;
                        sum++;
                        /**
                         * hier sollte die rekusion starten
                         */
                        sum += calcPossibility(rowCopy2);
                    }
                }
            }
        }
        return sum;
    }

}