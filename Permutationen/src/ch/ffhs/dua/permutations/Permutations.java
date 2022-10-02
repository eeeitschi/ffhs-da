package ch.ffhs.dua.permutations;


import java.util.Arrays;


public class Permutations {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(permutations(3)));
    }

    /**
     * Erzeugt ein Array von allen Permutationen von {0,1,2,3,...,n-1}.
     *
     * @param n Anzahl Elemente in einer Permutation.
     * @return Ein Array von Permutationen; jede Zeile ist eine Permutation von {0,1,2,3,...,n-1}.
     * Permutation ist ein Array von Integern.
     */
    public static int[][] permutations(int n) {
        if (n == 1) {
            int[][] a = {{1}, {1}};
            return a;
        }
        int[] data = createArray(n);
    permutation(data, data.length);
        return new int[][]{data};
    }

    /**
     * Erzeugt ein zweidimensionales Array mit der dimension [n][n] und füllt alle Werte
     *
     * @param n
     * @return
     */
    private static int[] createArray(int n) {
        if (n == 1) {
            int[] perm = new int[2];
            perm[0] = 1;
            perm[1] = 1;
            return perm;
        }
        int[] permutationArray = new int[n];
        for (int i = 0; i < n; i++) {
            permutationArray[i] = i;
        }
        return permutationArray;
    }

    private static void permutation(int[] data, int size) {
        if (size == 1) {
            System.out.println(Arrays.toString(data));
        }

        for (int i = 0; i < size; i++) {
            permutation(data, size - 1);

            // if size is odd, swap 0th i.e (first) and (size-1)th i.e (last) element
            if (size % 2 == 1) {
                int temp = data[0];
                data[0] = data[size - 1];
                data[size - 1] = temp;
            }

            // If size is even, swap ith and (size-1)th i.e last element
            else {
                int temp = data[i];
                data[i] = data[size - 1];
                data[size - 1] = temp;
            }
        }
    }
}