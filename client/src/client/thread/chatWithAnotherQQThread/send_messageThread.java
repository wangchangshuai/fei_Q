package client.thread.chatWithAnotherQQThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;

import common.message.chatMessage;
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
public// 发送信息线程
class send_messageThread extends Thread
{
	chatMessage messageToAnother = null;
	Socket sendToLocalTransmit = null;

	public send_messageThread(chatMessage messageToAnother) throws UnknownHostException, IOException
	{
		this.messageToAnother = messageToAnother;
		start();
	}

	public void run()
	{
		try
		{
			sendToLocalTransmit = new Socket(InetAddress.getLocalHost(), MainInterface.transmit_port);
			ObjectOutputStream oout = new ObjectOutputStream(sendToLocalTransmit.getOutputStream());
			oout.writeObject(messageToAnother);
			sendToLocalTransmit.close();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
