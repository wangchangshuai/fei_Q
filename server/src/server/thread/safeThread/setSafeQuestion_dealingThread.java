package server.thread.safeThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextPane;

import server.serverMain.serverMain;
import server.thread.safeThread.*;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.node_public;
import common.message.safeQuestionAnswer;
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
public class setSafeQuestion_dealingThread extends Thread
{
	Socket				client;
	Statement			state2;
	safeQuestionAnswer	safe;

	public setSafeQuestion_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state2 = serverMain.con2.createStatement();
		this.client = s_client;
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			safe = (safeQuestionAnswer) oin.readObject();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + safe.qq + "开始设置密保问题......\n");
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			String sql_del = "delete from safeQuestion_" + safe.qq + ";";
			state2.execute(sql_del);
			String sql_update1 = "insert into safeQuestion_" + safe.qq + " values ('" + safe.question1 + "','" + safe.answer1 + "'); ";
			String sql_update2 = "insert into safeQuestion_" + safe.qq + " values ('" + safe.question2 + "','" + safe.answer2 + "'); ";
			String sql_update3 = "insert into safeQuestion_" + safe.qq + " values ('" + safe.question3 + "','" + safe.answer3 + "');";
			state2.execute(sql_update1);
			state2.execute(sql_update2);
			state2.execute(sql_update3);
			String text = "您的密保已设置成功，当您忘记密码时可通过回答密保问题找回密码。";
			sendSystemMessageThread sendeSys = new sendSystemMessageThread(safe.qq, text);
			oout.writeObject(new testMessage());// 此处意思是成功
			serverMain.textPane2.setText(serverMain.textPane2.getText() + safe.qq + "设置密保问题完成！\n");
			state2.close();
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
