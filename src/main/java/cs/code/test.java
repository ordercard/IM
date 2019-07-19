package cs.code;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 19:04 2019-03-30 2019
 * @Modify:
 */
public class test
{
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss",Locale.US);
        System.out.println(format.format(new Date()));
    }
}
