package Windows_MainInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
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
//对top中的button事件处理
public class top_event implements ActionListener
{

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="QQ空间")
		{
			JOptionPane.showMessageDialog(null,"未开通空间！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="微博")
		{
			JOptionPane.showMessageDialog(null,"微博也没开通！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="邮箱")
		{
			JOptionPane.showMessageDialog(null,"邮箱过一会儿再开通！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="朋友网")
		{
			JOptionPane.showMessageDialog(null,"朋友网没开通！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="钱包")
		{
			JOptionPane.showMessageDialog(null,"钱包未开通！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="搜搜")
		{
			JOptionPane.showMessageDialog(null,"搜搜未开通！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="资讯")
		{
			JOptionPane.showMessageDialog(null,"无资讯内容！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="购物车")
		{
			JOptionPane.showMessageDialog(null,"购物车不会开通的！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getActionCommand()=="消息盒子")
		{
			JOptionPane.showMessageDialog(null,"暂没有消息！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		else{}
	}
	  
}