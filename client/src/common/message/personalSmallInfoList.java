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
public class personalSmallInfoList implements Serializable// 个人资料列表
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					count	= 0;						// 个数
	public personalSmallInfo	pinf[]	= new personalSmallInfo[0];

	public personalSmallInfoList(int count) // 参数是符合条件的qq信息的个数
	{
		super();
		this.count = count;
		this.pinf = new personalSmallInfo[count];
		for (int i = 0; i < count; i++)
		{
			this.pinf[i] = new personalSmallInfo();
		}
	}

	public personalSmallInfoList(personalSmallInfoList pinfl)
	{
		super();
		this.count = pinfl.count;
		this.pinf = new personalSmallInfo[pinfl.count];
		for (int i = 0; i < count; i++)
		{
			this.pinf[i] = new personalSmallInfo(pinfl.pinf[i]);
		}
	}

	public personalSmallInfoList()
	{
		super();
		// TODO Auto-generated constructor stub
	}
}
