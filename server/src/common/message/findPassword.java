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
public class findPassword implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					sign;						// 1、密保正确时 2、密保错误时
	public String				password = "";

	public findPassword(String password)
	{
		super();
		this.sign = 1;
		this.password = password;
	}

	public findPassword()
	{
		super();
		this.sign = 0;
	}

	public findPassword(findPassword find)
	{
		super();
		this.sign = find.sign;
		this.password = find.password;
	}
}
