package com.zhengtong.fsp.manager.pojo.common;

import org.springframework.beans.factory.annotation.Value;

public class ApiConstant {

//	@Value("${jd.loan.ip}")
//	public static String COMMON;
	public static String COMMON = "http://10.138.60.68:7979/Service/SYH/SYHLoan/";

	public static String SYHReSubmitLoan_URL = COMMON + "SYHReSubmitLoan";

	public static String SYHCancelContract_URL = COMMON + "SYHCancelContract";
}
