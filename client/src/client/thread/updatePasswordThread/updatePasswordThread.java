package client.thread.updatePasswordThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.testMessage;
import common.message.updatePassword;
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
public class updatePasswordThread extends Thread
{
	final int port = 10018;
	Socket client;
	updatePassword password;
	testMessage test;

	public updatePasswordThread(updatePassword password) throws UnknownHostException, IOException
	{
		super();
		client = new Socket(MainInterface.ip, port);
		this.password = new updatePassword(password);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(password);
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
