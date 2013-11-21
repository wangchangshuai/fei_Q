package client.thread.applicateThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
public class applicateClientThread extends Thread
{
	final int port = 10001;
	public int changed;
	public personalInfo personnalInformation;
	Socket client;
	public String getQQ; // 申请得到的qq号码
	public String password;

	public applicateClientThread(personalInfo personalInformation)
	{
		super();
		this.personnalInformation = new personalInfo(personalInformation);
		changed = 0;
		start();
	}

	public void run()
	{
		try
		{
			try
			{
				this.client = new Socket(MainInterface.ip, port);
				changed = 3;
			}
			catch (Exception e)
			{
				changed = 4;
				return;
			}
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(personnalInformation);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			testMessage appInfo = (testMessage) oin.readObject();
			if (appInfo != null)
			{
				getQQ = appInfo.qq;
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
