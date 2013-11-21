package Windows_systemMessage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Windows_MainInterface.MainInterface;
import Windows_addFriend.request_add;
import Windows_info.friendinfo;

import client.thread.addFriendsThread.sendAgreeRefuseThread;
import client.thread.chatWithAnotherQQThread.getAnotherQQIpPortThread_Thread;
import client.thread.getInfoThread.getInfoThread;

import common.playAudio;
import common.message.agreeRefuse;
import common.message.personalInfo;
import common.message.qq_num;
import common.message.systemMessage;
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
public class message extends JFrame implements ActionListener
{
	private JPanel	jp1;
	private JLabel	jl1;
	private JButton	jb1, jb2, jb3;
	private Dimension	screenSize, frameSize;
	private autoExit	exit_thread	= null;
	systemMessage		sysmessage;

	public message(systemMessage sysmessage) throws InterruptedException
	{
		this.sysmessage = sysmessage;
		exit_thread = new autoExit();
		exit_thread.start();
		Image image = Toolkit.getDefaultToolkit().getImage("pic//message//a.png");
		setIconImage(image);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 保持弹出窗口位置
		this.setLocation(screenSize.width - 310, screenSize.height - 200);
		Container container = getContentPane();
		// 设置背景图片
		JLabel jl_down = new JLabel();
		jl_down.setIcon(new ImageIcon("pic//background//3.jpg"));
		jl_down.setSize(550, 450);
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		if (sysmessage.sign == 1 || sysmessage.sign == 3 || sysmessage.sign == 4)
		{
			if (sysmessage.sign == 4) // 假如是同意添加好友信息
			{
				playAudio play_sys = new playAudio(1);
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
				MainInterface.c_temp_main.removeAll();
				MainInterface.main_frame.refresh();
				MainInterface.c_temp_main.repaint();
			}
			if (sysmessage.status != 10)//状态改变
			{
				playAudio play_sys = new playAudio(3);
				personalInfo pInfo;
				if (sysmessage.status == 0 || sysmessage.status == 4)
				{
					pInfo = MainInterface.map_friendsInfo.get(sysmessage.anotherqq);
					if (sysmessage.status == 0) // 假如是下线的话
					{
						MainInterface.map_ipPort.remove(sysmessage.anotherqq);
					}
					if (pInfo != null)
					{
						pInfo.status = sysmessage.status;
						MainInterface.setFriend(sysmessage.anotherqq, pInfo);
						MainInterface.c_temp_main.removeAll();
						MainInterface.main_frame.refresh();
						MainInterface.c_temp_main.repaint();
					}
				}
				else if (sysmessage.status == 1)
				{
					pInfo = MainInterface.map_friendsInfo.get(sysmessage.anotherqq);
					if (pInfo != null)
					{
						pInfo = new personalInfo(MainInterface.map_friendsInfo.get(sysmessage.anotherqq));
						pInfo.status = sysmessage.status;
						MainInterface.setFriend(sysmessage.anotherqq, pInfo);
						MainInterface.c_temp_main.removeAll();
						MainInterface.main_frame.refresh();
						MainInterface.c_temp_main.repaint();
						if (MainInterface.map_ipPort.get(sysmessage.anotherqq) == null)
						{
							MainInterface.map_friendsInfo.put(sysmessage.anotherqq, pInfo);
							getAnotherQQIpPortThread_Thread get_loop = new getAnotherQQIpPortThread_Thread(sysmessage.anotherqq);
						}
					}
				}
				else
				{
					// 离开忙碌暂时不做
				}
			}
			else
			{
				playAudio play_sys = new playAudio(1);
			}
			if (MainInterface.mInfo != null)
			{
				jl1 = new JLabel("<html><font face = '宋体'>" + "尊敬的QQ用户 " + MainInterface.mInfo.myself.nickname + "：<br>&nbsp您好，" + sysmessage.message + "</br></font></html>");
			}
			else
			{
				jl1 = new JLabel("<html><font face = '宋体'>" + "尊敬的QQ用户 " + "：<br>&nbsp您好，" + sysmessage.message + "</br></font></html>");
			}
			jl1.setLayout(null);
			jl1.setOpaque(false);
			jl1.setBounds(30, 20, 240, 80);
			jl1.setFont(new Font("宋体", Font.PLAIN, 12));
			c.add(jl1);
		}
		else
		{
			playAudio play_sys = new playAudio(1);
			jl1 = new JLabel("用户你好：");
			jl1.setLayout(null);
			jl1.setOpaque(false);
			jl1.setBounds(30, 30, 240, 20);
			jl1.setFont(new Font("宋体", Font.PLAIN, 14));
			c.add(jl1);
			jl1 = new JLabel("   " + sysmessage.nickname + "(" + sysmessage.anotherqq + ")   想添加你为好友");
			jl1.setLayout(null);
			jl1.setOpaque(false);
			jl1.setBounds(30, 50, 240, 20);
			jl1.setFont(new Font("宋体", Font.PLAIN, 12));
			c.add(jl1);
			jb1 = new JButton("查看");
			jb1.setBounds(50, 90, 60, 20);
			jb1.addActionListener(this);
			jb1.setFont(new Font("宋体", Font.PLAIN, 12));
			jb1.setActionCommand("查看");
			c.add(jb1);
			jb2 = new JButton("同意");
			jb2.setBounds(120, 90, 60, 20);
			jb2.setFont(new Font("宋体", Font.PLAIN, 12));
			jb2.addActionListener(this);
			jb2.setActionCommand("同意");
			c.add(jb2);
			jb3 = new JButton("拒绝");
			jb3.setBounds(190, 90, 60, 20);
			jb3.setFont(new Font("宋体", Font.PLAIN, 12));
			jb3.addActionListener(this);
			jb3.setActionCommand("拒绝");
			c.add(jb3);
		}
		getLayeredPane().setLayout(null);
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(300, 150);
		setVisible(true);
		if (sysmessage.sign == 1 || sysmessage.sign == 4)
		{
			setTitle("系统消息");
		}
		else if (sysmessage.sign == 2)
		{
			setTitle("添加好友");
		}
		else
		{
			setTitle("下线警告！");
		}
		this.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e)
			{
				exit_thread.stop();
			}

