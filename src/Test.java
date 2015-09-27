import org.junit.Assert;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/26/15.
 */
public class Test {

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
}
