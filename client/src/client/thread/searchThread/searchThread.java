package client.thread.searchThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.personalSmallInfoList;
import common.message.searchInfo;
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
public class searchThread extends Thread
{
	final int port = 10006;
	Socket client;
	public int changed;
	public personalSmallInfoList pList;
	searchInfo info;

	public searchThread(searchInfo info) throws UnknownHostException, IOException
	{
		super();
		client = new Socket(MainInterface.ip, port);
		this.info = new searchInfo(info);
		changed = 0;
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(info);
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			personalSmallInfoList pList_temp = (personalSmallInfoList) oin.readObject();
			if (pList_temp == null)
			{
				pList = new personalSmallInfoList(0);
			}
			else
			{
				pList = new personalSmallInfoList(pList_temp);
			}
			changed = 1;
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
