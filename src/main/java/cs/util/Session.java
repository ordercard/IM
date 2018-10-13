package cs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 19:23 2018/10/12 2018
 * @Modify:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    /*
     用户唯一性标识
      */

    private String userId;

    private String userName;


}