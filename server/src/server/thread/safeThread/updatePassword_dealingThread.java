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
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.node_public;
import common.message.testMessage;
import common.message.updatePassword;
import common.message.updateRemark;
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
public class updatePassword_dealingThread extends Thread
{
	Socket			client;
	Statement		state1;
	updatePassword	password;

	public updatePassword_dealingThread(Socket s_client) throws IOException, SQLException
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
			password = (updatePassword) oin.readObject();
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			String sql_update = "update Password set password = '" + password.newPassword + "' where qq = '" + password.qq + "';";
			state1.execute(sql_update);
			serverMain.map_array_passwordMap.put(password.qq, password.newPassword);
			String text = "您的密码已修改成功！";
			oout.writeObject(new testMessage());
			sendSystemMessageThread sender = new sendSystemMessageThread(password.qq, text);
			state1.close();
			client.close();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + password.qq + "修改密码成功！\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
