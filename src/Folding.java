import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by jenny on 9/27/15.
 */
public class Folding {
    public static void main (String args[]){
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

    public static boolean shouldGet(int sizeOfSoundLst){
        Random r = new Random();
        int rand = r.nextInt();
        double fBits = Math.ceil(Math.log(sizeOfSoundLst) / Math.log(2));
        int length = (int) Math.ceil(rand/fBits); // round up???

        // if the start of the result is 0, return true; otherwise return false
        int significantBit = fold(rand, length);
        if (significantBit == 0){
            return true;
        } else {
            return false;
        }
    }

    public static int fold(int rand, int length){

        String binaryString = Integer.toBinaryString(rand);
        while (binaryString.length() < 32) {
            binaryString = "0" + binaryString;
        }

        int xor = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (i % length == 0){
                xor ^= binaryString.charAt(i) - '0';
            }

        }
        return xor;
    }

}
