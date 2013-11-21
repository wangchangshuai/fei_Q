package common.message;
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
public class node_info
{
	public int	IsAuthenExist;
	public int	IsSystemMessageExist;

	public node_info(int isSystemMessageExist, int isAuthenExist)
	{
		super();
		IsSystemMessageExist = isSystemMessageExist;
		IsAuthenExist = isAuthenExist;
	}
}
