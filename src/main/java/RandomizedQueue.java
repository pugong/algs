import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * Created by lpug on 24/08/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int length;
    private List<Item> queue;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        queue = new ArrayList<Item>();
        length = 0;
    }

    public boolean isEmpty()                 // is the queue empty?
    {
        return 0 == length;
    }

    public int size()                        // return the number of items on the queue
    {
        return length;
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new java.lang.NullPointerException();
        queue.add(item);
        queue.set(length, item);
        length++;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int position = StdRandom.uniform(length);
        Item item = queue.get(position);

        queue.set(position, queue.get(length - 1));
        queue.remove(length-1);
        length--;

        return item;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int position = StdRandom.uniform(length);
        Item item = queue.get(position);

        return item;
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
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

        List<Item> queue = null;

        int position;

        public RandomizedQueueIterator() {
            queue = new ArrayList<Item>();
            for (int i = 0; i < length; i++)
                queue.add(RandomizedQueue.this.queue.get(i));
            Collections.shuffle(queue);
            position = 0;
        }

        public boolean hasNext() {
            return position < length;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = queue.get(position);
            position++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args)   // unit testing (optional)
    {
        StdOut.println("Hello, RandomizedQueue here");
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        StdOut.println(queue.itemString());

        StdOut.println(queue.sample());

        queue.dequeue();

        StdOut.println(queue.itemString());

        queue.dequeue();
        StdOut.println(queue.itemString());

        queue.dequeue();
        StdOut.println(queue.itemString());

//        queue.dequeue();
//        StdOut.println(queue.itemString());
    }
}