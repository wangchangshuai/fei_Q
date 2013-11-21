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
public class systemMessage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					sign				= 0;
	public int					status				= 10;
	public String				anotherqq			= "";
	public String				nickname			= "";
	public int					age					= 0;
	public String				sex					= "";
	public String				country				= "";
	public String				province			= "";
	public String				city				= "";
	public String				message				= "";
	public String				authentication		= "";

	public systemMessage()
	{
		super();
	}

	public systemMessage(int sign, String anotherqq, String nickname, int age, String sex, String country, String province, String city, String message, String authentication)
	{
		super();
		this.sign = sign;
		this.anotherqq = anotherqq;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.message = message;
		this.authentication = authentication;
	}

	public systemMessage(systemMessage sys)
	{
		super();
		this.sign = sys.sign;
		this.anotherqq = sys.anotherqq;
		this.status = sys.status;
		this.nickname = sys.nickname;
		this.age = sys.age;
		this.sex = sys.sex;
		this.country = sys.country;
		this.province = sys.province;
		this.city = sys.city;
		this.message = sys.message;
		this.authentication = sys.authentication;
	}

	public systemMessage(String text)
	{
		super();
		this.message = text;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
