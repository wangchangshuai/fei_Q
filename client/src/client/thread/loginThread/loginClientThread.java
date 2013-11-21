package client.thread.loginThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import Windows_MainInterface.MainInterface;

import common.message.*;
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
public class loginClientThread extends Thread
{
	final int port = 10000;
	public int changed;
	public login_message lmessage;
	public testMessage test;
	Socket client;

	public loginClientThread(login_message lmessage)
	{
		super();
		this.lmessage = new login_message(lmessage);
		try
		{
			this.client = new Socket(MainInterface.ip, port);
			changed = 0;
			start();
		}
		catch (Exception e)
		{}
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(lmessage);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			test = (testMessage) oin.readObject();
			if (test != null)
			{
				changed = 1;
			}
			else
			{
				changed = 2;
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
