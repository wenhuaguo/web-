package ajax.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Acer on 2016/12/6.
 */
public class HttpUtils {
    public static String httpCilentServlet(String url) throws IOException {
        //1/创建客户端对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建请求方式和请求地址
        HttpGet httpGet = new HttpGet(url);
        try {
            //3.发送请求并返回请求
            HttpResponse response = httpClient.execute(httpGet);
            //4.获取状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //5.根据状态码确定响应
            InputStream in = null;
            if (statusCode == 200) {
                //6.获得响应输入流
                in = response.getEntity().getContent();
                String out = IOUtils.toString(in,"UTF-8");//将输入流转换为字符流
                httpClient.close();
                return out;
            } else {
                throw new RuntimeException("请求" + url + "异常" + statusCode);
            }


        }catch (IOException io){
            throw new RuntimeException("请求" + url + "异常",io);
        }
    }
}
