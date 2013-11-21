package Windows_safe;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import common.message.login_message;
import common.message.safeQuestionAnswer;
import common.message.safeQuestionOrAnswer;
import common.message.updatePassword;

import client.thread.getPasswordThread.getSafeQuestionThread;
import client.thread.getPasswordThread.sendSafeAnswerThread;
import client.thread.setSafeQuestionThread.sendSetSafeQuestionThread;
import client.thread.updatePasswordThread.updatePasswordThread;
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
public class safe extends JFrame implements ActionListener
{
	private String			grade		= "";
	private Dimension		screenSize;
	private JPanel			card, jp1_card, jp2_card, jp_1, jp_2, jp_3, jp_4, jp_5, jp1_1, jp1_2, jp1_3, jp2_1, jp2_2, jp3_1, jp4_1, jp5_1;
	private JLabel			jl1_1, jl1_2, jl1_3, jl1_4, jl1_5, jl1_6, jl1_7, jl1_8, jl1_9, jl1_10, jl1_11, jl1_12, jl1_13, jl1_14, jl1_15, jl1_16, jl1_17, jl1_18, jl2_1, jl2_2, jl2_3, jl2_4, jl2_5,
			jl2_6, jl2_7, jl2_8, jl2_9, jl2_10, jl2_11, jl2_12, jl2_13, jl2_14, jl2_15, jl2_16, jl2_17, jl2_18, jl2_19, jl3_1, jl3_21, jl3_22, jl3_23, jl3_31, jl3_32, jl3_33, jl3_41, jl3_42, jl3_43,
			jl3_51, jl3_52, jl3_53, jl4_1, jl4_2, jl4_3, jl4_4, jl4_5, jl4_6, jl4_7, jl4_8, jl4_9;
	private JButton			jb1_s, jb1_2s, jb2_s, jb2_2s, jb_c, jb_1, jb_2, jb_3, jb_4, jb3_alter, jb_sure, jb_cancel;
	private JComboBox		jc_1, jc_2, jc_3;
	private CardLayout		cardl, cardl2, cardl3;
	private JPasswordField	jpw_1, jpw_2, jpw_3;
	private JTextArea		jt_1, jt_2, jt_3, jt1_1, jt1_2, jt1_3, jt2_1, jt2_2, jt2_3;
	private String			question[]	= { "您母亲的生日是？", "您父亲的生日是？", "您的生日是？", "您高中班主任的名字是？", "您初中班主任的名字是？", "您的出生地是？", "您学号（工号）是？", "您大学的名字是？" };
	private String			safeQusetion1	= "", safeQusetion2 = "", safeQusetion3 = "";
	getSafeQuestionThread	getQ			= null;
	login_message			lmessage;

