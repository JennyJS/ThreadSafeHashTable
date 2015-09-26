import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenny on 9/26/15.
 */
public class Hashing {
    public  static void main(String[] args){
        String s = "List";
        List<String> lst = new LinkedList<>();

        //translate the chunks into hex,
        int i = 0;
        int shift = 24;
        List<Integer> hexResult = new LinkedList<>();
        while (!lst.isEmpty()){
            String tmp = lst.get(i);
            char[] charArr = tmp.toCharArray();
            for (char c : charArr){
                hexResult.add((int)c << shift);
                shift -= 8;
            }
        }



        int hashCode = 0;

        for (Integer elem : hexResult){
            hashCode |= elem;
        }


    }

    public int hash(String s){

        List<String> lst = new LinkedList<>();
        //divide the string into 4-byte chunk
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

        //translate the chunks into hex,
        int i = 0;
        int shift = 24;
        List<Integer> hexResult = new LinkedList<>();
        while (!lst.isEmpty()){
            String tmp = lst.get(i);
            char[] charArr = tmp.toCharArray();
            for (char c : charArr){
                hexResult.add((int)c << shift);
                shift -= 8;
            }
        }



        int hashCode = 0;

        for (Integer elem : hexResult){
            hashCode |= elem;
        }

        // reverse the odd chunks




        //return the exclusive OR

        return 0;
    }
}
