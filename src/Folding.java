import java.util.Random;

/**
 * Created by jenny on 9/27/15.
 */
public class Folding {

    /**
     * Given size of input strings, return true or false randomly
     * */
    public static boolean shouldGet(int sizeOfSoundLst){
        Random r = new Random();
        int rand = r.nextInt();
        double fBits = Math.ceil(Math.log(sizeOfSoundLst) / Math.log(2));
        int length = (int) Math.ceil(rand/fBits);

        // if the start of the result is 0, return true; otherwise return false
        return foldAndGetSignificantBit(rand, length) == 0;
    }

    /**
     * Fold random integer to certain length of bits
     * then return most significant bit
     * */
    private static int foldAndGetSignificantBit(int rand, int length){
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
