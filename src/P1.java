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
        int numberOfThreads = -1;
        int numOfOperations = -1;
        List<String> strLst = new LinkedList<>();

        // Read from stdin
        // Print stdin
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while((input=br.readLine())!=null){
                input.trim();
                if (input.startsWith("#")) {
                    continue;
                }
                if (numberOfThreads == -1) {
                    numberOfThreads = Integer.parseInt(input);
                } else if (numOfOperations == -1){
                    numOfOperations = Integer.parseInt(input);
                } else {
                    strLst.add(input);
                }
            }

        } catch(IOException io){
            io.printStackTrace();
        }

        System.out.println("\n*****************************\n");

        if (numberOfThreads < 1){
            System.out.println("Invalid thread number");
            return;
        } else if (numOfOperations < 1){
            System.out.println("Invalid number of operation");
            return;
        } else if (strLst.isEmpty()){
            System.out.println("No input string");
            return;
        }
        String[] strs = new String[strLst.size()];
        for (int i = 0; i < strLst.size(); i++){
            strs[i] = strLst.get(i);
        }

        P1.start(numberOfThreads, numOfOperations, strs);
    }


    public static void start(int numOfThreads, int numOfOperations, String[] strings) {
        HashTable.init(HashTable.nextPrimeNumber(strings.length));

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
