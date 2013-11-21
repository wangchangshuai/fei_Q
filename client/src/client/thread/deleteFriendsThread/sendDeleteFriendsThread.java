package client.thread.deleteFriendsThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.deleteMessage;
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
public class sendDeleteFriendsThread extends Thread
{
	final int port = 10008;
	public int changed;
	public deleteMessage message;
	Socket client;

	public sendDeleteFriendsThread(deleteMessage lmessage) throws UnknownHostException, IOException
	{
		super();
		this.message = new deleteMessage(lmessage);
		this.client = new Socket(MainInterface.ip, port);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(message);
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
