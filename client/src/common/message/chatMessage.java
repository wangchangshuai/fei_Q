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
public class chatMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				myQQ				= "";
	public String				anotherQQ			= "";
	public String				time				= "";
	public String				speeker				= "";
	public String				text				= "";

	public chatMessage(String myQQ, String anotherQQ, String time, String speeker, String text)
	{
		this.myQQ = myQQ;
		this.anotherQQ = anotherQQ;
		this.time = time;
		this.speeker = speeker;
		this.text = text;
	}

	public chatMessage(chatMessage message)
	{
		this.myQQ = message.myQQ;
		this.anotherQQ = message.anotherQQ;
		this.time = message.time;
		this.speeker = message.speeker;
		this.text = message.text;
	}
}
