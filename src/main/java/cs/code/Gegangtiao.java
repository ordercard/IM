package cs.code;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 20:20 2018/10/22 2018
 * @Modify:
 */
public class Gegangtiao {

    public static int cut(int []p,int n)
    {
        if(n==0) {
            return 0;
        }
        int q=Integer.MIN_VALUE;
        for(int i=1;i<=n;i++)
        {
            q=Math.max(q, p[i-1]+cut(p, n-i));
        }
        return q;
    }



    public static int cutMemo(int []p)
    {
        int []r=new int[p.length+1];
        for (int i=0;i<=p.length;i++) {
            r[i]=-1;
        }
        return cut(p, p.length, r);
    }

    public static int cut(int []p,int n,int []r)
    {
        int q=-1;
        if(r[n]>=0) {
            return r[n];
        }
        r[n]=q;
        if(n==0) {
            q=0;
        } else {
            for(int i=1;i<=n;i++) {
                q=Math.max(q, cut(p, n-i,r)+p[i-1]);
            }
        }

        return q;
    }



    //自下向上

    public static int buttom_up_cut(int []p)
    {   int [] r =new int[p.length+1];


    return  1;
    }



    //自下向上
    public static int buttom_up_cut2(int []p)
    {
        int []r=new int[p.length+1];
        /*
        每次求出r1 r2的值  而且后面的值依赖前面的值，如内层的循环
         */
        for(int i=1;i<=p.length;i++)
        {
            int q=-1;

            /*
                循环的求出最优解  每次循环都会求出r【i】的最优解 然后自下向上的规则求出后面的    j=1: pj+ r[i-j]   j=2: pj+ r[i-j]   如此依次比较求出 截取一寸 两寸等方案中带来最大的收益
                 */
            for(int j=1;j<=i;j++) {

                q=Math.max(q, p[j-1]+r[i-j]);
            }
            r[i]=q;
        }
        return r[p.length];
    }


    public static void main(String[] args) {
        int[] a = new int[]{3,9,2,1,5};
       int x= buttom_up_cut2(a);
        System.out.println(x);
    }

}
