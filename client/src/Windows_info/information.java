package Windows_info;

import Windows_MainInterface.MainInterface;
import client.thread.updatePersonalInfoThread.sendUpdateInfo;
import common.message.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
public class information extends JFrame implements ActionListener, WindowListener
{
	Container			c_temp					= this.getContentPane();
	Container			c_temp_mainInterface;
	JPanel				c;
	JLabel				jl_down;
	private String		s1						= "pic//face//", s2 = ".JPG";
	private Dimension	screenSize, frameSize;
	private JComboBox	sex_option, province_option, country_option, city_option, animal_option, year_option, month_option, day_option, age_option, constellation_option, blood_option,
			language_option, job_option;
	private JPanel		card, jp_left, jp_1, jp_2, jp_3, jp_buttom;
	private JLabel		jl_head, jl1_1, jl1_2, jl1_3, jl1_4, jl1_5, jl1_6, jl1_7, jl1_8, jl1_9, jl1_10, jl1_11, jl1_12, jl1_13, jl1_0, jl2_1, jl2_2, jl2_3, jl2_4, jl2_5, jl2_6;
	private JButton		jb_head, jb_1, jb_2, jb_3, jb_sure, jb_cancel;
	private JTextField	jt_nickname, jt_user, jt_telephone, jt_mail, jt_collage;
	private JTextArea	jt_sign, jt_person;
	private CardLayout	cardl;
	private String		animal_style[]			= { "---", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
	private String		constellation_style[]	= { "---", "水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
	private String		blood_style[]			= { "---", "A", "B", "AB", "O" };
	private String		country_style[]			= { "---", "中国", "美国", "朝鲜", "韩国", "日本", "菲律宾", "越南", "老挝", "柬埔寨", "澳大利亚", "泰国", "南非", "英国", "法国", "德国", "西班牙", "葡萄牙", "意大利", "俄罗斯", "阿根廷", "巴西", "墨西哥" };
	private String		province_style[]		= { "---", "山东", "北京", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "天津", "河南", "湖南", "湖北", "广东", "广西", "海南", "重庆", "四川",
			"贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "香港", "澳门", "台湾" };
	private String		province1_style[]		= { "---", "洛杉矶", "休斯顿", "纽约", "芝加哥", "华盛顿", "西雅图", "旧金山", "费城", "亚特兰大" };
	private String		city_style[]			= { "---", "青岛", "淄博", "济南", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "莱芜", "临沂", "德州", "聊城", "滨州", "菏泽" };
	private String		city1_style[]			= { "---", "东城区", "西城区", "朝阳区", "顺义区", "石景山区", "海淀区", "丰台区" };
	personalInfo		pinfo;
	MainInterface		main_frame;

	public information(personalInfo pinfo, Container c_temp1, MainInterface mainFrame)
	{
		this.pinfo = pinfo;
		c_temp_mainInterface = c_temp1;
		this.main_frame = mainFrame;
		Image image = Toolkit.getDefaultToolkit().getImage("pic//face//b.jpg");
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前窗口大小
		frameSize = this.getPreferredSize();
		// 保持弹出窗口居中
		this.setLocation((screenSize.width - frameSize.width) / 5, (screenSize.height - frameSize.height) / 6);
		Container container = getContentPane();
		// 设置背景图片
		jl_down = new JLabel();
		// 把背景图片显示在一个标签里面
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(600, 450);// setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		// 左部
		jp_left = new JPanel();
		jp_left.setBounds(0, 0, 150, 380);
		jp_left.setLayout(null);
		jp_left.setOpaque(false);
		c.add(jp_left);
		// 头像
		jl_head = new JLabel();
		jl_head.setBounds(25, 10, 100, 100);
		jl_head.setIcon(new ImageIcon(s1 + pinfo.headImage + "_4" + s2));
		jp_left.add(jl_head);
		// 更换头像JButton
		jb_head = new JButton();
		jb_head.setBounds(30, 115, 90, 20);
		jb_head.setText("更换头像");
		jb_head.setActionCommand("更换头像");
		jb_head.addActionListener(this);
		jp_left.add(jb_head);
		// 基本资料
		jb_1 = new JButton();
		jb_1.setBounds(25, 160, 100, 25);
		jb_1.setText("基本资料");
		jb_1.setActionCommand("基本资料");
		jb_1.addActionListener(this);
		jp_left.add(jb_1);
		// 更多资料
		jb_2 = new JButton();
		jb_2.setBounds(25, 200, 100, 25);
		jb_2.setText("更多资料");
		jb_2.setActionCommand("更多资料");
		jb_2.addActionListener(this);
		jp_left.add(jb_2);
		// QQ秀
		jb_3 = new JButton();
		jb_3.setBounds(25, 240, 100, 25);
		jb_3.setText("QQ秀");
		jb_3.setActionCommand("QQ秀");
		jb_3.addActionListener(this);
		jp_left.add(jb_3);
		// 右部
		cardl = new CardLayout();
		card = new JPanel();
		card.setBounds(150, 0, 450, 380);
		card.setOpaque(false);
		card.setLayout(cardl);
		jp_1 = new JPanel();
		jp_2 = new JPanel();
		jp_3 = new JPanel();
		card.add("基本资料", jp_1);
		card.add("更多资料", jp_2);
		card.add("QQ秀", jp_3);
		c.add(card);
		// ////////////////////////////////////////////////////////////////////////////////////////////
		// jp_1
		jp_1.setLayout(null);
		jp_1.setOpaque(false);
		jl1_1 = new JLabel();
		jl1_1.setBounds(20, 10, 50, 20);
		jl1_1.setText("昵称:");
		jl1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_1);
		jl1_2 = new JLabel();
		jl1_2.setBounds(240, 10, 50, 20);
		jl1_2.setText("帐号:");
		jl1_2.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_2);
		jt_nickname = new JTextField();
		jt_nickname.setText(pinfo.nickname);
		jt_nickname.setBounds(20, 35, 200, 20);
		jt_nickname.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(jt_nickname);
		jt_user = new JTextField();
		jt_user.setText(pinfo.qq);
		jt_user.setEditable(false);
		jt_user.setBounds(240, 35, 200, 20);
		jt_user.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(jt_user);
		// 个性签名JLabel
		jl1_3 = new JLabel();
		jl1_3.setBounds(20, 65, 100, 20);
		jl1_3.setText("个性签名:");
		jl1_3.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_3);
		// 个性签名
		jt_sign = new JTextArea();
		jt_sign.setBounds(20, 95, 415, 60);
		jt_sign.setText(pinfo.signature);
		jt_sign.setBorder(new LineBorder(Color.gray));
		jt_sign.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(jt_sign);
		// 性别JLabel
		jl1_4 = new JLabel();
		jl1_4.setBounds(20, 165, 50, 20);
		jl1_4.setText("性别：");
		jl1_4.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_4);
		// 生日JLabel
		jl1_5 = new JLabel();
		jl1_5.setBounds(130, 165, 50, 20);
		jl1_5.setText("生日：");
		jl1_5.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_5);
		jl1_6 = new JLabel();
		jl1_6.setBounds(170, 165, 40, 20);
		jl1_6.setIcon(new ImageIcon("pic//main//birthday.gif"));
		jp_1.add(jl1_6);
		// 公历生日
		jl1_0 = new JLabel("公历生日");
		jl1_0.setBounds(250, 165, 100, 20);
		jl1_0.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_0);
		// 性别
		sex_option = new JComboBox();
		sex_option.setFont(new Font("宋体", Font.PLAIN, 14));
		sex_option.addItem("男");
		sex_option.addItem("女");
		sex_option.setSelectedItem(String.valueOf(pinfo.sex));
		sex_option.setBounds(20, 195, 100, 20);
		jp_1.add(sex_option);
		// 年
		year_option = new JComboBox();
		year_option.setBounds(130, 195, 100, 20);
		year_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 1954; i < 2013; i++)
		{
			year_option.addItem(String.valueOf(i));
		}
		year_option.setSelectedItem(String.valueOf(pinfo.birthday_year));// 设置上限为最早1954出生
		jp_1.add(year_option);
		// 月
		month_option = new JComboBox();
		month_option.setBounds(240, 195, 80, 20);
		month_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 1; i < 13; i++)
		{
			month_option.addItem(String.valueOf(i));
		}
		month_option.setSelectedItem(String.valueOf(pinfo.birthday_month));
		jp_1.add(month_option);
		// 日
		day_option = new JComboBox();
		day_option.setBounds(330, 195, 80, 20);
		day_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 1; i < 32; i++)
		{
			day_option.addItem(String.valueOf(i));
		}
		day_option.setSelectedItem(String.valueOf(pinfo.birthday_day));
		jp_1.add(day_option);
		// 年龄JLabel
		jl1_7 = new JLabel();
		jl1_7.setBounds(20, 225, 50, 20);
		jl1_7.setText("年龄：");
		jl1_7.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_7);
		// 生肖JLabel
		jl1_8 = new JLabel();
		jl1_8.setBounds(130, 225, 100, 20);
		jl1_8.setText("生肖：");
		jl1_8.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_8);
		// 星座JLabel
		jl1_9 = new JLabel();
		jl1_9.setText("星座：");
		jl1_9.setBounds(240, 225, 80, 20);
		jl1_9.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_9);
		// 血型JLabel
		jl1_10 = new JLabel();
		jl1_10.setText("血型：");
		jl1_10.setBounds(330, 225, 80, 20);
		jl1_10.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_10);
		// 年龄
		age_option = new JComboBox();
		for (int i = 119; i >= 0; i--)
		{
			age_option.addItem(String.valueOf(i));
		}
		age_option.setSelectedItem(String.valueOf(pinfo.age));
		age_option.setBounds(20, 255, 100, 20);
		age_option.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(age_option);
		// 生肖
		animal_option = new JComboBox();
		animal_option.setBounds(130, 255, 100, 20);
		animal_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 0; i < 13; i++)
		{
			animal_option.addItem(animal_style[i]);
		}
		animal_option.setSelectedItem((pinfo.animal));
		jp_1.add(animal_option);
		// 星座
		constellation_option = new JComboBox();
		constellation_option.setBounds(240, 255, 80, 20);
		constellation_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 0; i < 13; i++)
		{
			constellation_option.addItem(constellation_style[i]);
		}
		constellation_option.setSelectedItem((pinfo.constellation));
		jp_1.add(constellation_option);
		// 血型
		blood_option = new JComboBox();
		blood_option.setBounds(330, 255, 80, 20);
		blood_option.setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 0; i < 5; i++)
		{
			blood_option.addItem(blood_style[i]);
		}
		blood_option.setSelectedItem(pinfo.bloodType);
		jp_1.add(blood_option);
		// 国家JLabel
		jl1_11 = new JLabel();
		jl1_11.setText("国家：");
		jl1_11.setBounds(20, 285, 100, 20);
		jl1_11.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_11);
		// 省份JLabel
		jl1_12 = new JLabel();
		jl1_12.setText("省份：");
		jl1_12.setBounds(130, 285, 100, 20);
		jl1_12.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_12);
		// 城市JLabel
		jl1_13 = new JLabel();
		jl1_13.setText("城市：");
		jl1_13.setBounds(240, 285, 100, 20);
		jl1_13.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_1.add(jl1_13);
		// 国家
		country_option = new JComboBox();
		for (int i = 0; i < 23; i++)
		{
			country_option.addItem(country_style[i]);
		}
		country_option.setSelectedItem(pinfo.country);
		country_option.setBounds(20, 315, 100, 20);
		country_option.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(country_option);
		// 省份
		province_option = new JComboBox();
		for (int i = 0; i < 35; i++)
		{
			province_option.addItem(province_style[i]);
		}
		province_option.setSelectedItem(pinfo.province);
		province_option.setBounds(130, 315, 100, 20);
		province_option.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(province_option);
		// 城市
		city_option = new JComboBox();
		for (int i = 0; i < 18; i++)
		{
			city_option.addItem(city_style[i]);
		}
		city_option.setSelectedItem(pinfo.city);
		city_option.setBounds(240, 315, 100, 20);
		city_option.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_1.add(city_option);
		// ////////////////////////////////////////////////////////////////////////////////////////
		jp_2.setLayout(null);
		jp_2.setOpaque(false);
		// 手机号JLabel
		jl2_1 = new JLabel();
		jl2_1.setBounds(20, 10, 100, 20);
		jl2_1.setText("手机号:");
		jl2_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_1);
		// 手机号
		jt_telephone = new JTextField();
		jt_telephone.setText(pinfo.phoneNumber);
		jt_telephone.setBounds(20, 40, 200, 20);
		jt_telephone.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_2.add(jt_telephone);
		// 邮箱JLabel
		jl2_2 = new JLabel();
		jl2_2.setBounds(20, 70, 100, 20);
		jl2_2.setText("邮箱:");
		jl2_2.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_2);
		// 邮箱
		jt_mail = new JTextField();
		jt_mail.setBounds(20, 100, 200, 20);
		jt_mail.setEditable(false);
		jt_mail.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_2.add(jt_mail);
		// 语言JLabel
		jl2_3 = new JLabel();
		jl2_3.setBounds(20, 130, 100, 20);
		jl2_3.setText("语言:");
		jl2_3.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_3);
		// 语言
		language_option = new JComboBox();
		language_option.setFont(new Font("宋体", Font.PLAIN, 14));
		language_option.addItem("---");
		language_option.addItem("汉语");
		language_option.addItem("英语");
		language_option.addItem("德语");
		language_option.addItem("日语");
		language_option.addItem("韩语");
		language_option.addItem("西班牙语");
		language_option.addItem("葡萄牙语");
		language_option.addItem("法语");
		language_option.addItem("意大利语");
		language_option.addItem("俄罗斯语");
		language_option.setSelectedItem(pinfo.language);
		language_option.setBounds(20, 160, 200, 20);
		jp_2.add(language_option);
		// 职业JLabel
		jl2_4 = new JLabel();
		jl2_4.setBounds(20, 190, 100, 20);
		jl2_4.setText("职业:");
		jl2_4.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_4);
		// 职业
		job_option = new JComboBox();
		job_option.addItem(pinfo.occupation);
		job_option.addItem("工程师");
		job_option.addItem("计算机业");
		job_option.addItem("兼职");
		job_option.addItem("教育业");
		job_option.addItem("金融业");
		job_option.addItem("老板");
		job_option.addItem("全职");
		job_option.addItem("商业");
		job_option.addItem("失业中");
		job_option.addItem("退休");
		job_option.addItem("销售");
		job_option.addItem("学生");
		job_option.addItem("政府部门");
		job_option.addItem("制造业");
		job_option.addItem("其他");
		job_option.setFont(new Font("宋体", Font.PLAIN, 14));
		job_option.setBounds(20, 220, 200, 20);
		jp_2.add(job_option);
		// 院校JLabel
		jl2_5 = new JLabel();
		jl2_5.setBounds(20, 250, 100, 20);
		jl2_5.setText("院校:");
		jl2_5.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_5);
		// 院校
		jt_collage = new JTextField();
		jt_collage.setText(pinfo.collage);
		jt_collage.setBounds(20, 280, 200, 25);
		jt_telephone.setFont(new Font("宋体", Font.PLAIN, 14));
		jp_2.add(jt_collage);
		// 个人说明JLabel
		jl2_6 = new JLabel();
		jl2_6.setBounds(20, 310, 100, 20);
		jl2_6.setText("个人说明:");
		jl2_6.setFont(new Font("宋体", Font.PLAIN, 16));
		jp_2.add(jl2_6);
		// 个人说明
		jt_person = new JTextArea();
		jt_person.setText(pinfo.personInfo);
		jt_person.setBounds(20, 340, 400, 40);
		jt_person.setBorder(new LineBorder(Color.gray));
		jp_2.add(jt_person);
		// ///////////////////////////////////////////////////////////////////////////////////
		jp_3.setBackground(Color.blue);
		// 下部
		jp_buttom = new JPanel();
		jp_buttom.setLayout(null);
		jp_buttom.setOpaque(false);
		jp_buttom.setBounds(0, 380, 600, 120);
		c.add(jp_buttom);
		jb_sure = new JButton();
		jb_sure.setIcon(new ImageIcon("pic//main//sure.gif"));
		jb_sure.setBounds(400, 15, 53, 18);
		jb_sure.addActionListener(this);
		jb_sure.setActionCommand("确定");
		jp_buttom.add(jb_sure);
		jb_cancel = new JButton();
		jb_cancel.setIcon(new ImageIcon("pic//main//cancel.gif"));
		jb_cancel.setBounds(500, 15, 53, 18);
		jb_cancel.addActionListener(this);
		jb_cancel.setActionCommand("取消");
		jp_buttom.add(jb_cancel);
		// 国家选项
		country_option.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				country_optionItemStateChanged(e);
			}
		});
		// 省份选项
		province_option.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				province_optionItemStateChanged(e);
			}
		});
		getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(600, 450);
		setVisible(true);
		setTitle("我的资料");
	}

	public void change_head()
	{
		jl_head.setIcon(new ImageIcon(s1 + pinfo.headImage + "_4" + s2));
		c_temp.repaint();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("基本资料"))
		{
			cardl.show(card, "基本资料");
		}
		else if (e.getActionCommand().equals("更多资料"))
		{
			cardl.show(card, "更多资料");
		}
		else if (e.getActionCommand().equals("QQ秀"))
		{
			cardl.show(card, "QQ秀");
		}
		else if (e.getActionCommand().equals("更换头像"))
		{
			final head h = new head(c_temp);
			h.addWindowListener(new WindowAdapter() {
				public void windowActivated(WindowEvent e)
				{}

				// 当窗口不活跃的时候，自动关闭该窗口
				public void windowDeactivated(WindowEvent e)
				{
					h.dispose();
				}
			});
		}
		else if (e.getActionCommand().equals("确定"))
		{
			// 向数据库传送
			pinfo.sex = (String) sex_option.getSelectedItem();
			pinfo.age = Integer.parseInt((age_option.getSelectedItem().toString()));
			pinfo.animal = (String) animal_option.getSelectedItem();
			pinfo.signature = jt_sign.getText();
			pinfo.birthday_day = Integer.parseInt((day_option.getSelectedItem().toString()));
			pinfo.birthday_month = Integer.parseInt(month_option.getSelectedItem().toString());
			pinfo.birthday_year = Integer.parseInt(year_option.getSelectedItem().toString());
			pinfo.bloodType = (String) (blood_option.getSelectedItem());
			pinfo.city = (String) (city_option.getSelectedItem());
			pinfo.collage = jt_collage.getText();
			pinfo.constellation = (String) constellation_option.getSelectedItem();
			pinfo.country = (String) country_option.getSelectedItem();
			pinfo.language = (String) language_option.getSelectedItem();
			pinfo.nickname = jt_nickname.getText();
			pinfo.occupation = (String) job_option.getSelectedItem();
			pinfo.phoneNumber = jt_telephone.getText();
			pinfo.province = (String) province_option.getSelectedItem();
			pinfo.personInfo = jt_person.getText();
			c_temp.repaint();
			c_temp_mainInterface.removeAll();
			main_frame.refresh();// 这几句是刷新界面
			c_temp_mainInterface.repaint();// ///////////////////////////////////////////////////////////////////////////////
			// ------------更新个人资料服务器操作---------------
			try
			{
				sendUpdateInfo sender = new sendUpdateInfo(pinfo);
			}
			catch (UnknownHostException e1)
			{}
			catch (IOException e1)
			{}
			dispose();
		}
		else if (e.getActionCommand().equals("取消"))
		{
			dispose();
		}
	}

	// 国家选项
	private void country_optionItemStateChanged(ItemEvent e)
	{
		for (int i = 0; i < 22; i++)
		{
			if (e.getItem() == "中国")
			{
				city_option.setEnabled(true);
				province_option.setEnabled(true);
				province_option.removeAllItems();
				for (int j = 0; j < 34; j++)
				{
					province_option.addItem(province_style[j]);
				}
			}
			else if (e.getItem() == "美国")
			{
				province_option.setEnabled(true);
				city_option.setEnabled(false);
				province_option.removeAllItems();
				for (int j = 0; j < 9; j++)
				{
					province_option.addItem(province1_style[j]);
				}
			}
			else
			{
				city_option.setEnabled(false);
				province_option.setEnabled(false);
			}
		}
	}

	// 省份选项
	private void province_optionItemStateChanged(ItemEvent e)
	{
		for (int i = 0; i < 34; i++)
		{
			if (e.getItem() == "山东")
			{
				city_option.setEnabled(true);
				city_option.removeAllItems();
				for (int j = 0; j < 17; j++)
				{
					city_option.addItem(city_style[j]);
				}
			}
			else if (e.getItem() == "北京")
			{
				city_option.setEnabled(true);
				city_option.removeAllItems();
				for (int j = 0; j < 7; j++)
				{
					city_option.addItem(city1_style[j]);
				}
			}
			else
			{
				city_option.setEnabled(false);
			}
		}
	}

	public void windowOpened(WindowEvent e)
	{}

	public void windowClosing(WindowEvent e)
	{
		dispose();
	}

	public void windowClosed(WindowEvent e)
	{}

	public void windowIconified(WindowEvent e)
	{}

	public void windowDeiconified(WindowEvent e)
	{}

	public void windowActivated(WindowEvent e)
	{}

	public void windowDeactivated(WindowEvent e)
	{}

	public class head extends JFrame implements ActionListener
	{
		private String	IMAGE_1	= "pic//face//";
		private String	IMAGE_2	= ".jpg";
		private JPanel	jp_left, jp_buttom, jp_right;
		private JLabel	jl_logo, jl_1, jl_2, jl_3, jl_pic1, jl_pic2, jl_4;
		private JButton	jb[][], jb_sure, jb_cancel;
		private Dimension	screenSize, frameSize;
		private JScrollPane	js;

		public head(Container c_temp)
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
			// 上部--logo
			jl_logo = new JLabel();
			jl_logo.setIcon(new ImageIcon("pic//face//head.jpg"));
			jl_logo.setBounds(0, 0, 600, 70);
			c.add(jl_logo);
			// 左部
			jp_left = new JPanel();
			jp_left.setLayout(null);
			jp_left.setLocation(0, 0);
			jp_left.setPreferredSize(new Dimension(380, 400));
			jp_left.setBackground(Color.yellow);
			// 左部--字（系统头像）
			jl_4 = new JLabel();
			jl_4.setBounds(30, 20, 100, 20);
			jl_4.setText("系统头像：");
			jl_4.setFont(new Font("宋体", Font.PLAIN, 15));
			jp_left.add(jl_4);
			// 左部--头像
			jb = new JButton[9][];
			for (int i = 1; i <= 8; i++)
			{
				jb[i] = new JButton[7];
				for (int j = 1; j <= 6; j++)
				{
					jb[i][j] = new JButton();
					String s = String.valueOf((i - 1) * 6 + j);
					jb[i][j].setBounds(50 * j, 50 * i, 40, 40);
					jb[i][j].setActionCommand(s);
					jb[i][j].setIcon(new ImageIcon(IMAGE_1 + s + IMAGE_2));
					jp_left.add(jb[i][j]);
				}
			}
			// 左部--滚动条
			js = new JScrollPane(jp_left);
			js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			js.setBounds(5, 75, 400, 350);
			c.add(js);
			// 右部
			jp_right = new JPanel();
			jp_right.setLayout(null);
			jp_right.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "预览头像"));
			jp_right.setBounds(405, 75, 189, 350);
			c.add(jp_right);
			// 右部--样式
			jl_1 = new JLabel("样式：");
			jl_1.setBounds(25, 20, 70, 20);
			jl_1.setFont(new Font("宋体", Font.PLAIN, 14));
			jp_right.add(jl_1);
			// 右部--小头像
			jl_pic1 = new JLabel();
			jl_pic1.setIcon(new ImageIcon("pic//face//1.jpg"));
			jl_pic1.setBounds(75, 45, 40, 40);
			jp_right.add(jl_pic1);
			// 右部--40×40
			jl_2 = new JLabel("40×40");
			jl_2.setBounds(75, 95, 70, 20);
			jl_2.setFont(new Font("宋体", Font.PLAIN, 14));
			jp_right.add(jl_2);
			// 右部--大头像
			jl_pic2 = new JLabel();
			jl_pic2.setIcon(new ImageIcon("pic//face//1_4.jpg"));
			jl_pic2.setBounds(45, 135, 100, 100);
			jp_right.add(jl_pic2);
			// 右部--100×100
			jl_3 = new JLabel("100×100");
			jl_3.setBounds(65, 255, 70, 20);
			jl_3.setFont(new Font("宋体", Font.PLAIN, 14));
			jp_right.add(jl_3);
			// 下部
			jp_buttom = new JPanel();
			jp_buttom.setLayout(null);
			jp_buttom.setBounds(0, 425, 600, 60);
			jp_buttom.setBackground(Color.yellow);
			c.add(jp_buttom);
			// 下部--确定
			jb_sure = new JButton();
			jb_sure.setBounds(450, 15, 55, 16);
			jb_sure.setIcon(new ImageIcon("pic//sure.gif"));
			jb_sure.setActionCommand("sure");
			jp_buttom.add(jb_sure);
			// 下部--取消
			jb_cancel = new JButton();
			jb_cancel.setBounds(520, 15, 55, 16);
			jb_cancel.setIcon(new ImageIcon("pic//cancel.gif"));
			jb_cancel.setActionCommand("cancel");
			jp_buttom.add(jb_cancel);
			// 响应---确定
			jb_sure.addActionListener(this);
			// 响应---取消
			jb_cancel.addActionListener(this);
			// 响应---头像
			jb[1][1].addActionListener(this);
			jb[1][1].addActionListener(this);
			jb[1][2].addActionListener(this);
			jb[1][3].addActionListener(this);
			jb[1][4].addActionListener(this);
			jb[4][5].addActionListener(this);
			jb[1][5].addActionListener(this);
			jb[4][6].addActionListener(this);
			jb[1][6].addActionListener(this);
			jb[5][1].addActionListener(this);
			jb[2][1].addActionListener(this);
			jb[5][2].addActionListener(this);
			jb[2][2].addActionListener(this);
			jb[5][3].addActionListener(this);
			jb[2][3].addActionListener(this);
			jb[5][4].addActionListener(this);
			jb[2][4].addActionListener(this);
			jb[5][5].addActionListener(this);
			jb[2][5].addActionListener(this);
			jb[5][6].addActionListener(this);
			jb[3][1].addActionListener(this);
			jb[6][1].addActionListener(this);
			jb[3][2].addActionListener(this);
			jb[6][2].addActionListener(this);
			jb[3][3].addActionListener(this);
			jb[6][3].addActionListener(this);
			jb[3][4].addActionListener(this);
			jb[6][4].addActionListener(this);
			jb[3][5].addActionListener(this);
			jb[6][5].addActionListener(this);
			jb[3][6].addActionListener(this);
			jb[6][6].addActionListener(this);
			jb[4][1].addActionListener(this);
			jb[7][1].addActionListener(this);
			jb[4][2].addActionListener(this);
			jb[7][2].addActionListener(this);
			jb[4][3].addActionListener(this);
			jb[7][3].addActionListener(this);
			jb[4][4].addActionListener(this);
			jb[7][4].addActionListener(this);
			jb[8][1].addActionListener(this);
			jb[7][5].addActionListener(this);
			jb[8][2].addActionListener(this);
			jb[7][6].addActionListener(this);
			jb[8][3].addActionListener(this);
			jb[8][5].addActionListener(this);
			jb[8][4].addActionListener(this);
			jb[8][6].addActionListener(this);
			setResizable(false);
			setSize(600, 500);
			setVisible(true);
			setTitle("更换头像");
		}

		// 头像
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("sure"))
			{
				// 向数据库传送头像number
				change_head();
				dispose();
			}
			else if (e.getActionCommand().equals("cancel"))
			{
				dispose();
			}
			else
			{
				for (int i = 1; i <= 42; i++)
				{
					if (e.getActionCommand().equals(i + ""))
					{
						pinfo.headImage = i;
						jl_pic1.setIcon(new ImageIcon("pic//face//" + i + ".jpg"));
						jl_pic2.setIcon(new ImageIcon("pic//face//" + i + "_4.jpg"));
					}
				}
			}
		}
	}
	// public void change_head()
	// {
	// // TODO Auto-generated method stub
	// }
}
