package server.thread.safeThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.message.node_public;

import server.thread.updateRemarkThread.updateRemark_dealingThread;
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
public class updatePasswordThread extends Thread
{
	private static final int	POOL_SIZE	= 100;
	final int					port		= 10018;
	public ServerSocket			server;
	Socket						client;

	public updatePasswordThread() throws IOException
	{
		server = new ServerSocket(port);
		start();
	}

	public void run()
	{
		try
		{
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
			while (true)
			{
				client = server.accept();
				updatePassword_dealingThread update = new updatePassword_dealingThread(client);
				executorService.execute(update);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
