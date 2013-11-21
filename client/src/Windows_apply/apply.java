package Windows_apply;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.event.*;

import Windows_login.login;


import client.thread.applicateThread.applicateClientThread;

import common.message.personalInfo;
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
public class apply extends JFrame
{
	class judgeThread extends Thread
	{
		int						changed	= 0;
		applicateClientThread	t;

		public judgeThread(applicateClientThread t)
		{
			this.t = t;
		}

		public void run()
		{
			try
			{
				Thread.sleep(120000);
				if (t.changed == 0)
				{
					t.stop();
					changed = 1;
					this.stop();
				}
			}
			catch (InterruptedException e)
			{}
		}
	}

	private JPanel			jp1, jp2, jp3, card;
	private JLabel			jl_star;
	private JLabel			jl_nickname, jl_birthday, jl_sex, jl_password1, jl_password2, jl_address, jl_year, jl_month, jl_day, jl_1, jl_accept, jl_wait, jl3_1, jl3_2, jl3_3, jl3_4, jl3_5, jl3_6,
			jl3_7, jl3_8;
	private JTextField		jt_nickname;
	private JPasswordField	jt_password1, jt_password2;
	private JRadioButton	jr_man, jr_woman;
	private JComboBox		jc_year, jc_month, jc_day, jc_country, jc_province, jc_city;
	private JButton			jb_sure, jb_login;
	private ButtonGroup		radiogroup;
	private String			item_country[]		= { "中国", "美国", "朝鲜", "韩国", "日本", "菲律宾", "越南", "老挝", "柬埔寨", "澳大利亚", "泰国", "南非", "英国", "法国", "德国", "西班牙", "葡萄牙", "意大利", "俄罗斯", "阿根廷", "巴西", "墨西哥" };
	private String			item_province[]		= { "山东", "北京", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "天津", "河南", "湖南", "湖北", "广东", "广西", "海南", "重庆", "四川", "贵州",
			"云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "香港", "澳门", "台湾" };
	private String			item_province1[]	= { "洛杉矶", "休斯顿", "纽约", "芝加哥", "华盛顿", "西雅图", "旧金山", "费城", "亚特兰大" };
	private String			item_city[]			= { "淄博", "青岛", "济南", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽" };
	private String			item_city1[]		= { "东城区", "西城区", "朝阳区", "顺义区", "石景山区", "海淀区", "丰台区" };
	private JCheckBox		jc;
	private CardLayout		cardl;
	private int				year, month, day;
	private String			QQ					= "", nickname = "", password = "", sex = "", country = "", province = "", city = "";

	public apply()
	{
		Image image = Toolkit.getDefaultToolkit().getImage("pic//headpic.jpg");
		setIconImage(image);
		Container container = getContentPane();
		// 设置背景图片
		JLabel jl_down = new JLabel();
		// 把背景图片显示在一个标签里面
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(700, 500);
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		cardl = new CardLayout();
		card = new JPanel();
		card.setBounds(0, 0, 700, 500);
		card.setOpaque(false);
		card.setLayout(cardl);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		card.add("注册", jp1);
		card.add("等待", jp2);
		card.add("成功", jp3);
		c.add(card);
		// 面板1
		jp1.setLayout(null);
		jp1.setOpaque(false);
		int l = 50;
		for (int i = 1; i <= 6; i++)
		{
			jl_star = new JLabel();
			jl_star.setText("*");
			jl_star.setForeground(Color.red);
			jl_star.setBounds(75, l * i, 10, 20);
			jl_star.setFont(new Font("宋体", Font.PLAIN, 18));
			jp1.add(jl_star);
		}
		// 昵称
		jl_nickname = new JLabel();
		jl_nickname.setText("昵称:");
		jl_nickname.setBounds(85, 50, 90, 20);
		jl_nickname.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_nickname);
		// 昵称框
		jt_nickname = new JTextField();
		jt_nickname.setBounds(175, 50, 150, 20);
		jt_nickname.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jt_nickname);
		// 生日
		jl_birthday = new JLabel();
		jl_birthday.setText("生日：");
		jl_birthday.setBounds(85, 100, 90, 20);
		jl_birthday.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_birthday);
		// 生日--年
		jc_year = new JComboBox();
		for (int i = 1892; i <= 2012; i++)
		{
			jc_year.addItem(String.valueOf(i));
		}
		jc_year.setBounds(175, 100, 70, 20);
		jc_year.setFont(new Font("宋体", Font.PLAIN, 15));
		jp1.add(jc_year);
		jl_year = new JLabel();
		jl_year.setText("年");
		jl_year.setBounds(250, 100, 20, 20);
		jl_year.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_year);
		// 生日--月
		jc_month = new JComboBox();
		for (int i = 1; i <= 12; i++)
		{
			jc_month.addItem(String.valueOf(i));
		}
		jc_month.setBounds(270, 100, 50, 20);
		jc_month.setFont(new Font("宋体", Font.PLAIN, 15));
		jp1.add(jc_month);
		jl_month = new JLabel();
		jl_month.setText("月");
		jl_month.setBounds(320, 100, 20, 20);
		jl_month.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_month);
		// 生日--日
		jc_day = new JComboBox();
		for (int i = 1; i <= 31; i++)
		{
			jc_day.addItem(String.valueOf(i));
		}
		jc_day.setBounds(340, 100, 50, 20);
		jc_day.setFont(new Font("宋体", Font.PLAIN, 15));
		jp1.add(jc_day);
		jl_day = new JLabel();
		jl_day.setText("日");
		jl_day.setBounds(390, 100, 20, 20);
		jl_day.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_day);
		// 性别
		jl_sex = new JLabel();
		jl_sex.setText("性别：");
		jl_sex.setBounds(85, 150, 90, 20);
		jl_sex.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_sex);
		// 性别--男女
		radiogroup = new ButtonGroup();
		jr_man = new JRadioButton("男");
		jr_man.setSelected(true);
		jr_man.setBounds(175, 150, 40, 20);
		jr_woman = new JRadioButton("女");
		jr_woman.setBounds(215, 150, 40, 20);
		radiogroup.add(jr_man);
		radiogroup.add(jr_woman);
		jp1.add(jr_man);
		jp1.add(jr_woman);
		// 密码
		jl_password1 = new JLabel();
		jl_password1.setText("密码：");
		jl_password1.setBounds(85, 200, 90, 20);
		jl_password1.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_password1);
		// 密码框
		jt_password1 = new JPasswordField();
		jt_password1.setBounds(175, 200, 150, 20);
		jt_password1.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jt_password1);
		// 密码要求
		jl_1 = new JLabel();
		jl_1.setText("( 6-20个字符组成，区分大小写 )");
		jl_1.setBounds(330, 200, 600, 20);
		jl_1.setForeground(Color.red);
		jl_1.setFont(new Font("宋体", Font.PLAIN, 12));
		jp1.add(jl_1);
		// 确认密码
		jl_password2 = new JLabel();
		jl_password2.setText("确认密码：");
		jl_password2.setBounds(85, 250, 90, 20);
		jl_password2.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_password2);
		// 确认密码框
		jt_password2 = new JPasswordField();
		jt_password2.setBounds(175, 250, 150, 20);
		jt_password2.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jt_password2);
		// 地址
		jl_address = new JLabel();
		jl_address.setText("地址：");
		jl_address.setBounds(85, 300, 90, 20);
		jl_address.setFont(new Font("宋体", Font.PLAIN, 18));
		jp1.add(jl_address);
		// 地址--国家
		jc_country = new JComboBox();
		for (int i = 0; i < 22; i++)
		{
			jc_country.addItem(item_country[i]);
		}
		jc_country.setBounds(175, 300, 70, 20);
		jc_country.setFont(new Font("宋体", Font.PLAIN, 13));
		jp1.add(jc_country);
		// 地址--省份
		jc_province = new JComboBox();
		for (int i = 0; i < 34; i++)
		{
			jc_province.addItem(item_province[i]);
		}
		jc_province.setBounds(250, 300, 70, 20);
		jc_province.setFont(new Font("宋体", Font.PLAIN, 13));
		jp1.add(jc_province);
		// 地址--城市
		jc_city = new JComboBox();
		for (int i = 0; i < 17; i++)
		{
			jc_city.addItem(item_city[i]);
		}
		jc_city.setBounds(325, 300, 70, 20);
		jc_city.setFont(new Font("宋体", Font.PLAIN, 13));
		jp1.add(jc_city);
		// 同意相关服务条款
		jc = new JCheckBox();
		jc.setBounds(180, 350, 20, 20);
		jc.setSelected(true);
		jp1.add(jc);
		jl_accept = new JLabel();
		jl_accept.setBounds(200, 350, 200, 20);
		jl_accept.setText("我已阅读并同意相关服务条款");
		jl_accept.setFont(new Font("宋体", Font.PLAIN, 14));
		jp1.add(jl_accept);
		// 注册
		jb_sure = new JButton();
		jb_sure.setIcon(new ImageIcon("pic//zhuce.gif"));
		jb_sure.setBounds(180, 380, 69, 22);
		jp1.add(jb_sure);
		// 面板2
		jp2.setLayout(null);
		jp2.setOpaque(false);
		jl_wait = new JLabel();
		jl_wait.setText("正在申请中........");
		jl_wait.setFont(new Font("宋体", Font.PLAIN, 16));
		jl_wait.setBounds(200, 100, 300, 20);
		jp2.add(jl_wait);
		// 面板3
		jp3.setLayout(null);
		jp3.setOpaque(false);
		jl3_1 = new JLabel();
		jl3_1.setIcon(new ImageIcon("pic//apply//success.gif"));
		jl3_1.setBounds(100, 50, 60, 50);
		jp3.add(jl3_1);
		jl3_2 = new JLabel();
		jl3_2.setText("申请成功");
		jl3_2.setBounds(160, 50, 100, 20);
		jl3_2.setFont(new Font("宋体", Font.PLAIN, 20));
		jp3.add(jl3_2);
		jl3_3 = new JLabel();
		jl3_3.setText("你获得号码是：");
		jl3_3.setBounds(160, 80, 200, 20);
		jl3_3.setFont(new Font("宋体", Font.PLAIN, 20));
		jp3.add(jl3_3);
		// QQ号
		jl3_4 = new JLabel();
		jl3_4.setBounds(300, 80, 200, 20);
		jl3_4.setFont(new Font("宋体", Font.PLAIN, 25));
		jl3_4.setForeground(Color.RED);
		jp3.add(jl3_4);
		// 密码
		jl3_5 = new JLabel();
		jl3_5.setBounds(160, 110, 140, 20);
		jl3_5.setFont(new Font("宋体", Font.PLAIN, 20));
		jp3.add(jl3_5);
		jl3_6 = new JLabel();
		jl3_6.setBounds(300, 110, 100, 20);
		jl3_6.setFont(new Font("宋体", Font.PLAIN, 20));
		jl3_6.setForeground(Color.RED);
		jp3.add(jl3_6);
		jl3_7 = new JLabel();
		jl3_7.setBounds(380, 110, 100, 20);
		jl3_7.setFont(new Font("宋体", Font.PLAIN, 20));
		jp3.add(jl3_7);
		// 登陆QQ
		jb_login = new JButton();
		jb_login.setBounds(160, 170, 180, 39);
		jb_login.setIcon(new ImageIcon("pic//apply//login.jpg"));
		jp3.add(jb_login);
		// 广告
		jl3_8 = new JLabel();
		jl3_8.setBounds(160, 230, 290, 220);
		jl3_8.setIcon(new ImageIcon("pic//apply//ad.jpg"));
		jp3.add(jl3_8);
		// 响应
		// 注册按钮
		jb_sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jb_sureactionPerformed(e);
			}
		});
		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jb_loginactionPerformed(e);
			}
		});
		jc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jcitemStateChanged(e);
			}
		});
		jc_country.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jc_countryItemStateChanged(e);
			}
		});
		// 省份选项
		jc_province.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jc_provinceItemStateChanged(e);
			}
		});
		getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		Toolkit tk = this.getToolkit();// 得到窗口工具条
		Dimension dm = tk.getScreenSize();
		setLocation((int) (dm.getWidth() / 5.5), (int) (dm.getHeight() / 5.5));
		setResizable(false);
		setSize(700, 500);
		setVisible(true);
		setTitle("申请QQ账号");
	}

	// 国家选项
	private void jc_countryItemStateChanged(ItemEvent e)
	{
		for (int i = 0; i < 22; i++)
		{
			if (e.getItem() == "中国")
			{
				jc_province.setEnabled(true);
				jc_city.setEnabled(true);
				jc_province.removeAllItems();
				for (int j = 0; j < 34; j++)
				{
					jc_province.addItem(item_province[j]);
				}
			}
			else if (e.getItem() == "美国")
			{
				jc_province.setEnabled(true);
				jc_city.setEnabled(false);
				jc_province.removeAllItems();
				for (int j = 0; j < 9; j++)
				{
					jc_province.addItem(item_province1[j]);
				}
			}
			else
			{
				jc_province.setEnabled(false);
				jc_city.setEnabled(false);
			}
		}
	}

	// 省份选项
	private void jc_provinceItemStateChanged(ItemEvent e)
	{
		for (int i = 0; i < 34; i++)
		{
			if (e.getItem() == "山东")
			{
				jc_city.setEnabled(true);
				jc_city.removeAllItems();
				for (int j = 0; j < 17; j++)
				{
					jc_city.addItem(item_city[j]);
				}
			}
			else if (e.getItem() == "北京")
			{
				jc_city.setEnabled(true);
				jc_city.removeAllItems();
				for (int j = 0; j < 7; j++)
				{
					jc_city.addItem(item_city1[j]);
				}
			}
			else
			{
				jc_city.setEnabled(false);
			}
		}
	}

	// 登陆QQ
	private void jb_loginactionPerformed(ActionEvent e)
	{
		// dispose();
		login l = new login();
		l.setUser(QQ);
	}

	// 新用户注册
	private void jb_sureactionPerformed(ActionEvent e)
	{
		if (jt_nickname.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "请输入昵称！", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (jt_password1.getText().length() >= 20 || jt_password1.getText().length() < 6)
		{
			JOptionPane.showMessageDialog(null, "密码必须6-20个字符！", "提示", JOptionPane.INFORMATION_MESSAGE);
			jt_password1.setText("");
			jt_password2.setText("");
		}
		else if (!jt_password1.getText().equals(jt_password2.getText()))
		{
			JOptionPane.showMessageDialog(null, "两次密码输入不一致", "提示", JOptionPane.INFORMATION_MESSAGE);
			jt_password1.setText("");
			jt_password2.setText("");
		}
		else
		{
			// 获取注册信息
			nickname = jt_nickname.getText();
			year = jc_year.getSelectedIndex() + 1892;
			month = jc_month.getSelectedIndex() + 1;
			day = jc_day.getSelectedIndex() + 1;
			if (jr_man.isSelected())
			{
				sex = "男";
			}
			else
			{
				sex = "女";
			}
			password = jt_password1.getText();
			country = item_country[jc_country.getSelectedIndex()];
			if (country.equals("中国"))
			{
				province = item_province[jc_province.getSelectedIndex()];
				if (province.equals("山东"))
				{
					city = item_city[jc_city.getSelectedIndex()];
				}
				else if (province.equals("北京"))
				{
					city = item_city1[jc_city.getSelectedIndex()];
				}
			}
			else if (country.equals("美国"))
			{
				province = item_province1[jc_province.getSelectedIndex()];
				city = null;
			}
			cardl.show(card, "等待");
			// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			personalInfo person = new personalInfo(password, nickname, sex, year, month, day, country, province, city);
			applicateClientThread appliate;
			try
			{
				appliate = new applicateClientThread(person);
				judgeThread judge = new judgeThread(appliate);
				judge.start();
				appliate.join(15000);
				if (appliate.changed == 1)
				{
					QQ = appliate.getQQ;
					jl3_4.setText(QQ);
					jl3_5.setText("你的密码是：");
					jl3_6.setText(password);
					jl3_7.setText("  请牢记！");
					cardl.show(card, "成功");
				}
				else if (appliate.changed == 2)
				{
					jl_wait.setText("对不起，同一个IP申请过多");
				}
				else if (appliate.changed == 4)
				{
					jl_wait.setText("对不起，服务器还没有开启......");
				}
				else
				{
					jl_wait.setText("对不起，系统繁忙中.......");
				}
			}
			catch (Exception ex)
			{}
		}
	}

	// 同意相关服务条款
	private void jcitemStateChanged(ItemEvent e)
	{
		if (e.getStateChange() != e.SELECTED)
		{
			jb_sure.setEnabled(false);
		}
		else
		{
			jb_sure.setEnabled(true);
		}
	}
}
