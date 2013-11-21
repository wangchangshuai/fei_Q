package common.message;

import java.io.Serializable;
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
public class singleGroupInfoInMain implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					count				= 0;
	public String				groupName			= "";	// 分组的名称
	public personalInfo			pinfl[];					// 该分组的好友列表

	public singleGroupInfoInMain(int count, String groupName, personalInfo[] pinfl)
	{
		super();
		this.count = count;
		this.groupName = new String(groupName);
		this.pinfl = new personalInfo[count];
		for (int i = 0; i < count; i++)
		{
			this.pinfl[i] = new personalInfo(pinfl[i]);
		}
	}

	public singleGroupInfoInMain(singleGroupInfoInMain group)
	{
		super();
		this.count = group.count;
		this.groupName = new String(group.groupName);
		this.pinfl = new personalInfo[group.count];
		for (int i = 0; i < count; i++)
		{
			this.pinfl[i] = new personalInfo(group.pinfl[i]);
		}
	}

	public singleGroupInfoInMain()
	{
		super();
	}
}
