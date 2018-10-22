package cs.code;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:01 2018/10/22 2018
 * @Modify:
 */
public class DPsanjiao {
    int  n;

     static int [][] a;

    static {
        a = new int[100][100];
    }

    public static void main(String[] args) {

    }

     int  getMax(int[][] as,int x, int y){
         if( a[x][y] != -1 ) {
             return a[x][y];
         }

         if (as.length==n){
             a[x][y]= as[x][y];
        }
            else{
             int q = getMax(as,x+1,y);
             int z = getMax(as,x+1,y+1);

             a[x][y]= Math.max(q,z)+as[x][y];
         }

        return  a[x][y];
    }


}
