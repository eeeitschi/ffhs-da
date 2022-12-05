package ch.ffhs.dua.hash;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Teil-Implementierung einer HashSet Klasse.
 * Kollisionen sollen mit linearer Sondierung behandelt werden.
 * @param <E>
 */
public class LSHashSet<E> extends SetBasic<E>
{
    private int size, maxSize;
    private Object[] keys;
    private Object[] vals;

    public LSHashSet(int maxSize) {
        size = 0;
        this.maxSize = maxSize;
        keys = new Object[maxSize];
        vals = new Object[maxSize];
    }

    private int hash(Object key) {
        return key.hashCode() % maxSize;
    }


    @Override
    public boolean contains(Object obj)
    {
        int i = hash(obj);
        while (keys[i] != null) {
            if (keys[i].equals(obj))
                return true;
            i = (i + 1) % maxSize;
        }
        return false;
    }

    @Override
    public boolean add(Object e)
    {
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

            i = (i + 1) % maxSize;

        }
        // solange i nicht temp entspricht
        while (i != tmp);
        return false;
    }

    @Override
    public boolean remove(Object o)
    {
        if (!contains(o))
            return false;

        // Position suchen und löschen
        int i = hash(o);
        while (!o.equals(keys[i]))
            i = (i + 1) % maxSize;
        keys[i] = vals[i] = null;

        // Alle schlüssel neu hashen
        for (i = (i + 1) % maxSize; keys[i] != null;
             i = (i + 1) % maxSize) {
            Object tmp1 = keys[i];
            keys[i] = vals[i] = null;
            size--;
            add(tmp1);
        }
        size--;
        return true;
    }

    @Override
    public Iterator<E> iterator()
    {
        // TODO
        return null;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public void clear()
    {
        keys = null;
        vals = null;
        size = 0;
    }

}
