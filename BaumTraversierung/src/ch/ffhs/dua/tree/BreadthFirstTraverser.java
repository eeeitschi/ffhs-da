package ch.ffhs.dua.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Traverser-Klasse für Breitensuche.
 * Ein Traverser mit Breitensuche besucht zuerst die Wurzel, 
 * dann die Kinder der Wurzel, dann die Enkel usw.
 *
 * @param <N>
 */
public abstract class BreadthFirstTraverser<N> 
{
	/**
	 * Methode zum Traversieren eines Baumes.
	 * @param node Wurzelknoten des Baumes.
	 */
	public void traverse(TreeNode<N> node) 
	{
		// Linkedlist als Queue
		LinkedList<TreeNode<N>> queue = new LinkedList<>();
		queue.addLast(node);

		// Liste die alle besuchten Knoten speichert.
		ArrayList<N> preOrderList = new ArrayList<>();

		// Durchlaufe den Stack solange noch weitere Elemente vorhanden
		while (!queue.isEmpty()) {
			TreeNode<N> temp = queue.getFirst();
			queue.removeFirst();

			// Speichere den Wert als besuchter Knoten
			preOrderList.add(temp.value());
			// Füge alle ChildNodes der Queue hinzu.
			for (int i = 0; i <= temp.children().size() - 1; i++) {
				queue.addLast(temp.children().get(i));
			}
		}
		// Besuche jedes einzelne ChildNode.
		for (N i : preOrderList) {
			visitNode(i);
		}
	}
	
	/**
	 * Diese Methode gibt an, was beim travsersieren gemacht werden sollte.
	 * @param value
	 */
	protected abstract void visitNode(N value);   
    
}   