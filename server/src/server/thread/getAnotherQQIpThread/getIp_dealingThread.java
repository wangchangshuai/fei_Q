package server.thread.getAnotherQQIpThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import server.serverMain.serverMain;

import common.message.ipPort;
import common.message.node_public;
import common.message.qq_num;
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
public class getIp_dealingThread extends Thread
{
	Socket	client;
	qq_num	anotherQQ;

	public getIp_dealingThread(Socket client) throws SQLException
	{
		this.client = client;
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			anotherQQ = (qq_num) oin.readObject();
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			node_public node;
			if ((node = serverMain.map_onlineInfo.get(anotherQQ.qq)) != null)
			{
				oout.writeObject(new ipPort(node.ip, node.port_chat));
			}
			else
			{
				oout.writeObject(null);
			}
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
	}
}
