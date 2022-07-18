package ch.ffhs.dua.median;

public class Median 
{
	private Median()
	{}
	
	/**
	 * Bestimmt auf effiziente Art das der Größe nach n-te Element,
	 * ohne das Array zu sortieren.
	 * el = n_tesElement(array, n) liefert also das selbe wie
	 * <pre>
	 *     Arrays.sort(array);
	 *     el = array[n];
	 * </pre>
	 * @param array
	 * @param n
	 * @return Das der Größe nach n-te Element des Arrays
	 */
	public static int n_tesElement(int[] array, int n)
	{
		// TODO
		return -1;
	}
	
	/**
	 * Bestimmt den Median eines Arrays.
	 * (Besitzt das Array eine gerade Anzahl Elemente, 
	 * wird das größere der beiden mittleren Elemente zurückgegeben,
	 * nicht der Mittelwert dieser beiden mittleren Element.)
	 * @param array
	 * @return
	 */
	public static int median(int[] array)
	{
		return n_tesElement(array, array.length/2);
	}
}
