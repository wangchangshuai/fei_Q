package client.thread.chatWithAnotherQQThread;

import Windows_MainInterface.MainInterface;

import common.message.qq_num;
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
public class getAnotherQQIpPortThread_Thread extends Thread
{
	String	qq_temp	= null;

	public getAnotherQQIpPortThread_Thread(String qq)
	{
		this.qq_temp = qq;
		start();
	}

	public void run()
	{
		try
		{
			getAnotherQQIpPortThread get_ipPort = new getAnotherQQIpPortThread(new qq_num(qq_temp));
			get_ipPort.join(10000);
			if (get_ipPort.iport != null)
			{
				MainInterface.map_ipPort.put(qq_temp, get_ipPort.iport);
			}

		}
		catch (Exception e)
		{
			getAnotherQQIpPortThread_Thread get_loop = new getAnotherQQIpPortThread_Thread(qq_temp);
			e.printStackTrace();
		}
	}
}
