import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by lpug on 25/08/2017.
 */
public class Permutation {
    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("missing args");
        }

        int length = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            randomizedQueue.enqueue(str);
        }

        for (int i = 0; i < length; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
