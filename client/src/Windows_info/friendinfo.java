package Windows_info;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Windows_MainInterface.MainInterface;

import client.thread.getInfoThread.getInfoThread;

import common.message.mainInfo;
import common.message.personalInfo;
import common.message.qq_num;
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
public class friendinfo extends JFrame implements ActionListener
{
	// 定义组件
	JScrollPane		jsp;
	JPanel			jp;
	JLabel			image, foundationinfo, moreinfo;
	JLabel			Nick, Remark, Signature;
	JLabel			Sex, Age, Birthday, Animal, Constellation, Address, College, PhoneNum, PersonInfo, Language, Occupation;
	final JLabel	imgLabel1	= new JLabel();
	JButton			refreshButt	= null;
	Container		c;
	personalInfo	pinfo;
	int				x, y;
	friendinfo		friCont;

	public friendinfo(personalInfo info)// 需要参数进行构造具体好友的信息
	{
		friCont = this;
		c = this.getContentPane();
		this.pinfo = info;
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		x = (screensize.width - framesize.width) / 5;
		y = (screensize.height - framesize.height) / 5;
		setLocation(x, y);
		this.setLayout(null);// 不用任何布局
		// 创建各组件
		jsp = new JScrollPane();
		jsp.setVisible(true);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setPreferredSize(new Dimension(460, 500));
		jp.setOpaque(false);
		image = new JLabel(new ImageIcon("pic/face/" + info.headImage + ".JPG"));// 从服务器传来的
		foundationinfo = new JLabel("基本资料");
		moreinfo = new JLabel("更多资料");
		Nick = new JLabel(pinfo.nickname +"("+ pinfo.qq+")");
		Remark = new JLabel("备 注：" + pinfo.remark);
		if (pinfo.remark == null)
		{
			Remark.setText("备 注：");
		}
		Signature = new JLabel("个性签名：" + pinfo.signature);
		if (pinfo.signature == null)
		{
			Signature.setText("个性签名：");
		}
		Sex = new JLabel("性 别：" + pinfo.sex);
		Age = new JLabel("年 龄：" + pinfo.age);
		if (("" + pinfo.age) == null)
		{
			Age.setText("年 龄：");
		}
		Birthday = new JLabel("生日：" + pinfo.birthday_year + "年" + pinfo.birthday_month + "月" + pinfo.birthday_day + "日");
		if (("" + pinfo.birthday_year) == null || ("" + pinfo.birthday_month) == null || ("" + pinfo.birthday_day) == null)
		{
			Birthday.setText("生日：");
		}
		Animal = new JLabel("生 肖：" + pinfo.animal);
		if (pinfo.animal == null)
		{
			Animal.setText("生 肖：");
		}
		Constellation = new JLabel("星 座：" + pinfo.constellation);
		if (pinfo.constellation == null)
		{
			Constellation.setText("星 座：");
		}
		Address = new JLabel("地 址：" + pinfo.country + pinfo.province + pinfo.city);
		College = new JLabel("学 校：" + pinfo.collage);
		if (pinfo.collage == null)
		{
			College.setText("学 校：");
		}
		PhoneNum = new JLabel("手 机：" + pinfo.phoneNumber);
		if (pinfo.phoneNumber == null)
		{
			PhoneNum.setText("手 机：");
		}
		PersonInfo = new JLabel("个人说明：" + pinfo.personInfo);
		if (pinfo.personInfo == null)
		{
			PersonInfo.setText("个人说明：");
		}
		Language = new JLabel("语 言：" + pinfo.language);
		if (pinfo.language == null)
		{
			Language.setText("语 言：");
		}
		Occupation = new JLabel("职 业：" + pinfo.occupation);
		if (pinfo.occupation == null)
		{
			Occupation.setText("职 业：");
		}
		// 设置背景图片
		ImageIcon img1 = new ImageIcon("main_background.jpg");
		imgLabel1.setIcon(img1);
		imgLabel1.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false);
		getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
		// 设置背景图片完成
		// 将组件添加到容器中
		// 左部
		this.add(image);
		image.setBounds(5, 5, 90, 90);
		// 右部
		jp.add(Nick);
		Nick.setBounds(5, 10, 100, 20);
		jp.add(Remark);
		Remark.setBounds(5, 40, 150, 20);
		jp.add(Signature);
		Signature.setBounds(5, 70, 200, 20);
		jp.add(foundationinfo);
		foundationinfo.setBounds(5, 120, 150, 20);
		jp.add(Sex);
		Sex.setBounds(5, 150, 100, 20);
		jp.add(Age);
		Age.setBounds(170, 150, 100, 20);
		jp.add(Birthday);
		Birthday.setBounds(280, 150, 200, 20);
		jp.add(Animal);
		Animal.setBounds(5, 180, 100, 20);
		jp.add(Constellation);
		Constellation.setBounds(170, 180, 100, 20);
		jp.add(Address);
		Address.setBounds(5, 210, 150, 20);
		jp.add(moreinfo);
		moreinfo.setBounds(5, 280, 100, 20);
		jp.add(College);
		College.setBounds(5, 310, 100, 20);
		jp.add(PhoneNum);
		PhoneNum.setBounds(5, 340, 100, 20);
		jp.add(Language);
		Language.setBounds(5, 370, 100, 20);
		jp.add(Occupation);
		Occupation.setBounds(5, 400, 100, 20);
		jp.add(PersonInfo);
		PersonInfo.setBounds(5, 430, 100, 20);
		jsp.getViewport().add(jp);
		jp.setBounds(0, 0, 470, 1000);
		add(jsp);
		jsp.setBounds(100, 5, 480, 390);
		// 窗体设置
		refreshButt = new JButton("刷新");
		refreshButt.setBounds(10, 370, 70, 20);
		add(refreshButt);
		setTitle("好友资料");
		setSize(596, 434);
		setResizable(false);
		setVisible(true);
		refreshButt.addActionListener(this);
		refreshButt.setActionCommand("refresh");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("refresh"))
		{
			try
			{
				getInfoThread getInfoThread = new getInfoThread(new qq_num(pinfo.qq));
				getInfoThread.join(70000);
				if (getInfoThread.pInfo != null)
				{
					String remark_t = MainInterface.map_friendsInfo.get(pinfo.qq).remark;
					pinfo = new personalInfo(getInfoThread.pInfo);
					if (remark_t != null)
					{
						pinfo.remark = new String(remark_t);
					}
					MainInterface.setFriend(pinfo.qq, pinfo);
					MainInterface.map_friendsInfo.put(pinfo.qq, pinfo);
					MainInterface.c_temp_main.removeAll();
					MainInterface.main_frame.refresh();
					MainInterface.c_temp_main.repaint();
				}
			}
			catch (Exception e1)
			{}
			refresh();
		}
	}

	protected void refresh()
	{
		jsp.removeAll();
		jp.removeAll();
		c.removeAll();
		// 创建各组件
		// 创建各组件
		//
		setLocation(friCont.getLocation().x, friCont.getLocation().y);
		this.setLayout(null);// 不用任何布局
		// 创建各组件
		jsp = new JScrollPane();
		jsp.setVisible(true);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setPreferredSize(new Dimension(460, 500));
		jp.setOpaque(false);
		image = new JLabel(new ImageIcon("pic/face/" + pinfo.headImage + ".JPG"));// 从服务器传来的
		foundationinfo = new JLabel("基本资料");
		moreinfo = new JLabel("更多资料");
		Nick = new JLabel(pinfo.nickname + "("+pinfo.qq+")");
		Remark = new JLabel("备 注：" + pinfo.remark);
		if (pinfo.remark == null)
		{
			Remark.setText("备 注：");
		}
		Signature = new JLabel("个性签名：" + pinfo.signature);
		if (pinfo.signature == null)
		{
			Signature.setText("个性签名：");
		}
		Sex = new JLabel("性 别：" + pinfo.sex);
		Age = new JLabel("年 龄：" + pinfo.age);
		if (("" + pinfo.age) == null)
		{
			Age.setText("年 龄：");
		}
		Birthday = new JLabel("生日：" + pinfo.birthday_year + "年" + pinfo.birthday_month + "月" + pinfo.birthday_day + "日");
		if (("" + pinfo.birthday_year) == null || ("" + pinfo.birthday_month) == null || ("" + pinfo.birthday_day) == null)
		{
			Birthday.setText("生日：");
		}
		Animal = new JLabel("生 肖：" + pinfo.animal);
		if (pinfo.animal == null)
		{
			Animal.setText("生 肖：");
		}
		Constellation = new JLabel("星 座：" + pinfo.constellation);
		if (pinfo.constellation == null)
		{
			Constellation.setText("星 座：");
		}
		Address = new JLabel("地 址：" + pinfo.country + pinfo.province + pinfo.city);
		College = new JLabel("学 校：" + pinfo.collage);
		if (pinfo.collage == null)
		{
			College.setText("学 校：");
		}
		PhoneNum = new JLabel("手 机：" + pinfo.phoneNumber);
		if (pinfo.phoneNumber == null)
		{
			PhoneNum.setText("手 机：");
		}
		PersonInfo = new JLabel("个人说明：" + pinfo.personInfo);
		if (pinfo.personInfo == null)
		{
			PersonInfo.setText("个人说明：");
		}
		Language = new JLabel("语 言：" + pinfo.language);
		if (pinfo.language == null)
		{
			Language.setText("语 言：");
		}
		Occupation = new JLabel("职 业：" + pinfo.occupation);
		if (pinfo.occupation == null)
		{
			Occupation.setText("职 业：");
		}
		// 将组件添加到容器中
		// 左部
		this.add(image);
		image.setBounds(5, 5, 90, 90);
		// 右部
		jp.add(Nick);
		Nick.setBounds(5, 10, 100, 20);
		jp.add(Remark);
		Remark.setBounds(5, 40, 150, 20);
		jp.add(Signature);
		Signature.setBounds(5, 70, 200, 20);
		jp.add(foundationinfo);
		foundationinfo.setBounds(5, 120, 150, 20);
		jp.add(Sex);
		Sex.setBounds(5, 150, 100, 20);
		jp.add(Age);
		Age.setBounds(170, 150, 100, 20);
		jp.add(Birthday);
		Birthday.setBounds(280, 150, 200, 20);
		jp.add(Animal);
		Animal.setBounds(5, 180, 100, 20);
		jp.add(Constellation);
		Constellation.setBounds(170, 180, 100, 20);
		jp.add(Address);
		Address.setBounds(5, 210, 150, 20);
		jp.add(moreinfo);
		moreinfo.setBounds(5, 280, 100, 20);
		jp.add(College);
		College.setBounds(5, 310, 100, 20);
		jp.add(PhoneNum);
		PhoneNum.setBounds(5, 340, 100, 20);
		jp.add(Language);
		Language.setBounds(5, 370, 100, 20);
		jp.add(Occupation);
		Occupation.setBounds(5, 400, 100, 20);
		jp.add(PersonInfo);
		PersonInfo.setBounds(5, 430, 100, 20);
		jsp.getViewport().add(jp);
		jp.setBounds(0, 0, 470, 1000);
		add(jsp);
		jsp.setBounds(100, 5, 480, 390);
		// 窗体设置
		refreshButt.setBounds(10, 370, 70, 20);
		add(refreshButt);
		setTitle("好友资料");
		setSize(596, 434);
		setResizable(false);
		setVisible(true);
		jsp.repaint();
		jp.repaint();
		imgLabel1.repaint();
		c.repaint();
	}
}
