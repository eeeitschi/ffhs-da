package ch.ffhs.dua.sort;

public class HeapSort {
    /**
     * Sortiert ein Array mit Heapsort.
     * @param array
     */
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * Sortiert ein Teilstück eines Array s mit Heapsort.
     * @param array
     * @param start Index des ersten  Elementes des zu sortierenden Teils.
     * @param start Index des letzten Elementes des zu sortierenden Teils.
     */
    public static void sort(int[] array, int start, int end) {
        makeHeap(array, start, end);

        for (int i = end; i >= 0; i--) {
            // Wechselt die positionen der Elemente
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            // Generiert wieder einen vollständigen (maximalen) Heap
            sink(array, 0, i, 0);
        }
    }

    /**
     * Erzeugt aus einem angegebenen Teilstück einen Heap.
     * @param start Index des ersten Elementes, aus dem ein Heap erzeugt werden sollte.
     *              Das ist auch der Index der Wurzel des Heaps; die Kinder der Wurzel
     *              liegen dann an den Position start+1 und start+2.
     * @param end   Index des letzten Elementes des Stücks, aus dem ein Heap erzeugt werden sollte.
     */
    public static void makeHeap(int[] array, int start, int end) {
        end = end + 1;
        // Generiert einen vollständigen Heap aus dem übermittelten Array.
        for (int i = end / 2; i >= 0; i--) {
            sink(array, start, end, i + start);
        }
    }

    /**
     * Hilfsmethode für Heapsort:
     * Aus einem Teilstück eines Arrays mit den Grenzen start und end
     * sollte ein Heap erzeugt werden. Für Indices grösser als index
     * sei die Heap-Eigenschaft bereits erfüllt.
     * Die Methode ordnet das Stück zwischen index und end so um,
     * dass die Heapeigenschaft für alle Elemente erfüllt ist.
     */
    static void sink(int[] array, int start, int end, int index) {
        // Übernehme das grösste Element, und finde das linke sowie rechte Child.
        int largest = index;
        int l = 2 * index + 1 - start;
        int r = 2 * index + 2 - start;

        // Vergleiche ob linkes oder rechtes kind grösser als Root ist.
        if (l < end && array[l] > array[largest]) largest = l;
        if (r < end && array[r] > array[largest]) largest = r;

        // Tausche Element aus und generiere einen neuen (max) Heap.
        if (largest != index) {
            int swap = array[index];
            array[index] = array[largest];
            array[largest] = swap;
            sink(array, start, end, largest);
        }
    }

    /**
     * Entfernt das Wurzelelement eines Heaps, baut den Heap um,
     * so dass er nach dem Entfernen wieder ein Heap ist (mit einem Element weniger),
     * und setzt das ehemalige Wurzelelement an die vormals letzte Stelle im Heap
     * (die nun nicht mehr zum Heap gehört).
     * @param array Ein Array, das als Teilstück einen heap enthält.
     * @param start Indes der Wurzel des heaps
     * @param end   Index des letzten Heap-Elements.
     */
    public static void removeHeapRoot(int[] array, int start, int end) {
        // Nimmt das letzte Element
        int lastElement = array[end];
        // Ersetzte das letzte Element mit dem ersten
        array[end] = array[0];
        array[0] = lastElement;
        // Verringere die Heap-Grösse um 1 generiere einen neuen (max) Heap.
        end = end - 1;
        sink(array, start, end + 1, 0);
    }

    /**
     * Berechnet den Index des linken Kindelementes in einem Heap.
     * @param parentIndex
     * @param offset      Offset für Heap-Eigenschaft: entspricht
     *                    dem Index der Heapwurzel - 1
     * @return Index des linken Kindes
     */
    static int leftChild(int parentIndex, int offset) {
        return 2 * parentIndex - offset;
    }

    /**
     * Berechnet den Index des rechten Kindelementes in einem Heap.
     * @param parentIndex
     * @param offset      Offset für Heap-Eigenschaft: entspricht
     *                    dem Index der Heapwurzel - 1
     * @return Index des rechten Kindes
     */
    static int rightChild(int parentIndex, int offset) {
        return leftChild(parentIndex, offset) + 1;
    }
}