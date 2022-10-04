package ch.ffhs.dua.park;

import java.math.BigInteger;
import java.util.Arrays;

public class Parkettierung {

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

        // Finde die höchste Anzahl von Spalten aus allen Reihen.
        int indexMaxRow = 0;
        int numMaxRow = columnCounter[0];
        for (int i = 0; i < columnCounter.length; i++) {
            if (columnCounter[i] > numMaxRow) {
                indexMaxRow = i;
                numMaxRow = columnCounter[i];
            }
        }

        // Retourniere 1 wenn alle Felder erfolgreich belegt wurden.
        if (numMaxRow == 0) return 1;

        // Legt einen Stein horizontal in das Spielfend wenn es in der gewählten Reihe möglich ist.
        if (numMaxRow >= 2) {
            // Kopiere das array (deep copy) und berechne die neuen Reihenlängen.
            int[] newHorizontalRowCounter = new int[columnCounter.length];
            System.arraycopy(columnCounter, 0, newHorizontalRowCounter, 0, columnCounter.length);
            newHorizontalRowCounter[indexMaxRow] = newHorizontalRowCounter[indexMaxRow] - 2;
            // Starte eine neue Rekusionsebene.
            successfulOccu += placeOneDominoStone(newHorizontalRowCounter);
        }

        // Legt einen Stein vertikal in das Spielfeld wenn es in gewählten sowie der darauffolgenden Reihe möglich ist.
        if ((columnCounter.length - 1) > indexMaxRow) {
            // Testet ob noch eine gleich lange Zeile unterhalb verüfgbar ist.
            if (columnCounter[indexMaxRow] <= columnCounter[indexMaxRow + 1]) {
                if (numMaxRow >= 1) {
                    // Kopiere das array (deepcopy) und berechne die neuen Reihenlängen.
                    int[] rowCopy2 = new int[columnCounter.length];
                    System.arraycopy(columnCounter, 0, rowCopy2, 0, columnCounter.length);
                    rowCopy2[indexMaxRow] = rowCopy2[indexMaxRow] - 1;
                    rowCopy2[indexMaxRow + 1] = rowCopy2[indexMaxRow + 1] - 1;
                    // Starte eine neue Rekusionsebene.
                    successfulOccu += placeOneDominoStone(rowCopy2);
                }
            }
        }
        return successfulOccu;
    }
}
