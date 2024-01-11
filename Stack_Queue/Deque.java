import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last, current;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        current = first;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node newNode = new Node();
            newNode.item = item;
            if (isEmpty()) {
                last = newNode;
            } else {
                first.prev = newNode;
            }
            newNode.next = first;
            first = newNode;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node newNode = new Node();
            newNode.item = item;
            if (isEmpty()) {
                first = newNode;
            } else {
                last.next = newNode;
                newNode.prev = last;
            }
            last = newNode;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item = first.item;
            if (first.next == null) {
                last = null;
            }
            else {
                first.next.prev = null;
            }
            first = first.next;
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item item = last.item;
            if (first.next == null) {
                first = null;
            }
            else {
                last.prev.next = null;
            }
            last = last.prev;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }    

        @Override
        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(5);
        d.addFirst(4);
        d.addLast(2);
        d.addLast(3);
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        for (Integer s : d) {
            System.out.print(s);
        }
    }
}