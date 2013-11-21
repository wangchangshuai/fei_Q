package server.thread.heartbeatThread;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.HashMap;

import server.serverMain.serverMain;

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
// 此线程是 心跳模型的 —— 拉模型
public class aliveMessageSenderThread extends Thread
{
	int			port;
	String		qq;
	public int	changed	= 0;
	Socket		client;

	public aliveMessageSenderThread(String qq)
	{
		this.qq = qq;
		start();
	}

	public void run()
	{
		serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + "开始推拉检测 " + qq + " 是否在线！\n");
		try
		{
			client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_alive);
			serverMain.map_date.put(qq, new Date());
			changed = 1;
		}
		catch (Exception e)
		{
			changed = 2;
		}
	}
}
