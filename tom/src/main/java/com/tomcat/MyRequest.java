package com.tomcat;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Auther :huiqiang
 * @Description :
 * @Date: Create in 23:22 2019-07-19 2019
 * @Modify:
 */
@Data
public class MyRequest {
    InputStream inputStream;
    private String url;
    private String method;
    protected  String body;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest;
        int length;
        byte[] bytes = new byte[2048];
        if ((length = inputStream.read(bytes))>0) {
            httpRequest = new String(bytes, Charset.forName("utf-8"));

        } else {
            throw new RuntimeException();
        }
         this.method = httpRequest.split("\\s")[0];
        this.url = httpRequest.split("\\s")[1];
        this.body = httpRequest.split("\\r\\n\\r\\n")[1];

    }


// \\|  \\( \\n这类在正则中主要是配到控制字符   \\.
    //先\\是先拿到能表示的转义符号\\   比如跟上. 就是让.原义匹配，因为.这个字符在正则表达式中有特殊的用法
    //  \: 将下一个字符标记为或特殊字符、或原义字符、或向后引用、或八进制转义符。


    public static void main(String[] args) {
        String t = "a||b||c||d";
        String tt = "a(b";
        System.out.println(tt.replaceAll("\\(","s"));
        String[] temp = t.split("\\|");
        System.out.println("\n");
        System.out.println(temp.length);
        String str = "Java转义字符(补.遗)";
      String[]  strs = str.split("\\\\");
        System.out.println(strs.length);
    }
}
