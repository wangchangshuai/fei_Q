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
public class deleteMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				myqqNum				= "";
	public String				qq				= "";

	public deleteMessage(String myqqNum, String qq)
	{
		super();
		this.myqqNum = new String(myqqNum);
		this.qq = new String(qq);
	}

	public deleteMessage()
	{
		super();
	}

	public deleteMessage(deleteMessage message)
	{
		this.myqqNum = new String(message.myqqNum);
		this.qq = new String(message.qq);
	}
}
