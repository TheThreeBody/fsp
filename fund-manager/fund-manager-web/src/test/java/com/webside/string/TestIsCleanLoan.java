package com.webside.string;

import com.npsex.fsp.commons.utils.HttpClientUtil;
import org.junit.Test;

/**
 * Created by dongwen on 2017/7/27.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class TestIsCleanLoan {

    @Test
    public void isClean(){
        String s = HttpClientUtil.doPostJson("http://10.1.12.51:10001/V3SBaseService/PostLoan/QueryBusinessStatusAddXYH","{\"BusinessIDList\":[5077176]}");
        System.out.println(s);
    }

    public static void main(String[] args) {
//        String s = HttpClientUtil.doPostJson("http://10.1.12.51:10001/V3SBaseService/PostLoan/QueryBusinessStatusAddXYH","{\"BusinessIDList\":[5077176]}");
//        String s = HttpClientUtil.doPostJson("http://mysql.npsex.com:8083/esurfing/list.html","");
        String s = HttpClientUtil.doPostJson("http://mysql.npsex.com:8083/syqrs/generateSyqrs.html","{}");
        System.out.println(s);
    }
}
