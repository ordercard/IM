package com.tomcat;

import com.tomcat.servlet.map.ServletMap;
import sun.reflect.Reflection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:21 2019-07-19 2019
 * @Modify:
 */
public class TinyTomcat {
    int port =8080;
    Map<String, String> urlClassMap = new HashMap<>();
    ServerSocket serverSocket;

    public TinyTomcat(int port) {
        this.port = port;
    }
    public void  start () throws Exception {
        init();
        serverSocket = new ServerSocket(port);
        while (true) {
        Socket socket  = serverSocket.accept();
        MyResponse myResponse = new MyResponse(socket.getOutputStream());
        MyRequest myRequest = new MyRequest(socket.getInputStream());
        this.dispatch(myRequest,myResponse);
        socket.close();}
    }

    private void dispatch(MyRequest myRequest, MyResponse myResponse) throws Exception {
        String cls = this.urlClassMap.get(myRequest.getUrl());
        if (cls ==null) return;
        Class clss = Class.forName(cls);
        TinyabstractServlet tinyabstractServlet = (TinyabstractServlet) clss.newInstance();
        tinyabstractServlet.service(myRequest,myResponse);

    }

    private void init() {
        Config.list.add(new ServletMap("/grep","com.tomcat.impl.TinyServlet","tinyservlet"));
        this.urlClassMap.putAll(Config.list.stream().collect(toMap(x->x.getUrl(), y->y.getClasssaz())));

    }

    public static void main(String[] args) throws Exception {
        new TinyTomcat(8888).start();
    }
}
