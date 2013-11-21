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
public class addMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				myqqNum				= "";
	public String				qq					= "";

	public addMessage(String myqqNum, String qq)
	{
		super();
		this.myqqNum = myqqNum;
		this.qq = qq;
	}

	public addMessage()
	{
		super();
	}

	public addMessage(addMessage message)
	{
		this.myqqNum = message.myqqNum;
		this.qq = message.qq;
	}
}
