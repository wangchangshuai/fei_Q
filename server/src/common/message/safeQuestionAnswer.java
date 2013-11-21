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
public class safeQuestionAnswer implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq					= "";
	public String				question1			= "";
	public String				question2			= "";
	public String				question3			= "";
	public String				answer1				= "";
	public String				answer2				= "";
	public String				answer3				= "";

	public safeQuestionAnswer(String qq, String question1, String question2, String question3, String answer1, String answer2, String answer3)
	{
		super();
		this.qq = qq;
		this.question1 = question1;
		this.question2 = question2;
		this.question3 = question3;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}

	public safeQuestionAnswer(safeQuestionAnswer safe)
	{
		super();
		this.qq = safe.qq;
		this.question1 = safe.question1;
		this.question2 = safe.question2;
		this.question3 = safe.question3;
		this.answer1 = safe.answer1;
		this.answer2 = safe.answer2;
		this.answer3 = safe.answer3;
	}

	public safeQuestionAnswer()
	{
		// TODO Auto-generated constructor stub
	}
}
