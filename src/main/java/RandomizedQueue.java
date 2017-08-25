import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Created by lpug on 24/08/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int length;
    private final List<Item> queue;

    public RandomizedQueue() {
        queue = new ArrayList<Item>();
        length = 0;
    }

    public boolean isEmpty() {
        return 0 == length;
    }

    public int size() {
        return length;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        queue.add(item);
        queue.set(length, item);
        length++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int position = StdRandom.uniform(length);
        Item item = queue.get(position);

        if(length > 1)
            queue.set(position, queue.get(length - 1));
        queue.remove(length-1);

        length--;

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int position = StdRandom.uniform(length);
        Item item = queue.get(position);

        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private String itemString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append("-->");
        }
        s.append("null");
        return s.toString();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private List<Item> copiedQueue = null;

        private int position;

        public RandomizedQueueIterator() {
            copiedQueue = new ArrayList<Item>();

            Item[] temp = (Item[]) new Object[length];

            for (int i = 0; i < length; i++) {
                temp[i] = queue.get(i);
            }

            StdRandom.shuffle(temp);

            for (int i = 0; i < length; i++) {
                copiedQueue.add(temp[i]);
            }
            position = 0;
        }

        public boolean hasNext() {
            return position < length;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = copiedQueue.get(position);
            position++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        StdOut.println(queue.itemString());
        queue.dequeue();
        StdOut.println(queue.itemString());
        queue.enqueue("B");
        StdOut.println(queue.itemString());
        queue.enqueue("C");

        StdOut.println(queue.itemString());

        StdOut.println(queue.sample());

        queue.dequeue();

        StdOut.println(queue.itemString());

        queue.dequeue();
        StdOut.println(queue.itemString());

    }
}