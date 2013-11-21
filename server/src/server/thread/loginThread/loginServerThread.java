package server.thread.loginThread;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class loginServerThread extends Thread
{
	private static final int	POOL_SIZE	= 100;
	static int					times		= 1;
	final int					port		= 10000;
	public ServerSocket			server;
	Socket						client;

	public loginServerThread() throws IOException
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
				// System.out.println("At the time " + sf.format(new Date()) + " loginServer support the " + times + "th time service to" + client.getInetAddress().toString().substring(1));
				// times++;
				client = server.accept();
				login_dealingThread dt = new login_dealingThread(client);
				executorService.execute(dt);
				// System.out.println("loginServer go on!");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
