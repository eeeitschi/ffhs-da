package ch.ffhs.dua.binSearch;

public class BinSearch {
    /**
     * Findet für einen aufsteigend geordneten Array zu einer Zahl value
     * den kleinsten und den grössten Index.
     *
     * @param array
     * @param value
     * @return Ein Paar mit kleinestem und grösstem Index oder
     * null, wenn der gegebene Wert im array nicht vorkommt.
     */
    public static Pair search(int[] array, int value) {
        // Tested ob das Array korrekt initialisiert wuerde.
        if (array.length == 0) return null;
        // Startet den Rekusions-Algorithmus.
        return rank(array, 0, array.length, value);
    }

    /**
     * Durchsucht ein Array in rekursiver Vorgehensweise und sucht den kleinsten
     * sowie grössten Index an welcher die parametrisierte Zahl vorkommt.
     *
     * @param array Das array das durchsucht werden soll
     * @param low   Die untere Grenze der durchsuchung
     * @param high  Die obere Grenze der durchsuchung
     * @param value Der Wert nach dem gesucht werden soll..
     * @return Gibt ein Paar zurück mit dem oberen und unteren Maximum
     */
    public static Pair rank(int[] array, int low, int high, int value) {
        // Berechnet den mittleren Index des Arrays.
        int mid = low + (high - low) / 2;
        Pair lowPair;
        Pair highPair;

        // Prüft ob der mittlere Index bereits auf dem "low" Value liegt.
        if (low < mid) {
            // Ruft eine weitere Rekusionsstufe auf.
            lowPair = rank(array, low, mid, value);
        } else {
            // Erstelle neues Paar wenn der gesuchte Wert vorkommt.
            if (array[low] == value) return new Pair(low, mid);
            else return null;
        }

        // Prüft ob der mittlere Index bereits auf dem "high" Value liegt.
        if (mid < high) {
            // Ruft eine weitere Rekusionsstufe auf.
            highPair = rank(array, mid, high, value);
        } else {
            // Erstelle neues Paar wenn der gesuchte Wert vorkommt.
            if (array[mid] == value) return new Pair(mid, high);
            else return null;
        }

        // Prüft die erhaltenen Paare nach "null" und erstellt aus den Extremwerten ein neues Paar.
        if (lowPair != null && highPair != null) {
            return new Pair(
                    Math.min(lowPair.lower, highPair.lower),
                    Math.max(lowPair.higher, highPair.higher)
            );
        }
        else if (lowPair == null && highPair != null) return highPair;
        else return lowPair;
    }
}
