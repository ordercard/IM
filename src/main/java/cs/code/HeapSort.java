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
     *  循环向上级进行是否满足大顶堆的概念   还有一种做法是每次都最低处向下调整
     *
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
//
//    static int len;
//    /**
//     * 堆排序算法
//     *
//     * @param array
//     * @return
//     */
//    public static int[] HeapSort(int[] array) {
//        len = array.length;
//        if (len < 1) return array;
//        //1.构建一个最大堆
//        buildMaxHeap(array);
//        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
//        while (len > 0) {
//            swap(array, 0, len - 1);
//            len--;
//            adjustHeap(array, 0);
//        }
//        return array;
//    }
//    /**
//     * 建立最大堆
//     *
//     * @param array
//     */
//    public static void buildMaxHeap(int[] array) {
//        //从最后一个非叶子节点开始向上构造最大堆
//        for (int i = (len - 1) / 2; i >= 0; i--) {
//            adjustHeap(array, i);
//        }
//    }
//    /**
//     * 调整使之成为最大堆
//     *
//     * @param array
//     * @param i
//     */
//    public static void adjustHeap(int[] array, int i) {
//        int maxIndex = i;
//        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
//        if (i * 2 < len && array[i * 2] > array[maxIndex])
//            maxIndex = i * 2;
//        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
//        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex])
//            maxIndex = i * 2 + 1;
//        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
//        if (maxIndex != i) {
//            swap(array, maxIndex, i);
//            adjustHeap(array, maxIndex);
//        }
//    }




}
