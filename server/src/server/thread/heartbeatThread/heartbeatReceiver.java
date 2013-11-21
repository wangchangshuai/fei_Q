package server.thread.heartbeatThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.message.node_public;
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
public class heartbeatReceiver extends Thread
{
	private static final int	POOL_SIZE	= 1000;
	final int					port		= 10003;
	public ServerSocket			server;
	Socket						client;

	public heartbeatReceiver() throws IOException
	{
		server = new ServerSocket(port);
		start();
	}

	public void run()
	{
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
		while (true)
		{
			try
			{
				// SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
				client = server.accept();
				// System.out.println("At the time " + sf.format(new Date()) + " heartbeatReceiverServer support the " + times + "th time service to" + client.getInetAddress().toString().substring(1));
				// times++;
				// executorService.execute(new dealingThread(s_client));
				heartbeatReceiver_dealingThread hdt = new heartbeatReceiver_dealingThread(client);
				executorService.execute(hdt);
				// dt.start();
				// System.out.println("applicateServer go on!");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
