import com.sun.xml.internal.bind.v2.model.core.MaybeElement;
import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/27/15.
 */
public class Controller {

    public static void main(String[] args) {
        int numberOfThreads = 12;
        int numOfOperations = 10;
        String[] strs = {
            "Listen to the music",
            "Hello world",
            "Jenny is a genius"
        };

        Controller.start(numberOfThreads, numOfOperations, strs);
    }


    public static void start(int numOfThreads, int numOfOperations, String[] strings) {
        HashTable hashTable = new HashTable();
        List<Thread> threads = new LinkedList<>();
        for (int i = 0; i < numOfThreads; i++){
            //HashTable hashTable, String[] strArr, int operationNum, int threadId
            Thread thread = new Thread(new MyRunnable(hashTable, strings, numOfOperations, i));
            threads.add(thread);
        }


        for (Thread thread : threads) {
            thread.start();
        }
    }
}
