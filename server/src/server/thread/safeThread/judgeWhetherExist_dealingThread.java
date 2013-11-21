package server.thread.safeThread;

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
import common.message.safeQuestionOrAnswer;
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
public class judgeWhetherExist_dealingThread extends Thread
{
	Socket					client;
	Statement				state2;
	safeQuestionOrAnswer	safe;
	testMessage				test;
	public int				changed	= 0;

	public judgeWhetherExist_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state2 = serverMain.con2.createStatement();
		this.client = s_client;
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			test = new testMessage((testMessage) oin.readObject());
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + test.qq + "开始验证有无密保问题......\n");
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			String returnQuestion = "select * from safeQuestion_" + test.qq + ";";
			ResultSet res_return = state2.executeQuery(returnQuestion);
			if (res_return.next())
			{
				changed = 2;
				String text1 = res_return.getString("question");
				res_return.next();
				String text2 = res_return.getString("question");
				res_return.next();
				String text3 = res_return.getString("question");
				oout.writeObject(new safeQuestionOrAnswer(null, text1, text2, text3));
				oout.close();
				serverMain.textPane2.setText(serverMain.textPane2.getText() + test.qq + "有无密保问题！\n");
			}
			else
			{
				oout.writeObject(null);
				changed = 1;
				serverMain.textPane2.setText(serverMain.textPane2.getText() + test.qq + "无密保问题！\n");
			}
			client.close();
			state2.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
