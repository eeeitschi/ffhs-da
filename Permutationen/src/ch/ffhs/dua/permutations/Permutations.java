package ch.ffhs.dua.permutations;

import java.util.ArrayList;
import java.util.List;


public class Permutations {

    /**
     * Erzeugt ein Array von allen Permutationen von {0,1,2,3,...,n-1}.
     *
     * @param n Anzahl Elemente in einer Permutation.
     * @return Ein Array von Permutationen; jede Zeile ist eine Permutation von {0,1,2,3,...,n-1}.
     * Permutation ist ein Array von Integern.
     */
    public static int[][] permutations(int n) {
        int[] arr = createArray(n);

        int[][] result;
        // auf ein leeres Array prüfen
        if (n > 0) {
            List<List<Integer>> list = new ArrayList<>();
            permuteHelper(list, new ArrayList<>(), arr);
            result = new int[list.size()][n];
            matricize(list, result);
        } else {
            result = new int[0][0];
        }
        return result;

    }

    /**
     * Erzeugt ein zweidimensionales Array mit der dimension [n][n] und füllt alle Werte
     *
     * @param n
     * @return
     */
    private static int[] createArray(int n) {
        int[] newArray = new int[n];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = i;
        }
        return newArray;
    }

    private static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int[] arr) {
        if (resultList.size() == arr.length) {
            list.add(new ArrayList<>(resultList));
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (resultList.contains(arr[i])) {
                    // Wenn das element bereits in der Liste ist, wird es übersprungen
                    continue;
                }
                //element wählen
                resultList.add(arr[i]);
                //untersuchen
                permuteHelper(list, resultList, arr);
                // element herausnehmen
                resultList.remove(resultList.size() - 1);
            }
        }
    }

    private static void matricize(List<List<Integer>> list, int[][] matrix) {
        for (int row = 0; row < list.size(); row++) {
            ArrayList rowlist = (ArrayList) list.get(row);
            for (int col = 0; col < rowlist.size(); col++) {
                matrix[row][col] = (int) rowlist.get(col);
            }
        }
    }
}