package client.thread.addFriendsThread;

import java.io.IOException;
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
public class sendAgreeRefuseThread extends Thread
{
	final int port = 10009;
	Socket client;
	public agreeRefuse agreement;

	public sendAgreeRefuseThread(agreeRefuse agreement) throws UnknownHostException, IOException
	{
		super();
		this.agreement = new agreeRefuse(agreement);
		this.client = new Socket(MainInterface.ip, port);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(agreement);
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
