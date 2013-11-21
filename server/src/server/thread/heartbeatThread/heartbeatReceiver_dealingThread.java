package server.thread.heartbeatThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import server.serverMain.serverMain;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.*;
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
public class heartbeatReceiver_dealingThread extends Thread
{
	String	ip	= "";
	String	qq	= "";
	Socket	client;

	public heartbeatReceiver_dealingThread(Socket s_client) throws SQLException
	{
		super();
		this.ip = s_client.getInetAddress().toString().substring(1);
		this.client = s_client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			testMessage tmessage = (testMessage) oin.readObject();
			qq = tmessage.qq;
			serverMain.map_date.put(qq, new Date());
			if ((serverMain.map_status.get(qq) == null && tmessage.sign != 0) || (serverMain.map_status.get(qq) != null && serverMain.map_status.get(qq) != tmessage.sign))
			{
				Integer status_t = serverMain.map_status.put(qq, tmessage.sign);
				if (tmessage.sign == 0 && status_t != null)
				{
					serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "下线了\n");
					serverMain.map_status.remove(qq);
					serverMain.map_date.remove(qq);
					serverMain.map_onlineInfo.remove(qq);
					String sql = "update mainInfo set status = 0 where qq = '" + qq + "';";
					Statement state1 = serverMain.con1.createStatement();
					state1.execute(sql);
					state1.close();
				}
				sendSystemMessageThread warning = new sendSystemMessageThread(qq, tmessage.sign);
			}
			client.close();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			System.out.println("发送该心跳包的qq，意外掉线，超出检测的周期——来自heartbeatReceiver_dealingThread:" + this.getName());
		}
	}
}
