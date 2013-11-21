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
public class safeQuestionOrAnswer  implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public String				qq				= "";
	public String				text1				= "";
	public String				text2				= "";
	public String				text3				= "";
	public safeQuestionOrAnswer(String qq,String text1, String text2, String text3) {
		super();
		this.qq =qq;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
	}
	public safeQuestionOrAnswer(safeQuestionOrAnswer safe) {
		super();
		this.qq =safe.qq;
		this.text1 = safe.text1;
		this.text2 = safe.text2;
		this.text3 = safe.text3;
	}
	
}
