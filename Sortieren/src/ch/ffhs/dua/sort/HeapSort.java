package ch.ffhs.dua.sort;

import java.util.Arrays;

public class HeapSort
{
	/**
	 * Sortiert ein Array mit Heapsort.
	 * @param array
	 */
	public static void sort(int[] array)
	{
		sort(array, 0, array.length);
	}
	
	/**
	 * Sortiert ein Teilstück eines Array s mit Heapsort.
	 * @param array
	 * @param start Index des ersten  Elementes des zu sortierenden Teils.
	 * @param start Index des letzten Elementes des zu sortierenden Teils.
	 */
	public static void sort(int[] array, int start, int end)
	{
		//Rearrange array (building heap)
		for (int i = end / 2 - 1; i >= start; i--) {
			makeHeap(array, end, i);
		}

		//Extract elements from heap one by one
		for (int i = end - 1; i >= start; i--) {
			//Current root moved to the end
			int tmp = array[0];
			array[0] = array[i];
			array[i] = tmp;

			makeHeap(array, i, 0);//calling max heapify on the heap reduced
		}
	}
	
	/**
	 * Erzeugt aus einem angegebenen Teilstück einen Heap.
	 * @param array
	 * @param start Index des ersten Elementes, aus dem ein Heap erzeugt werden sollte.
	 *              Das ist auch der Index der Wurzel des Heaps; die Kinder der Wurzel
	 *              liegen dann an den Position start+1 und start+2.
	 * @param end   Index des letzten Elementes des Stücks, aus dem ein Heap erzeugt werden sollte.
	 */
	public static void makeHeap(int[] array, int start, int end)
	{
		// bubble down from the leaf nodes up to the top
		for (int index = array.length - 1; index >= 0; index--) {
			sink(array, start, array.length, index);
		}
	}
	
	/**
	 * Hilfsmethode für Heapsort:
	 * Aus einem Teilstück eines Arrays mit den Grenzen start und end
	 * sollte ein Heap erzeugt werden. Für Indices grösser als index
	 * sei die Heap-Eigenschaft bereits erfüllt.
	 * Die Methode ordnet das Stück zwischen index und end so um, 
	 * dass die Heapeigenschaft für alle Elemente erfüllt ist.
	 * @param array
	 * @param start
	 * @param end
	 * @param index
	 */
	static void sink(int[] array, int start, int end, int index)
	{
		// TODO	(Implementieren Sie diese Methode, wenn Sie sie für die Sort-Methoden brauchen.		+
		while (index < end) {
			int leftIndex  = leftChild(index, 1);
			int rightIndex = rightChild(index, 2);

			// if we don't have any child nodes, we can stop
			if (leftIndex >= end) {
				break;
			}

			// find the larger of the two children
			int largerChildIndex = leftIndex;
			if (rightIndex < end && array[leftIndex] < array[rightIndex]) {
				largerChildIndex = rightIndex;
			}

			// are we larger than our children?
			// if so, swap with the larger child.
			if (array[index] < array[largerChildIndex]) {
				int tmp = array[largerChildIndex];
				array[largerChildIndex] = array[index];
				array[index] = tmp;

				// continue bubbling down
				index = largerChildIndex;
			}
			else {

				// we're larger than both children, so we're done
				break;
			}
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
	public static void removeHeapRoot(int[] array, int start, int end)
	{
		// TODO	(Implementieren Sie diese Methode, wenn Sie sie für die Sort-Methoden brauchen.
		// grab the largest value from the root
		int maxValue = array[0];

		// move the last item in the heap into the root position
		array[0] = array[array.length - 1];

		// and bubble down from the root to restore the heap
		makeHeap(array, array.length - 1, 0);
	}
	
	/**
	 * Berechnet den Index des linken Kindelementes in einem Heap.
	 * @param parentIndex
	 * @param offset Offset für Heap-Eigenschaft: entspricht 
	 *         dem Index der Heapwurzel - 1
	 * @return Index des linken Kindes
	 */
	static int leftChild(int parentIndex, int offset)
	{
		return 2 * parentIndex - offset;
	}

	/**
	 * Berechnet den Index des rechten Kindelementes in einem Heap.
	 * @param parentIndex
	 * @param offset Offset für Heap-Eigenschaft: entspricht 
	 *         dem Index der Heapwurzel - 1
	 * @return Index des rechten Kindes
	 */
	static int rightChild(int parentIndex, int offset)
	{
		return leftChild(parentIndex, offset) + 1;
	}


}
