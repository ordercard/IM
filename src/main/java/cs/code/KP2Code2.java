package cs.code;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 17:54 2018/10/19 2018
 * @Modify:
 */
public class KP2Code2 {    public static void main(String[] args) {
    int[] test = {6, 9, 28 ,13 ,31 ,26 ,7 ,16 ,7, 22, 49 ,46,2,46};
    quicksort(test,0);
    for (int i = 0; i < test.length; i++) {
        System.out.print(test[i] + "\t");
    }
}
    /**
     * 每个（未排序）的拆分的遍历
     *  将第一个元素设为轴心点
     *    存储指数 = 轴心点指数 +1
     *    从 i=轴心点指数 +1 到 最右指数 的遍历
     *      如果 元素[i] < 元素[轴心点]
     *        交换(i, 存储指数); 存储指数++
     *    交换(轴心点, 存储指数 - 1)
     * @param arr
     */
    private static void quicksort(int[] arr,int l) {
        if(l >= arr.length) return;//递归出口
        int j = l + 1;//l 作为轴心点 j 作为存储指数
        for (int i = l; i < arr.length; i++) {
            if(j >= arr.length) break;//越界情况
            if(arr[i] < arr[j]){
                swap(arr, i, j);
            }
            j++;
        }
        if(j <= arr.length) swap(arr, l, j-1);
        quicksort(arr, ++l);
    }
    /**
     * 交换数组中的两个元素
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
        }
