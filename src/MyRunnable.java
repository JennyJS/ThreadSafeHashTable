/**
 * Created by jenny on 9/27/15.
 */
public class MyRunnable implements Runnable {
    private HashTable hashTable;
    private String[] strArr;
    private int operationNum;
    private int threadId;

    public MyRunnable(HashTable hashTable, String[] strArr, int operationNum, int threadId) {
        this.hashTable = hashTable;
        this.strArr = strArr;
        this.operationNum = operationNum;
        this.threadId = threadId;
    }

    public void run(){

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}

        // access shared hashtable
        for (int i = 0; i < operationNum; i++) {
            // Determine GET ? PUT
            StringBuffer sb = new StringBuffer();
            sb.append(this.threadId);
            String str = this.strArr[i % this.strArr.length];

            boolean toGet = Folding.shouldGet(strArr.length);
            sb.append(toGet? " Get": " Put");
            sb.append(" " + str);

            //get access to the shared hashtable
            if (toGet){
                String value = this.hashTable.get(Hashing.hash(str));
                if (value != null) {
                   sb.append(" Found");
                } else {
                    sb.append(" Not found");
                }
            } else {
                this.hashTable.put(Hashing.hash(str), str);
            }

            System.out.println(sb.toString());

        }

    }
}
