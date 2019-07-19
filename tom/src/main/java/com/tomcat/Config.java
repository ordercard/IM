package com.tomcat;

import com.tomcat.servlet.map.ServletMap;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 01:23 2019-07-20 2019
 * @Modify:
 */
@Data
public class Config {
    public static final  List<ServletMap> list = new ArrayList<ServletMap>();

}
