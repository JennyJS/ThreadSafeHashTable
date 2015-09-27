import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/27/15.
 */
public class HashTable {
    private int size;
    private int count;

    public class Node {
        int key;
        int value;
        Node next;
    }

    Node[] hashTable = new Node[size];


    public int get (int key){
        int hashKey = key % size;
        Node tmpNode = hashTable[hashKey];
        while(tmpNode.key!= key){
            tmpNode = tmpNode.next;
        }

        return tmpNode.value;

    }

    public void put (int key, int value){

        count++;
        add(key, value);
        // rehashing
        if (count > size){
            rehashing();
        }

    }

    public void add (int key, int value){
        Node n = new Node();
        n.key = key;
        n.value = value;

        int hashKey = key % size;

        Node tmpNode = hashTable[hashKey];
        while (tmpNode != null) {
            tmpNode = tmpNode.next;
        }
        tmpNode = n;
    }

    public void rehashing() {
        //put the nodes already in the hashtable to a list
        List<Node> nodeLst = new LinkedList<>();
        for (int i = 0; i < hashTable.length; i++){
            Node n = hashTable[i];
            while(n != null){
                nodeLst.add(n);
            }
        }

        //put the list nodes into the double-sized hashtable again

        size *= 2;
        hashTable = new Node[size];

        for (Node n : nodeLst){
            int valueOfN = n.value;
            int keyOfN = n.key;
            put(keyOfN, valueOfN);
        }

    }




}
