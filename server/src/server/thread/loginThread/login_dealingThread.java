package server.thread.loginThread;

import java.io.IOException;
import common.message.*;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.net.*;

import server.serverMain.serverMain;
import server.thread.sendMainInfoThread.sendMainInfoThread;
import server.thread.systemMessageThread.sendSystemMessageThread;
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
public class login_dealingThread extends Thread
{
	Socket			client;
	login_message	lmessage;
	Statement		state1;
	Statement		state2;
	String			qq	= "";
	String			ip	= "";

	public login_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.state2 = serverMain.con2.createStatement();
		this.client = s_client;
		// start();
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			lmessage = (login_message) oin.readObject();
			qq = lmessage.qq;
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "开始登录！\n");
			ip = client.getInetAddress().toString().substring(1);
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			if (serverMain.map_array_passwordMap.get(qq) == null || !serverMain.map_array_passwordMap.get(qq).equals(lmessage.password))
			{
				oout.writeObject(null);
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "因密码不正确而登录失败！\n");
				return;
			}
			else
			{
				if (serverMain.map_onlineInfo.get(qq) != null) // 判断是否重复登录
				{
					sendSystemMessageThread sendSys = new sendSystemMessageThread(6, qq);
					for (int i = 1; i <= 120 && serverMain.map_onlineInfo.get(qq) != null; i++) // 此方法以资源换程序的正确性
					{
						sleep(100);
					}
					if (serverMain.map_onlineInfo.get(qq) != null)
						return;
				}
				oout.writeObject(new testMessage(1));
				serverMain.map_onlineInfo.put(qq, new node_public(ip, lmessage.port_sys, lmessage.port_main, lmessage.port_alive, lmessage.port_chat));
				serverMain.map_date.put(qq, new Date());
				serverMain.map_status.put(qq, lmessage.status);
				// 发出登录成功消息（发这么早是为了不让用户等待太久，后面的事只是服务器的，
				// 当然假如很多很多用户同时登录的话，这种方式可能会造成几个用户登录失败，
				// 这种概率应该随着同时登录的人数的增加呈对数形式增长
				// 我们暂且认为这种情况不存在，呵呵。。。）
				// 发送主框架的全部信息
				sendMainInfoThread sendMain = new sendMainInfoThread(ip, qq);
				// // 给其好友发送登录系统消息
				if (lmessage.status != 4)
				{
					sendSystemMessageThread sendFriends = new sendSystemMessageThread(qq, 1);
				}
				// 给该用户发送上次登录信息
				String sql_selectIp = "select * from qqIp where qq = '" + qq + "';";
				ResultSet res_selectIp = state1.executeQuery(sql_selectIp);
				res_selectIp.next();
				sendSystemMessageThread send_oldMessage = new sendSystemMessageThread(qq, 3, res_selectIp.getString("ip"), res_selectIp.getString("date"));
				// 读取是否有系统消息
				if (serverMain.map_IsSysMessageExist.get(qq) != 0)
				{
					String sql_systemMessage = " select warning from systemMessage where qq = '" + qq + "'  ;";
					ResultSet res_systemMessage = state1.executeQuery(sql_systemMessage);
					res_systemMessage.next();
					if (res_systemMessage.getString("warning") != null)
					{
						sendSystemMessageThread sendSystemMessage = new sendSystemMessageThread(qq, res_systemMessage.getString("warning"));
						String sql_updateSystemMessage = "update systemMessage set warning = null where qq = '" + qq + "' ;";
						state1.execute(sql_updateSystemMessage);
					}
					serverMain.map_IsSysMessageExist.put(qq, 0);
				}
				// 读取是否有好友验证信息
				String sql_addMessageCount = "select count(*) as count from authentication_" + qq + " ;";
				String sql_addMessage = "select qq,authentication from authentication_" + qq + " ;";
				ResultSet res_addMessageCount = state2.executeQuery(sql_addMessageCount);
				res_addMessageCount.next();
				int count = res_addMessageCount.getInt("count");
				if (count > 0)
				{
					String otherqq[] = new String[count];
					String authentication[] = new String[count];
					ResultSet res_addMessage = state2.executeQuery(sql_addMessage);
					for (int i = 0; res_addMessage.next(); i++)
					{
						otherqq[i] = new String(res_addMessage.getString("qq"));
						authentication[i] = new String(res_addMessage.getString("authentication"));
					}
					for (int i = 0; i < count; i++)
					{
						String sql_find = "select nickname,age,sex,country,province,city from mainInfo where qq = '" + otherqq[i] + "' ;";
						ResultSet res_find = state1.executeQuery(sql_find);
						res_find.next();
						sendSystemMessageThread sendAuthentication = new sendSystemMessageThread(qq, new systemMessage(2, otherqq[i], res_find.getString("nickname"), res_find.getInt("age"),
								res_find.getString("sex"), res_find.getString("country"), res_find.getString("province"), res_find.getString("city"), "", authentication[i]));
					}
					String sql_del = "delete from authentication_" + qq + ";";
					state2.execute(sql_del);
				}
				// 更新必要的在线信息
				SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
				String sql_updateqqIp = "update qqIp set ip = '" + ip + "', date = '" + sf.format(new Date()) + "' where qq = '" + qq + "' ;";
				state1.execute(sql_updateqqIp);
				String sql_updateStatus = "update mainInfo set status = " + 1 + " where qq = '" + qq + "' ;";
				state1.execute(sql_updateStatus);
				oin.close();
				oout.close();
				state1.close();
				state2.close();
				client.close();
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + qq + "登录完毕！\n");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
