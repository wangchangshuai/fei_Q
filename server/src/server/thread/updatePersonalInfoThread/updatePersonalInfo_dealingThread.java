package server.thread.updatePersonalInfoThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import server.serverMain.serverMain;
import server.thread.systemMessageThread.sendSystemMessageThread;

import common.message.node_public;
import common.message.personalInfo;
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
public class updatePersonalInfo_dealingThread extends Thread
{
	Socket			client;
	Statement		state1;
	personalInfo	pInfo;

	public updatePersonalInfo_dealingThread(Socket s_client) throws IOException, SQLException
	{
		this.state1 = serverMain.con1.createStatement();
		this.client = s_client;
		// start();
	}

	public String str(personalInfo p)
	{
		String s = "set nickname = '" + p.nickname + "' , age = " + p.age + " , birthday_year = " + p.birthday_year + " , birthday_month = " + p.birthday_month + " , birthday_day = " + p.birthday_day
				+ " , headImage = " + p.headImage + " , language = '" + p.language;
		if (p.animal != null)
		{
			s += "' , animal = '" + p.animal;
		}
		if (p.bloodType != null)
		{
			s += "' , bloodType = '" + p.bloodType;
		}
		if (p.country != null)
		{
			s += "' , country = '" + p.country;
			if (p.province != null)
			{
				s += "' , province = '" + p.province;
				if (p.city != null)
				{
					s += "' , city = '" + p.city;
				}
			}
		}
		if (p.collage != null)
		{
			s += "' , collage = '" + p.collage;
		}
		if (p.constellation != null)
		{
			s += "' , constellation = '" + p.constellation;
		}
		if (p.occupation != null)
		{
			s += "' , occupation = '" + p.occupation;
		}
		if (p.personInfo != null)
		{
			s += "' , personalInfo = '" + p.personInfo;
		}
		if (p.phoneNumber != null)
		{
			s += "' , phoneNumber = '" + p.phoneNumber;
		}
		if (p.sex != null)
		{
			s += "' , sex = '" + p.sex;
		}
		if (p.signature != null)
		{
			s += "' , signature = '" + p.signature;
		}
		return s;
	}

	public void run()
	{
		try
		{
			ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
			pInfo = (personalInfo) oin.readObject();
			serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：" + pInfo.qq + "更新个人信息成功！\n");
			String sql_update = "update mainInfo " + str(pInfo) + "' where qq = '" + pInfo.qq + "';";
			state1.execute(sql_update);
			state1.close();
			// String text = "更新个人信息成功！";
			// sendSystemMessageThread sender = new sendSystemMessageThread(pInfo.qq, text);
			client.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
