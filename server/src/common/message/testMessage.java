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
public class testMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String qq = "";
	public int sign = 0;
	public int port;

	public testMessage(String qq, int sign) {
		super();
		this.qq = qq;
		this.sign = sign;
	}

	public testMessage(int sign) {
		super();
		this.sign = sign;
		// TODO Auto-generated constructor stub
	}

//	public testMessage(int sign, int port) {
//		super();
//		this.port = port;
//		this.sign = sign;
//		// TODO Auto-generated constructor stub
//	}

	public testMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public testMessage(testMessage t) {
		this.port = t.port;
		this.qq = t.qq;
		this.sign = t.sign;
	}
}
