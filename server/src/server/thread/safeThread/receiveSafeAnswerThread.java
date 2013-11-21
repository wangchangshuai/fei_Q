package server.thread.safeThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.message.getAvailableServerSocketFunction;
import common.message.safeQuestionAnswer;
/**
 * 2011年10月
 * 
 * 山东科技大学信息学院  版权所有
 * 
 * 联系邮箱：415939252@qq.com
 * 
 * Copyright © 1999-2012, sdust, All Rights Reserved
 * 
 * @author 王昌帅
 *
 */
public class receiveSafeAnswerThread extends Thread
{
	final int			POOL_SIZE	= 100;
	int					port		= 10041;
	public ServerSocket	server;

	public receiveSafeAnswerThread() throws IOException
	{
		this.server = new ServerSocket(port);
		start();
	}

	public void run()
	{
		try
		{
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
			while (true)
			{
				Socket client = server.accept();
				receiveAnswer_dealingThread receiver = new receiveAnswer_dealingThread(client);
				executorService.execute(receiver);
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
