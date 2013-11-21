package server.thread.moveFriendThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import server.serverMain.serverMain;

import common.message.moveFriend;
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
public class moveFriend_dealingThread extends Thread
{
	Socket		client;
	Statement	state2;
	moveFriend	move;

	public moveFriend_dealingThread(Socket s_client) throws IOException, SQLException
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
			move = (moveFriend) oin.readObject();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + move.myqqNum + " 移动好友" + move.qq + " 到 " + move.newGroup + " 组中！\n");
			String sql_update = "update friendsList_" + move.myqqNum + " set groupName = '" + move.newGroup + "' where qq = '" + move.qq + "';";
			state2.execute(sql_update);
			state2.close();
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
