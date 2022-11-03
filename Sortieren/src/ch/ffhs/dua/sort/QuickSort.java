package ch.ffhs.dua.sort;

public class QuickSort {
    /**
     * Sortiert ein Array durch Quicksort.
     * @param array Zu sortierendes Array.
     */
    public static void sort(int[] array) {
        // Starte die Sortierung mit dem ersten und letzten Index.
        sort(array, 0, array.length - 1);
    }

    /**
     * Sortiert ein Teilstück eines Arrays durch Quicksort.
     * @param array ZU sortierenden Array
     * @param start Index des ersten Elementes des Teils, das sortiert werden muss.
     * @param end   Index des letzen Elementes des Teils, das sortiert werden muss.
     */
    public static void sort(int[] array, int start, int end) {
        // Testet ob das ende der Rekusion erreicht wurde.
        if (start >= end) return;

        int piv = findPivot(array, start, end);
        int pivotPos = partition(array, start, end, piv);
        sort(array, start, pivotPos - 1);
        sort(array, pivotPos + 1, end);
    }

    /**
     * Schwellwert, bei welcher Arraygrösse in der Rekursion InsertSort
     * statt Quicksort aufgerufen werden sollte.
     */
    static int THRESHOLD = 20; // TODO finden Sie einen sinnvollen Wert

    /**
     * Modifiziertes Quicksorts.
     * Wenn die Grösse des zu sortierenden Arrays in der Rekursion
     * einen Schwellwert unterschreitet, wird InsertSort statt Quicksort
     * aufgerufen.
     *
     * @param array Zu sortierendes Array
     */
    public static void sortPlus(int[] array) {
        sortPlus(array, 0, array.length - 1);
    }

    /**
     * Modifiziertes Quicksorts zum SOrtieren eines Teilstücks eines Arrays.
     * Wenn die Grösse des zu sortierenden Arrays in der Rekursion
     * einen Schwellwert unterschreitet, wird InsertSort statt Quicksort
     * aufgerufen.
     * @param array Zu sortierendes Array
     * @param start Index des ersten  Elementes des zu sortierenden teilstücks.
     * @param end   Index des letzten Elementes des zu sortierenden teilstücks.
     */
    public static void sortPlus(int[] array, int start, int end) {
        while (start < end) {
            // Wenn array kleiner als Threshold, InsertSort ausführen.
            if ((end - start) < THRESHOLD) {
                InsertSort.sort(array, start, end);
                break;
            } else {
                // Pivot element finden
                int piv = findPivot(array, start, end);
                int pivot = partition(array, start, end, piv);

                // Rekursiv den Aufruf starten um kleine Elemente zu sortieren.
                if (pivot - start < pivot - end) {
                    sortPlus(array, start, pivot - 1);
                    start = pivot + 1;
                } else {
                    sortPlus(array, pivot + 1, end);
                    end = pivot - 1;
                }
            }
        }
    }

    /**
     * Hilfsmethode für Quicksort.
     * Ein Teilstück eines Arrays wird geteilt, so dass alle Elemente,
     * die kleiner als ein gewisses Pivot-Elements sind, links stehen
     * und alle Elemente, die grösser als das Pivot-Element rechts stehen.
     * @param array Array zum Umordnen.
     * @param start Indes des ersten  Elements des Teilstücks, das geteilt werden muss.
     * @param end   Index des letztes Elements des Teilstücks, das geteilt werden muss.
     * @param piv   Index des PiotElements
     * @return Index des Piot-Element nach der Partitionierung.
     */
    static int partition(int[] array, int start, int end, int piv) {
        // Markierung für nächstes startelement
        int i = (start - 1);

        // Vergleiche jedes element mit dem pivot Element
        for (int j = start; j < end; j++) {
            // Wenn das element kleiner ist, swap ausführen
            if (array[j] <= piv) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        // Gibt die position wo die Partition erledigt wurde zurück.
        return (i + 1);
    }

    /**
     * Hilfsmethode zum Vertauschen zweier Array-Elemente
     * @param array
     * @param a
     * @param b
     */
    static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    /**
     * Hilfsmethode zum Finden eines Pivot-Elementes für Quicksort.
     * Zu einem Array und den zwei Indices start und end wird
     * der Index eines möglichen Pivot-Elementes angegeben
     * @param array
     * @param start
     * @param end
     * @return Index eines Pivot-Elementes
     */
    static int findPivot(int[] array, int start, int end) {
        if (start < end) return array[end];
        return -1;
    }
}
