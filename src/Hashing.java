import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/26/15.
 */
public class Hashing {

    private final static int CHUNK_SIZE = 4;
    private final static int INT_SIZE = 32;
    private final static int BYTE_SIZE = 8;
    private final static long BIT_MASK = 0xffffffffL;

    /**
     * Get hash key from input string by chunking and folding the string
     * */
    public static long hash(String s){
        if (s == null) {
            return 0L;
        }

        //divide the string into 4-byte chunk
        List<String> lst = chunkString(s);

        //translate the chunks into hex, and reversed the odd chunks
        List<Integer> reversedLst = reverseOddChunks(lst);

        //return the exclusive OR
        int res = 0;
        for (Integer num : reversedLst){
            res ^= num;
        }

        return BIT_MASK & res;
    }

    /**
     * Chunk input string to 4-byte subStrings and
     * add them to list
     * */
    public static List<String> chunkString(String s){
        List<String> lst = new LinkedList<>();
        for(int i = 0; i < s.length(); ){
            StringBuilder subStr = new StringBuilder();
            while(subStr.length() < CHUNK_SIZE){
                if (i < s.length()){
                    subStr.append(s.charAt(i));
                } else {
                    break;
                }
                i++;
            }
            lst.add(subStr.toString());
        }
        return lst;
    }

    /**
     * This method takes list of strings, convert each of them to hex 
     * and reverse bits for string add odd index
     * */
    public static List<Integer> reverseOddChunks(List<String> lst){
        List<Integer> reversedLst = new LinkedList<>();
        boolean isOddIndex = true;
        for (String str : lst){
            int hashCode = getHexFromString(str);

            if (isOddIndex){
                hashCode = reverseBits(hashCode);
                isOddIndex = false;
            } else {
                isOddIndex = true;
            }

            reversedLst.add(hashCode);
        }
        return reversedLst;
    }
    
    public static int getHexFromString(String str) {
        int shift = BYTE_SIZE * 3;
        int hashCode = 0;
        char[] charArr = str.toCharArray();
        for (char c : charArr){
            hashCode |= (int)c << shift;
            shift -= BYTE_SIZE;
        }
        return hashCode;
    }

    public static int reverseBits(int n){
        for (int i = 0; i < INT_SIZE / 2; i++){
            n = swapBits(n, i, INT_SIZE - i - 1);
        }
        return n;
    }

    private static int swapBits(int n, int i, int j){
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;
        if ((a ^ b) != 0){
            n ^= (1 << i) | (1 << j);
        }
        return n;
    }
}
