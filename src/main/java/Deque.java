import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by lpug on 24/08/2017.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node firstNode;
    private Node lastNode;
    private int nodesLength;

    private class Node {
        public Item value = null;
        public Node next = null;
        public Node prev = null;
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currentNode = firstNode;

        public boolean hasNext() {
            return currentNode != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item item = currentNode.value;
            currentNode = currentNode.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque()                           // construct an empty deque
    {
        nodesLength = 0;
        firstNode = null;
        lastNode = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        if (null == firstNode)
            return true;
        else
            return false;
    }

    public int size()                        // return the number of items on the deque
    {
        return nodesLength;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (null == item)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.next = firstNode;
        node.value = item;
        node.prev = null;
        if (null != firstNode)
            firstNode.prev = node;
        firstNode = node;

        nodesLength++;

        if (lastNode == null)
            lastNode = node;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (null == item)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.value = item;
        node.prev = lastNode;
        node.next = null;

        if (lastNode != null)
            lastNode.next = node;

        lastNode = node;

        nodesLength++;

        if (firstNode == null)
            firstNode = node;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (nodesLength == 0)
            throw new NoSuchElementException();

        Node node = new Node();
        if (firstNode != null)
            node = firstNode.next;
        node.prev = null;

        nodesLength--;

        if (firstNode == lastNode)
            lastNode = node;

        firstNode = node;

        return node.value;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (nodesLength == 0)
            throw new NoSuchElementException();

        Node node = new Node();
        if (lastNode != null)
            node = lastNode.prev;
        node.next = null;

        nodesLength--;

        if (firstNode == lastNode)
            firstNode = node;

        lastNode = node;

        return node.value;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
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

    public static void main(String[] args)   // unit testing (optional)
    {
        StdOut.println("Hello, algs4");
        Deque<String> deque = new Deque<String>();

        deque.addFirst("C");
        deque.addFirst("B");
        deque.addFirst("A");
        deque.addLast("D");
        deque.addLast("E");
        StdOut.println(deque.itemString());

        deque.removeFirst();
        deque.removeLast();
        StdOut.println(deque.itemString());

        // deque.addFirst(null);
        // deque.addLast(null);
    }
}
