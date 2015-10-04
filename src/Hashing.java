import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/26/15.
 */
public class Hashing {

    public static long hash(String s){
        if (s == null) {
            return 0L;
        }
        List<String> lst = new LinkedList<>();
        //divide the string into 4-byte chunk
        chunkString(s, lst);

        //translate the chunks into hex, and reversed the odd chunks
        List<Integer> reversedLst = reverseOddChunks(lst);


        //return the exclusive OR
        int res = 0;
        for (Integer num : reversedLst){
            res ^= num;
        }

        return 0xffffffffL & res;
    }

    /********************************************************/
    /********Chunk the String to 4-Byte subString***********/
    /******************************************************/


    public static void chunkString(String s, List<String> lst){
        for(int i = 0; i < s.length(); ){

            StringBuilder subStr = new StringBuilder();
            while(subStr.length() < 4){
                if (i < s.length()){
                    subStr.append(s.charAt(i));
                } else {
                    break;
                }
                i++;
            }
            lst.add(subStr.toString());
        }
    }

    /*************************************************/
    /*******Convert String to hexadecimal************/
    /***********************************************/


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
        int shift = 24;
        int hashCode = 0;
        char[] charArr = str.toCharArray();
        for (char c : charArr){
            hashCode |= (int)c << shift;
            shift -= 8;
        }
        return hashCode;
    }


    public static int reverseBits(int n){
        for (int i = 0; i < 16; i++){
            n = swapBits(n, i, 32 - i - 1);
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
