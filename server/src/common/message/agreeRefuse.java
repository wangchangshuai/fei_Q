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
public class agreeRefuse implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public int					sign				= 0;	// 标记同意还是拒绝
	public String				myqqNum				= "";
	public String				qq					= "";
	public String				refuseReason		= "";

	public agreeRefuse(int sign, String myqqNum, String qq, String refuseReason)
	{
		super();
		this.sign = sign;
		this.myqqNum = myqqNum;
		this.qq = qq;
		this.refuseReason = refuseReason;
	}

	public agreeRefuse(agreeRefuse agreement)
	{
		this.sign = agreement.sign;
		this.myqqNum = agreement.myqqNum;
		this.qq = agreement.qq;
		this.refuseReason = agreement.refuseReason;
	}
}
