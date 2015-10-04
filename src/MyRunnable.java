/**
 * Created by jenny on 9/27/15.
 */
public class MyRunnable implements Runnable {
    private final String[] strArr;
    private final int operationNum;
    private final int threadId;

    public MyRunnable(String[] strArr, int operationNum, int threadId) {

        this.strArr = strArr;
        this.operationNum = operationNum;
        this.threadId = threadId;
    }

    public void run(){
        // access shared hashTable
        for (int i = 0; i < operationNum; i++) {
            // Determine GET ? PUT
            StringBuilder sb = new StringBuilder();
            sb.append(this.threadId);
            String str = this.strArr[i % this.strArr.length];

            boolean toGet = Folding.shouldGet(strArr.length);
            sb.append(toGet? " Get": " Put");
            sb.append(" ").append(str);

            //get access to the shared hashTable

            long stringKey = Hashing.hash(str);

            try {
                HashTable.getInstance().lockHashTable();
                if (toGet){
                    String value = HashTable.getInstance().get(stringKey);
                    if (value != null) {
                        sb.append(" Found");
                    } else {
                        sb.append(" Not found");
                    }
                } else {
                    HashTable.getInstance().put(stringKey, str);
                }

                System.out.println(sb.toString());
            } finally {
                HashTable.getInstance().unLockHashTable();
            }
        }
    }
}
