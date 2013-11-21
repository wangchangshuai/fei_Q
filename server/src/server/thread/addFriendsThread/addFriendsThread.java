package server.thread.addFriendsThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
public class addFriendsThread extends Thread
{
	private static final int	POOL_SIZE	= 100;
	final int					port		= 10007;
	public ServerSocket			server;

	public addFriendsThread() throws IOException, SQLException
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
				Socket client = server.accept();
				add_dealingThread addThread = new add_dealingThread(client);
				executorService.execute(addThread);
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
