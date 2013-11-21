package Windows_addFriend;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Windows_MainInterface.MainInterface;

import common.message.authentication;

import client.thread.addFriendsThread.sendAuthenticationThread;
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
public class addfriend extends JFrame implements ActionListener
{
	private String		nickname	= "", user = "";
	private int			head_num;
	private JPanel		jp_up, jp_buttom;
	private JButton		jb_sure, jb_cancel;
	private JLabel		jl_head;
	private JLabel		jl_1, jl_2, jl_3, jl_4;
	private JTextArea	jt;
	private JComboBox	jc;

	public addfriend(String nickname, String user, int head_num)
	{
		this.nickname = nickname;
		this.user = user;
		this.head_num = head_num;
		Image image = Toolkit.getDefaultToolkit().getImage("pic//face//18.jpg");
		setIconImage(image);
		Container container = getContentPane();
		// 设置背景图片
		JLabel jl_down = new JLabel();
		// 把背景图片显示在一个标签里面
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(550, 450);// setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		jl_1 = new JLabel();
		jl_1.setBounds(30, 20, 120, 20);
		jl_1.setText("<html><font size = '3' color = green>你将添加以下好友</font></html>");
		c.add(jl_1);
		jp_up = new JPanel();
		jp_up.setOpaque(false);
		jp_up.setLayout(null);
		jp_up.setBounds(0, 40, 400, 130);
		c.add(jp_up);
		// 头像
		String p1 = "pic//face//", p2 = ".jpg";
		jl_head = new JLabel();
		jl_head.setBounds(40, 10, 40, 40);
		jl_head.setIcon(new ImageIcon(p1 + String.valueOf(head_num) + p2));
		jp_up.add(jl_head);
		jl_2 = new JLabel();
		jl_2.setBounds(100, 10, 100, 20);
		jl_2.setText(nickname);
		jp_up.add(jl_2);
		jl_3 = new JLabel();
		jl_3.setBounds(100, 30, 100, 20);
		jl_3.setText(user);
		jp_up.add(jl_3);
		jl_4 = new JLabel();
		jl_4.setBounds(30, 55, 110, 20);
		jl_4.setText("请输入验证信息：");
		jp_up.add(jl_4);
		jt = new JTextArea();
		jt.setBounds(30, 75, 330, 50);
		jt.setBorder(new LineBorder(Color.gray));
		jp_up.add(jt);
		// jl_5 = new JLabel();
		// jl_5.setBounds(30,130,70,25);
		// jl_5.setText("备注名称:");
		// jp_up.add(jl_5);
		//
		//
		// jt_name = new JTextField();
		// jt_name.setBounds(105,130,200,25);
		// jt_name.addMouseListener(this);
		// jp_up.add(jt_name);
		//
		// jl_6 = new JLabel();
		// jl_6.setBounds(30,160,70,25);
		// jl_6.setText("分组:");
		// jp_up.add(jl_6);
		//
		// jc = new JComboBox ();
		// jc.addMouseListener(this);
		// jc.setBounds(105,160,200,25);
		// jc.addItem("我的好友");
		// jc.addItem("朋友");
		// jc.addItem("家人");
		// jc.addItem("企业好友");
		// jc.addItem("陌生人");
		// jc.addItem("黑名单");
		// jp_up.add(jc);
		//
		//
		// jb_new = new JButton();
		// jb_new.setBounds(310,160,90,25);
		// jb_new.setText("创建分组");
		// jp_up.add(jb_new);
		//
		//
		// //下部
		jp_buttom = new JPanel();
		jp_buttom.setOpaque(false);
		jp_buttom.setLayout(null);
		jp_buttom.setBounds(0, 170, 400, 70);
		c.add(jp_buttom);
		jb_sure = new JButton();
		jb_sure.setIcon(new ImageIcon("pic//search//sure.jpg"));
		jb_sure.setBounds(200, 10, 67, 24);
		jb_sure.addActionListener(this);
		jb_sure.setActionCommand("确定");
		jp_buttom.add(jb_sure);
		jb_cancel = new JButton();
		jb_cancel.setIcon(new ImageIcon("pic//search//cancel.jpg"));
		jb_cancel.setBounds(290, 10, 67, 24);
		jb_cancel.addActionListener(this);
		jb_cancel.setActionCommand("取消");
		jp_buttom.add(jb_cancel);
		getLayeredPane().setLayout(null);
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setLocation(400, 280);
		setResizable(false);
		setSize(400, 240);
		setVisible(true);
		setTitle("添加好友");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("确定"))
		{
			try
			{
				if (user.equals(MainInterface.mInfo.myself.qq))
				{
					JOptionPane.showMessageDialog(null, "不能添加自己为好友！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				else if (MainInterface.findFriend(user) != null)
				{
					JOptionPane.showMessageDialog(null, "该用户已经在您的好友列表中！", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					sendAuthenticationThread sener_au = new sendAuthenticationThread(new authentication(MainInterface.mInfo.myself.qq, user, jt.getText()));
					JOptionPane.showMessageDialog(null, "已发送好友添加请求！", "温馨提示", JOptionPane.PLAIN_MESSAGE);
				}
				dispose();
			}
			catch (UnknownHostException e1)
			{}
			catch (IOException e1)
			{}
		}
		else if (e.getActionCommand().equals("取消"))
		{
			dispose();
		}
	}
}
