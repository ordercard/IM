package com.tomcat.impl;

import com.tomcat.MyRequest;
import com.tomcat.MyResponse;
import com.tomcat.TinyabstractServlet;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 01:16 2019-07-20 2019
 * @Modify:
 */
public class TinyServlet extends TinyabstractServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) throws Exception {
        System.out.println("你好");
        myResponse.write("i love you ");
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) throws Exception {
        System.out.println("你好");
        myResponse.write("i love you ");
    }
}
