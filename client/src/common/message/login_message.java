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
public class login_message implements Serializable // 登录信息
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String qq = "";
	public String password = "";

	public int port_sys;
	public int port_main;
	public int port_alive;
	public int port_chat;
	public int status = 0;

	public login_message(String qq, String password, int portSys, int portMain,
			int portAlive, int portChat, int status) {
		super();
		this.qq = qq;
		this.password = password;
		this.port_sys = portSys;
		this.port_main = portMain;
		this.port_alive = portAlive;
		this.port_chat = portChat;
		this.status = status;
	}

	public login_message(login_message lmessage) {
		super();
		this.qq = lmessage.qq;
		this.password = lmessage.password;
		this.port_sys = lmessage.port_sys;
		this.port_main = lmessage.port_main;
		this.port_alive = lmessage.port_alive;
		this.port_chat = lmessage.port_chat;
		this.status = lmessage.status;
	}

//	public login_message() {
//		super();
//	}
//
//	public login_message(String qq, String password) {
//		super();
//		this.qq = qq;
//		this.password = password;
//	}
}
