
package client.thread.addFriendsThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
public class sendAddRequestThread extends Thread
{
	final int			port	= 10007;
	int					localSysPort;
	Socket				client;
	public int			changed;
	public addMessage	add;
	testMessage			test;

	public sendAddRequestThread(addMessage add, int localSystemReceiverPort) throws UnknownHostException, IOException
	{
		super();
		changed = 0;
		this.localSysPort = localSystemReceiverPort;
		this.add = new addMessage(add);
		this.client = new Socket(MainInterface.ip, port);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(add);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			test = new testMessage((testMessage) oin.readObject());
			if (test.sign == 2)
			{
				sendAuthenticationThread sender = new sendAuthenticationThread(new authentication(add.myqqNum, add.qq, "wangchangshuai"));
			}
			else
			{
				Socket client = new Socket(InetAddress.getLocalHost(), localSysPort);
				ObjectOutputStream oou = new ObjectOutputStream(client.getOutputStream());
				String text = "";
				if (test.sign == 1)
				{
					text = "添加好友成功！";
				}
				else
				{
					text = "对方设置不允许任何人加入！";
				}
				oou.writeObject(new systemMessage(text));
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
