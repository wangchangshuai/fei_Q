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
public class groupDeal implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					sign;						// 1、增加 2、删除 3、修改
	public int					sign2;						// 1、不删除组中好友
															// 2、删除组中好友
	public String				qq					= "";
	public String				group				= "";
	public String				newGroup			= "";

	public groupDeal(int sign, String qq, String Group)
	{// 增
		super();
		this.sign = sign;
		this.qq = qq;
		this.group = Group;
	}

	public groupDeal(int sign, int sign2, String qq, String Group)
	{// 删
		super();
		this.sign = sign;
		this.sign2 = sign2;
		this.qq = qq;
		this.group = Group;
	}

	public groupDeal(int sign, String qq, String group, String newGroup)
	{// 改
		super();
		this.sign = sign;
		this.qq = qq;
		this.group = group;
		this.newGroup = newGroup;
	}

	public groupDeal(groupDeal group)
	{
		super();
		this.sign = group.sign;
		this.sign2 = group.sign2;
		this.qq = group.qq;
		this.group = group.group;
		this.newGroup = group.newGroup;
	}
}
