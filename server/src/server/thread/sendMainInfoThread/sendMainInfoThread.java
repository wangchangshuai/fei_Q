package server.thread.sendMainInfoThread;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.HashMap;

import javax.print.attribute.standard.PresentationDirection;

import server.serverMain.serverMain;

import common.message.*;
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
public class sendMainInfoThread extends Thread
{
	Socket		client;
	mainInfo	maininformation;
	Statement	state1;
	Statement	state2;
	String		qq	= "";
	int			port;

	public sendMainInfoThread(String ip, String qq) throws SQLException
	{
		this.qq = qq;
		this.state1 = serverMain.con1.createStatement();
		this.state2 = serverMain.con2.createStatement();
		maininformation = new mainInfo();
		try
		{
			if (serverMain.map_onlineInfo.get(qq) != null)
			{
				this.client = new Socket(serverMain.map_onlineInfo.get(qq).ip, serverMain.map_onlineInfo.get(qq).port_main);
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		start();
	}

	public void run()
	{
		String sql_main = "select * from mainInfo where qq = '" + qq + "';";
		String sql_count = "select count(*) as 'count' from groupsList_" + qq + ";";
		String sql_group = "select groupName from groupsList_" + qq + ";";
		ResultSet res_main = null, res_count = null, res_group = null;
		try
		{
			// 提取个人信息
			res_main = state1.executeQuery(sql_main);
			if (res_main.next())
			{
				maininformation.myself = new personalInfo(res_main.getString("qq"), res_main.getString("nickname"), res_main.getString("sex"), res_main.getInt("age"),
						res_main.getInt("birthday_year"), res_main.getInt("birthday_month"), res_main.getInt("birthday_day"), res_main.getString("animal"), res_main.getString("constellation"),
						res_main.getString("bloodType"), res_main.getString("country"), res_main.getString("province"), res_main.getString("city"), res_main.getInt("headImage"),
						res_main.getInt("status"), res_main.getString("signature"), res_main.getString("phoneNumber"), res_main.getString("collage"), res_main.getString("personalInfo"),
						res_main.getString("language"), res_main.getString("occupation"), null);
			}
			// 查找全部组的个数
			res_count = state2.executeQuery(sql_count);
			res_count.next();
			int count = res_count.getInt("count");
			maininformation.setFirst(count);
			// 查找组名
			res_group = state2.executeQuery(sql_group);
			for (int i = 0; res_group.next(); i++)
			{
				maininformation.group[i].groupName = new String(res_group.getString("groupName"));
			}
			for (int i = 0; i < count; i++)
			{
				personalInfo personL[];
				String sql_group_members = "select qq,remark from friendsList_" + qq + " where groupName = '" + maininformation.group[i].groupName + "';";
				String sql_group_members_count = "select count(*) as 'count' from friendsList_" + qq + " where groupName = '" + maininformation.group[i].groupName + "';";
				ResultSet res_group_members_count = state2.executeQuery(sql_group_members_count);
				res_group_members_count.next();
				int personCount = res_group_members_count.getInt("count");
				personL = new personalInfo[personCount];
				for (int k = 0; k < personCount; k++)
				{
					personL[k] = new personalInfo();
				}
				ResultSet res_group_members = state2.executeQuery(sql_group_members);
				for (int k = 0; res_group_members.next(); k++)
				{
					personL[k].remark = res_group_members.getString("remark");
					personL[k].qq = res_group_members.getString("qq");
				}
				for (int j = 0; j < personCount; j++)
				{
					String qq_temp = personL[j].qq;
					String sql_group_personInfo = "select * from mainInfo where qq = '" + qq_temp + "';";
					ResultSet res_group_personInfo = state1.executeQuery(sql_group_personInfo);
					res_group_personInfo.next();
					personL[j] = new personalInfo(personL[j].qq, res_group_personInfo.getString("nickname"), res_group_personInfo.getString("sex"), res_group_personInfo.getInt("age"),
							res_group_personInfo.getInt("birthday_year"), res_group_personInfo.getInt("birthday_month"), res_group_personInfo.getInt("birthday_day"),
							res_group_personInfo.getString("animal"), res_group_personInfo.getString("constellation"), res_group_personInfo.getString("bloodType"),
							res_group_personInfo.getString("country"), res_group_personInfo.getString("province"), res_group_personInfo.getString("city"), res_group_personInfo.getInt("headImage"),
							res_group_personInfo.getInt("status"), res_group_personInfo.getString("signature"), res_group_personInfo.getString("phoneNumber"),
							res_group_personInfo.getString("collage"), res_group_personInfo.getString("personalInfo"), res_group_personInfo.getString("language"),
							res_group_personInfo.getString("occupation"), personL[j].remark);
					if (serverMain.map_onlineInfo.get(qq_temp) != null)
					{
						personL[j].ip = serverMain.map_onlineInfo.get(qq_temp).ip;
						personL[j].port = serverMain.map_onlineInfo.get(qq_temp).port_chat;
					}
				}
				maininformation.group[i] = new singleGroupInfoInMain(personCount, maininformation.group[i].groupName, personL);
			}
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			oout.writeObject(maininformation);
			oout.close();
			state1.close();
			state2.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
