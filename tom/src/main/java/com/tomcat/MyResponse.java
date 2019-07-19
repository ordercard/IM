package com.tomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 01:01 2019-07-20 2019
 * @Modify:
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
    /*
    执行 写
     */
    public void write(String content) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HTTP/1.1 200 OK\n");
        stringBuffer.append("Content-Type: text/html\n");
        stringBuffer.append("\r\n").append("<html><head><title>tiny</title></head><body>")
                .append(content).append("</body><html>");
        this.outputStream.write(stringBuffer.toString().getBytes());
        this.outputStream.close();


    }
}