	public safe(login_message lmessage) throws UnknownHostException, IOException, InterruptedException
	{
		this.lmessage = lmessage;
		getQ = new getSafeQuestionThread(lmessage.qq);
		getQ.join(10000);
		Image image = Toolkit.getDefaultToolkit().getImage("pic//face//b.jpg");
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(screenSize.width / 5, screenSize.height / 6);
		Container container = getContentPane();
		// 设置背景图片
		JLabel jl_down = new JLabel();
		// 把背景图片显示在一个标签里面
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(550, 450);
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		cardl = new CardLayout();
		card = new JPanel();
		card.setBounds(120, 0, 430, 380);
		card.setOpaque(false);
		card.setLayout(cardl);
		jp_1 = new JPanel();
		jp_2 = new JPanel();
		jp_3 = new JPanel();
		jp_4 = new JPanel();
		card.add("安全概述", jp_3);
		card.add("修改密码", jp_1);
		card.add("我的密保", jp_2);
		card.add("常见诈骗", jp_4);
		c.add(card);
		jb_1 = new JButton();
		jb_1.setBounds(10, 60, 90, 20);
		jb_1.setText("修改密码");
		jb_1.setActionCommand("修改密码");
		jb_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jb_1.addActionListener(this);
		c.add(jb_1);
		jb_2 = new JButton();
		jb_2.setBounds(10, 120, 90, 20);
		jb_2.setText("我的密保");
		jb_2.setActionCommand("我的密保");
		jb_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jb_2.addActionListener(this);
		c.add(jb_2);
		jb_3 = new JButton();
		jb_3.setBounds(10, 180, 90, 20);
		jb_3.setText("安全概述");
		jb_3.setActionCommand("安全概述");
		jb_3.setFont(new Font("宋体", Font.PLAIN, 14));
		jb_3.addActionListener(this);
		c.add(jb_3);
		jb_4 = new JButton();
		jb_4.setBounds(10, 240, 90, 20);
		jb_4.setText("常见诈骗");
		jb_4.setActionCommand("常见诈骗");
		jb_4.setFont(new Font("宋体", Font.PLAIN, 14));
		jb_4.addActionListener(this);
		c.add(jb_4);
		// 第一个 修改密码
		jp_1.setLayout(null);
		jp_1.setOpaque(false);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 修改密码-----QQ密码
		jl1_1 = new JLabel();
		jl1_1.setText("QQ密码");
		jl1_1.setBounds(20, 20, 100, 20);
		jl1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_1);
		jp1_1 = new JPanel();
		jp1_1.setLayout(null);
		jp1_1.setOpaque(false);
		jp1_1.setBorder(new LineBorder(Color.gray));
		jp1_1.setBounds(20, 45, 390, 100);
		jp_1.add(jp1_1);
		jl1_2 = new JLabel();
		jl1_2.setText("QQ密码强度：");
		jl1_2.setBounds(20, 20, 100, 20);
		jl1_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_1.add(jl1_2);
		checkGrade(lmessage.password);
		jl1_3 = new JLabel();
		jl1_3.setText(grade);
		jl1_3.setBounds(110, 20, 100, 20);
		jl1_3.setFont(new Font("宋体", Font.PLAIN, 14));
		if (grade.equals("危险"))
		{
			jl1_3.setForeground(Color.red);
		}
		else
		{
			jl1_3.setForeground(Color.green);
		}
		jp1_1.add(jl1_3);
		jl1_4 = new JLabel();
		jl1_4.setText("<html>设置高强度密码可以有效防止他人破解你的密码。建议设置<br>密码时区分大小写</br>添加特殊字符例如：%，#，@，？</html>");
		jl1_4.setBounds(20, 45, 390, 50);
		jl1_4.setFont(new Font("宋体", Font.PLAIN, 14));
		jl1_4.setForeground(Color.gray);
		jp1_1.add(jl1_4);
		cardl2 = new CardLayout();
		jp1_card = new JPanel();
		jp1_card.setBounds(20, 175, 390, 200);
		jp1_card.setOpaque(false);
		jp1_card.setLayout(cardl2);
		// jp1_2
		jp1_2 = new JPanel();
		jp1_2.setLayout(null);
		jp1_2.setOpaque(false);
		jp1_2.setBorder(new LineBorder(Color.gray));
		jp_1.add(jp1_2);
		// jp1_3
		jp1_3 = new JPanel();
		jp1_3.setLayout(null);
		jp1_3.setOpaque(false);
		jp1_3.setBorder(new LineBorder(Color.gray));
		jp_1.add(jp1_3);
		jp1_card.add("验证密保", jp1_2);
		jp1_card.add("验证密保修改密码", jp1_3);
		jp_1.add(jp1_card);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 修改密码-----验证密保
		jl1_5 = new JLabel();
		jl1_5.setIcon(new ImageIcon("pic//safe//key.png"));
		jl1_5.setBounds(20, 5, 40, 40);
		jl1_5.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_5);
		// jl1_6
		jl1_6 = new JLabel();
		jl1_6.setText("验证密保修改密码");
		jl1_6.setBounds(65, 15, 150, 20);
		jl1_6.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_6);
		// jl1_7
		jl1_7 = new JLabel();
		// jl1_7.setText("问题一：" + question[1]);
		jl1_7.setBounds(20, 45, 300, 20);
		jl1_7.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_7);
		// jl1_8
		jl1_8 = new JLabel();
		jl1_8.setText("答案一：");
		jl1_8.setBounds(20, 65, 60, 20);
		jl1_8.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_8);
		// jt1_1
		jt1_1 = new JTextArea();
		jt1_1.setBounds(77, 65, 200, 20);
		jt1_1.setBorder(new LineBorder(Color.gray));
		jp1_2.add(jt1_1);
		// jl1_9
		jl1_9 = new JLabel();
		// jl1_9.setText("问题二：" + question[2]);
		jl1_9.setBounds(20, 85, 300, 20);
		jl1_9.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_9);
		// jl1_10
		jl1_10 = new JLabel();
		jl1_10.setText("答案二：");
		jl1_10.setBounds(20, 105, 60, 20);
		jl1_10.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_10);
		// jt1_2
		jt1_2 = new JTextArea();
		jt1_2.setBounds(77, 105, 200, 20);
		jt1_2.setBorder(new LineBorder(Color.gray));
		jp1_2.add(jt1_2);
		// jl1_11
		jl1_11 = new JLabel();
		// jl1_11.setText("问题三：" + question[3]);
		jl1_11.setBounds(20, 125, 300, 20);
		jl1_11.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_11);
		// jl1_12
		jl1_12 = new JLabel();
		jl1_12.setText("答案三：");
		jl1_12.setBounds(20, 150, 60, 20);
		jl1_12.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_2.add(jl1_12);
		// jt_3
		jt1_3 = new JTextArea();
		jt1_3.setBounds(77, 150, 200, 20);
		jt1_3.setBorder(new LineBorder(Color.gray));
		jp1_2.add(jt1_3);
		// jb1_s
		jb1_s = new JButton();
		jb1_s.setBounds(320, 170, 50, 20);
		jb1_s.setIcon(new ImageIcon("pic//safe//tijiao.gif"));
		jb1_s.addActionListener(this);
		jb1_s.setActionCommand("提交");
		jp1_2.add(jb1_s);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 修改密码-----验证密保修改密码
		jl1_13 = new JLabel();
		jl1_13.setText("修改密码");
		jl1_13.setBounds(20, 20, 100, 20);
		jl1_13.setFont(new Font("宋体", Font.PLAIN, 16));
		jl1_13.setForeground(Color.red);
		jp1_3.add(jl1_13);
		// jl1_14
		jl1_14 = new JLabel();
		jl1_14.setText("请输入原密码：");
		jl1_14.setBounds(20, 45, 300, 20);
		jl1_14.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jl1_14);
		// jpw_1
		jpw_1 = new JPasswordField();
		jpw_1.setBounds(20, 65, 200, 20);
		jpw_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jpw_1);
		// jl1_15
		jl1_15 = new JLabel();
		jl1_15.setText("");
		jl1_15.setBounds(230, 65, 200, 20);
		jl1_15.setFont(new Font("宋体", Font.PLAIN, 14));
		jl1_15.setForeground(Color.red);
		jp1_3.add(jl1_15);
		// jl1_16
		jl1_16 = new JLabel();
		jl1_16.setText("请输入新密码：");
		jl1_16.setBounds(20, 85, 300, 20);
		jl1_16.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jl1_16);
		// jpw_2
		jpw_2 = new JPasswordField();
		jpw_2.setBounds(20, 105, 200, 20);
		jpw_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jpw_2);
		// jl1_17
		jl1_17 = new JLabel();
		jl1_17.setText("");
		jl1_17.setBounds(230, 105, 200, 20);
		jl1_17.setFont(new Font("宋体", Font.PLAIN, 14));
		jl1_17.setForeground(Color.red);
		jp1_3.add(jl1_17);
		// jl1_18
		jl1_18 = new JLabel();
		jl1_18.setText("请重复输入新密码：");
		jl1_18.setBounds(20, 125, 300, 20);
		jl1_18.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jl1_18);
		// jpw_3
		jpw_3 = new JPasswordField();
		jpw_3.setBounds(20, 150, 200, 20);
		jpw_3.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1_3.add(jpw_3);
		// jb1_2s
		jb1_2s = new JButton();
		jb1_2s.setBounds(320, 170, 50, 20);
		jb1_2s.setIcon(new ImageIcon("pic//safe//tijiao.gif"));
		jb1_2s.addActionListener(this);
		jb1_2s.setActionCommand("提交2");
		jp1_3.add(jb1_2s);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 第二个 我的密保
		jp_2.setLayout(null);
		jp_2.setOpaque(false);
		jl2_1 = new JLabel();
		jl2_1.setText("我的密保");
		jl2_1.setBounds(20, 20, 100, 20);
		jl2_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_1);
		cardl3 = new CardLayout();
		jp2_card = new JPanel();
		jp2_card.setBounds(20, 45, 390, 300);
		jp2_card.setOpaque(false);
		jp2_card.setLayout(cardl3);
		jp_2.add(jp2_card);
		jp2_1 = new JPanel();
		jp2_2 = new JPanel();
		jp2_card.add("验证密保", jp2_1);
		jp2_card.add("修改密保2", jp2_2);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 我的密保------验证密保
		jp2_1.setLayout(null);
		jp2_1.setOpaque(false);
		jp2_1.setBorder(new LineBorder(Color.gray));
		jl2_9 = new JLabel();
		jl2_9.setIcon(new ImageIcon("pic//safe//key.png"));
		jl2_9.setBounds(20, 30, 40, 40);
		jl2_9.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_9);
		jl2_10 = new JLabel();
		jl2_10.setText("验证密保修改密保");
		jl2_10.setBounds(65, 40, 150, 20);
		jl2_10.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_10);
		// 问题一
		jl2_11 = new JLabel();
		jl2_11.setBounds(20, 80, 300, 20);
		jl2_11.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_11);
		// 答案一
		jl2_12 = new JLabel();
		jl2_12.setText("答案一：");
		jl2_12.setBounds(20, 110, 60, 20);
		jl2_12.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_12);
		jt2_1 = new JTextArea();
		jt2_1.setBounds(77, 110, 200, 20);
		jt2_1.setBorder(new LineBorder(Color.gray));
		jp2_1.add(jt2_1);
		// 如果为空输出“空”
		jl2_17 = new JLabel();
		jl2_17.setBounds(280, 110, 100, 20);
		jl2_17.setFont(new Font("宋体", Font.PLAIN, 14));
		jl2_17.setForeground(Color.red);
		jp2_1.add(jl2_17);
		// 问题二
		jl2_13 = new JLabel();
		jl2_13.setBounds(20, 140, 300, 20);
		jl2_13.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_13);
		// 答案二
		jl2_14 = new JLabel();
		jl2_14.setText("答案二：");
		jl2_14.setBounds(20, 170, 60, 20);
		jl2_14.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_14);
		jt2_2 = new JTextArea();
		jt2_2.setBounds(77, 170, 200, 20);
		jt2_2.setBorder(new LineBorder(Color.gray));
		jp2_1.add(jt2_2);
		// 如果为空输出“空”
		jl2_18 = new JLabel();
		jl2_18.setBounds(280, 170, 100, 20);
		jl2_18.setFont(new Font("宋体", Font.PLAIN, 14));
		jl2_18.setForeground(Color.red);
		jp2_1.add(jl2_18);
		// 问题三
		jl2_15 = new JLabel();
		jl2_15.setBounds(20, 200, 300, 20);
		jl2_15.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_15);
		// 答案三
		jl2_16 = new JLabel();
		jl2_16.setText("答案三：");
		jl2_16.setBounds(20, 230, 60, 20);
		jl2_16.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_1.add(jl2_16);
		jt2_3 = new JTextArea();
		jt2_3.setBounds(77, 230, 200, 20);
		jt2_3.setBorder(new LineBorder(Color.gray));
		// 如果为空输出“空”
		jl2_19 = new JLabel();
		jl2_19.setBounds(280, 230, 100, 20);
		jl2_19.setFont(new Font("宋体", Font.PLAIN, 14));
		jl2_19.setForeground(Color.red);
		jp2_1.add(jl2_19);
		// 提交3
		jp2_1.add(jt2_3);
		jb2_s = new JButton();
		jb2_s.setBounds(320, 260, 50, 20);
		jb2_s.setIcon(new ImageIcon("pic//safe//tijiao.gif"));
		jb2_s.addActionListener(this);
		jb2_s.setActionCommand("提交3");
		jp2_1.add(jb2_s);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 我的密保------修改密保
		jp2_2.setLayout(null);
		jp2_2.setOpaque(false);
		jp2_2.setBorder(new LineBorder(Color.gray));
		jl2_2 = new JLabel();
		jl2_2.setText("<html>请按照下面提示的问题填写答案，为了您更好地记忆<br>问题，强烈建议您定期进行验证。</br></html>");
		jl2_2.setBounds(20, 20, 390, 50);
		jl2_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jl2_2.setForeground(Color.gray);
		jp2_2.add(jl2_2);
		jl2_3 = new JLabel();
		jl2_3.setText("问题一：");
		jl2_3.setBounds(20, 80, 80, 20);
		jl2_3.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_3);
		jc_1 = new JComboBox();
		jc_1.setBounds(100, 80, 200, 20);
		for (int i = 0; i < 8; i++)
		{
			jc_1.addItem(question[i]);
		}
		jc_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jc_1);
		jl2_4 = new JLabel();
		jl2_4.setText("答案一：");
		jl2_4.setBounds(20, 110, 80, 20);
		jl2_4.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_4);
		jt_1 = new JTextArea();
		jt_1.setBounds(100, 110, 200, 20);
		jt_1.setBorder(new LineBorder(Color.gray));
		jp2_2.add(jt_1);
		jl2_5 = new JLabel();
		jl2_5.setText("问题二：");
		jl2_5.setBounds(20, 140, 80, 20);
		jl2_5.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_5);
		jc_2 = new JComboBox();
		jc_2.setBounds(100, 140, 200, 20);
		for (int i = 0; i < 8; i++)
		{
			jc_2.addItem(question[i]);
		}
		jc_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jc_2);
		jl2_6 = new JLabel();
		jl2_6.setText("答案二：");
		jl2_6.setBounds(20, 170, 80, 20);
		jl2_6.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_6);
		jt_2 = new JTextArea();
		jt_2.setBounds(100, 170, 200, 20);
		jt_2.setBorder(new LineBorder(Color.gray));
		jp2_2.add(jt_2);
		jl2_7 = new JLabel();
		jl2_7.setText("问题三：");
		jl2_7.setBounds(20, 200, 80, 20);
		jl2_7.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_7);
		jc_3 = new JComboBox();
		jc_3.setBounds(100, 200, 200, 20);
		for (int i = 0; i < 8; i++)
		{
			jc_3.addItem(question[i]);
		}
		jc_3.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jc_3);
		jl2_8 = new JLabel();
		jl2_8.setText("答案三：");
		jl2_8.setBounds(20, 230, 80, 20);
		jl2_8.setFont(new Font("宋体", Font.PLAIN, 14));
		jp2_2.add(jl2_8);
		jt_3 = new JTextArea();
		jt_3.setBounds(100, 230, 200, 20);
		jt_3.setBorder(new LineBorder(Color.gray));
		jp2_2.add(jt_3);
		jb2_2s = new JButton();
		jb2_2s.setBounds(320, 260, 50, 20);
		jb2_2s.setIcon(new ImageIcon("pic//safe//tijiao.gif"));
		jb2_2s.addActionListener(this);
		jb2_2s.setActionCommand("提交4");
		jp2_2.add(jb2_2s);
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 安全概述
		jp_3.setLayout(null);
		jp_3.setOpaque(false);
		jl3_1 = new JLabel();
		jl3_1.setText("安全概况");
		jl3_1.setBounds(20, 20, 100, 20);
		jl3_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_3.add(jl3_1);
		// jp3_1
		jp3_1 = new JPanel();
		jp3_1.setLayout(null);
		jp3_1.setOpaque(false);
		jp3_1.setBorder(new LineBorder(Color.gray));
		jp3_1.setBounds(20, 45, 390, 330);
		jp_3.add(jp3_1);
		// 危险图标1
		jl3_21 = new JLabel();
		jl3_21.setBounds(150, 20, 61, 61);
		jp3_1.add(jl3_21);
		// jl3_3
		jl3_31 = new JLabel();
		jl3_31.setText("");
		jl3_31.setFont(new Font("宋体", Font.PLAIN, 16));
		jp3_1.add(jl3_31);
		// jl3_4
		jl3_41 = new JLabel();
		jl3_41.setText("");
		jl3_41.setFont(new Font("宋体", Font.PLAIN, 14));
		jp3_1.add(jl3_41);
		// jl3_5
		jl3_51 = new JLabel();
		jl3_51.setText("");
		jl3_51.setFont(new Font("宋体", Font.PLAIN, 14));
		jp3_1.add(jl3_51);
		// 立即完成
		jb3_alter = new JButton();
		jb3_alter.setIcon(new ImageIcon("pic//safe//alter.jpg"));
		jb3_alter.setBounds(135, 250, 93, 32);
		jb3_alter.addActionListener(this);
		jp3_1.add(jb3_alter);
		checkSafe();
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// 常见诈骗
		jp_4.setLayout(null);
		jp_4.setOpaque(false);
		jl4_1 = new JLabel();
		jl4_1.setText("常见诈骗");
		jl4_1.setBounds(20, 20, 100, 20);
		jl4_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_4.add(jl4_1);
		jp4_1 = new JPanel();
		jp4_1.setLayout(null);
		jp4_1.setOpaque(false);
		jp4_1.setBorder(new LineBorder(Color.gray));
		jp4_1.setBounds(20, 45, 390, 330);
		jp_4.add(jp4_1);
		jl4_2 = new JLabel();
		jl4_2.setText("<1>钓鱼网址");
		jl4_2.setBounds(10, 10, 100, 20);
		jl4_2.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_2.setForeground(Color.red);
		jp4_1.add(jl4_2);
		jl4_3 = new JLabel();
		jl4_3.setText("<html>仿冒腾讯公司网站诱导用户输入QQ账号，密码，密保，个人信息等资料</html>");
		jl4_3.setBounds(30, 35, 370, 40);
		jl4_3.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_3.setForeground(Color.gray);
		jp4_1.add(jl4_3);
		jl4_4 = new JLabel();
		jl4_4.setText("<2>视频诈骗");
		jl4_4.setBounds(10, 80, 100, 20);
		jl4_4.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_4.setForeground(Color.red);
		jp4_1.add(jl4_4);
		jl4_5 = new JLabel();
		jl4_5.setText("<html>播放录制好的视频给好友，使好友以为正在视频聊天，骗取信任后向好友借钱。</html>");
		jl4_5.setBounds(30, 105, 380, 40);
		jl4_5.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_5.setForeground(Color.gray);
		jp4_1.add(jl4_5);
		jl4_6 = new JLabel();
		jl4_6.setText("<3>冒充好友诈骗");
		jl4_6.setBounds(10, 150, 130, 20);
		jl4_6.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_6.setForeground(Color.red);
		jp4_1.add(jl4_6);
		jl4_7 = new JLabel();
		jl4_7.setText("<html>窃取QQ密码后，冒充主人与好有聊天，骗取钱财，QQ币，游戏装备等。</html>");
		jl4_7.setBounds(30, 175, 350, 40);
		jl4_7.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_7.setForeground(Color.gray);
		jp4_1.add(jl4_7);
		jl4_8 = new JLabel();
		jl4_8.setText("<4>中奖其炸");
		jl4_8.setBounds(10, 220, 130, 20);
		jl4_8.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_8.setForeground(Color.red);
		jp4_1.add(jl4_8);
		jl4_9 = new JLabel();
		jl4_9.setText("<html>冒充腾讯公司通知用户中奖，以丰厚的奖金做幌子，骗取用户支付押金，运费等。</html>");
		jl4_9.setBounds(30, 245, 350, 40);
		jl4_9.setFont(new Font("宋体", Font.PLAIN, 15));
		jl4_9.setForeground(Color.gray);
		jp4_1.add(jl4_9);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// jp_5 = new JPanel();
		// jp_5.setBounds(0,380,550,380);
		// jp_5.setOpaque(false);
		// jp_5.setLayout(null);
		// c.add(jp_5);
		//
		//
		//
		//
		//
		// jb_sure = new JButton();
		// jb_sure.setBounds(400,10,40,20);
		// jb_sure.setActionCommand("确定");
		// jb_sure.addActionListener(this);
		// jp_5.add(jb_sure);
		//
		// jb_cancel = new JButton();
		// jb_cancel.setBounds(480,10,40,20);
		// jb_cancel.setActionCommand("取消");
		// jb_cancel.addActionListener(this);
		// jp_5.add(jb_cancel);
		getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(550, 430);
		setVisible(true);
		setTitle("安全沟通---为你提供全面的QQ保护");
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// 密码等级判断
	public void checkGrade(String pw)
	{
		int len = pw.length();
		int count = 0;
		int count1 = 0;
		int c_number = 0, c_character = 0, c_Character = 0, c_special = 0;
		for (int i = 0; i < len; i++)
		{
			if (pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z')
			{
				c_character = 1;
			}
			else if (pw.charAt(i) >= 'A' && pw.charAt(i) <= 'Z')
			{
				c_Character = 1;
			}
			else if (pw.charAt(i) >= '1' && pw.charAt(i) <= '9')
			{
				c_number = 1;
			}
			else
			{
				c_special = 1;
			}
		}
		count = c_number + c_character + c_Character + c_special;
		count1 = c_number + c_special + (c_character > 0 ? 1 : c_Character);
		if (count1 >= 3 && len > 6)
		{
			grade = "优秀";
		}
		else if (count >= 3 && len > 5)
		{
			grade = "良好";
		}
		else if (count >= 2 || len > 4)
		{
			grade = "中等";
		}
		else
		{
			grade = "危险";
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// 安全状态判断
	public void checkSafe()
	{
		checkGrade(lmessage.password);
		if (getQ.changed == 1 && (grade.equals("优秀") || grade.equals("良好")))
		{
			jl3_21.setIcon(new ImageIcon("pic//safe//safe.jpg"));
			jl3_41.setText("");
			jl3_51.setText("");
			jl3_31.setText("安全状况良好请继续保持良好的习惯");
			jl3_31.setForeground(Color.green);
			jl3_31.setBounds(80, 150, 300, 20);
			jb3_alter.setActionCommand("立即完成设置密码");
		}
		else if (getQ.changed == 1 && (grade.equals("中等") || grade.equals("危险")))
		{
			jl3_21.setIcon(new ImageIcon("pic//safe//dangerous1.jpg"));
			jl3_31.setText("");
			jl3_51.setText("");
			jl3_41.setText("安全状况存在隐患请定期修改密码");
			jl3_41.setForeground(Color.orange);
			jl3_41.setBounds(80, 150, 300, 20);
			jb3_alter.setActionCommand("立即完成设置密码");
		}
		else
		{
			jl3_21.setIcon(new ImageIcon("pic//safe//dangerous.jpg"));
			jl3_31.setText("");
			jl3_41.setText("");
			jl3_51.setText("安全状况危险请立刻设置密保问题");
			jl3_51.setForeground(Color.red);
			jl3_51.setBounds(80, 150, 300, 20);
			jb3_alter.setActionCommand("立即完成设置密保");
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if (e.getActionCommand().equals("修改密码"))
			{
				jt1_1.setText("");
				jt1_2.setText("");
				jt1_3.setText("");
				jpw_1.setText("");
				jpw_2.setText("");
				jpw_3.setText("");
				if (getQ.safe == null)
				{
					JOptionPane.showMessageDialog(null, "对不起，请设置密保问题后再来修改密码！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					// 提取已设置的密保问题
					if (getQ.changed != 0)
					{
						// 有密保问题
						if (getQ.changed == 1)
						{
							safeQusetion1 = getQ.safe.text1;
							safeQusetion2 = getQ.safe.text2;
							safeQusetion3 = getQ.safe.text3;
							jl1_7.setText("问题一：" + safeQusetion1);
							jl1_9.setText("问题二：" + safeQusetion2);
							jl1_11.setText("问题三：" + safeQusetion3);
							cardl.show(card, "修改密码");
							cardl2.show(jp1_card, "验证密保");
						}
						// 没有密保问题
						else
						{
							JOptionPane.showMessageDialog(null, "对不起，请设置密保问题后再来修改密码！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{}
				}
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			else if (e.getActionCommand().equals("我的密保"))
			{
				jt2_1.setText("");
				jt2_2.setText("");
				jt2_3.setText("");
				jt_1.setText("");
				jt_2.setText("");
				jt_3.setText("");
				jc_1.removeAllItems();
				for (int j = 0; j < 8; j++)
				{
					jc_1.addItem(question[j]);
				}
				jc_2.removeAllItems();
				for (int j = 0; j < 8; j++)
				{
					jc_2.addItem(question[j]);
				}
				jc_3.removeAllItems();
				for (int j = 0; j < 8; j++)
				{
					jc_3.addItem(question[j]);
				}
				if (getQ.safe == null)
				{
					cardl.show(card, "我的密保");
					cardl3.show(jp2_card, "修改密保2");
					jt_1.requestFocus();
				}
				else
				{
					if (getQ.changed != 0)
					{
						// 有密保问题
						if (getQ.changed == 1)
						{
							getQ.safe = new safeQuestionOrAnswer(getQ.safe);
							// 提取已设置的密保问题
							safeQusetion1 = getQ.safe.text1;
							safeQusetion2 = getQ.safe.text2;
							safeQusetion3 = getQ.safe.text3;
							jl2_11.setText("问题一：" + safeQusetion1);
							jl2_13.setText("问题二：" + safeQusetion2);
							jl2_15.setText("问题三：" + safeQusetion3);
							cardl.show(card, "我的密保");
						}
						// 没有密保问题
						else
						{
							cardl.show(card, "我的密保");
							cardl3.show(jp2_card, "修改密保2");
							jt_1.requestFocus();
						}
					}
				}
			}
			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			else if (e.getActionCommand().equals("安全概述"))
			{
				cardl.show(card, "安全概述");
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			else if (e.getActionCommand().equals("常见诈骗"))
			{
				cardl.show(card, "常见诈骗");
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			else if (e.getActionCommand().equals("立即完成设置密保"))
			{
				cardl.show(card, "我的密保");
				cardl3.show(jp2_card, "修改密保2");
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			else if (e.getActionCommand().equals("立即完成设置密码"))
			{
				if (getQ.safe == null)
				{
					JOptionPane.showMessageDialog(null, "对不起，请设置密保问题后再来修改密码！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					// 提取已设置的密保问题
					if (getQ.changed != 0)
					{
						// 有密保问题
						if (getQ.changed == 1)
						{
							safeQusetion1 = getQ.safe.text1;
							safeQusetion2 = getQ.safe.text2;
							safeQusetion3 = getQ.safe.text3;
							jl1_7.setText("问题一：" + safeQusetion1);
							jl1_9.setText("问题二：" + safeQusetion2);
							jl1_11.setText("问题三：" + safeQusetion3);
							cardl.show(card, "修改密码");
							cardl2.show(jp1_card, "验证密保");
						}
						// 没有密保问题
						else
						{
							JOptionPane.showMessageDialog(null, "对不起，请设置密保问题后再来修改密码！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{}
				}
				cardl.show(card, "修改密码");
				cardl2.show(jp1_card, "验证密保");
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// 修改密码时提交验证密保答案
			else if (e.getActionCommand().equals("提交"))
			{
				// 验证密保不能为空
				if (jt1_1.getText().equals("") || jt1_2.getText().equals("") || jt1_3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "对不起，密保问题回答不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					sendSafeAnswerThread sender_answer = new sendSafeAnswerThread(new safeQuestionOrAnswer(lmessage.qq, jt1_1.getText(), jt1_2.getText(), jt1_3.getText()));
					// 判断三个问题回答正确
					sender_answer.join(20000);
					if (sender_answer.password != null)
					{
						cardl2.show(jp1_card, "验证密保修改密码");
					}
					else
					{
						jt1_1.setText(" ");
						jt1_2.setText(" ");
						jt1_3.setText(" ");
						JOptionPane.showMessageDialog(null, "对不起，密保问题回答不能正确！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// 修改密码时提交新密码
			else if (e.getActionCommand().equals("提交2"))
			{
				// 与原密码匹配
				if (jpw_1.getText().equals(lmessage.password))
				{
					jl1_15.setText("");
					// 密码输入为空
					if (jpw_3.getText().equals("") || jpw_2.getText().equals(""))
					{
						jl1_17.setText("密码不能为空");
					}
					// 两次密码输入不一致
					else if (!jpw_2.getText().equals(jpw_3.getText()) && !jpw_2.getText().equals(""))
					{
						jpw_2.setText("");
						jpw_3.setText("");
						jl1_17.setText("两次密码输入不一致");
					}
					// 两次密码输入一致
					else if (jpw_2.getText().equals(jpw_3.getText()) && !jpw_2.getText().equals(""))
					{
						lmessage.password = jpw_2.getText();
						jl1_17.setText("");
						updatePasswordThread update = new updatePasswordThread(new updatePassword(lmessage.qq, jpw_2.getText()));
						checkSafe();
						cardl.show(card, "安全概述");
					}
				}
				// 与原密码不匹配
				else
				{
					jpw_1.setText("");
					jpw_2.setText("");
					jpw_3.setText("");
					jl1_15.setText("密码有误，请重新输入");
				}
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// 修改密保时提交验证密保答案
			else if (e.getActionCommand().equals("提交3"))
			{
				// 修改密保时验证密保问题不能为空
				if (jt2_1.getText().equals(""))
				{
					jl2_17.setText("*不能为空");
				}
				else
				{
					jl2_17.setText("");
				}
				if (jt2_2.getText().equals(""))
				{
					jl2_18.setText("*不能为空");
				}
				else
				{
					jl2_18.setText("");
				}
				if (jt2_3.getText().equals(""))
				{
					jl2_19.setText("*不能为空");
				}
				else
				{
					jl2_19.setText("");
				}
				if (!jt2_1.getText().equals("") && !jt2_2.getText().equals("") && !jt2_3.getText().equals(""))
				{
					sendSafeAnswerThread sender_answer = new sendSafeAnswerThread(new safeQuestionOrAnswer(lmessage.qq, jt2_1.getText(), jt2_2.getText(), jt2_3.getText()));
					// 判断三个问题回答正确
					sender_answer.join(20000);
					if (sender_answer.password != null)
					{
						cardl3.show(jp2_card, "修改密保2");
					}
					else
					{
						jl2_17.setText("*密保错误");
						jt2_1.setText(" ");
						jt2_2.setText(" ");
						jt2_3.setText(" ");
					}
				}
			}
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// 修改密保时提交新密保答案
			else if (e.getActionCommand().equals("提交4"))
			{
				int a = jc_1.getSelectedIndex();
				int b = jc_2.getSelectedIndex();
				int c = jc_3.getSelectedIndex();
				// 密保答案不能为空
				if (jt_2.getText().equals("") || jt_2.getText().equals("") || jt_3.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "对不起，密保问题答案不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				// 密保问题不能重复
				else if (a == b || a == c || b == c)
				{
					JOptionPane.showMessageDialog(null, "对不起，密保问题不能重复！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				// 像服务器发送信息
				else
				{
					sendSetSafeQuestionThread send_safe = new sendSetSafeQuestionThread(new safeQuestionAnswer(lmessage.qq, question[a], question[b], question[c], jt_1.getText(), jt_2.getText(),
							jt_3.getText()));
					// 发送成功
					send_safe.join(10000);
					if (send_safe.test != null)
					{
						getQ.changed = 1;
						getQ.safe = new safeQuestionOrAnswer(null, question[a], question[b], question[c]);
						checkSafe();
						cardl.show(card, "安全概述");
					}
				}
			}
		}
		catch (UnknownHostException e1)
		{}
		catch (IOException e1)
		{}//
		catch (InterruptedException ex)
		{}
	}
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
