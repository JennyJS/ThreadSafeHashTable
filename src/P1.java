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
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input;

            while((input=br.readLine())!=null){
                input = input.trim();

                if (input.startsWith("#")) {
                    // ignore line starting with #
                    continue;
                } else {
                    // Remove string after #
                    int index = input.indexOf('#');
                    if (index != -1) {
                        input = input.substring(0, index);
                        input = input.trim();
                    }
                }

                if (numberOfThreads == -1) {
                    try {
                        numberOfThreads = Integer.parseInt(input);
                        if (numberOfThreads < 1) {
                            System.err.println("Invalid number of threads: " + numberOfThreads);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number of threads: " + input);
                        return;
                    }
                } else if (numOfOperations == -1) {
                    try {
                        numOfOperations = Integer.parseInt(input);
                        if (numOfOperations < 1) {
                            System.err.println("Invalid number of operations: " + numOfOperations);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number of operations: " + input);
                        return;
                    }
                } else if (!input.isEmpty()){
                    strLst.add(input);
                }
            }
        } catch(IOException io){
            io.printStackTrace();
            System.err.println("Error reading from IO");
            return;
        }

        if (strLst.isEmpty()) {
            System.err.println("No sound found!");
            return;
        }

        String[] sounds = new String[strLst.size()];
        for (int i = 0; i < strLst.size(); i++){
            sounds[i] = strLst.get(i);
        }

        start(numberOfThreads, numOfOperations, sounds);
    }


    private static void start(int numOfThreads, int numOfOperations, String[] strings) {
        HashTable.init(strings.length);

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
