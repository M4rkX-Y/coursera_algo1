package Intro.src;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class RandomWord {
    public static void main(String[] args) {
        double champion_result = 0;
        String champion_word = "";
        
        for(int i = 1; i<5; i++) {
            String current_word = StdIn.readString();
 
            double current_result = (double)1/i;
 
            if(current_result > champion_result) {
                champion_result = current_result;
                champion_word = current_word;
            }
        }

        StdOut.println(champion_word);
    }
}
