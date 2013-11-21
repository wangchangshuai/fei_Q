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
public class systemSettings implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq = "";
	public int					whetherCanAdd		= 2;

	public systemSettings(String qq, int whetherCanAdd)
	{
		this.qq = qq;
		this.whetherCanAdd = whetherCanAdd;
	}

	public systemSettings(systemSettings settings)
	{
		this.qq = settings.qq;
		this.whetherCanAdd = settings.whetherCanAdd;
	}
}
