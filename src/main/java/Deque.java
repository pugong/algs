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
        private Item value = null;
        private Node next = null;
        private Node prev = null;

        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currentNode = firstNode;

        public boolean hasNext() {
            return currentNode != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = currentNode.getValue();
            currentNode = currentNode.getNext();
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque() {
        nodesLength = 0;
        firstNode = null;
        lastNode = null;
    }

    public boolean isEmpty() {
        return nodesLength == 0;
    }

    public int size() {
        return nodesLength;
    }

    public void addFirst(Item item) {
        if (null == item)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.setNext(firstNode);
        node.setValue(item);
        node.setPrev(null);
        if (null != firstNode)
            firstNode.setPrev(node);
        firstNode = node;

        nodesLength++;

        if (lastNode == null)
            lastNode = node;
    }

    public void addLast(Item item) {
        if (null == item)
            throw new IllegalArgumentException();
        Node node = new Node();
        node.setValue(item);
        node.setPrev(lastNode);
        node.setNext(null);

        if (lastNode != null)
            lastNode.setNext(node);

        lastNode = node;

        nodesLength++;

        if (firstNode == null)
            firstNode = node;
    }

    public Item removeFirst() {
        if (nodesLength == 0)
            throw new NoSuchElementException();

        Item item = firstNode.getValue();

        Node node = new Node();
        if (firstNode != null)
            node = firstNode.getNext();

        nodesLength--;

        if (firstNode == lastNode)
            lastNode = node;

        firstNode = node;

        if (node != null) {
            node.setPrev(null);
        }

        return item;
    }

    public Item removeLast()

    {
        if (nodesLength == 0)
            throw new NoSuchElementException();

        Item item = lastNode.getValue();
        Node node = new Node();
        if (lastNode != null)
            node = lastNode.getPrev();

        if(null != node)
            node.setNext(null);

        nodesLength--;

        if (firstNode == lastNode)
            firstNode = node;

        lastNode = node;

        return item;
    }

    public Iterator<Item> iterator()

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

    // unit testing (optional)
    public static void main(String[] args) {

        Deque<String> deque = new Deque<String>();

        deque.addFirst("C");
        deque.removeFirst();
        // deque.removeLast();
        deque.addFirst("B");
        deque.removeLast();
        deque.addFirst("A");
        deque.addLast("D");
        deque.addLast("E");
        StdOut.println(deque.itemString());

        deque.removeFirst();
        deque.removeLast();
        StdOut.println(deque.itemString());

    }
}
