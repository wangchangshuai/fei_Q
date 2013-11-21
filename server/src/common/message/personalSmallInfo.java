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
public class personalSmallInfo implements Serializable // 个人资料（所含资料很少）
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String	qq		= "";	// qq号码
	public String	nickname	= "";	// qq昵称
	public String	sex			= "";	// 性别
	public int		headImage	= 0;	// qq头像编号
	public int		status		= 0;	// 个人状态编号
	public String	country		= "";	// 国家
	public String	province	= "";	// 省
	public String	city		= "";	// 市
	public String	signature	= "";	// 个人签名
	public String	remarks		= "";	// 备注

	public personalSmallInfo(String qq, String nickname, String sex, int headImage, int status, String country, String province, String city, String signature, String remarks)
	{
		super();
		this.qq = qq;
		this.nickname = nickname;
		this.sex = sex;
		this.headImage = headImage;
		this.status = status;
		this.country = country;
		this.province = province;
		this.city = city;
		this.signature = signature;
		this.remarks = remarks;
	}

	// public void set(String remarks)
	// {
	// this.remarks = remarks;
	// }
	public personalSmallInfo(personalSmallInfo personalSmallInfo)
	{
		super();
		this.qq = personalSmallInfo.qq;
		this.nickname = personalSmallInfo.nickname;
		this.sex = personalSmallInfo.sex;
		this.headImage = personalSmallInfo.headImage;
		this.province = personalSmallInfo.province;
		this.city = personalSmallInfo.city;
		this.status = personalSmallInfo.status;
		this.signature = personalSmallInfo.signature;
		this.remarks = personalSmallInfo.remarks;
	}

	public personalSmallInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}
}
