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
public class personalInfo implements Serializable// 个人资料（全）
{
	/**
	 * 共计25个变量（包括密码）
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq					= "";	// qq号码
	public String				password			= "";	// 密码
	public String				nickname			= "";	// qq昵称
	public String				sex					= "";	// 性别
	public int					age					= 0;	// 年龄
	public int					birthday_year		= 0;	// 生日_年
	public int					birthday_month		= 0;	// 月
	public int					birthday_day		= 0;	// 日
	public String				animal				= "";	// 生肖
	public String				constellation		= "";	// 星座
	public String				bloodType			= "";	// 血型
	public String				country				= "";	// 国家
	public String				province			= "";	// 省
	public String				city				= "";	// 市
	public int					headImage			= 0;	// qq头像编号
	public int					status				= 0;	// 个人状态编号
	public String				signature			= "";	// 个人签名
	public String				phoneNumber			= "";	// 手机号
	public String				collage				= "";	// 学校
	public String				personInfo			= "";	// 个人说明
	public String				language			= "";	// 语言
	public String				occupation			= "";	// 职业
	public String				remark				= "";	// 此变量只在发送主框架时用
	public String				ip					= "";	// 此变量只在聊天时用到
	public Integer				port				= 0;	// 此变量只在聊天时用到

	public personalInfo(String password, String nickname, String sex, int birthday_year, int birthday_month, int birthday_day, String country, String province, String city)
	{
		// 申请时用
		super();
		this.password = password;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday_year = birthday_year;
		this.birthday_month = birthday_month;
		this.birthday_day = birthday_day;
		this.country = country;
		this.province = province;
		this.city = city;
	}

	public personalInfo(String qq, String nickname, String sex, int age, int birthday_year, int birthday_month, int birthday_day, String animal, String constellation, String bloodType,
			String country, String province, String city, int headImage, int status, String signature, String phoneNumber, String collage, String personInfo, String language, String occupation,
			String remark)
	// 设置、查看资料时用的
	{
		super();
		this.qq = qq;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
		this.birthday_year = birthday_year;
		this.birthday_month = birthday_month;
		this.birthday_day = birthday_day;
		this.animal = animal;
		this.constellation = constellation;
		this.bloodType = bloodType;
		this.country = country;
		this.province = province;
		this.city = city;
		this.headImage = headImage;
		this.status = status;
		this.signature = signature;
		this.phoneNumber = phoneNumber;
		this.collage = collage;
		this.personInfo = personInfo;
		this.language = language;
		this.occupation = occupation;
		this.remark = remark;
	}

	public personalInfo(personalInfo personalInformation)
	{
		super();
		this.qq = personalInformation.qq;
		this.password = personalInformation.password;
		this.nickname = personalInformation.nickname;
		this.sex = personalInformation.sex;
		this.age = personalInformation.age;
		this.birthday_year = personalInformation.birthday_year;
		this.birthday_month = personalInformation.birthday_month;
		this.birthday_day = personalInformation.birthday_day;
		this.animal = personalInformation.animal;
		this.constellation = personalInformation.constellation;
		this.bloodType = personalInformation.bloodType;
		this.country = personalInformation.country;
		this.province = personalInformation.province;
		this.city = personalInformation.city;
		this.headImage = personalInformation.headImage;
		this.status = personalInformation.status;
		this.signature = personalInformation.signature;
		this.phoneNumber = personalInformation.phoneNumber;
		this.collage = personalInformation.collage;
		this.personInfo = personalInformation.personInfo;
		this.language = personalInformation.language;
		this.occupation = personalInformation.occupation;
		this.remark = personalInformation.remark;
		this.ip = personalInformation.ip;
		this.port = personalInformation.port;
	}

	public personalInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}
}
