package ch.ffhs.dua.hash;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Teil-Implementierung einer HashSet Klasse.
 * Kollisionen sollen mit geketten Listen behandelt werden.
 *
 * @param <E>
 */
public class LLHashSet<E> extends SetBasic<E> {

    private int size = 0;

    private CollisionEntry[] collisionBucket;


    private static class CollisionEntry {
        Object key;
        CollisionEntry next;
    }

    private int hashFunction(int hashCode) {
        int index = hashCode;
        if (index < 0) {
            index = -index;
        }
        return index % collisionBucket.length;
    }


    @Override
    public boolean contains(Object o) {
        int index = hashFunction(o.hashCode());
        CollisionEntry current = collisionBucket[index];

        while (current != null) {
            // check if node contains element
            if (current.key.equals(o)) { return true; }
            // otherwise visit next node in the bucket
            current = current.next;
        }
        // no element found
        return false;
    }

    @Override
    public boolean add(E e) {
        int index = hashFunction(e.hashCode());
        CollisionEntry current = collisionBucket[index];

        while (current != null) {
            // element is already in set
            if (current.key.equals(e)) { return false; }
            // otherwise visit next entry in the bucket
            current = current.next;
        }
        // no element found so add new entry
        CollisionEntry entry = new CollisionEntry();
        entry.key = e;
        // current Entry is null if bucket is empty
        // if it is not null it becomes next Entry
        entry.next  = collisionBucket[index];
        collisionBucket[index] = entry;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = hashFunction(o.hashCode());
        CollisionEntry current = collisionBucket[index];
        CollisionEntry previous = null;

        while (current != null) {
            // element found so remove it
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
        // no element found nothing to remove
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
            // currentEntry node has next
            if (currentEntry != null && currentEntry.next != null) { return true; }

            // there are still nodes
            for (int index = currentBucket+1; index < collisionBucket.length; index++) {
                if (collisionBucket[index] != null) { return true; }
            }
            // nothing left
            return false;
        }

        @Override
        public Object next() {
            previousEntry = currentEntry;
            previousBucket = currentBucket;

            // if either the current or next node are null
            if (currentEntry == null || currentEntry.next == null) {
                // go to next bucket
                currentBucket++;
                // keep going until you find a bucket with a node
                while (currentBucket < collisionBucket.length &&
                        collisionBucket[currentBucket] == null) {
                    // go to next bucket
                    currentBucket++;
                }
                // if bucket array index still in bounds
                // make it the current node
                if (currentBucket < collisionBucket.length) {
                    currentEntry = collisionBucket[currentBucket];
                }
                // otherwise there are no more elements
                else {
                    throw new NoSuchElementException();
                }
            }
            // go to the next element in bucket
            else {
                currentEntry = currentEntry.next;
            }
            // return the element in the current node
            return currentEntry.key;
        }
    }
}
