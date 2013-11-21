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
 * @author 王昌帅，司吉峰，王松松 （计算机2009-5、6班）
 *
 */
// ////////////////////////////////////////////////////////////
public class mainInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;
	public int						groupCount;
	public personalInfo				myself;					// 分组数
	public singleGroupInfoInMain	group[];					// 全部分组

	public mainInfo(mainInfo mI)
	{
		super();
		this.myself = new personalInfo(mI.myself);
		this.groupCount = mI.groupCount;
		group = new singleGroupInfoInMain[mI.groupCount];
		for (int i = 0; i < groupCount; i++)
		{
			this.group[i] = new singleGroupInfoInMain(mI.group[i]);
		}
	}

	public void setFirst(int groupCount)
	{
		this.groupCount = groupCount;
		this.group = new singleGroupInfoInMain[groupCount];
		for (int i = 0; i < groupCount; i++)
		{
			this.group[i] = new singleGroupInfoInMain();
		}
		// TODO Auto-generated constructor stub
	}

	public void setSecond(singleGroupInfoInMain[] group)
	{
		for (int i = 0; i < groupCount; i++)
		{
			this.group[i] = new singleGroupInfoInMain(group[i]);
		}
	}

	public mainInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}
}
