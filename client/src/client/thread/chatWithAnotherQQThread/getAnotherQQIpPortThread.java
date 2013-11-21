package client.thread.chatWithAnotherQQThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.ipPort;
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
 * @author 王昌帅，司吉峰，王松松 （计算机2009-5、6班）
 *
 */
public class getAnotherQQIpPortThread extends Thread
{
	final int port = 10004;
	Socket client = null;
	qq_num qq = null;
	public ipPort iport = null;

	public getAnotherQQIpPortThread(qq_num qq) throws UnknownHostException, IOException
	{
		super();
		this.qq = qq;
		start();
	}

	public void run()
	{
		try
		{
			client = new Socket(MainInterface.ip, port);
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(qq);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			this.iport = (ipPort) oin.readObject();
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
