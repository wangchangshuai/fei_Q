package server.thread.heartbeatThread;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import common.message.node_public;

import server.serverMain.serverMain;
import server.thread.systemMessageThread.sendSystemMessageThread;
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
public class judgeSecondThread extends Thread
{
	aliveMessageSenderThread	heart;
	String						qq;

	public judgeSecondThread(String qq, aliveMessageSenderThread heartbeatSender) throws SQLException
	{
		this.heart = heartbeatSender;
		this.qq = qq;
		start();
	}

	public void run()
	{
		try
		{
			sleep(3000);
			if (heart.changed == 0 || heart.changed == 2)
			{
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "连接不上，大约已意外掉线！\n");
				serverMain.map_status.remove(qq);
				serverMain.map_date.remove(qq);
				serverMain.map_onlineInfo.remove(qq);
				String sql = "update mainInfo set status = 0 where qq = '" + qq + "';";
				Statement state1 = serverMain.con1.createStatement();
				state1.execute(sql);
				state1.close();
				sendSystemMessageThread warning = new sendSystemMessageThread(qq, 0);
			}
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
