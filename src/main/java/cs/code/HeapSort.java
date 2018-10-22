package cs.code;



/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:35 2018/10/19 2018
 * @Modify:
 */
public class HeapSort {
    //记录从1开始
    private int[] heapArr;

    // 当前位置的计数，由此可得出：
    // 父节点位置 count/2 ；
    // 左边的子节点 count * 2 ；
    // 右边的子节点 count * 2 + 1

    private int count;

    // 堆的大小
    private int length;
    public HeapSort(int length) {
        this.heapArr = new int[length + 1];
        this.count = 0;
        this.length = length;
    }
    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert (int item){
        if (count+1>length) throw  new ArrayIndexOutOfBoundsException();
        heapArr[count+1]=item;
        count++;
        shiftInsert(count);


    }
    private void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /***
     * 调整位置为 n 的元素的位置，即保持最大二叉堆的定义 -> 父结点的键值总是大于或等于任何一个子节点的键值
     * @param s
     */
    private void shiftInsert(int s) {
       while (s>1 && heapArr[s/2]<heapArr[s]){
           swap(heapArr,s/2,s);
           s=s/2;
       }
    }
    public int extractMax() {
        if (count<1) throw  new ArrayIndexOutOfBoundsException();
        int ret =heapArr[1];
        swap(heapArr,1,count);
        count--;
        shiftDec(1);
        return  count>=0 ?ret:Integer.MIN_VALUE;
    }

    private void shiftDec(int i) {
        while (i*2<=count){
            int  j= i*2;
            if (j+1<=count){

                j= heapArr[j+1]>heapArr[j]?j+1:j;
            }
            if (heapArr[i]<heapArr[j]){
               swap(heapArr,i,j);
                i=j;
            }else{
                break;
            }
        }
    }






}
