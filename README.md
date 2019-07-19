# add tinyTomcat 
 if you  sent get request /grep
 this will  return yur i love you
 
 ---Myrequst
 ---Myrresponse
 wrap socket.getInputStream and outPutStream  deal with   http request message 
 
 
 for example
 
 GET /grep HTTP/1.1
Host: localhost:8888
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
Cookie: Idea-d1ba6153=4863a315-4d27-47d0-8091-bbf2dba3ba24; JSESSIONID=node0xgfs6mwugkrmwzsjxextxcxb2.node0


 
 
 
---c\
  servletMap
  Config
  TingTomcat
  use reflection   sockct  to get instance deal with message and  return  a message
  
  
  
  
