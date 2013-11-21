package server.thread.searchThread;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.Date;

import server.serverMain.serverMain;

import common.message.personalSmallInfoList;
import common.message.searchInfo;
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
public class search_dealingThread extends Thread
{
	Socket		client;
	searchInfo	search;
	Statement	state1;

	public search_dealingThread(Socket client) throws SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.client = client;
	}

	public static String re(searchInfo info)
	{
		String re = "status= " + info.status + " ";
		if (info.sex != null)
		{
			re += "and sex = '" + info.sex + "' ";
		}
		if (info.country != null)
		{
			re += "and country = '" + info.country + "' ";
			if (info.province != null)
			{
				re += "and province = '" + info.province + "' ";
				if (info.city != null)
				{
					re += "and city = '" + info.city + "' ";
				}
			}
		}
		if (info.language != null)
		{
			re += "and language = '" + info.language + "' ";
		}
		if (info.age1 != -1 && info.age2 != -1)
		{
			re += "and age >=" + info.age1 + " ";
			re += "and age <= " + info.age2 + " ";
		}
		return re;
	}

	public void run()
	{
		try
		{
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + client.getInetAddress() + "请求查找！\n");
			ResultSet res_find = null;
			personalSmallInfoList pList = null;
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			search = new searchInfo((searchInfo) oin.readObject());
			if (search.sign == 1)
			{
				if (search.nickname == null)
				{
					String sql_find = "select qq,nickname,sex,headImage,country,province,city,status from mainInfo where qq = '" + search.qq + "';";
					res_find = state1.executeQuery(sql_find);
					if (res_find.next())
					{
						pList = new personalSmallInfoList(1);
						pList.pinf[0].qq = res_find.getString("qq");
						pList.pinf[0].nickname = res_find.getString("nickname");
						pList.pinf[0].sex = res_find.getString("sex");
						pList.pinf[0].headImage = res_find.getInt("headImage");
						pList.pinf[0].country = res_find.getString("country");
						pList.pinf[0].province = res_find.getString("province");
						pList.pinf[0].city = res_find.getString("city");
						pList.pinf[0].status = res_find.getInt("status");
					}
					else
					{
						oout.writeObject(null); // ////////////////////假如搜索的结果为空
						return;
					}
				}
				else if (search.qq == null)
				{
					String sql_find_count = " select count(*) as 'count' from mainInfo where nickname like '%" + search.nickname + "%';";
					ResultSet res_find_count = state1.executeQuery(sql_find_count);
					res_find_count.next();
					if (res_find_count.getInt("count") > 0)
					{
						String sql_find = " select qq,nickname,sex,headImage,country,province,city,status from mainInfo where nickname like '%" + search.nickname + "%';";
						pList = new personalSmallInfoList(res_find_count.getInt("count"));
						res_find = state1.executeQuery(sql_find);
						for (int i = 0; res_find.next(); i++)
						{
							pList.pinf[i].qq = res_find.getString("qq");
							pList.pinf[i].nickname = res_find.getString("nickname");
							pList.pinf[i].sex = res_find.getString("sex");
							pList.pinf[i].headImage = res_find.getInt("headImage");
							pList.pinf[i].country = res_find.getString("country");
							pList.pinf[i].province = res_find.getString("province");
							pList.pinf[i].city = res_find.getString("city");
							pList.pinf[i].status = res_find.getInt("status");
						}
					}
					else
					{
						oout.writeObject(null); // ////////////////////假如搜索的结果为空
						return;
					}
				}
				else
				{
					String sql_find = " select qq,nickname,sex,headImage,country,province,city,status from mainInfo where qq = '" + search.qq + "' and nickname = '" + search.nickname + "';";
					res_find = state1.executeQuery(sql_find);
					if (res_find.next())
					{
						pList = new personalSmallInfoList(1);
						pList.pinf[0].qq = res_find.getString("qq");
						pList.pinf[0].nickname = res_find.getString("nickname");
						pList.pinf[0].sex = res_find.getString("sex");
						pList.pinf[0].headImage = res_find.getInt("headImage");
						pList.pinf[0].country = res_find.getString("country");
						pList.pinf[0].province = res_find.getString("province");
						pList.pinf[0].city = res_find.getString("city");
						pList.pinf[0].status = res_find.getInt("status");
					}
					else
					{
						oout.writeObject(null); // ////////////////////假如搜索的结果为空
						return;
					}
				}
				oout.writeObject(pList);
			}
			else if (search.sign == 2)
			{
				String sql_find_count = " select count(*) as 'count' from mainInfo where " + re(search) + " ;";
				ResultSet res_find_count = state1.executeQuery(sql_find_count);
				res_find_count.next();
				if (res_find_count.getInt("count") > 0)
				{
					String sql_find = " select qq,nickname,sex,headImage,country,province,city,status from mainInfo where " + re(search) + " ;";
					pList = new personalSmallInfoList(res_find_count.getInt("count"));
					res_find = state1.executeQuery(sql_find);
					for (int i = 0; res_find.next(); i++)
					{
						pList.pinf[i].qq = res_find.getString("qq");
						pList.pinf[i].nickname = res_find.getString("nickname");
						pList.pinf[i].sex = res_find.getString("sex");
						pList.pinf[i].headImage = res_find.getInt("headImage");
						pList.pinf[i].country = res_find.getString("country");
						pList.pinf[i].province = res_find.getString("province");
						pList.pinf[i].city = res_find.getString("city");
						pList.pinf[i].status = res_find.getInt("status");
					}
					oout.writeObject(pList);
				}
				else
				{
					oout.writeObject(null); // ////////////////////假如搜索的结果为空
				}
			}
			state1.close();
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
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
