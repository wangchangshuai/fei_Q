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
public class authentication  implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				myqqNum				= "";
	public String				qq					= "";
	public String				authentication		= "";

	public authentication(String myqqNum, String qq, String authentication)
	{
		super();
		this.myqqNum = myqqNum;
		this.qq = qq;
		this.authentication = authentication;
	}

	public authentication(authentication au)
	{
		this.myqqNum = au.myqqNum;
		this.qq = au.qq;
		this.authentication = au.authentication;
	}
}
