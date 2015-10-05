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

    private static final String indexLogMsg = " INDEX:";

    private static HashTable sharedHashTable;

    public static class Node {
        long key;
        String value;
        Node next;

        @Override
        public boolean equals(Object o){
            if (!(o instanceof Node)){
                return false;
            }

            if (o == this) {
                return true;
            }

            Node n = (Node) o;
            return (this.key == n.key && this.value.equals(n.value));
        }
    }

    private HashTable(long minSize) {
        size = (int)(isPrime(minSize) ? minSize : nextPrimeNumber(minSize));
        hashTable = new Node[size];
    }

    public static void init(long size) {
        sharedHashTable = new HashTable(nextPrimeNumber(size));
    }

    public static HashTable getInstance(){
        return sharedHashTable;
    }

    public void lockHashTable() {
        this.lock.lock();
    }

    public void unLockHashTable() {
        this.lock.unlock();
    }

    /**
     * Lookup hashTable and return string by key,
     * return null if not found
     * */
    public String get(long key, StringBuilder sb){
        int index = (int) (key % size);
        sb.append(indexLogMsg).append(index);
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

    /**
     * Put key-value pair into hashTable.
     * return true if added successfully
     * return false if key - value already existed
     * */
    public boolean put(long key, String value, StringBuilder sb){
        if (key < 0 || value == null) {
            return false;
        }

        if (add(key, value, sb)) {
            count++;
            // rehash if necessary
            if (count > size){
                rehash();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Internal add key-value pair to hashTable
     * return true if added successfully
     * return false if put duplicate key-value pair
     * */
    private boolean add(long key, String value, StringBuilder sb){
        Node n = new Node();
        n.key = key;
        n.value = value;

        int index = (int)(key % size);
        if (sb != null) {
            sb.append(indexLogMsg).append(index);
        }

        if (hashTable[index] == null){
            hashTable[index] = n;
            return true;
        } else {
            Node tmpNode = hashTable[index];
            while (tmpNode.next != null) {
                if(tmpNode.equals(n)){
                    return false;
                }
                tmpNode = tmpNode.next;
            }
            if(tmpNode.equals(n)){
                return false;
            }
            tmpNode.next = n;
            return true;
        }
    }

    /**
     * Increase hashTable size and perform rehash
     * */
    private void rehash() {
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
            add(n.key, n.value, null);
        }
    }

    /**
     * Give a number, return next prime number greater or equal to input number
     * */
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

    /**
     * Check if a number is prime number
     * */
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
