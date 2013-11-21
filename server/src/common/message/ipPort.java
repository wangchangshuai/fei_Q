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
public class ipPort implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				ip					= "";
	public int					chatPort;

	public ipPort(String ip, int port)
	{
		super();
		this.ip = ip;
		this.chatPort = port;
	}

	public ipPort(ipPort info)
	{
		super();
		this.ip = info.ip;
		this.chatPort = info.chatPort;
	}
}
