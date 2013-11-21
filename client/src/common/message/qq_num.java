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
public class qq_num implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq					= "";

	public qq_num(String qq)
	{
		super();
		this.qq = qq;
	}

	public qq_num(qq_num qq)
	{
		super();
		this.qq = qq.qq;
	}
}
