package ch.ffhs.dua.list;

import java.util.Iterator;
import java.util.List;

/**
 * @param <E>
 * @TODO / Verbesserungsvorschlag: Klassenname auf DoubleLinkedList anpassen.
 * Eine geordnete Liste mit n Elementen. Der Benutzer hat die Kontrolle über die einzelnen
 * Inhalte der Liste. In der Liste können die
 */
public class LinkedList<E> extends ListBasic<E> implements List<E> {

    /**
     * Listenelement als Start.
     */
    Node<E> init;

    /**
     * Konstruktor, definiert bei der Objekterzeugung zwei leere Listenelemente.
     * Ein Startelement (init) und ein Listenende (last).
     */
    public LinkedList() {
        init = new Node<E>();

        // Setzt die Abhängigkeiten prev und next für die ersten beiden Listenelemente.
        init.next = init;
        init.prev = init;
    }

    @Override
    public int size() {
        int counter = 0;
        if (init.prev == init) return counter;

        Node tempNode = init;
        while (tempNode.next != init) {
            counter++;
            tempNode = tempNode.next;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return (init.next == init);
    }

    @Override
    public E get(int index) {
        if (isEmpty()) return null;
        if (index > size()) return null;

        Node nAi = init;
        for (int i = 0; i <= index; i++) {
            nAi = nAi.next;
        }
        // TODO
        return (E) nAi.element;
    }


    @Override
    public E set(int index, E element) {
        if (index > size()) return null;

        Node nodeCache = init;

        for (int i = 0; i <= index; i++) {
            nodeCache = nodeCache.next;

        }
        E temporaryElement = (E) nodeCache.element;
        nodeCache.element = element;

        return temporaryElement;
    }


    @Override
    public boolean add(E element) {
        Node newNode = new Node<E>();
        newNode.element = element;

        newNode.next = init;
        newNode.prev = init.prev;
        init.prev.next = newNode;
        init.prev = newNode;

        // TODO
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > size()) return;
        Node nodeCache = init;

        for (int i = 0; i <= index; i++) {
            nodeCache = nodeCache.next;
        }

        Node newNode = new Node();
        newNode.element = element;


        newNode.next = nodeCache;
        newNode.prev = nodeCache.prev;
        nodeCache.prev.next = newNode;
        nodeCache.prev = newNode;
    }


    public boolean contains(Object o) {
        if (isEmpty()) return false;

        Node temp = init.next;
        boolean ret = false;

        do {
            System.out.println(temp.element);
            if (o == temp.element) {
                ret = true;
                break;
            }
            ;
            temp = temp.next;
        } while (temp != init);
        return ret;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()) return null;

        Node nodeCache = init;
        for (int i = 0; i <= index; i++) {
            nodeCache = nodeCache.next;
        }

        E temp = (E) nodeCache.element;
        nodeCache.prev.next = nodeCache.next;
        nodeCache.next.prev = nodeCache.prev;

        return temp;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) return false;

        return false;
    }

    @Override
    public void clear() {
        init = null;
        //TODO
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    // Die anderen Methoden des List-Interfaces müssen nicht implementiert werden.

///////////////////////////////////////////////////


    /**
     * Einzelnes Element (Node) einer doppelt Verketteten Liste. Als Besonderheit dieses Listentyps,
     * hat das einzelne Element jeweils eine Referenz zum vorhergehend und nachherigen Listen Element.
     *
     * @param <E>: element: Datentyp die im Node gespeichert werden.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node currentNode = init;
        private Node lastNode = init.prev;

        @Override
        public boolean hasNext() {
            return currentNode != lastNode;
        }

        @Override
        public E next() {
            E currentElement = init.next.element;
            init.next = init.next.next;
            return currentElement;
        }

        @Override
        public void remove() {
            Node prevNode = init.prev;
            Node nextNode = init.next;
            prevNode.next = init.next.prev;
            nextNode.prev = init.next.next;

        }
    }


}
