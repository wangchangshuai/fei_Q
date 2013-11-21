package server.thread.systemMessageThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import server.serverMain.serverMain;

import common.message.agreeRefuse;
import common.message.node_public;
import common.message.systemMessage;
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
public class sendSystemMessageThread extends Thread
{
	int						port;
	public Socket			client;
	public String			ip_old	= "";
	public int				sign;
	public String			qq		= "";
	public int				status;
	public String			text	= "";
	public systemMessage	sys;
	Statement				state1	= null;
	Statement				state2	= null;
	private String			time	= "";

	// 1、发送改变状态 2、发送系统消息（全部人） 3、发送上次登录信息（登陆者）
	// 4、发送系统消息（对登录者）5、发送添加好友请求信息 6、当在其他地方登录时给另一个QQ发送下线通知
	public sendSystemMessageThread(String qq, int status) throws SQLException
	{
		this.sign = 1;
		this.qq = qq;
		this.status = status;
		state1 = serverMain.con1.createStatement();
		state2 = serverMain.con2.createStatement();
		start();
	}

	public sendSystemMessageThread(String text) throws SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.sign = 2;
		this.text = text;
		start();
	}

	public sendSystemMessageThread(String qq, int sign, String ip_od, String t) throws SQLException
	{
		this.sign = 3;
		this.qq = qq;
		this.ip_old = ip_od;
		this.time = t;
		start();
	}

	public sendSystemMessageThread(String qq, String text) throws SQLException
	{
		this.sign = 4;
		this.qq = qq;
		this.text = text;
		start();
	}

	public sendSystemMessageThread(String qq, systemMessage sys) throws SQLException
	{
		this.sign = 5;
		this.qq = qq;
		this.sys = new systemMessage(sys);
		start();
	}

	public sendSystemMessageThread(int sign, String qq) throws SQLException
	{
		this.sign = 6;
		this.qq = qq;
		this.sys = new systemMessage();
		start();
	}

	public sendSystemMessageThread(String qq, systemMessage sys, int agree)
	{
		sign = 7;
		this.qq = qq;
		this.sys = sys;
		start();
	}

	public void run()
	{
		try
		{
			ObjectOutputStream oout = null;
			if (sign == 1)
			{
				String sql_count = "select count(*) as 'count' from friendsList_" + qq + ";";
				ResultSet res_count = state2.executeQuery(sql_count);
				res_count.next();
				int count = res_count.getInt("count");
				String qqNum[] = new String[count];
				String sql_selectFriends = "select qq from friendsList_" + qq + ";";
				ResultSet res_friends = state2.executeQuery(sql_selectFriends);
				for (int i = 0; res_friends.next(); i++)
				{
					qqNum[i] = res_friends.getString("qq");
				}
				int port_sys;
				String ip;
				node_public node;
				for (int i = 0; i < count; i++)
				{
					if ((node = serverMain.map_onlineInfo.get(qqNum[i])) != null)
					{
						ip = node.ip;
						port_sys = node.port_sys;
					}
					else
					{
						continue;
					}
					// 得到此qq在好友列表中的昵称
					String sql_select = " select remark from friendsList_" + qqNum[i] + " where qq = '" + qq + "';";
					ResultSet res_select = state2.executeQuery(sql_select);
					if (res_select.next())
					{
						String remark = res_select.getString("remark");
						if (remark == null) // 判断是否设置了昵称
						{
							String sql_nickname = "select nickname from mainInfo where qq = '" + qq + "' ;";
							ResultSet res_nickname = state1.executeQuery(sql_nickname);
							res_nickname.next();
							remark = new String(res_nickname.getString("nickname"));
						}
						if (status == 1)
							text = "         您的好友 " + remark + " 上线了！";
						else if (status == 2)
							text = "         您的好友 " + remark + " 离开了！";
						else if (status == 3)
							text = "         您的好友 " + remark + " 开始忙碌了！";
						else if (status == 4 || status == 0)
							text = "         您的好友 " + remark + " 下线或隐身了！";
						sys = new systemMessage(1, qq, null, 0, null, null, null, null, text, null);
						sys.setStatus(status);
						client = new Socket(ip, port_sys);
						oout = new ObjectOutputStream(client.getOutputStream());
						oout.writeObject(sys);
						oout.close();
					}
				}
			}
			else if (sign == 2)
			{
				if (!text.equals("null"))
				{
					text = "" + text;
					String sql_count = "select count(*) as count from Password;";
					String sql_all = "select qq from Password;";
					ResultSet res_count = state1.executeQuery(sql_count);
					res_count.next();
					int count = res_count.getInt("count");
					String qq[] = new String[count];
					ResultSet res_all = state1.executeQuery(sql_all);
					for (int i = 0; res_all.next(); i++)
					{
						qq[i] = res_all.getString("qq");
					}
					for (int i = 0; i < count; i++)
					{
						if (serverMain.map_onlineInfo.get(qq[i]) != null)
						{
							client = new Socket(serverMain.map_onlineInfo.get(qq[i]).ip, serverMain.map_onlineInfo.get(qq[i]).port_sys);
							oout = new ObjectOutputStream(client.getOutputStream());
							sys = new systemMessage(1, null, null, 0, null, null, null, null, text, null);
							oout.writeObject(sys);
							oout.close();
						}
						else
						{
							String sql_insert = "update systemMessage set warning = '" + text + "' where qq = '" + qq[i] + "';";
							state1.execute(sql_insert);
							serverMain.map_IsSysMessageExist.put(qq[i], 1);
						}
					}
				}
				else
				{
					String updateNull = "update systemMessage set warning = null;";
					state1.execute(updateNull);
					Map m = serverMain.map_IsSysMessageExist;
					Iterator ite = m.keySet().iterator();
					while (ite.hasNext())
					{
						Object key = ite.next();
						serverMain.map_IsSysMessageExist.put(key.toString(), 0);
					}
				}
			}
			else if (sign == 3)
			{
				if (ip_old != null)
				{
					String text = "您上次登录ip为：" + ip_old + ",登录时间为：" + time + "， 如果有异常，建议您立即更改密码！";
					client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
					oout = new ObjectOutputStream(client.getOutputStream());
					sys = new systemMessage(1, null, null, 0, null, null, null, null, text, null);
					oout.writeObject(sys);
					oout.close();
				}
				else
				{
					String text = "您此次登录为第一次登录，欢迎使用QQ软件，我们会为您提供最优质的服务，再次感谢!";
					client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
					oout = new ObjectOutputStream(client.getOutputStream());
					sys = new systemMessage(1, null, null, 0, null, null, null, null, text, null);
					oout.writeObject(sys);
					oout.close();
				}
			}
			else if (sign == 4)
			{
				text = "" + text;
				client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
				oout = new ObjectOutputStream(client.getOutputStream());
				sys = new systemMessage(1, null, null, 0, null, null, null, null, text, null);
				oout.writeObject(sys);
				oout.close();
			}
			else if (sign == 5)
			{
				// String sql_select = "use main; select nickname from mainInfo where qq = '" + qq + "';";
				// ResultSet res_selectNickname = state.executeQuery(sql_select);
				// res_selectNickname.next();
				text = "QQ号为：" + sys.anotherqq + " 的QQ用户想添加您为好友！";
				sys.message = new String(text);
				client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
				oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(sys);
				oout.close();
			}
			else if (sign == 6)
			{
				text = "您的QQ在另一处登录，若非本人所为，建议您立即修改密码，QQ将在7秒后退出！";
				sys.message = new String(text);
				sys.sign = 3;
				client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
				oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(sys);
				oout.close();
			}
			else if (sign == 7)
			{
				client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_sys);
				oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(sys);
				oout.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("发送系统消息失败！估计要发的qq已在10秒内意外下线，超出服务器心跳周期！");
		}
	}
}
