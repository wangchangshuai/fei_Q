package server.thread.deleteFridendsThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import server.serverMain.serverMain;
import server.thread.sendMainInfoThread.sendMainInfoThread;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.deleteMessage;
import common.message.mainInfo;
import common.message.node_public;
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
public class delete_dealingThread extends Thread
{
	Statement		state1;
	Statement		state2;
	Socket			client;
	deleteMessage	message	= new deleteMessage("", "");

	public delete_dealingThread(Socket client) throws SQLException
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
			message = new deleteMessage((deleteMessage) oin.readObject());
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + message.myqqNum + "删除好友" + message.qq + "\n");
			String deleteFromMe1 = "delete from friendsList_" + message.myqqNum + " where qq = '" + message.qq + "'; ";
			String deleteFromMe2 = "drop table history_" + message.myqqNum + "_" + message.qq + " ;";
			String deleteFromHim1 = "delete from friendsList_" + message.qq + " where qq = '" + message.myqqNum + "';";
			String deleteFromHim2 = " drop table history_" + message.qq + "_" + message.myqqNum + " ;";
			state2.execute(deleteFromMe1);
			state2.execute(deleteFromMe2);
			state2.execute(deleteFromHim1);
			state2.execute(deleteFromHim2);
			if (serverMain.map_onlineInfo.get(message.qq) != null)
			{
				sendMainInfoThread sendMain = new sendMainInfoThread(serverMain.map_onlineInfo.get(message.qq).ip, message.qq);
			}
			String text = "您已经将该用户（" + message.qq + "）删除！ ";
			sendSystemMessageThread sender = new sendSystemMessageThread(message.myqqNum, text);
			state1.close();
			state2.close();
			client.close();
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
