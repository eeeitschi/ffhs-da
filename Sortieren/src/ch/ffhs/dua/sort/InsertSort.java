package ch.ffhs.dua.sort;

public class InsertSort 
{
	/**
	 * Sortiert ein Array durch Einfügen
	 * @param array Das zu sortierende Array.
	 */
	public static void sort(int[] array)
	{
		int size = array.length;

		for (int step = 1; step < size; step++) {
			int key = array[step];
			int j = step - 1;

			// Vergleicht den Schlüssel mit jedem Element links bis ein kleineres Element gefunden wird.
			while (j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				--j;
			}

			// Platziert den Schlüssel nach dem Element das kleiner ist.
			array[j + 1] = key;
		}
	}
	
	/**
	 * Sortiert einen durch zwei Grenzen angebenen Teil eines Arrays durch Einfügen.
	 * Arrayelemente ausserhalb der Grenzen werden nicht verschoben.
	 * @param array 
	 * @param start Index des ersten  Elementes des Teils, das Sortiert werden muss.
	 * @param end   Index des letzten Elementes des Teils, das sortiert werden muss.
	 */
	public static void sort(int[] array, int start, int end)
	{
		// Prüft ob der end-Index innerhalb der Array-Länge ist.
		if(array.length < end) return;

		for (int step = start; step < end; step++) {
			int key = array[step];
			int j = step - 1;

			// Vergleicht den Schlüssel mit jedem Element links bis ein kleineres Element gefunden wird.
			while (j >= 0 && key < array[j]) {
				array[j + 1] = array[j];
				--j;
			}

			// Platziert den Schlüssel nach dem Element das kleiner ist.
			array[j + 1] = key;
		}
	}

}
