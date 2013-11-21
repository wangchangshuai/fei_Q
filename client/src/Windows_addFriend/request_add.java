package Windows_addFriend;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;


import Windows_MainInterface.MainInterface;
import Windows_systemMessage.message.autoExit;

import common.message.agreeRefuse;
import common.message.mainInfo;
import common.message.personalInfo;
import common.message.systemMessage;

import client.thread.addFriendsThread.sendAgreeRefuseThread;
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
public class request_add extends JFrame implements ActionListener
{
	private int			head_num	= 1;
	private Dimension	screenSize;
	private JPanel		jp_up, jp_buttom;
	private JButton		jb_sure, jb_cancel;
	private JLabel		jl_head;
	private JLabel		jl_1, jl_2, jl_3, jl_4, jl_5;
	private JTextArea	jt;
	private JCheckBox	jc;
	String				nickname	= "";
	String				user		= "";
	String				sex			= "";
	int					age;
	String				address		= "";
	String				information	= "";
	systemMessage		sysmessage;
	MainInterface		mainframe;
	Container			c_temp_mainInterface;

	public request_add(String nickname, String user, String sex, int age, String address, String information, systemMessage sysmessage, MainInterface mainframe, Container c_temp_mainInterface)
	{
		this.c_temp_mainInterface = c_temp_mainInterface;
		this.mainframe = mainframe;
		this.sysmessage = sysmessage;
		this.nickname = nickname;
		this.user = user;
		this.age = age;
		this.address = address;
		this.information = information;
		Image image = Toolkit.getDefaultToolkit().getImage("pic//search_result//1.jpg");
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 保持弹出窗口居中
		this.setLocation(screenSize.width / 5, screenSize.height / 6);
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
		jp_up = new JPanel();
		jp_up.setOpaque(false);
		jp_up.setLayout(null);
		jp_up.setBounds(0, 0, 400, 250);
		c.add(jp_up);
		// 头像
		String p1 = "pic//face//", p2 = ".jpg";
		jl_head = new JLabel();
		jl_head.setBounds(20, 10, 40, 40);
		jl_head.setIcon(new ImageIcon(p1 + String.valueOf(head_num) + p2));
		jp_up.add(jl_head);
		jl_1 = new JLabel();
		jl_1.setBounds(90, 10, 100, 20);
		jl_1.setText(nickname);
		jl_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_up.add(jl_1);
		jl_2 = new JLabel();
		jl_2.setBounds(90, 30, 100, 20);
		jl_2.setText(user);
		jl_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_up.add(jl_2);
		jl_3 = new JLabel();
		jl_3.setBounds(90, 55, 300, 20);
		jl_3.setText("性别：" + sex + "  年龄：" + String.valueOf(age) + "岁" + "  所在地：" + address);
		jl_3.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_up.add(jl_3);
		jl_4 = new JLabel();
		jl_4.setBounds(90, 80, 300, 20);
		jl_4.setText("请求添加你为好友");
		jl_4.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_up.add(jl_4);
		// 验证信息
		jt = new JTextArea();
		jt.setBounds(90, 105, 250, 100);
		jt.setBorder(new LineBorder(Color.gray));
		jt.setEnabled(false);
		jt.setText(information);
		jp_up.add(jt);
		// 同意并添加为好友复选框
		jc = new JCheckBox();
		jc.setBounds(90, 210, 20, 20);
		jc.setSelected(true);
		jp_up.add(jc);
		// JLabel
		jl_5 = new JLabel();
		jl_5.setBounds(110, 210, 200, 20);
		jl_5.setFont(new Font("宋体", Font.PLAIN, 12));
		jl_5.setText("同意并添加对方为好友");
		jp_up.add(jl_5);
		// 下部
		jp_buttom = new JPanel();
		jp_buttom.setOpaque(false);
		jp_buttom.setLayout(null);
		jp_buttom.setBounds(0, 250, 400, 70);
		c.add(jp_buttom);
		// 确定Button
		jb_sure = new JButton();
		jb_sure.setIcon(new ImageIcon("pic//search//sure.jpg"));
		jb_sure.setBounds(200, 10, 68, 22);
		jb_sure.addActionListener(this);
		jb_sure.setActionCommand("确定");
		jp_buttom.add(jb_sure);
		// 忽略Button
		jb_cancel = new JButton();
		jb_cancel.setIcon(new ImageIcon("pic//search//ignore.jpg"));
		jb_cancel.setBounds(280, 10, 68, 22);
		jb_cancel.addActionListener(this);
		jb_cancel.setActionCommand("忽略");
		jp_buttom.add(jb_cancel);
		getLayeredPane().setLayout(null);
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(400, 320);
		setVisible(true);
		setTitle("添加好友");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("确定"))
		{
			if (jc.isSelected())
			{
				try
				{
					personalInfo person = new personalInfo();
					person.age = sysmessage.age;
					person.qq = sysmessage.anotherqq;
					person.status = sysmessage.status;
					person.nickname = sysmessage.nickname;
					person.sex = sysmessage.sex;
					person.country = sysmessage.country;
					person.province = sysmessage.province;
					person.city = sysmessage.city;
					MainInterface.insertFriend("我的好友", person);
					MainInterface.map_friendsInfo.put(sysmessage.anotherqq, person);
					c_temp_mainInterface.removeAll();
					mainframe.refresh();
					c_temp_mainInterface.repaint();
					sendAgreeRefuseThread sender = new sendAgreeRefuseThread(new agreeRefuse(1, MainInterface.mInfo.myself.qq, user, ""));
					dispose();
				}
				catch (UnknownHostException e1)
				{}
				catch (IOException e1)
				{}
			}
		}
		else if (e.getActionCommand().equals("忽略"))
		{
			dispose();
		}
	}
}
