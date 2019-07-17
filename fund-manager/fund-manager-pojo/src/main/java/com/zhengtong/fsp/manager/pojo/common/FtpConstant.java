package com.zhengtong.fsp.manager.pojo.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by songhuiping on 2017/8/1.
 */
public class FtpConstant {

    /**
     * ftp地址
     */

    @Value("${ftp.ip}")
    public static String FTP_IP;
//    public static String FTP_IP = "10.138.61.18";
    /**
     * ftp用户名
     */
    @Value("${ftp.username}")
    public static String FTP_USERNAME;
//    public static String FTP_USERNAME = "coop";

    /**
     * ftp用户密码
     */
    @Value("${ftp.pwd}")
    public static String FTP_PWD;
//    public static String FTP_PWD = "coop";

    /**
     * ftp端口号
     */
    @Value("${ftp.port}")
    public static String FTP_PORT;
//    public static String FTP_PORT = "21";

}
