package client.thread.updateRemarkThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.updateRemark;
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
public class updateRemarkThread extends Thread
{
	final int		port	= 10011;
	Socket			client;
	updateRemark	remark;

	public updateRemarkThread(updateRemark remark) throws UnknownHostException, IOException
	{
		super();
		client = new Socket(MainInterface.ip, port);
		this.remark = new updateRemark(remark);
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(remark);
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
