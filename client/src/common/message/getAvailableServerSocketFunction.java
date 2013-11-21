package common.message;

import java.io.IOException;
import java.net.ServerSocket;
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
public class getAvailableServerSocketFunction
{
	public static ServerSocket getServerSocket(int port)
	{
		ServerSocket s = null;
		try
		{
			s = new ServerSocket(port);
			return s;
		}
		catch (IOException e)
		{
			return getServerSocket(port + 1);
		}
	}
}
