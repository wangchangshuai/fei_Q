package server.thread.getInfoThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import server.serverMain.serverMain;

import common.message.personalInfo;
import common.message.qq_num;
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
public class getInfo_dealingThread extends Thread
{
	Statement		state;
	Socket			client;
	personalInfo	pInfo;
	qq_num			message;

	public getInfo_dealingThread(Socket client) throws SQLException
	{
		this.state = serverMain.con1.createStatement();
		this.client = client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			message = (qq_num) oin.readObject();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + client.getInetAddress() + "查询" + message.qq + "的资料\n");
			String sql_main = "select * from mainInfo where qq = '" + message.qq + "';";
			ResultSet res_main = state.executeQuery(sql_main);
			if (res_main.next())
			{
				pInfo = new personalInfo(res_main.getString("qq"), res_main.getString("nickname"), res_main.getString("sex"), res_main.getInt("age"), res_main.getInt("birthday_year"),
						res_main.getInt("birthday_month"), res_main.getInt("birthday_day"), res_main.getString("animal"), res_main.getString("constellation"), res_main.getString("bloodType"),
						res_main.getString("country"), res_main.getString("province"), res_main.getString("city"), res_main.getInt("headImage"), res_main.getInt("status"),
						res_main.getString("signature"), res_main.getString("phoneNumber"), res_main.getString("collage"), res_main.getString("personalInfo"), res_main.getString("language"),
						res_main.getString("occupation"), null);
				ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(pInfo);
			}
			state.close();
			client.close();
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
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
