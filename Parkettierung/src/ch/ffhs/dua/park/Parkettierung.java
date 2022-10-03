package ch.ffhs.dua.park;

import java.math.BigInteger;
import java.util.Arrays;

public class Parkettierung {

    public static void main(String[] args) {
        System.out.println(anzahlParkettierungen(2, 2));
    }

    private Parkettierung() {
    }

    /**
     * Berechnet die Anzahl möglicher Züge wie ein m x n Schachbrett mit Dominosteinen der Grösse 1 x 2 belegt werden kann.
     * @param m Anzahl der Reihen die das Schachbrett aufweist.
     * @param n Anzahl der Spalten die das Schachbrett aufweist.
     * @return gibt die Anzahl der möglichen Varianten wie die Steine gelegt werden zurück.
     */
    public static BigInteger anzahlParkettierungen(int m, int n) {
        // Prüft ob das Sachachbrett eine maximale grösse von 2 Feldern (oder kleiner Aufweist) und retourniert diesen Fall.
        if ((m * n) <= 2) return BigInteger.ONE;

        // Generiere ein neues Array und fülle es mit den initialen Spaltenlängen ab.
        int[] columnCounter = new int[m];
        Arrays.fill(columnCounter, n);

        return BigInteger.valueOf((placeOneDominoStone(columnCounter)));
    }

    private static int placeOneDominoStone(int[] columnCounter) {
        // Zähler für die erfolgreichen (kompletten) Belegungen des Schachbrettes.
        int successfulOccu = 0;

        // finde die höchste Anzahl in den spalten
        int indexOfLongestRow = 0;
        int numberOfLongestRow = columnCounter[0];
        for (int i = 0; i < columnCounter.length; i++) {
            if (columnCounter[i] > numberOfLongestRow) {
                indexOfLongestRow = i;
                numberOfLongestRow = columnCounter[i];
            }
        }

        // Wenn alle Felder belegt sind, retourniere den Wert 1 - für eine erfolgreiche Belegung des gesamten Spielfelds.
        if (numberOfLongestRow == 0) return 1;

        //Lege einen Stein Horizontal wenn in dieser Reihe möglich
        if (numberOfLongestRow >= 2) {
            // clone das array und fülle es mit den initialen werten ab
            int[] newHorizontalRowCounter = new int[columnCounter.length];
            System.arraycopy(columnCounter, 0, newHorizontalRowCounter, 0, columnCounter.length);
            // RowCount um einen horizontalen Stein (2) verringern und sum um eins erhöhen.
            newHorizontalRowCounter[indexOfLongestRow] = newHorizontalRowCounter[indexOfLongestRow] - 2;
            successfulOccu += placeOneDominoStone(newHorizontalRowCounter);
        }

        //Lege einen Stein vertical wenn in den nächsten beiden Reihen möglich
        //testen ob noch ein weiterer index verfügbar ist
        if ((columnCounter.length - 1) > indexOfLongestRow) {
            // testen ob noch eine gleich lange zeile unterhalb verüfgbar ist
            if (columnCounter[indexOfLongestRow] <= columnCounter[indexOfLongestRow + 1]) {
                if (numberOfLongestRow >= 1) {
                    //wähle gröste Zeile aus und setzte dominostein vertical.
                    int[] rowCopy2 = new int[columnCounter.length];
                    System.arraycopy(columnCounter, 0, rowCopy2, 0, columnCounter.length);
                    rowCopy2[indexOfLongestRow] = rowCopy2[indexOfLongestRow] - 1;
                    // testen ob noch ein weiterer index frei ist
                    rowCopy2[indexOfLongestRow + 1] = rowCopy2[indexOfLongestRow + 1] - 1;
                    successfulOccu += placeOneDominoStone(rowCopy2);
                }
            }
        }
        return successfulOccu;
    }
}
