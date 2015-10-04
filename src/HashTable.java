import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by jenny on 9/27/15.
 */
public class HashTable {
    private int size;
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private Node[] hashTable;

    public static class Node {
        long key;
        String value;
        Node next;
    }

    public void lockHashTable() {
        this.lock.lock();
    }

    public void unLockHashTable() {
        this.lock.unlock();
    }


    private static HashTable sharedHashTable;


    private HashTable(long minSize) {
        long tableSize = isPrime(minSize) ? minSize : nextPrimeNumber(minSize);
        size = (int)tableSize;
        hashTable = new Node[size];
    }


    public static void init(long size) {
        sharedHashTable = new HashTable(nextPrimeNumber(size));
    }

    public static HashTable getInstance(){
        return sharedHashTable;
    }

    /********************************************************/
    /************** Get function ***************************/
    /******************************************************/


    public String get (Long key){

        int index = (int) (key % size);
        Node tmpNode = hashTable[index];
        while(tmpNode != null){
            if (tmpNode.key == key){
                return tmpNode.value;
            } else {
                tmpNode = tmpNode.next;
            }
        }

        return null;
    }




    /********************************************************/
    /************** Put function ***************************/
    /******************************************************/

    public void put (Long key, String value){

        if (key == null ||value == null) {
            return;
        }
        count++;
        add(key, value);

        // rehashing
        if (count > size){
            rehashing();
        }

    }


    /********************************************************/
    /************** Add function ***************************/
    /******************************************************/

    private void add(long key, String value){
        Node n = new Node();
        n.key = key;
        n.value = value;

        int index = (int)(key % size);

        if (hashTable[index] == null){
            hashTable[index] = n;
        } else {
            Node tmpNode = hashTable[index];
            while (tmpNode.next != null) {
                tmpNode = tmpNode.next;

            }
            tmpNode.next = n;
        }

    }

    /********************************************************/
    /************** Rehashing function *********************/
    /******************************************************/

    private void rehashing() {
        //put the nodes already in the hashTable to a list
        List<Node> nodeLst = new LinkedList<>();
        for (Node n : hashTable) {
            while (n != null) {
                nodeLst.add(n);
                n = n.next;
            }
        }

        //put the list nodes into the double-sized hashTable again
        size = (int)nextPrimeNumber(size * 2);
        hashTable = new Node[size];

        for (Node n : nodeLst){
            String valueOfN = n.value;
            Long keyOfN = n.key;
            put(keyOfN, valueOfN);
        }

    }

    /********************************************************/
    /************** checking prime number*******************/
    /******************************************************/
    protected static long nextPrimeNumber(long input) {
        if (!isPrime(input)){
            for (long num = input + 1; num < input * 2; num++){
                if (isPrime(num)){
                    return num;
                }
            }
        } else {
            return input;
        }
        return -1;
    }


    private static boolean isPrime(long num){
        if (num % 2 == 0){
            return false;
        }

        for (int i = 3; i * i <= num; i += 2){
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
