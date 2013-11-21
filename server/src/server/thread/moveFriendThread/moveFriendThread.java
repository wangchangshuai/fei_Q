package server.thread.moveFriendThread;

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
public class moveFriendThread extends Thread
{
	private static final int	POOL_SIZE	= 100;
	final int					port		= 10013;
	public ServerSocket			server;
	Socket						client;

	public moveFriendThread() throws IOException
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
				moveFriend_dealingThread move = new moveFriend_dealingThread(client);
				executorService.execute(move);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
