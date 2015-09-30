import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/27/15.
 */
public class P1 {

    public static void main(String[] args) {
        // Read from stdin
        // Print stdin
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while((input=br.readLine())!=null){
                System.out.println(input);
            }

        } catch(IOException io){
            io.printStackTrace();
        }

        System.out.println("\n*****************************\n");

        int numberOfThreads = 12;
        int numOfOperations = 10;
        String[] strs = {
            "Listen to the music",
            "Hello world",
            "Jenny is a genius"
        };

        P1.start(numberOfThreads, numOfOperations, strs);
    }


    public static void start(int numOfThreads, int numOfOperations, String[] strings) {

        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < numOfThreads; i++){
            //HashTable hashTable, String[] strArr, int operationNum, int threadId
            Thread thread = new Thread(new MyRunnable(strings, numOfOperations, i));
            threads.add(thread);
        }


        for (Thread thread : threads) {
            thread.start();
        }
    }
}
