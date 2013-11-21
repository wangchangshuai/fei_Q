package server.thread.safeThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
public class judgeWhetherQuestionExistThread extends Thread
{
	final int			POOL_SIZE	= 100;
	final int			port		= 10016;
	public ServerSocket	server;
	Socket				client;

	public judgeWhetherQuestionExistThread() throws IOException
	{
		super();
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
				judgeWhetherExist_dealingThread get = new judgeWhetherExist_dealingThread(client);
				executorService.execute(get);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
