package com.tomcat;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 01:10 2019-07-20 2019
 * @Modify:
 */
public abstract class TinyabstractServlet {
    public abstract void doGet(MyRequest myRequest,MyResponse myResponse)throws Exception;
    public abstract void doPost(MyRequest myRequest,MyResponse myResponse)throws Exception;

    public void  service (MyRequest myRequest,MyResponse myResponse) throws Exception {
        if (myRequest.getMethod().equals("GET")) {
            this.doGet(myRequest,myResponse);
        }else if (myRequest.getMethod().equals("POST")) {
            this.doPost(myRequest,myResponse);
        }else {
            error(myRequest,myResponse);
        }
    }
    protected  void error(MyRequest myRequest,MyResponse myResponse) throws Exception  {

    }
}
