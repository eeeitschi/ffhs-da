package ch.ffhs.dua.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Ein Iterator, der in Depth-First Reihenfolge alle Werte
 * der Knoten eines Baumes ausgibt.
 *
 * @param <N> Typ des Knotenwertes.
 */
public class TreeIterator<N> implements Iterator<N> {
    private int nextIndex = 0;
    ArrayList<N> orderedList = new ArrayList<>();

    /**
     * Erzeugt einen neuen neuen Baum-Knoten-Iterator
     *
     * @param node Die Wurzel des zu traversierenden Baumes.
     */
    public TreeIterator(TreeNode<N> node) {
        Stack<TreeNode<N>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            TreeNode<N> temp = stack.peek();
            stack.pop();

            // Speichere den Wert als besuchter Knoten
            orderedList.add(temp.value());
            // Füge alle ChildNodes auf den Stack (von links nach rechts).
            for (int i = temp.children().size() - 1; i >= 0; i--) {
                stack.push(temp.children().get(i));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextIndex <= orderedList.size() - 1;
    }

    @Override
    public N next() {
        return orderedList.get(nextIndex++);
    }

    // remove() muss nicht implementiert werden.
}
