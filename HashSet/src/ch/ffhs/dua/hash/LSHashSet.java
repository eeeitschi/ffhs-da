package ch.ffhs.dua.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Teil-Implementierung einer HashSet Klasse.
 * Kollisionen sollen mit linearer Sondierung behandelt werden.
 * @param <E>
 */
public class LSHashSet<E> extends SetBasic<E> {
    private int size = 0;
    private Object[] keys = new Object[50];
    private Object[] vals = new Object[50];

    /**
     * Gibt einen Hashcode für den übermittelten Key zurück
     * @param key
     * @return HashCode
     */
    private int hash(Object key) {
        int index = key.hashCode();
        if (index < 0) {
            index = -index;
        }
        return index % keys.length;
    }

    /**
     * Prüft ob ein entsprechendes Element im HashSet vorhanden ist.
     * @param obj Element das auf vorhandensein überprüft werden soll.
     * @return true wenn das Element vorhanen ist.
     */
    @Override
    public boolean contains(Object obj) {
        int i = hash(obj);
        while (keys[i] != null) {
            if (keys[i].equals(obj))
                return true;
            i = (i + 1) % keys.length;
        }
        return false;
    }

    /**
     * Ergänzt einen eintrag im HashSet
     * @param e das Element welches in die Sammlung eingesetzt werden soll
     * @return true wenn das einfügen erfolgreich vollzogen wurde.
     */
    @Override
    public boolean add(Object e) {
        int tmp = hash(e);
        int i = tmp;

        // vergleiche durchführen
        do {
            if (keys[i] == null) {
                keys[i] = e;
                vals[i] = e;
                size++;
                return true;
            }

            if (keys[i].equals(e)) {
                vals[i] = e;
                return true;
            }

            i = (i + 1) % keys.length;
        }
        // solange i nicht temp entspricht
        while (i != tmp);
        return false;
    }

    /**
     * Entfernt ein Objekt aus dem HashSet wenn es vorhanden ist.
     * @param o das Objekt das entfernt werden soll.
     * @return true wenn das entfernen erfolgreich durchgeführt werden konnte.
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;

        // Position suchen und löschen
        int i = hash(o);
        while (!o.equals(keys[i]))
            i = (i + 1) % keys.length;
        keys[i] = vals[i] = null;

        // Alle schlüssel neu hashen
        for (i = (i + 1) % keys.length; keys[i] != null;
             i = (i + 1) % keys.length) {
            Object tmp1 = keys[i];
            keys[i] = vals[i] = null;
            size--;
            add(tmp1);
        }
        size--;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new LSHashSetIterator();
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
        keys = null;
        vals = null;
        size = 0;
    }

    class LSHashSetIterator implements Iterator {
        private int currentKeyIndex;

        public LSHashSetIterator() {
            currentKeyIndex = 0;
        }

        @Override
        public boolean hasNext() {
            // currentEntry node hat weiteren Eintrag
            if (currentKeyIndex < size) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            currentKeyIndex++;
            return vals[currentKeyIndex];
        }
    }
}