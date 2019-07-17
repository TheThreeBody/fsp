package com.webside.ip2region;

import com.zhengtong.fsp.commons.utils.iP2RegionUtil;
import org.junit.Test;


/**
 * project test script
 * 
 * @author DongWen
*/
public class TestUtil 
{
	
	@Test
    public void testUtil()
    {
/*        //1. test the ip2long
        String[] ipSet = new String[]{
            "120.24.78.68", 
            "120.24.229.68", 
            "120.24.87.145", 
            "218.17.162.99"
        };
        
        for ( String ip : ipSet )
        {
            int ipInt = iP2RegionUtil.ip2Int(ip);
            System.out.println("src ip: " + ip + ", ip2Int: " + ipInt + ", int2IP: " + iP2RegionUtil.int2IP(ipInt));
        }*/
        
/*        int[] arr = new int[]{12344, -1234, 2146789, 0, -1024};
        byte[] b = new byte[arr.length * 4];
        
        //write the int
        System.out.println("+--Testing writeInt ... ");
        int i, idx = 0;
        for ( i = 0; i < b.length; i += 4 )
        {
            System.out.println("offset: " + i);
            iP2RegionUtil.writeInt(b, i, arr[idx++]);
        }
        System.out.println("|----[Ok]");
        
        //read the int
        System.out.println("+--Testing getInt ... ");
        idx = 0;
        for ( i = 0; i < b.length; i += 4 )
        {
            System.out.println(arr[idx++]+", " + iP2RegionUtil.getInt(b, i));
        }
        System.out.println("|----[Ok]");*/
        
/*        HeaderBlock headerBlock = new HeaderBlock(241658345, 2134785);
        byte[] b = headerBlock.getBytes();
        System.out.println(headerBlock.getIndexStartIp() + ", " + headerBlock.getIndexPtr());
        System.out.println(iP2RegionUtil.getInt(b, 0) + ", " + iP2RegionUtil.getInt(b, 4));*/
        
        System.out.println(iP2RegionUtil.ip2long("255.255.255.0"));
    }
}
