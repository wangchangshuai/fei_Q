package server.thread.addFriendsThread;

import java.io.IOException;
import java.net.*;
import java.sql.*;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextPane;

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
public class receiveAgreeRefuseThread extends Thread
{
	private static final int	POOL_SIZE	= 100;
	final int					port		= 10009;
	public ServerSocket			server;
	Socket						client;

	public receiveAgreeRefuseThread() throws IOException
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
				receive_dealingAgreeRefuseThread receiver = new receive_dealingAgreeRefuseThread(client);
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
