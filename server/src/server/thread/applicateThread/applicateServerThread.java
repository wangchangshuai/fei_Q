package server.thread.applicateThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class applicateServerThread extends Thread
{
	private static final int	POOL_SIZE	= 500;
	final int					port		= 10001;
	public ServerSocket			server;
	Socket						client;
	ArrayList<String>			ipCountList	= new ArrayList<String>();

	class clearIpCountThread extends Thread // 定时申请ip清空列表
	{
		public void run()
		{
			try
			{
				while (true)
				{
					sleep(86400000);
					ipCountList.clear();
				}
			}
			catch (Exception e)
			{
				run();
				e.printStackTrace();
			}
		}
	}

	public applicateServerThread() throws SQLException, InterruptedException
	{
		try
		{
			server = new ServerSocket(port);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		start();
	}

	public void run()
	{
		clearIpCountThread clear = new clearIpCountThread();
		clear.start();
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
		while (true)
		{
			try
			{
				// SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
				// System.out.println("At the time " + sf.format(new Date()) + " applicateServer support the " + times + "th time service to" + client.getInetAddress().toString().substring(1));
				// times++;
				// executorService.execute(new dealingThread(s_client));
				client = server.accept();
				applicate_dealingThread adt = new applicate_dealingThread(client, ipCountList);
				executorService.execute(adt);
				// // dt.start();
				// System.out.println("applicateServer go on!");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
