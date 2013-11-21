package server.thread.addFriendsThread;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextPane;

import common.message.agreeRefuse;
import common.message.node_public;
import common.message.systemMessage;
import server.serverMain.serverMain;
import server.thread.systemMessageThread.*;
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
public class receive_dealingAgreeRefuseThread extends Thread
{
	Socket		client;
	Statement	state1;
	Statement	state2;
	agreeRefuse	agreement;

	public receive_dealingAgreeRefuseThread(Socket client) throws SQLException
	{
		this.client = client;
		this.state1 = serverMain.con1.createStatement();
		this.state2 = serverMain.con2.createStatement();
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			agreement = new agreeRefuse((agreeRefuse) oin.readObject());
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + agreement.qq + "发送同意或拒绝信息！\n");
			// 根据同意与否组织语言
			String text = null;
			systemMessage sysm = new systemMessage();;
			if (agreement.sign == 1) // 假如同意
			{
				String sql_select2 = "select nickname,age,sex,country,province,city from mainInfo where qq = '" + agreement.myqqNum + "' ;";;
				ResultSet selectInfo = state1.executeQuery(sql_select2);
				selectInfo.next();
				sysm = new systemMessage(4, agreement.myqqNum, selectInfo.getString("nickname"), selectInfo.getInt("age"), selectInfo.getString("sex"), selectInfo.getString("country"),
						selectInfo.getString("province"), selectInfo.getString("city"), null, agreement.refuseReason);
				String nick2 = selectInfo.getString("nickname");
				text = new String("用户 " + nick2 + "(" + agreement.myqqNum + ") 同意了您的添加好友请求！");
				String sql_insertFriendsList2 = "insert into friendsList_" + agreement.myqqNum + " values ('" + agreement.qq + "',null,'我的好友') ;";
				String sql_createHistoryTable2 = "create table history_" + agreement.myqqNum + "_" + agreement.qq + " (time varchar(20),speeker varchar(20),text varchar(500)) ;";
				state2.execute(sql_insertFriendsList2);
				state2.execute(sql_createHistoryTable2);
				String sql_insertFriendsList3 = "insert into friendsList_" + agreement.qq + " values ('" + agreement.myqqNum + "',null,'我的好友') ;";
				String sql_createHistoryTable3 = "create table history_" + agreement.qq + "_" + agreement.myqqNum + " (time varchar(20),speeker varchar(20),text varchar(500)) ;";
				state2.execute(sql_insertFriendsList3);
				state2.execute(sql_createHistoryTable3);
			}
			else if (agreement.sign == 0)// 假如拒绝
			{
				text = new String("用户 " + "(" + agreement.myqqNum + ") 拒绝了您的添加好友请求！\n拒绝理由为：" + agreement.refuseReason);
			}
			// 根据在线用户中是否存在该qq来判断被同意者是否在线
			if (serverMain.map_status.get(agreement.qq) != null) // 假如被同意者在线
			{
				sysm.message = new String(text);
				sendSystemMessageThread sender = new sendSystemMessageThread(agreement.qq, sysm, 1);
			}
			else
			// 假如被同意者不在线
			{
				String sql_insertSystemMessage = "update systemMessage set warning = '" + text + "' where qq = '" + agreement.qq + "';";
				state1.execute(sql_insertSystemMessage);
			}
			state1.close();
			state2.close();
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}
}
