import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/27/15.
 */
public class HashTable {
    private int size = 10;
    private int count = 0;

    public static class Node {
        Integer key;
        String value;
        Node next;
    }

    Node[] hashTable = new Node[size];


    public String get (Integer key){
        if (key == null) {
            return null;
        }
        int hashKey = key % size;
        Node tmpNode = hashTable[hashKey];
        while(tmpNode != null){
            if (tmpNode.key == key){
                return tmpNode.value;
            } else {
                tmpNode = tmpNode.next;
            }
        }

        return null;

    }

    public void put (Integer key, String value){
        if (key == null || value == null) {
            return;
        }

        count++;
        add(key, value);
        // rehashing
        if (count > size){
            rehashing();
        }
    }

    public void add (Integer key, String value){
        Node n = new Node();
        n.key = key;
        n.value = value;

        int index = key % size;

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

        size *= 2;
        hashTable = new Node[size];

        for (Node n : nodeLst){
            String valueOfN = n.value;
            Integer keyOfN = n.key;
            put(keyOfN, valueOfN);
        }

    }




}
