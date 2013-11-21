package server.thread.addqqCanUseThread;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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
public class addqqCanUseThread extends Thread
{
	Statement	state;

	public addqqCanUseThread() throws SQLException
	{
		start();
	}

	public void run()
	{
		try
		{
			this.state = serverMain.con1.createStatement();
			String sql = "select qq from canUse order by qq";
			ResultSet res = state.executeQuery(sql);
			while (res.next())
			{
				serverMain.array_qqCanUse.add(res.getString("qq"));
			}
			ResultSet res_count, res_selectLast1CanUse = null;
			String qq = null;
			String sql_insert;
			String sql_count = "select count(*) as count from canUse;";
			while (true)
			{
				this.state = serverMain.con1.createStatement();
				res_count = state.executeQuery(sql_count);
				res_count.next();
				int count = res_count.getInt("count");
				if (count == 0)
				{
					Integer num = 1000001;
					for (int i = 0; i < 500; i++, num++)
					{
						sql_insert = "insert into canUse values('" + num + "');";
						state.execute(sql_insert);
						serverMain.array_qqCanUse.add(num.toString());
					}
					serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：系统添加到数据库可申请的QQ号码500个！\n");
				}
				else if (count < 300)
				{
					int need = 500 - count;
					String sql_selectLast1CanUse = "select *  from canUse order by qq desc ;";
					res_selectLast1CanUse = state.executeQuery(sql_selectLast1CanUse);
					res_selectLast1CanUse.next();
					qq = new String(res_selectLast1CanUse.getString("qq"));
					Integer num = Integer.parseInt(qq) + 1;
					for (int i = 0; i < need; i++, num++)
					{
						sql_insert = "insert into canUse values('" + num + "');";
						state.execute(sql_insert);
						serverMain.array_qqCanUse.add(num.toString());
					}
					serverMain.textPane2.setText(serverMain.textPane2.getText() + (new Date()) + "：系统添加到数据库可申请的QQ号码" + need + "个！\n");
				}
				state.close();
				sleep(3600000);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
