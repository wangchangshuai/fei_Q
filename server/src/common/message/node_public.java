package common.message;
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
public class node_public
{
	public String	ip	= "";
	public int		port_sys;
	public int		port_main;
	public int		port_alive;
	public int		port_chat;

	public node_public(String ip, int port_sys, int port_main, int port_alive, int port_chat)
	{
		super();
		this.ip = ip;
		this.port_sys = port_sys;
		this.port_main = port_main;
		this.port_alive = port_alive;
		this.port_chat = port_chat;
	}
}
