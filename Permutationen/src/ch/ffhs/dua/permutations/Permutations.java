package ch.ffhs.dua.permutations;


import java.util.Arrays;


public class Permutations
{

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(permutations(3)));
	}

	/**
	 * Erzeugt ein Array von allen Permutationen von {0,1,2,3,...,n-1}.
	 * @param n Anzahl Elemente in einer Permutation.
	 * @return  Ein Array von Permutationen; jede Zeile ist eine Permutation von {0,1,2,3,...,n-1}.
	 * Permutation ist ein Array von Integern.
	 */
	public static int[][] permutations(int n)
	{
		if (n == 1) {
			int [][] perm = new int[2][1];
			perm[0][0] = 1;
			perm[1][0] = 1;
			return perm;
		}
		int[][] permutationArray = new int[n][n];

		for (int i = 0; i < n; i++) {
			permutations(n - 1);

			if (n % 2 == 1 ){
				int temp = permutationArray[n][0];
				permutationArray[n][0] = permutationArray[n][n-1];
				permutationArray[n][n -1 ] = temp;
			}

			else {
				int temp = permutationArray[n][n];
				permutationArray[n][i] = permutationArray[n][n -1];
				permutationArray[n][n-1] = temp;
			}
		}
		// TODO
		return permutationArray;
	}
	

}
