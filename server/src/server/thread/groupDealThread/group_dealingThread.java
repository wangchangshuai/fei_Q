package server.thread.groupDealThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import server.serverMain.serverMain;

import common.message.groupDeal;
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
public class group_dealingThread extends Thread
{
	Socket		client;
	Statement	state2;
	groupDeal	deal;

	public group_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state2 = serverMain.con2.createStatement();
		this.client = s_client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			deal = new groupDeal((groupDeal) oin.readObject());
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + deal.qq + "对组进行操作！\n");
			if (deal.sign == 1)
			{
				String sql_update = "insert into groupsList_" + deal.qq + "  values ('" + deal.group + "') ;";
				state2.execute(sql_update);
			}
			else if (deal.sign == 2)
			{
				if (deal.sign2 == 1)
				{
					String sql_deleteGroup = "delete from  groupsList_" + deal.qq + "  where groupName = '" + deal.group + "' ;";
					String sql_updatefriends = "update friendsList_" + deal.qq + " set groupName = '我的好友' where groupName = '" + deal.group + "' ;";
					state2.execute(sql_updatefriends);
					state2.execute(sql_deleteGroup);
				}
				else if (deal.sign2 == 2)
				{
					String sql_deleteGroup = "delete from  groupsList_" + deal.qq + "  where groupName = '" + deal.group + "' ;";
					String sql_deletefriends = "delete from friendsList_" + deal.qq + " where groupName = '" + deal.group + "' ;";
					state2.execute(sql_deletefriends);
					state2.execute(sql_deleteGroup);
				}
			}
			else if (deal.sign == 3)
			{
				String sql_update = "update groupsList_" + deal.qq + " set groupName = '" + deal.newGroup + "' where groupName = '" + deal.group + "' ;";
				String sql_updatefriends = "update friendsList_" + deal.qq + " set groupName = '" + deal.newGroup + "' where groupName = '" + deal.group + "' ;";
				state2.execute(sql_updatefriends);
				state2.execute(sql_update);
			}
			state2.close();
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
