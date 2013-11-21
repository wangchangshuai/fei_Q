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
import java.util.HashMap;

import server.serverMain.serverMain;

import common.message.findPassword;
import common.message.safeQuestionOrAnswer;
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
public class receiveAnswer_dealingThread extends Thread
{
	Socket					client;
	Statement				state2;
	safeQuestionOrAnswer	safe;
	public String			qq		= "";	// 仅在设置密保时使用
	public int				changed	= 0;

	public receiveAnswer_dealingThread(Socket client) throws IOException, SQLException
	{
		this.state2 = serverMain.con2.createStatement();
		this.client = client;
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			safe = new safeQuestionOrAnswer((safeQuestionOrAnswer) oin.readObject());
			qq = safe.qq;
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "开始验证密保答案是否正确......\n");
			String sql_selectAnswer = "select answer from safeQuestion_" + qq + ";";
			ResultSet res_select = state2.executeQuery(sql_selectAnswer);
			res_select.next();
			String answer1 = res_select.getString("answer");
			res_select.next();
			String answer2 = res_select.getString("answer");
			res_select.next();
			String answer3 = res_select.getString("answer");
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			if (answer1.equals(safe.text1) && answer2.equals(safe.text2) && answer3.equals(safe.text3))
			{
				oout.writeObject(new findPassword(serverMain.map_array_passwordMap.get(qq)));
				changed = 1;
				serverMain.textPane2.setText(serverMain.textPane2.getText() + qq + "密保答案正确！\n");
			}
			else
			{
				oout.writeObject(null);
				changed = 2;
				serverMain.textPane2.setText(serverMain.textPane2.getText() + qq + "密保答案错误！\n");
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
