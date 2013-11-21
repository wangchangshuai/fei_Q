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
public class loginport implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq					= "";
	public String				password			= "";
	public int					port_sys;
	public int					port_main;
	public int					port_alive;
	public int					port_chat;

	public loginport(String qq, String pass, int portSys, int portMain, int portAlive, int portChat)
	{
		super();
		this.qq = qq;
		this.password = pass;
		this.port_sys = portSys;
		this.port_main = portMain;
		this.port_alive = portAlive;
		this.port_chat = portChat;
	}

	public loginport(loginport p)
	{
		super();
		this.qq = p.qq;
		this.password = p.password;
		this.port_sys = p.port_sys;
		this.port_main = p.port_main;
		this.port_alive = p.port_alive;
		this.port_chat = p.port_chat;
	}

	public loginport()
	{
		super();
	}
}
