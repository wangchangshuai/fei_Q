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
public class updatePassword implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String qq;
	public String newPassword;

	public updatePassword(String qq, String newPassword)
	{
		super();
		this.qq = new String(qq);
		this.newPassword = new String(newPassword);
	}

	public updatePassword(updatePassword update)
	{
		super();
		this.qq = new String(update.qq);
		this.newPassword = new String(update.newPassword);
	}
}
