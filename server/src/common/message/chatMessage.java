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
public class chatMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	String						qq;
	String						time;
	String						speeker;
	String						text;

	public chatMessage(String qq, String time, String speeker, String text)
	{
		super();
		this.qq = new String(qq);
		this.time = new String(time);
		this.speeker = new String(speeker);
		this.text = new String(text);
	}

	public chatMessage(chatMessage message)
	{
		super();
		this.qq = new String(message.qq);
		this.time = new String(message.time);
		this.speeker = new String(message.speeker);
		this.text = new String(message.text);
	}
}
