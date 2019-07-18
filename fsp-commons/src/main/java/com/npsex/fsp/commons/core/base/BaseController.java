package com.npsex.fsp.commons.core.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @ClassName: BaseController
 * @Description: controller基类,目前功能比较简单
 * @date 2016年7月12日 下午3:02:14
 *
 */
public abstract class BaseController {
	
	public Logger logger = LogManager.getLogger(this.getClass());

}