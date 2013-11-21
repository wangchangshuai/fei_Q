package client.thread.addFriendsThread;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.authentication;
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
public class sendAuthenticationThread extends Thread
{
	int port = 10031;
	Socket client;
	authentication au;

	public sendAuthenticationThread(authentication au) throws UnknownHostException, IOException
	{
		this.au = new authentication(au);
		start();
	}

	public void run()
	{
		try
		{
			client = new Socket(MainInterface.ip, port);
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(au);
			oout.close();
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
