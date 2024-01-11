import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        int size = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != null) {
                size++;
            }
        }
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            if (N == s.length) {
                resize(2*s.length);
            }
            s[N++] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            int i = StdRandom.uniform(0, N);
            Item item = s[i];
            s[i] = s[N-1];
            s[N-1] = null;
            --N;
            if (N > 0 && N == s.length/4) {
                resize(s.length/2);
            }
            return item; 
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else {
            int i = StdRandom.uniform(0, N);
            Item item = s[i];
            return item;
        }
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++){
            copy[i] = s[i];
        }
        s = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Integer k = N;
        private Item[] l = (Item[]) new Object[s.length];

        public RandomizedQueueIterator() {
            for (int i = 0; i < s.length; i++){
                l[i] = s[i];
            }
        }

        @Override
        public boolean hasNext() {
            return k != 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (hasNext()) {
                int i = StdRandom.uniform(0, k);
                Item item = l[i];
                l[i] = l[k-1];
                l[k-1] = null;
                --k;
                return item;
            }
            else {
                throw new NoSuchElementException();
            }
        }  
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(5);
        r.enqueue(6);
        System.out.println(r.size());
        for (int i = 0; i < 5; i++){
            for (Integer s : r) {
                System.out.print(s);
            }
            System.out.print("\n");
        }
    }        
}
