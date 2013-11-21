package server.thread.addFriendsThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextPane;

import server.serverMain.serverMain;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.addMessage;
import common.message.node_public;
import common.message.systemMessage;
import common.message.testMessage;
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
public class add_dealingThread extends Thread
{
	Statement	state1;
	Statement	state2;
	Socket		client;
	addMessage	message	= new addMessage("", "");

	public add_dealingThread(Socket client) throws SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.state2 = serverMain.con2.createStatement();
		this.client = client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			message = new addMessage((addMessage) oin.readObject());
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date())+"："+message.qq + "开始添加好友......\n");
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			String sql_find = "select can from whetherCanAdd where qq = " + message.qq + "  ;";
			ResultSet res_find = state1.executeQuery(sql_find);
			res_find.next();
			int judge = res_find.getInt("can");
			switch (judge)
			{
			case 1:
				String sql_insertFriendsList = "insert into friendsList_" + message.myqqNum + " values (" + message.qq + ",null,'好友') ;";
				String sql_createHistoryTable = "create table history_" + message.myqqNum + "_" + message.qq + " (time varchar(20),speeker nvarchar(20),text nvarchar(500)) ;";
				state2.execute(sql_insertFriendsList);
				state2.execute(sql_createHistoryTable);
				String sql_insertFriendsList1 = "insert into friendsList_" + message.qq + " values (" + message.myqqNum + ",null,'好友') ;";
				String sql_createHistoryTable1 = "create table history_" + message.qq + "_" + message.myqqNum + " (time varchar(20),speeker nvarchar(20),text nvarchar(500)) ;";
				state2.execute(sql_insertFriendsList1);
				state2.execute(sql_createHistoryTable1);
				oout.writeObject(new testMessage(1));
				break;
			case 2:
				oout.writeObject(new testMessage(2));
				break;
			case 0:
				oout.writeObject(new testMessage(0));
				break;
			}
			client.close();
			state1.close();
			state2.close();
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
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
