package server.thread.updateRemarkThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import server.serverMain.serverMain;

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
public class updateRemark_dealingThread extends Thread
{
	Socket			client;
	Statement		state2;
	updateRemark	remark;

	public updateRemark_dealingThread(Socket s_client) throws IOException, SQLException
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
			remark = (updateRemark) oin.readObject();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + remark.myqqNum + "更新好友  " + remark.qq + " 的备注成功\n");
			String sql_update = "update friendsList_" + remark.myqqNum + " set remark = '" + remark.newRmark + "' where qq = '" + remark.qq + "';";
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