			public void mousePressed(MouseEvent e)
			{
				exit_thread.stop();
			}

			public void mouseExited(MouseEvent e)
			{
				exit_thread = new autoExit();
				exit_thread.start();
			}

			public void mouseEntered(MouseEvent e)
			{
				exit_thread.stop();
			}

			public void mouseClicked(MouseEvent e)
			{}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		// String user = "12", sysmessage.nickname = "xiaosi", sysmessage.sign = "Come On!", sex = "男", year = "1990", month = "1", day = "15", country = "中国", province = "山东", city = "淄博", animal = "马", blood = "B", constellation = "水瓶座", telephone = "15054218253", mail = "1203745031@qq.com", language = "汉语", job = "计算机", collage = "山东科技大学";
		// String address = "山东 青岛", information = "同学";
		// int age = 21;
		if (e.getActionCommand().equals("查看"))
		{
			getInfoThread get_info = null;
			try
			{
				get_info = new getInfoThread(new qq_num(sysmessage.anotherqq));
				get_info.join(15000);
			}
			catch (Exception e1)
			{}
			if (get_info.pInfo != null)
			{
				exit_thread.stop();
				friendinfo info_window = new friendinfo(get_info.pInfo);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "系统繁忙，请稍候再试！", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if (e.getActionCommand().equals("同意"))
		{
			request_add r = new request_add(sysmessage.nickname, sysmessage.anotherqq, sysmessage.sex, sysmessage.age, sysmessage.country + sysmessage.city, sysmessage.authentication, sysmessage,
					MainInterface.main_frame, MainInterface.c_temp_main);
			dispose();
		}
		else if (e.getActionCommand().equals("拒绝"))
		{
			try
			{
				sendAgreeRefuseThread sender = new sendAgreeRefuseThread(new agreeRefuse(0, MainInterface.mInfo.myself.qq, sysmessage.anotherqq, "无"));
			}
			catch (Exception e1)
			{}
			dispose();
		}
	}

	public class autoExit extends Thread
	{
		public void run()
		{
			try
			{
				sleep(7000);
				dispose();
			}
			catch (InterruptedException e)
			{}
		}
	}
}
