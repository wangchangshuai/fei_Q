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
public class searchInfo implements Serializable// 查找信息
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					sign				= 0;	// 用于区分是什么类型查找
	public String				qq					= "";	// qq号码
	public String				nickname			= "";	// qq昵称
	public String				sex					= "";	// 性别
	public int					age1				= 0;	// 年龄1
	public int					age2				= 0;	// 年龄2
	public String				country				= "";	// 国家
	public String				province			= "";	// 省
	public String				city				= "";	// 市
	public int					status				= 0;	// 个人状态编号
	public String				language			= "";	// 语言

	public searchInfo(int sign, String qq, String nickname, String sex, int age1, int age2, String country, String province, String city, int status, String language)
	{
		super();
		this.sign = sign;
		this.qq = qq;
		this.nickname = nickname;
		this.sex = sex;
		this.age1 = age1;
		this.age2 = age2;
		this.country = country;
		this.province = province;
		this.city = city;
		this.status = status;
		this.language = language;
	}

	public searchInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public searchInfo(searchInfo readObject)
	{
		super();
		this.sign = readObject.sign;
		this.qq = readObject.qq;
		this.nickname = readObject.nickname;
		this.sex = readObject.sex;
		this.age1 = readObject.age1;
		this.age2 = readObject.age2;
		this.country = readObject.country;
		this.province = readObject.province;
		this.city = readObject.city;
		this.status = readObject.status;
		this.language = readObject.language;
		// TODO Auto-generated constructor stub
	}
}
