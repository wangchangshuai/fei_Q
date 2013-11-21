package server.thread.systemSettingsThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import server.serverMain.serverMain;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.mainInfo;
import common.message.node_public;
import common.message.systemSettings;
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
public class setting_dealingThread extends Thread
{
	Socket			client;
	systemSettings	settings;
	Statement		state1;

	public setting_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.client = s_client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			settings = (systemSettings) oin.readObject();
			String sql_update = "update whetherCanAdd set can = " + settings.whetherCanAdd + " where qq = '" + settings.qq + "';";
			state1.execute(sql_update);
			state1.close();
			String text = new String("您的设置已生效");
			sendSystemMessageThread sender = new sendSystemMessageThread(settings.qq, text);
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
