import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/26/15.
 */
public class Test {

    @org.junit.Test
    public void testNull() {
        HashTable.Node[] nodeArr = new HashTable.Node[10];
        HashTable.Node addedNode = new HashTable.Node();
        nodeArr[0] = addedNode;

//        HashTable.Node tmpNode = nodeArr[0];
//        tmpNode = addedNode;
    }


    @org.junit.Test
    public void test(){
        String s = "Listen to the music";
        List<String> lst = new LinkedList<>();
        //divide the string into 4-byte chunk
        Hashing.chunkString(s, lst);
        //translate the chunks into hex, and reversed the odd chunks
        List<Integer> reversedLst = Hashing.reverseOddChunks(lst);
        int i = 0;
        while (i < reversedLst.size()){
            System.out.println(Integer.toHexString(reversedLst.get(i)).toUpperCase());
            i++;
        }
    }

    @org.junit.Test
    public void testConvertHex() {
        Assert.assertTrue(Integer.toHexString(Hashing.getHexFromString("List")).toUpperCase().equals("4C697374"));
        Assert.assertTrue(Integer.toHexString(Hashing.getHexFromString("en t")).toUpperCase().equals("656E2074"));
    }

    @org.junit.Test
    public void testReverse() {
        int hash = Hashing.getHexFromString("List");
        int reversed = Hashing.reverseBits(hash);
        Assert.assertTrue(Integer.toHexString(reversed).toUpperCase().equals("2ECE9632"));
    }

    @org.junit.Test
    public void testHash() {
        String testString = "Listen to the music";
        String[] strs = new String[]{"2ECE9632", "656E2074", "162E04F6", "65206D75", "00C696CE"};

        int expectHash = 0;
        for (String s : strs){
            expectHash ^= Integer.parseInt(s, 16);
        }

        int computedHash = Hashing.hash(testString);

        Assert.assertEquals(expectHash, computedHash);
    }

    @org.junit.Test
    public void testTable() {
        HashTable t = new HashTable();
        t.add(0, "A");
        t.add(10, "B");
        t.add(20, "C");
        t.put(1, "hi");
        t.put(2, "bye");
//        Assert.assertEquals("bye", t.get(2) );
       System.out.println(t.get(3));



    }


}
