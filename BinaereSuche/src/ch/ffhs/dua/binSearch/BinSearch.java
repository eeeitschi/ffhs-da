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

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        search(arr, 0);

        // Result: with 0 --> 0, 4
        // Result: with 1 --> 5, 7
        // Result: with 2 --> 8, 13
    }

    public static Pair search(int[] array, int value) {
/*        int lower = -1;
        int higher = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                if (lower < 0) lower = i;
                else higher = i;
            }
        }

        if (lower >= 0) {
            if (higher >= 0) return new Pair(lower, higher);
            else return new Pair(lower, lower);
        }*/

        rank(array, 0, array.length, value);
        return null;
    }

    public static int rank(int[] array, int low, int high, int value) {
        int mid = low + (high - low) / 2;
        int temp1 = -1;
        int temp2 = -1;

        if (low < mid) {
            temp1 = rank(array, low, mid, value);
        } else {
            if (array[low] == value) return low;
            else return -1;
        }

        if (mid < high) {
            temp2 = rank( array, mid, high, value);
        } else {
            if (array[mid] == value) return low;
            else return -1;
        }

        System.out.println("temp1: " + temp1 + " temp2: " + temp2);
        return -1;

    }
}
