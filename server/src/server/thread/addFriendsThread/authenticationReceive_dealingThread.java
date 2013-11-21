package server.thread.addFriendsThread;

import java.io.ObjectInputStream;
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

import common.message.authentication;
import common.message.node_public;
import common.message.systemMessage;
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
public class authenticationReceive_dealingThread extends Thread
{
	Socket			client;
	Statement		state1;
	Statement		state2;
	authentication	au;

	public authenticationReceive_dealingThread(Socket client) throws SQLException
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
			au = (authentication) oin.readObject();
			String myqqNum = au.myqqNum;
			String qq = au.qq;
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date())+"："+"收到" + au.qq + "的验证信息\n");
			if (serverMain.map_onlineInfo.get(qq) != null)
			{
				String sql_adderInfo = "select nickname,age,sex,country,province,city from mainInfo where qq = '" + myqqNum + "' ;";
				ResultSet res_Info = state1.executeQuery(sql_adderInfo);
				res_Info.next();
				sendSystemMessageThread sender = new sendSystemMessageThread(qq, new systemMessage(2, myqqNum, res_Info.getString("nickname"), res_Info.getInt("age"), res_Info.getString("sex"),
						res_Info.getString("country"), res_Info.getString("province"), res_Info.getString("city"), null, au.authentication));
			}
			else
			{
				String sql_judge = "select * from authentication_" + qq + " where  qq = '" + myqqNum + "';";
				ResultSet res_judge = state2.executeQuery(sql_judge);
				if (res_judge.next())
				{
					String sql_updateAuthentication = "update authentication_" + qq + " set authentication = '" + au.authentication + "' where qq =  '" + myqqNum + "' ;";
					state2.execute(sql_updateAuthentication);
				}
				else
				{
					String sql_insertAuthentication = "insert into authentication_" + qq + " values ('" + myqqNum + "','" + au.authentication + "') ";
					state2.execute(sql_insertAuthentication);
				}
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date())+"："+au.qq + "的验证信息已加入数据库！\n");
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
