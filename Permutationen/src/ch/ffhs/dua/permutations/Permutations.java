package ch.ffhs.dua.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    /**
     * Erzeugt ein Array von allen Permutationen von {0,1,2,3,...,n-1}.
     * @param n Anzahl Elemente in einer Permutation.
     * @return Ein Array von Permutationen; jede Zeile ist eine Permutation von {0,1,2,3,...,n-1}.
     * Permutation ist ein Array von Integern.
     */
    public static int[][] permutations(int n) {
        // Prüft die Anzahl, wenn 0 wird direkt ein Leeres Array mit 0 zurückgegeben.
        if (n == 0) return new int[0][0];

        // Erzeugt ein Array mit der dimension [n] und füllt jedes Element mit dem eigenen Index ab.
        int[] initialData = new int[n];
        for (int i = 0; i < initialData.length; i++) {
            initialData[i] = i;
        }

        List<List<Integer>> list = new ArrayList<>();
        permutationBuilder(list, new ArrayList<>(), initialData);
        return convert2DListTo2DArray(list);
    }

    /**
     * Erstellt eine Permutation im Rekusiven verfahren und gibt die Lösung zurück.
     * @param finalResults
     * @param progressingResults
     * @param providedArr
     */
    private static void permutationBuilder(List<List<Integer>> finalResults, List<Integer> progressingResults, int[] providedArr) {
        // Wenn alle Elemente untersucht worden sind, die Lösung der finalen Liste hinzufügen.
        if (progressingResults.size() == providedArr.length) {
            finalResults.add(new ArrayList<>(progressingResults));
        } else {
            for (int i = 0; i < providedArr.length; i++) {
                // überspringe das Element wenn es bereits in der Liste enthalten ist.
                if (progressingResults.contains(providedArr[i])) continue;
                // Element auswählen und den Resultaten hinzufügen.
                progressingResults.add(providedArr[i]);
                // Neues Element in der Rekusion untersuchen.
                permutationBuilder(finalResults, progressingResults, providedArr);
                // Element aus den Resultaten entfernen.
                progressingResults.remove(progressingResults.size() - 1);
            }
        }
    }

    /**
     * Konvertiert eine verschachtelte Liste in ein zweidimensionales Array von int's.
     * @param list die Liste von Integern die in ein Array umgewandelt werden soll.
     * @return Retourniert das 2D-Array mit den Werten aus der verschachtelten Liste.
     */
    private static int[][] convert2DListTo2DArray(List<List<Integer>> list) {
        return list.stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
    }
}