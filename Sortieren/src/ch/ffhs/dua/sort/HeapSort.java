package ch.ffhs.dua.sort;

import java.util.Arrays;

public class HeapSort {
    /**
     * Sortiert ein Array mit Heapsort.
     *
     * @param array
     */
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * Sortiert ein Teilstück eines Array s mit Heapsort.
     *
     * @param array
     * @param start Index des ersten  Elementes des zu sortierenden Teils.
     * @param start Index des letzten Elementes des zu sortierenden Teils.
     */
    public static void sort(int[] array, int start, int end) {
        System.out.println("start -> " + Arrays.toString(array));
        makeHeap(array, start, end);
        System.out.println("maxhe -> " + Arrays.toString(array));

        for (int i = end; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            System.out.println("step " + i + "-> " + Arrays.toString(array));
            // Heapify root element
            sink(array, 0, i, 0);
        }
        System.out.println("end   -> " + Arrays.toString(array));
    }

    /**
     * Erzeugt aus einem angegebenen Teilstück einen Heap.
     *
     * @param array
     * @param start Index des ersten Elementes, aus dem ein Heap erzeugt werden sollte.
     *              Das ist auch der Index der Wurzel des Heaps; die Kinder der Wurzel
     *              liegen dann an den Position start+1 und start+2.
     * @param end   Index des letzten Elementes des Stücks, aus dem ein Heap erzeugt werden sollte.
     */
    public static void makeHeap(int[] array, int start, int end) {
        int n = end - start;
        for (int i = n / 2; i >= 0; i--) {
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
     *
     * @param array
     * @param start
     * @param end
     * @param index
     */
    static void sink(int[] array, int start, int end, int index) {
        // Find largest among root, left child and right child
        int largest = index;
        int l = leftChild(index, start - 1);
        int r = rightChild(index, start - 1);

        if (l <= end && array[l] > array[largest]) largest = l;
        if (r <= end && array[r] > array[largest]) largest = r;

        // Swap and continue heapifying if root is not largest
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
     *
     * @param array Ein Array, das als Teilstück einen heap enthält.
     * @param start Indes der Wurzel des heaps
     * @param end   Index des letzten Heap-Elements.
     */
    public static void removeHeapRoot(int[] array, int start, int end) {
        // Get the last element
        int lastElement = array[end];
        // Replace root with last element
        array[0] = lastElement;
        // Decrease size of heap by 1
        end = end - 1;
        // heapify the root node
        sink(array,start, end, 0);
    }

    /**
     * Berechnet den Index des linken Kindelementes in einem Heap.
     *
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
     *
     * @param parentIndex
     * @param offset      Offset für Heap-Eigenschaft: entspricht
     *                    dem Index der Heapwurzel - 1
     * @return Index des rechten Kindes
     */
    static int rightChild(int parentIndex, int offset) {
        return leftChild(parentIndex, offset) + 1;
    }


}
