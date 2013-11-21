package client.thread.systemSettingThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.systemSettings;
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
public class systemSettingThread extends Thread
{
	final int port = 10014;
	Socket client;
	systemSettings settings;

	public systemSettingThread(systemSettings settings) throws UnknownHostException, IOException
	{
		super();
		client = new Socket(MainInterface.ip, port);
		this.settings = new systemSettings(settings);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(settings);
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
