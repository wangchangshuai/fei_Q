package server.thread.applicateThread;

import java.io.IOException;
import common.message.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.net.*;

import server.serverMain.serverMain;
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
public class applicate_dealingThread extends Thread// implements Runnable//
{
	Statement			state1;
	Statement			state2;
	Socket				client;
	String				qq		= "";
	ArrayList<String>	iplist;
	int					count	= 0;

	public applicate_dealingThread(Socket s_client, ArrayList<String> l1) throws IOException, SQLException
	{
		this.iplist = l1;
		this.state1 = serverMain.con1.createStatement();
		this.state2 = serverMain.con2.createStatement();
		this.client = s_client;
		// start();
	}

	public void run()
	{ // 从可申请qq号码表中取出一个qq号码
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			personalInfo applicatePersonInfo = (personalInfo) oin.readObject();
			ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
			String ip = client.getInetAddress().toString().substring(1);
			for (int i = 0; i < iplist.size(); i++)
			{
				if ((iplist.get(i)).equals(ip))
					count++;
			}
			iplist.add(ip);
			if (count < serverMain.limit) // 先判断此ip是否申请次数过多！！！
			{
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + ip + "已经成功申请" + count + "次！\n");
				if (serverMain.array_qqCanUse.isEmpty()) // 如果canUse表中的qq号已经用完（虽然这种情况几乎不存在）
				{
					oout.writeObject(null);
					return;
				}
				qq = serverMain.array_qqCanUse.remove(0);
				oout.writeObject(new testMessage(qq, 1));
				String sql_del = "delete  from canUse where qq = '" + qq + "';";
				state1.execute(sql_del);
				serverMain.map_array_passwordMap.put(qq, applicatePersonInfo.password); // 加入到密码预存
				String sql_insertPassword = "insert into Password values('" + qq + "','" + applicatePersonInfo.password + "');";
				state1.execute(sql_insertPassword);
				String sql_insertMainInfo = "insert into mainInfo(qq,nickname,sex,birthday_year,birthday_month,birthday_day,country,province,city,language) values('" + qq + "','"
						+ applicatePersonInfo.nickname + "','" + applicatePersonInfo.sex + "'," + applicatePersonInfo.birthday_year + "," + applicatePersonInfo.birthday_month + ","
						+ applicatePersonInfo.birthday_day + ",'" + applicatePersonInfo.country + "','" + applicatePersonInfo.province + "','" + applicatePersonInfo.city + "','汉语');";
				String sql_insertQQip = "insert into qqIp values('" + qq + "',null,null);";
				String sql_insertSystemMessage = "insert into systemMessage values ('" + qq + "',null);";
				String sql_insertWhetherCanAdd = "insert into whetherCanAdd values ('" + qq + "',2);";// 默认被别人加为好友时验证好友信息
				String sql_insertMail = " insert into mailAdress values ('" + qq + "',null);";
				String sql_createTable1 = "create table  friendsList_" + qq + " (qq varchar(7) primary key,remark varchar(20),groupName varchar(20)) charset utf8 collate utf8_general_ci;";
				String sql_createTable2 = "create table  groupsList_" + qq + " (groupName varchar(20) primary key) charset utf8 collate utf8_general_ci;";
				String sql_insertGroup1 = "insert into groupsList_" + qq + " values ('我的好友'); ";
				String sql_insertGroup2 = "insert into groupsList_" + qq + " values ('同学'); ";
				String sql_insertGroup3 = "insert into groupsList_" + qq + " values ('家人');";
				String sql_insertGroup4 = "insert into groupsList_" + qq + " values ('同事');";
				String sql_createAuthentication = "create table  authentication_" + qq + " (qq varchar(7),authentication varchar(50)) charset utf8 collate utf8_general_ci;";
				String sql_createSafeQuestion = "create table  safeQuestion_" + qq + " (question varchar(50),answer varchar(50)) charset utf8 collate utf8_general_ci;";
				state1.execute(sql_insertMainInfo);
				state1.execute(sql_insertSystemMessage);
				state1.execute(sql_insertWhetherCanAdd);
				state1.execute(sql_insertMail);
				state1.execute(sql_insertQQip);
				state2.execute(sql_createTable1);
				state2.execute(sql_createTable2);
				state2.execute(sql_createAuthentication);
				state2.execute(sql_createSafeQuestion);
				state2.execute(sql_insertGroup1);
				state2.execute(sql_insertGroup2);
				state2.execute(sql_insertGroup3);
				state2.execute(sql_insertGroup4);
				serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + "qq号码：" + qq + " 申请完毕！\n");
				oin.close();
				serverMain.map_IsSysMessageExist.put(qq, 0);
			}
			else
			{
				oout.writeObject(null);
			}
			oout.close();
			oin.close();
			state1.close();
			state2.close();
			client.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + "过多qq号申请导致，某个qq号重复插入数据库，数据库已拒绝！\n");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
