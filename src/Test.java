import org.junit.Assert;

import java.util.List;
import java.util.Random;

/**
 * Created by jenny on 9/26/15.
 */
public class Test {

    @org.junit.Test
    public void testFolding(){
        int getCount = 0;
        int putCount = 0;
        for (int i = 0; i < 100000; i++) {
            if (Folding.shouldGet(9)) {
                getCount++;
            } else {
                putCount++;
            }
        }

        System.out.print("getCount: " + getCount + "  putCount: " + putCount);
    }

    @org.junit.Test
    public void testNull() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt();
            System.out.println(Integer.toBinaryString(n)  + " : " + n);
        }
    }


    @org.junit.Test
    public void test(){
        String s = "Listen to the music";
        //divide the string into 4-byte chunk
        List<String> lst = Hashing.chunkString(s);
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

        Long expectHash = 0L;
        for (String s : strs){
            expectHash ^= Integer.parseInt(s, 16);
        }

        Long computedHash = Hashing.hash(testString);

        Assert.assertEquals(expectHash, computedHash);
    }

    @org.junit.Test
    public void testPrime() {
        int[] nums = new int[] {1, 4, 9, 60};
        int[] expected = new int[]{1, 5, 11, 61};
        for (int i = 0; i < nums.length; i++){
            Assert.assertEquals(expected[i], HashTable.nextPrimeNumber(nums[i]));
        }
    }
}
