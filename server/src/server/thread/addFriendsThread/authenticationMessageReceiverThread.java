package server.thread.addFriendsThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextPane;

import common.message.getAvailableServerSocketFunction;
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
public class authenticationMessageReceiverThread extends Thread
{
	int					POOL_SIZE	= 100;
	int					port		= 10031;
	public ServerSocket	server;

	public authenticationMessageReceiverThread() throws IOException
	{
		this.server = new ServerSocket(port);
		start();
	}

	public void run()
	{
		try
		{
			while (true)
			{
				ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
				Socket client = server.accept();
				authenticationReceive_dealingThread auRe = new authenticationReceive_dealingThread(client);
				executorService.execute(auRe);
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
