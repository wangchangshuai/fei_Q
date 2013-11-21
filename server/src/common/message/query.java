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
public class query implements Serializable // SQL query text
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String	queryText1		= "";
	private String	queryText_count	= "";

	public query(String str1, String str2)
	{
		this.queryText1 = str1;
		this.queryText_count = str2;
	}

	public String getQueryText1()
	{
		return queryText1;
	}

	public String getQueryText2()
	{
		return queryText_count;
	}

	public query()
	{
		super();
		// TODO Auto-generated constructor stub
	}
}
