package ch.ffhs.dua.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Teil-Implementierung einer HashSet Klasse.
 * Kollisionen sollen mit geketten Listen behandelt werden.
 * @param <E>
 */
public class LLHashSet<E> extends SetBasic<E> {
    private int size = 0;
    private CollisionEntry[] collisionBucket = new CollisionEntry[16];

    /**
     * Klasse für einen Kollisionseintrag.
     * Speichert das entsprechende Objekt und eine Referenz
     * auf das nächste Element im Container (sofern vorhanden).
     */
    private static class CollisionEntry {
        Object key;
        CollisionEntry next;
    }

    /**
     * Gibt einen neuen Hashcode zurück
     * @param hashCode
     * @return
     */
    private int hashFunction(int hashCode) {
        int index = hashCode;
        if (index < 0) {
            index = -index;
        }
        return index % collisionBucket.length;
    }

    /**
     * Prüft ob ein entsprechendes Element im HashSet vorhanden ist.
     * @param o Element das auf vorhandensein überprüft werden soll.
     * @return true wenn das Element vorhanen ist.
     */
    @Override
    public boolean contains(Object o) {
        int index = hashFunction(o.hashCode());
        CollisionEntry current = collisionBucket[index];

        while (current != null) {
            // Prüft ob die Node das Element enthält
            if (current.key.equals(o)) {
                return true;
            }
            // ansonsten wird das nächste Node im Bucket besucht
            current = current.next;
        }
        // Kein Element gefunden
        return false;
    }

    /**
     * Ergänzt einen eintrag im HashSet
     * @param e das Element welches in die Sammlung eingesetzt werden soll
     * @return true wenn das einfügen erfolgreich vollzogen wurde.
     */
    @Override
    public boolean add(E e) {
        int index = hashFunction(e.hashCode());
        CollisionEntry current = collisionBucket[index];

        while (current != null) {
            // Element bereits im set vorhanden
            if (current.key.equals(e)) {
                return false;
            }
            // Ansonsten das nächstes Element im Container auswählen
            current = current.next;
        }
        // Kein Element gefunden, neuen eintrag ergänzen
        CollisionEntry entry = new CollisionEntry();
        entry.key = e;
        // Aktueller Eintrag ist null, wenn der container leer ist
        entry.next = collisionBucket[index];
        collisionBucket[index] = entry;
        size++;
        return true;
    }

    /**
     * Entfernt ein Objekt aus dem HashSet wenn es vorhanden ist.
     * @param o das Objekt das entfernt werden soll.
     * @return true wenn das entfernen erfolgreich durchgeführt werden konnte.
     */
    @Override
    public boolean remove(Object o) {
        int index = hashFunction(o.hashCode());
        CollisionEntry current = collisionBucket[index];
        CollisionEntry previous = null;

        while (current != null) {
            // Element gefunden -> Entfernen
            if (current.key.equals(o)) {

                if (previous == null) {
                    collisionBucket[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        // Passendes Element nicht gefunden
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LLHashSetIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        collisionBucket = null;
        size = 0;
    }

    class LLHashSetIterator implements Iterator {
        private int currentBucket;
        private int previousBucket;
        private CollisionEntry currentEntry;
        private CollisionEntry previousEntry;

        public LLHashSetIterator() {
            currentEntry = null;
            previousEntry = null;
            currentBucket = -1;
            previousBucket = -1;
        }

        @Override
        public boolean hasNext() {
            // currentEntry node hat weiteren Eintrag
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }

            // Weitere Nodes bearbeiten
            for (int index = currentBucket + 1; index < collisionBucket.length; index++) {
                if (collisionBucket[index] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Object next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;

            if (currentEntry == null || currentEntry.next == null) {
                currentBucket++;
                while (currentBucket < collisionBucket.length &&
                        collisionBucket[currentBucket] == null) {
                    currentBucket++;
                }
                if (currentBucket < collisionBucket.length) {
                    currentEntry = collisionBucket[currentBucket];
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                currentEntry = currentEntry.next;
            }
            return currentEntry.key;
        }
    }
}
