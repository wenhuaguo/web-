package com.kaishengit.util;



import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Acer on 2016/12/16.
 * 发送电子邮件
 */
public class EmailUtils {
    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    public static void sendEmail(String address,String subject,String content){
        //发送电子邮件需要知道的东西
        //1.邮件服务器地址借助commons emails 包
        HtmlEmail email = new HtmlEmail();//首先email对象分为三种类型（.txt 通过 new SimpleEmial()创建Email对象）
        //html类型通过new HtmlEmail()创建 HtmlEmail对象
        //附件类型
        email.setHostName(ReadConfig.get("email.smtp"));//邮件服务器地址
        //2.邮件服务器端口号
        email.setSmtpPort(Integer.valueOf(ReadConfig.get("email.port")));
        //还需要证明验证
        email.setAuthentication(ReadConfig.get("email.user"),ReadConfig.get("email.password"));
        //3.是否需要验证（一般为true）
        email.setStartTLSEnabled(true);

        try {
            //4.发件人地址
            email.setFrom(ReadConfig.get("email.fromaddress"));//邮件发送人地址
            //5.发件人账号
            //6.发件人密码

            email.setCharset("UTF-8");//处理发送内容有中文乱码的问题
            //8.邮件主题
            email.setSubject(subject);
            //9.邮件的内容
            email.setHtmlMsg(content);
            //7.接件人账号
            email.addTo(address);
            //邮件发送
            email.send();
        } catch (EmailException e) {
            logger.error("向{}发送邮件失败",address);
            throw new RuntimeException("发送邮件失败",e);
        }
    }




}
