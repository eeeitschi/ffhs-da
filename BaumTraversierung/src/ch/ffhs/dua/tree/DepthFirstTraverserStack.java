package ch.ffhs.dua.tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Klasse zum Traversieren eines Baumes mit Tiefensuche.
 * Diese Implementierung verwende keine Rekursion, sondern einen Stack.
 *
 * @param <N>
 */
public abstract class DepthFirstTraverserStack<N> {
    /**
     * Traversiert einen Baum mit Tiefensuche.
     *
     * @param Die Wurzel des zu traversierenden Baumes.
     */
    public void traverse(TreeNode<N> root) {
        Stack<TreeNode<N>> stack = new Stack<>();
        stack.push(root);

        // Liste die alle besuchten Knoten speichert.
        ArrayList<N> preOrderList = new ArrayList<>();

        // Durchlaufe den Stack solange noch weitere Elemente vorhanden
        while (!stack.isEmpty()) {
            TreeNode<N> temp = stack.peek();
            stack.pop();

            // Speichere den Wert als besuchter Knoten
            preOrderList.add(temp.value());
            // Füge alle ChildNodes auf den Stack (von links nach rechts).
            for (int i = temp.children().size() - 1; i >= 0; i--) {
                stack.push(temp.children().get(i));
            }
        }
        // Besuche jedes einzelne ChildNode.
        for (N i : preOrderList) {
            visitNode(i);
        }
    }

    /**
     * Operation auf einem Knoten bei der Traversierung;
     * die Operation wird durchgeführt,
     * bevor die Nachkommen besucht werden.
     *
     * @param value
     */
    abstract protected void visitNode(N value);

}   