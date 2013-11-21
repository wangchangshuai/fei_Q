package server.thread.heartbeatThread;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import server.serverMain.serverMain;

import common.message.node_public;
/**
 * 2011年10月
 * 
 * 山东科技大学信息学院  版权所有
 * 
 * 联系邮箱：415939252@qq.com
 * 
 * Copyright © 1999-2012, sdust, All Rights Reserved
 * 
 * @author 王昌帅
 *
 */
public class judgeAliveThread extends Thread
{
	public judgeAliveThread() throws SQLException
	{
		start();
	}

	public void run()
	{
		try
		{
			while (true)
			{
				Iterator iter = serverMain.map_date.keySet().iterator();
				while (iter.hasNext())
				{
					String key = (String) iter.next();
					Date val = serverMain.map_date.get(key);
					long n = (new Date()).getTime() - val.getTime();
					if (n > 13000)
					{
						aliveMessageSenderThread heartbeatSender = new aliveMessageSenderThread(key);
						judgeSecondThread judge = new judgeSecondThread(key, heartbeatSender);
					}
				}
				sleep(7000);
			}
		}
		catch (Exception e)
		{
			run();
			e.printStackTrace();
		}
	}
}
