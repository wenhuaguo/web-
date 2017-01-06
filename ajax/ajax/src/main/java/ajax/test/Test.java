package ajax.test;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2016/12/6.
 */
public class Test {

//    //解决跨域问题代理模式
   public static void main(String[] args) throws IOException {
       //创建一个客户端
       CloseableHttpClient httpClient = HttpClients.createDefault();
       //创建一个请求方式和请求地址
       HttpPost httpPost = new HttpPost("http://localhost:8080/save");
       List<NameValuePair> list = new ArrayList<>();
       list.add(new BasicNameValuePair("userName","jack"));
       list.add(new BasicNameValuePair("password","123123"));
       httpPost.setEntity(new UrlEncodedFormEntity(list));
       //发送请求
       for(int i = 0 ; i < 10; i++) {
           httpClient.execute(httpPost);
       }
        httpClient.close();
//        //1.创建一个可以发送请求的客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //2.请求方式的创建和传递一个请求的服务器地址
//        HttpGet httpGet = new HttpGet("http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg");
//        //3.发送请求并接受服务端的响应
//        HttpResponse response = httpClient.execute(httpGet);
//        //4.获取响应的状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        if(statusCode == 200){
//            //5.创建一个响应输入流
//            InputStream in = response.getEntity().getContent();
//            //5.将响应输入流转换为字节流
//            OutputStream outputStream = new FileOutputStream("D:/xx.jpg");
//            IOUtils.copy(in,outputStream);
//        }else {
//            System.out.println("服务器异常" + statusCode);
//        }
//        //7.关闭请求客户端
//        httpClient.close();
//        //让服务端伪装成客户端向别的服务器端获得请求
//        //1.创建一个可以发送请求的客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //2.请求方式方式的创建,并将请求地址放进去
//        HttpGet httpGet = new HttpGet("http://www.kaishengit.com");
//        //3.发出请求，并接受服务器端的相应
//        HttpResponse response = httpClient.execute(httpGet);
//        //4.获取Http响应的状态码
//        int statusCode = response.getStatusLine().getStatusCode();
//        //5.获取输入流
//        if(statusCode == 200){
//            //获取响应输入流
//            InputStream in = response.getEntity().getContent();
//            //将响应输入流转换为字符流
//            String result = IOUtils.toString(in);
//            System.out.println(result);
//
//        }else {
//            System.out.println("服务器异常" + statusCode);
//        }
//        //5.关闭服务端的请求
//        httpClient.close();
    }
}
