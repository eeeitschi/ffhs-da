package ch.ffhs.dua.list;

import java.util.Iterator;
import java.util.List;

/**
 * Implementierung der List-Schnittstelle in form einer doppelt verketteten (zirkulären) Liste.
 * Implementiert nicht alle optionalen Listenoperationen aber erlaubt alle Elemente (einschlisslich "null").
 * Alle Operationen laufen so ab, wie man es von einer doppelt verketteten Liste erwarten kann.
 * Operationen, die in die Liste indexieren, durchlaufen die Liste vom Anfang.
 * @param <E> - die Art der in dieser Sammlung enthaltenen Elemente
 */
public class LinkedList<E> extends ListBasic<E> implements List<E> {

    /**
     * Pseudo-Knoten als Listenreferenz, welcher auf den ersten sowie letzten Knoten zeigt.
     */
    private Node<E> init;

    private int size;


    /**
     * Konstruiert eine leere Liste.
     */
    public LinkedList() {
        // Instanziiert den "Pseudo-Knoten" und setzt die zirkuläre Referenz auf sich selbst.
        init = new Node<>();
        init.next = init;
        init.prev = init;
    }

    /**
     * Gibt den Knoten (Node) an der vom Index angegebenen Stelle zurück.
     * @param index an der Stelle die geuscht wird.
     * @return den Knoten (Node) an dem entsprechenden Index
     * @throws IndexOutOfBoundsException wenn der Index ausserhalb der Listenlänge liegt
     */
    private Node<E> getNodeAtIndex(int index) {
        checkIfIndexIsInBounds(index);
        Node<E> tempNodeCache = init;
        for (int i = 0; i <= index; i++) {
            tempNodeCache = tempNodeCache.next;
        }
        return tempNodeCache;
    }

    /**
     * Gibt true zurück wenn der Index einen gültigen Wert aufweist.
     * @param index der auf gültigkeit überprüft werden soll
     * @return {@code true} wenn der Index gültig ist
     * @throws IndexOutOfBoundsException if index is not between 0 and collection-size - 1
     */
    private boolean checkIfIndexIsInBounds(int index) {
        if (index >= 0 && index < size) return true;
        throw new IndexOutOfBoundsException();
    }

    /**
     * Gibt die Anzahl der Elemente in dieser Liste zurück.
     * @return die Anzahl der Elemente in dieser Liste
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Gibt an ob die Liste leer ist.
     * @return ob die Liste leer ist
     */
    @Override
    public boolean isEmpty() {
        return (init.next == init);
    }

    /**
     * Gibt das Element an der angegebenen Position in dieser Liste zurück.
     * @param index Index des zurückzugebenden Elements
     * @return das Element an der angegebenen Position in dieser Liste
     * @throws IndexOutOfBoundsException wenn der Index ausserhalb der Listenlänge liegt
     */
    @Override
    public E get(int index) {
        return getNodeAtIndex(index).element;
    }

    /**
     * Ersetzt das Element an der angegebenen Position in dieser Liste durch das angegebene Element.
     * @param index   Index des zu ersetzenden Elements
     * @param element das Element das neu an der angegebenen Position eingefügt werden soll
     * @return das Element an der angegebenen Position das ersetzt wurde
     * @throws IndexOutOfBoundsException wenn der Index ausserhalb der Listenlänge liegt
     */
    @Override
    public E set(int index, E element) {
        checkIfIndexIsInBounds(index);
        Node<E> nodeToChange = getNodeAtIndex(index);
        E oldElement = nodeToChange.element;
        nodeToChange.element = element;
        return oldElement;
    }

    /**
     * Hängt das angegebene Element an das Ende der Liste an.
     * @param element Element, das an diese Liste angehängt wird
     * @return {@code true}
     */
    @Override
    public boolean add(E element) {
        Node newNode = new Node<E>();
        newNode.element = element;

        newNode.next = init;
        newNode.prev = init.prev;
        init.prev.next = newNode;
        init.prev = newNode;
        size++;
        return true;
    }

