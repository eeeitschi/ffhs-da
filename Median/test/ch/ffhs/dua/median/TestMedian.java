package ch.ffhs.dua.median;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class TestMedian 
{
	@Test
	public void test_k_tesElement()
	{
		int[] array = new int[] {3, 4, 2, 3, 2, 0};
		int[] clone = array.clone();
		int[] sortA = array.clone();
		Arrays.sort(sortA);
		for (int n = 0; n < array.length; n++)
		{
			assertEquals(sortA[n], Median.n_tesElement(array, n));
			assertArrayEquals(clone, array);
		}
	}
}
