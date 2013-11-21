package common;

import java.text.*;
import java.util.*;
/**
 * 2011年10月
 * 
 * 山东科技大学信息学院  版权所有
 * 
 * 联系邮箱：415939252@qq.com
 * 
 * Copyright © 1999-2012, sdust, All Rights Reserved
 * 
 * @author 王昌帅，司吉峰，王松松 （计算机2009-5、6班）
 *
 */
public final class time
{
	public static final String gettime()
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = s.format(c.getTime());
		return date;
	}
}