    /**
     * Fügt das angegebene Element an der angegebenen Position in diese Liste ein.
     * @param index   Index, an dem das Element eingefügt werden soll
     * @param element das Element das der Liste hinzugefügt werden soll
     * @throws IndexOutOfBoundsException wenn der Index ausserhalb der Listenlänge liegt
     */
    @Override
    public void add(int index, E element) {
        checkIfIndexIsInBounds(index);
        Node shiftingNode = getNodeAtIndex(index);
        Node newNode = new Node();
        newNode.element = element;
        newNode.next = shiftingNode;
        newNode.prev = shiftingNode.prev;
        shiftingNode.prev.next = newNode;
        shiftingNode.prev = newNode;
        size++;
    }

    /**
     * Gibt true zurück, wenn diese Liste das angegebene Element enthält.
     * @param o Element, dessen Vorhandensein in dieser Liste geprüft werden soll
     * @return {@code true} wenn diese Liste das angegebene Element enthält
     */
    public boolean contains(Object o) {
        if (isEmpty()) return false;

        Node tempNode = init.next;
        boolean ret = false;

        do {
            if (o == tempNode.element) {
                ret = true;
                break;
            }
            tempNode = tempNode.next;
        } while (tempNode != init);
        return ret;
    }

    /**
     * Entfernt das  Element an dem angegebenen Index aus dieser Liste, sofern dieser vorhanden ist.
     * @param index der Index des zu entfernenden Elements
     * @return das Element welches an dem Index gespeichert war
     * @throws IndexOutOfBoundsException wenn der Index ausserhalb der Listengrösse liegt
     */

    @Override
    public E remove(int index) {
        checkIfIndexIsInBounds(index);
        Node<E> nodeToRemove = getNodeAtIndex(index);
        E removedElement = nodeToRemove.element;
        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;
        size--;
        return removedElement;
    }

    /**
     * Entfernt das erste Vorkommen des angegebenen Elements aus dieser Liste, sofern es vorhanden ist.
     * @param o aus dieser Liste zu entfernendes Element, falls vorhanden
     * @return {@code true} wenn das Element erfolgreich entfernt werden konnte
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        Node<E> nodeToDelete = init;
        if (o == null) {
            for (int i = 0; i < size(); i++) {
                if (nodeToDelete.element == null) {
                    remove(i);
                    return true;
                }
                nodeToDelete = nodeToDelete.next;
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (o.equals(nodeToDelete.element)) {
                    remove(i);
                    return true;
                }
                nodeToDelete = nodeToDelete.next;
            }
        }
        return false;
    }

    /**
     * Entfernt alle Elemente aus der Liste
     */
    @Override
    public void clear() {
        Node<E> nodeToClear = init;
        for (int i = 0; i++ < size(); i++) {
            nodeToClear.prev = null;
            nodeToClear.element = null;
            Node<E> temp = nodeToClear;
            nodeToClear = nodeToClear.next;
            temp.next = null;
        }
        size = 0;
        init = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    // Die anderen Methoden des List-Interfaces müssen nicht implementiert werden.

///////////////////////////////////////////////////


    /**
     * Einzelner Knoten (Node) für eine doppelt Verkettete Liste.
     * @param <E>: Datentyp die im Node gespeichert werden.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;
    }

    private class LinkedListIterator implements Iterator<E> {

        // Knoten-Referenz die bei dem ersten Element (init) beginnt
        // und immer auf den aktuellen Iterator Stand zeigt.
        private Node currentNode = init;

        // Knoten-Referenz die immer auf das letzte Element in der Sammlung zeigt.
        private Node lastNode = init.prev;

        /**
         * Gibt true zurück, wenn die Iteration weitere Elemente gespeichert hat.
         * @return {@code true} wenn die Iteration weitere Elemente gespeichert hat
         */
        @Override
        public boolean hasNext() {
            return currentNode != lastNode;
        }

        /**
         * Gibt das nächste Element in der Iteration zurück.
         * @return das nächste Element in der Iteration
         */
        @Override
        public E next() {
            E currentElement = (E) currentNode.next.element;
            currentNode = currentNode.next;
            return currentElement;
        }

        /**
         * Entfernt das letzte von diesem Iterator zurückgegebene Element aus der zugrunde liegenden Sammlung.
         */
        @Override
        public void remove() {
            Node prevNode = currentNode.prev;
            Node nextNode = currentNode.next;
            prevNode.next = currentNode.next;
            nextNode.prev = currentNode.prev;
            size--;
        }
    }
}