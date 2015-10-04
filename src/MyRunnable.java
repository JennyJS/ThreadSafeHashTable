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

            long stringKey = Hashing.hash(str);

            try {
                // Lock shared hashTable
                HashTable.getInstance().lockHashTable();

                if (toGet){
                    String value = HashTable.getInstance().get(stringKey);
                    sb.append(value != null? " FOUND": " NOT_FOUND");
                } else {
                    HashTable.getInstance().put(stringKey, str);
                }

                System.out.println(sb.toString());
            } finally {
                // Unlock shared hashTable
                HashTable.getInstance().unLockHashTable();
            }
        }
    }
}
