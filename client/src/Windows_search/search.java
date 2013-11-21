package Windows_search;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.thread.searchThread.searchThread;

import common.message.mainInfo;
import common.message.personalSmallInfoList;
import common.message.searchInfo;
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
public class search extends JFrame implements ActionListener, KeyListener
{
	personalSmallInfoList	pList;
	private JPanel			jp_center, jp_center1, jp_center2, jp_buttom;
	private JLabel			jl_pic, jl_nickname, jl_user, jl_country, jl_city, jl_province, jl_sex, jl_language, jl_age, jl_qunnumber, jl_index, jl_limits;
	private JTextField		jt_nickname, jt_user, jt_qunnumber, jt_index;
	private JComboBox		jc_country, jc_city, jc_province, jc_sex, jc_language, jc_age, jc_limits;
	private JButton			jb_search, jb_cancel;
	private JCheckBox		jc;
	private ButtonGroup		radiogroup, radiogroup1;
	private JRadioButton	jr_1, jr_2, jr1_1, jr1_2;
	private Dimension		screenSize, frameSize;
	private JTabbedPane		jtp;
	private String			item_country[]		= { "---", "中国", "美国", "朝鲜", "韩国", "日本", "菲律宾", "越南", "老挝", "柬埔寨", "澳大利亚", "泰国", "南非", "英国", "法国", "德国", "西班牙", "葡萄牙", "意大利", "俄罗斯", "阿根廷", "巴西", "墨西哥" };
	private String			item_province[]		= { "---", "山东", "北京", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "天津", "河南", "湖南", "湖北", "广东", "广西", "海南", "重庆", "四川",
			"贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "香港", "澳门", "台湾" };
	private String			item_province1[]	= { "---", "洛杉矶", "休斯顿", "纽约", "芝加哥", "华盛顿", "西雅图", "旧金山", "费城", "亚特兰大" };
	private String			item_city[]			= { "---", "淄博", "青岛", "济南", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽" };
	private String			item_city1[]		= { "---", "东城区", "西城区", "朝阳区", "顺义区", "石景山区", "海淀区", "丰台区" };
	private String			item_language[]		= { "汉语", "英语", "日语", "韩语", "法语", "德语", "葡萄牙语", "俄罗斯语", "西班牙语" };
	private int				tabNum				= 0, age1, age2, sign, status = 0;
	private String			user				= "", nickname = "", country = "", province = "", city = "", sex = "", language = "";

	public search()
	{
		Image image = Toolkit.getDefaultToolkit().getImage("pic//headpic.jpg");
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前窗口大小
		frameSize = this.getPreferredSize();
		// 保持弹出窗口居中
		this.setLocation((screenSize.width - frameSize.width) / 5, (screenSize.height - frameSize.height) / 6);
		Container c = getContentPane();
		c.setLayout(null);
		// ////////////////////////////////////////////////////////////////////////////
		// 上部
		jl_pic = new JLabel();
		jl_pic.setIcon(new ImageIcon("pic//search//1.jpg"));
		jl_pic.setBounds(0, 0, 705, 92);
		// ///////////////////////////////////////////////////////////////////////////
		// 中部
		jp_center = new JPanel();
		jp_center.setBounds(0, 92, 705, 350);
		jp_center.setLayout(null);
		jp_center.setBackground(Color.yellow);
		radiogroup = new ButtonGroup();
		// 精确查找
		jr_1 = new JRadioButton();
		jr_1.setText("精确查找");
		jr_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jr_1.setSelected(true);
		jr_1.setBounds(60, 30, 110, 20);
		// 按条件查找
		jr_2 = new JRadioButton();
		jr_2.setText("按条件查找");
		jr_2.setFont(new Font("宋体", Font.PLAIN, 16));
		jr_2.setBounds(60, 70, 110, 20);
		radiogroup.add(jr_1);
		radiogroup.add(jr_2);
		jp_center.add(jr_1);
		jp_center.add(jr_2);
		// 账号
		jl_user = new JLabel();
		jl_user.setText("账号：");
		jl_user.setBounds(300, 30, 50, 20);
		jl_user.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_user);
		// 账号文本框
		jt_user = new JTextField();
		jt_user.setText("");
		jt_user.setBounds(370, 30, 180, 20);
		jp_center.add(jt_user);
		jt_user.addKeyListener(this);
		// 昵称
		jl_nickname = new JLabel();
		jl_nickname.setText("昵称：");
		jl_nickname.setBounds(300, 70, 50, 20);
		jl_nickname.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_nickname);
		// 昵称文本框
		jt_nickname = new JTextField();
		jt_nickname.setText("");
		jt_nickname.setBounds(370, 70, 180, 20);
		jp_center.add(jt_nickname);
		jt_nickname.addKeyListener(this);
		// 地址--国家
		jl_country = new JLabel();
		jl_country.setText("国家：");
		jl_country.setBounds(120, 120, 50, 20);
		jl_country.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_country);
		jc_country = new JComboBox();
		for (int i = 0; i < 22; i++)
		{
			jc_country.addItem(item_country[i]);
		}
		jc_country.setBounds(120, 150, 150, 20);
		jc_country.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_country.setEnabled(false);
		jp_center.add(jc_country);
		// 地址--省份
		jl_province = new JLabel();
		jl_province.setText("省份：");
		jl_province.setBounds(400, 120, 50, 20);
		jl_province.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_province);
		jc_province = new JComboBox();
		for (int i = 0; i < 34; i++)
		{
			jc_province.addItem(item_province[i]);
		}
		jc_province.setBounds(400, 150, 150, 20);
		jc_province.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_province.setEnabled(false);
		jp_center.add(jc_province);
		// 地址--城市
		jl_city = new JLabel();
		jl_city.setText("城市：");
		jl_city.setBounds(120, 190, 50, 20);
		jl_city.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_city);
		jc_city = new JComboBox();
		for (int i = 0; i < 17; i++)
		{
			jc_city.addItem(item_city[i]);
		}
		jc_city.setBounds(120, 220, 150, 20);
		jc_city.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_city.setEnabled(false);
		jp_center.add(jc_city);
		// 年龄
		jl_age = new JLabel();
		jl_age.setText("年龄：");
		jl_age.setBounds(400, 190, 50, 20);
		jl_age.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_age);
		jc_age = new JComboBox();
		jc_age.addItem("0-16岁");
		jc_age.addItem("16-22岁");
		jc_age.addItem("23-30岁");
		jc_age.addItem("31-40岁");
		jc_age.addItem("40岁以上");
		jc_age.setBounds(400, 220, 70, 20);
		jc_age.setFont(new Font("宋体", Font.PLAIN, 12));
		jc_age.setEnabled(false);
		jp_center.add(jc_age);
		// 性别
		jl_sex = new JLabel();
		jl_sex.setText("性别：");
		jl_sex.setBounds(480, 190, 50, 20);
		jl_sex.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_sex);
		jc_sex = new JComboBox();
		jc_sex.addItem("男");
		jc_sex.addItem("女");
		jc_sex.setBounds(480, 220, 70, 20);
		jc_sex.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_sex.setEnabled(false);
		jp_center.add(jc_sex);
		// 语言
		jl_language = new JLabel();
		jl_language.setText("语言：");
		jl_language.setBounds(120, 260, 50, 20);
		jl_language.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jl_language);
		jc_language = new JComboBox();
		for (int i = 0; i < 9; i++)
		{
			jc_language.addItem(item_language[i]);
		}
		jc_language.setBounds(120, 290, 150, 20);
		jc_language.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_language.setEnabled(false);
		jp_center.add(jc_language);
		// 状态
		jc = new JCheckBox();
		jc.setBounds(400, 280, 60, 20);
		jc.setText("在线");
		jc.setEnabled(false);
		jc.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_center.add(jc);
		// 面板2---查找群
		jp_center1 = new JPanel();
		jp_center1.setBounds(0, 92, 705, 350);
		jp_center1.setLayout(null);
		jp_center1.setBackground(Color.yellow);
		radiogroup1 = new ButtonGroup();
		// 面板2---查找群--精确查找
		jr1_1 = new JRadioButton();
		jr1_1.setText("精确查找");
		jr1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jr1_1.setSelected(true);
		jr1_1.setBounds(60, 30, 110, 20);
		// 面板2---查找群--按条件查找
		jr1_2 = new JRadioButton();
		jr1_2.setText("按条件查找");
		jr1_2.setFont(new Font("宋体", Font.PLAIN, 16));
		jr1_2.setBounds(60, 70, 110, 20);
		radiogroup1.add(jr1_1);
		radiogroup1.add(jr1_2);
		jp_center1.add(jr1_1);
		jp_center1.add(jr1_2);
		// 面板2---查找群--群账号
		jl_qunnumber = new JLabel();
		jl_qunnumber.setText("群账号：");
		jl_qunnumber.setFont(new Font("宋体", Font.PLAIN, 16));
		jl_qunnumber.setBounds(250, 70, 80, 20);
		jp_center1.add(jl_qunnumber);
		// 面板2---查找群--群账号框
		jt_qunnumber = new JTextField();
		jt_qunnumber.setText("");
		jt_qunnumber.setBounds(350, 70, 180, 20);
		jp_center1.add(jt_qunnumber);
		jt_qunnumber.addKeyListener(this);
		// 面板2---查找群--查找关键字
		jl_index = new JLabel();
		jl_index.setText("查找关键字：");
		jl_index.setFont(new Font("宋体", Font.PLAIN, 16));
		jl_index.setBounds(60, 125, 120, 20);
		jp_center1.add(jl_index);
		// 面板2---查找群--查找关键字
		jt_index = new JTextField();
		jt_index.setText("");
		jt_index.setBounds(60, 165, 180, 20);
		jt_index.setEnabled(false);
		jt_index.setBackground(null);
		jp_center1.add(jt_index);
		jt_index.addKeyListener(this);
		// 面板2---查找群--查找范围
		jl_limits = new JLabel();
		jl_limits.setText("查找范围：");
		jl_limits.setFont(new Font("宋体", Font.PLAIN, 16));
		jl_limits.setBounds(60, 205, 120, 20);
		jp_center1.add(jl_limits);
		jc_limits = new JComboBox();
		jc_limits.addItem("不限");
		jc_limits.addItem("游戏");
		jc_limits.addItem("学习");
		jc_limits.addItem("购物");
		jc_limits.addItem("影视");
		jc_limits.addItem("老乡会");
		jc_limits.setBounds(60, 245, 180, 20);
		jc_limits.setFont(new Font("宋体", Font.PLAIN, 13));
		jc_limits.setEnabled(false);
		jp_center1.add(jc_limits);
		// 面板3--查找企业
		jp_center2 = new JPanel();
		jp_center2.setBounds(0, 92, 705, 350);
		jp_center2.setLayout(null);
		jp_center2.setBackground(Color.yellow);
		JLabel jl_1 = new JLabel();
		jl_1.setText("对不起,该功能还未开放");
		jl_1.setFont(new Font("宋体", Font.PLAIN, 17));
		jl_1.setForeground(Color.red);
		jl_1.setBounds(200, 20, 200, 30);
		jp_center2.add(jl_1);
		jtp = new JTabbedPane();
		jtp.setBounds(0, 92, 705, 350);
		jtp.add("查找联系人", jp_center);
		jtp.add("查找群", jp_center1);
		jtp.add("查找企业", jp_center2);
		jtp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				tabNum = jtp.getSelectedIndex();
			}
		});
		// 下部
		jp_buttom = new JPanel();
		jp_buttom.setBounds(0, 442, 705, 58);
		jp_buttom.setBackground(Color.yellow);
		// 下部--查找按钮
		jp_buttom.setLayout(null);
		jb_search = new JButton();
		jb_search.setBounds(450, 5, 68, 20);
		jb_search.setIcon(new ImageIcon("pic//search//search.jpg"));
		jp_buttom.add(jb_search);
		// 下部--取消按钮
		jb_cancel = new JButton();
		jb_cancel.setBounds(600, 5, 68, 22);
		jb_cancel.setIcon(new ImageIcon("pic//search//cancel.jpg"));
		jb_cancel.addActionListener(this);
		jb_cancel.setActionCommand("cancel");
		jp_buttom.add(jb_cancel);
		// 响应
		// 中部--面板1
		jr_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jr_1itemStateChanged(e);
			}
		});
		// 中部--面板1
		jr_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jr_2itemStateChanged(e);
			}
		});
		// 中部--面板2
		jr1_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jr1_1itemStateChanged(e);
			}
		});
		// 中部--面板2
		jr1_2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				jr1_2itemStateChanged(e);
			}
		});
		// 国家选项
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
		// 下部--查找
		jb_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					jb_searchactionPerformed(e);
				}
				catch (UnknownHostException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (InterruptedException ex)
				{
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		c.add(jl_pic);
		c.add(jtp);
		c.add(jp_buttom);
		setResizable(false);
		setSize(705, 500);
		setVisible(true);
		setTitle("查找联系人/群");
		this.addKeyListener(this);
	}

	public void actionPerform(ActionEvent e)
	{
		if (e.getActionCommand().equals("cancel"))
		{
			this.dispose();
		}
	}

	// 面板1
	private void jr_1itemStateChanged(ItemEvent e)
	{
		if (jr_1.isSelected())
		{
			jc.setEnabled(false);
			jt_user.setEnabled(true);
			jt_nickname.setEnabled(true);
			jt_user.setBackground(Color.white);
			jt_nickname.setBackground(Color.white);
			jc_country.setEnabled(false);
			jc_province.setEnabled(false);
			jc_city.setEnabled(false);
			jc_age.setEnabled(false);
			jc_sex.setEnabled(false);
			jc_language.setEnabled(false);
		}
	}

	// 面板1
	private void jr_2itemStateChanged(ItemEvent e)
	{
		if (jr_2.isSelected())
		{
			jc.setEnabled(true);
			jc_country.removeAllItems();
			for (int i = 0; i < 22; i++)
			{
				jc_country.addItem(item_country[i]);
			}
			jc_province.removeAllItems();
			jc_province.addItem("---");
			jc_city.removeAllItems();
			jc_city.addItem("---");
			jt_user.setEnabled(false);
			jt_nickname.setEnabled(false);
			jt_user.setBackground(null);
			jt_nickname.setBackground(null);
			jc_country.setEnabled(true);
			jc_province.setEnabled(false);
			jc_city.setEnabled(false);
			jc_age.setEnabled(true);
			jc_sex.setEnabled(true);
			jc_language.setEnabled(true);
		}
	}

	// 面板2
	private void jr1_1itemStateChanged(ItemEvent e)
	{
		if (jr1_1.isSelected())
		{
			jc_limits.setEnabled(false);
			jt_index.setEnabled(false);
			jt_index.setBackground(null);
			jt_qunnumber.setEnabled(true);
			jt_qunnumber.setBackground(Color.white);
		}
	}

	// 面板2
	private void jr1_2itemStateChanged(ItemEvent e)
	{
		if (jr1_2.isSelected())
		{
			jc_limits.setEnabled(true);
			jt_index.setEnabled(true);
			jt_index.setBackground(Color.white);
			jt_qunnumber.setEnabled(false);
			jt_qunnumber.setBackground(null);
		}
	}

	// 国家选项
	private void jc_countryItemStateChanged(ItemEvent e)
	{
		for (int i = 0; i < 22; i++)
		{
			if (e.getItem() == "---")
			{
				jc_province.setEnabled(false);
				jc_city.setEnabled(false);
			}
			else if (e.getItem() == "中国")
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
			if (e.getItem() == "---")
			{
				jc_city.setEnabled(false);
			}
			else if (e.getItem() == "山东")
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

	// 查看按钮
	private void jb_searchactionPerformed(ActionEvent e) throws UnknownHostException, IOException, InterruptedException
	{
		action();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("cancel"))
		{
			dispose();
		}
	}

	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			action();
		}
	}

	public void keyPressed(KeyEvent e)
	{}

	void action()
	{
		int mark = 1;
		if (tabNum == 0) // tabNum = 0查找联系人
		{
			if (jr_1.isSelected()) // 查找联系人 ----- 精确查找
			{
				sign = 1;
				if (jt_user.getText().equals("") && jt_nickname.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "请至少填一个查询条件", "警告", JOptionPane.WARNING_MESSAGE);
					mark = 0;
				}
				if (!jt_user.getText().equals(""))
				{
					user = jt_user.getText();
				}
				else
				{
					user = null;
				}
				if (!jt_nickname.getText().equals(""))
				{
					nickname = jt_nickname.getText();
				}
				else
				{
					nickname = null;
				}
			}
			else if (jr_2.isSelected()) // 查找联系人 ----- 条件查找
			{
				sign = 2;
				// 地区
				country = item_country[jc_country.getSelectedIndex()];
				if (country.equals("中国"))
				{
					province = item_province[jc_province.getSelectedIndex()];
					if (province.equals("山东"))
					{
						if (!item_city[jc_city.getSelectedIndex()].equals("---"))
						{
							city = item_city[jc_city.getSelectedIndex()];
						}
						else
						{
							city = null;
						}
					}
					else if (province.equals("北京"))
					{
						if (!item_city[jc_city.getSelectedIndex()].equals("---"))
						{
							city = item_city1[jc_city.getSelectedIndex()];
						}
						else
						{
							city = null;
						}
					}
					else if (province.equals("---"))
					{
						province = null;
						city = null;
					}
				}
				else if (country.equals("美国"))
				{
					if (!province.equals("---"))
					{
						province = item_province1[jc_province.getSelectedIndex()];
					}
					else if (province.equals("---"))
					{
						province = null;
						city = null;
					}
					city = null;
				}
				else if (country.equals("---"))
				{
					country = null;
					province = null;
					city = null;
				}
				// 性别
				if (jc_sex.getSelectedIndex() == 0)
				{
					sex = "男";
				}
				else
				{
					sex = "女";
				}
				// 年龄
				if (jc_age.getSelectedIndex() == 0)
				{
					age1 = 0;
					age2 = 15;
				}
				else if (jc_age.getSelectedIndex() == 1)
				{
					age1 = 16;
					age2 = 22;
				}
				else if (jc_age.getSelectedIndex() == 2)
				{
					age1 = 23;
					age2 = 30;
				}
				else if (jc_age.getSelectedIndex() == 3)
				{
					age1 = 31;
					age2 = 40;
				}
				else
				{
					age1 = 41;
					age2 = 200;
				}
				// 语言
				language = item_language[jc_language.getSelectedIndex()];
				if (jc.isSelected())
				{
					status = 1;
				}
				else
				{
					status = 0;
				}
			}
		}
		else if (tabNum == 1) // tabNum = 1 查找群
		{
			if (jr1_1.isSelected()) // 查找群 ----- 精确查找
			{
				sign = 1;
				if (jt_qunnumber.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "你输入的群号码有误，请重新输入!", "警告", JOptionPane.WARNING_MESSAGE);
					mark = 0;
				}
			}
			else if (jr1_2.isSelected()) // 查找群 ----- 条件查找
			{
				sign = 2;
			}
		}
		if (mark == 1)
		{
			searchInfo si = new searchInfo(sign, user, nickname, sex, age1, age2, country, province, city, status, language);
			searchThread find = null;
			try
			{
				find = new searchThread(si);
				find.join(10000);
				pList = find.pList;
			}
			catch (UnknownHostException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mainInfo mInfo = null;
			search_result sr = new search_result(pList);
		}
	}

	public void keyReleased(KeyEvent e)
	{}
}
