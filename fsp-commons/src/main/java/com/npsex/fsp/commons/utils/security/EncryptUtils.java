package com.npsex.fsp.commons.utils.security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.util.Arrays;

/**
 * 加密、解密 工具类
 * Created by dongwen on 2016/12/9.
 */
public class EncryptUtils {
    //*******************************************  AES 加解密    ******************************************************/
    //**  AES可以使用128、192、和256位密钥，并且用128位分组加密和解密数据，JRE默认只能用16个字节(128)位密钥， 需要在 **/
    //**  Oracle官方网站上下载无政策限制权限文件（Unlimited Strength Jurisdiction Policy Files）覆盖jre>lib>security **/
    //**  目录下jar包                                                                                                **/
    //*****************************************************************************************************************/
    private static Cipher cipher = null; // 私鈅加密对象Cipher

    static {
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }


    /**
     * AES加密
     *
     * @param message
     * @return
     */
    public static byte[] encryptByAES(String message, String passWord) {
        try {
            /* AES算法 */
            SecretKey secretKey = new SecretKeySpec(passWord.getBytes(), "AES");// 获得密钥
            /* 获得一个私鈅加密类Cipher，DESede->AES算法，ECB是加密模式，PKCS5Padding是填充方式 */
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 设置工作模式为加密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
            return resultBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param messageBytes
     * @param passWord
     * @return
     */
    public static String decryptByAES(byte[] messageBytes, String passWord) {
        String result = "";
        try {
            /* AES算法 */
            SecretKey secretKey = new SecretKeySpec(passWord.getBytes(), "AES");// 获得密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKey); // 设置工作模式为解密模式，给出密钥
            byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
            result = new String(resultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //*******************************************  3DES 加解密    ******************************************************/
    //**                                                                                                              **/
    //******************************************************************************************************************/
    private static final String ALGORITHM_MD5 = "md5";

    private static final String ALGORITHM_DESEDE = "DESede";

    private static final String CIPHER_TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    private static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 3DES加解密
     *
     * @param message origin message
     * @param sKey    origin privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByDES(String message, String sKey) throws Exception {
        final byte[] keyBytes = getKeyBytes(sKey);

        final SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM_DESEDE);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);
        final byte[] cipherText = cipher.doFinal(plainTextBytes);

        return cipherText;
    }

    /**
     * decode from encoded message
     *
     * @param message encoded message
     * @param sKey    origin privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByDES(byte[] message, String sKey) throws Exception {
        final byte[] keyBytes = getKeyBytes(sKey);

        final SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM_DESEDE);
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
        decipher.init(Cipher.DECRYPT_MODE, key, iv);

        final byte[] plainText = decipher.doFinal(message);
        return new String(plainText, CHARSET_UTF_8);
    }

    /**
     * generate keyBytes
     *
     * @param sKey origin privateKey
     * @return
     * @throws Exception
     */
    private static byte[] getKeyBytes(String sKey) throws Exception {
        final MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
        final byte[] digestOfPassword = md.digest(sKey.getBytes(CHARSET_UTF_8));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8; ) {
            keyBytes[k++] = keyBytes[j++];
        }
        return keyBytes;
    }
    //*******************************************  RSA 加解密    ******************************************************/
    //**  RSA非对称加密算法。用法：1公钥加密(C)，私钥解密(S)；2私钥加密(S)，公钥解密(C)                              **/
    //*****************************************************************************************************************/

    /**
     * 指定加密算法为RSA
     */
    private static String ALGORITHM = "RSA";

    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 1024;

    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "PublicKey";

    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    /**
     * 生成密钥对
     */
    private static void generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        /** 清空缓存，关闭文件输出流 */
        oos1.close();
        oos2.close();
    }

    /**
     * 加密方法 source： 源数据
     */
    public static String encryptByRSA(String source) throws Exception {
        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64.encode(b1);
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public static String decryptByRSA(String cryptograph) throws Exception {
        /** 将文件中的私钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        Key key = (Key) ois.readObject();
        ois.close();
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decode(cryptograph);
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    //*******************************************  测试类    **********************************************************/
    public static void main(String[] args) throws Exception {
        //****************** AES 加密测试  ******************
        System.out.println("AES加解密测试开始：>>>>> ");

        String password = "c8a9229820ffa315bc6a17a9e43d01a9";
        String content = "6222001521522152212";
        // AES加密（传输)
        System.out.println("加密前：" + content);
        byte[] encryptResult = encryptByAES(content, password);

        // 以HEX进行传输
        String codedtextb = Base64.encode(encryptResult);// data transfer as text
        System.out.println("Base64 format:" + codedtextb);
        encryptResult = Base64.decode(codedtextb);

        // AES解密
        String decryptResultb = decryptByAES(encryptResult, password);
        System.out.println("解密后：" + decryptResultb);
        System.out.println("AES加解密测试结束：<<<<< ");

        //****************** DES 加密测试  ******************
        System.out.println("DES加解密测试开始：>>>>> ");

        final String privateKey = "HG58YZ3CR9HG58YZ3CR9HG58YZ3CR9";

        String text = "hello world!";// origin data
        System.out.println("加密前:" + text);
        byte[] codedtext = encryptByDES(text, privateKey);

        codedtextb = Base64.encode(codedtext);// data transfer as text
        System.out.println("Base64 format:" + codedtextb);
        codedtext = Base64.decode(codedtextb);

        String decodedtext = decryptByDES(codedtext, privateKey);
        System.out.println("解密后:" + decodedtext); // This correctly shows "hello world!"
        System.out.println("DES加解密测试结束：<<<<< ");

        //****************** RSA 加密测试  ******************
        System.out.println("RSA加解密测试：");

        generateKeyPair();

        final String source = "73C58BAFE578C59366D8C995CD0B9";// 要加密的字符串
        System.out.println("加密前:" + source);

        String cryptograph = encryptByRSA(source);// 生成的密文
        System.out.println("Base64 format:" + cryptograph);

        String target = decryptByRSA(cryptograph);// 解密密文
        System.out.println("解密后:" + target);
    }
}
