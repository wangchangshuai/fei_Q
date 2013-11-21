package client.thread.getPasswordThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.findPassword;
import common.message.safeQuestionOrAnswer;
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
 * @author 王昌帅，司吉峰，王松松 （计算机2009-5、6班）
 *
 */
public class getSafeQuestionThread extends Thread
{
	final int port = 10016;
	Socket client;
	public int changed;
	public safeQuestionOrAnswer safe;
	public testMessage test;
	public findPassword password;

	public getSafeQuestionThread(String qq) throws UnknownHostException, IOException
	{
		super();
		this.client = new Socket(MainInterface.ip, port);
		this.test = new testMessage(qq, 0);
		changed = 0;
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(test);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			safe = (safeQuestionOrAnswer) oin.readObject(); // 接收来自服务器的密保问题
			if (safe != null)
			{
				changed = 1;
			}
			else
			{
				changed = 2;
				return;
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
