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

    private static HashTable sharedHashTable;


    private HashTable(int minSize) {
        if (!isPrime(minSize)){
            int nextPrime = nextPrimeNumber(minSize);
            size = nextPrime;
            hashTable = new Node[nextPrime];
        }
    }


    public static HashTable getInstance(int size){
        if (sharedHashTable == null){
            sharedHashTable = new HashTable(size);
        }
        return sharedHashTable;
    }

    /********************************************************/
    /************** Get function ***************************/
    /******************************************************/


    public String get (Long key){
        if (key == null) {
            return null;
        }

        lock.lock();
        try{
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

        } finally {
            lock.unlock();
        }


    }

    /********************************************************/
    /************** Put function ***************************/
    /******************************************************/

    public void put (Long key, String value){
        lock.lock();
        try{
            if (key == null ||value == null) {
                return;
            }
            count++;
            add(key, value);

            // rehashing
            if (count > size){
                rehashing();
            }

        }finally {
            lock.unlock();
        }

    }


    /********************************************************/
    /************** Add function ***************************/
    /******************************************************/

    public void add(long key, String value){
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

    public void rehashing() {
        //put the nodes already in the hashtable to a list
        List<Node> nodeLst = new LinkedList<>();
        for (int i = 0; i < hashTable.length; i++){
            Node n = hashTable[i];
            while(n != null){
                nodeLst.add(n);
                n = n.next;
            }
        }

        //put the list nodes into the double-sized hashtable again

        size *= size;
        for (int s = 1; s < size * 2; s++){
            int nextNum = size + s;
            if (isPrime(nextNum)){
                size = nextNum;
            }
        }
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
    public static int nextPrimeNumber(int input) {
        if (!isPrime(input)){
            for (int i = 0; i < input * 2; i++){
                int next = input + i;
                if (isPrime(next)){
                    return next;
                }
            }
        } else {
            return input;
        }
        return -1;
    }


    public static boolean isPrime(int num){
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
