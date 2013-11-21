package Windows_chat;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.thread.chatWithAnotherQQThread.getAnotherQQIpPortThread_Thread;
import client.thread.chatWithAnotherQQThread.send_messageThread;

import Windows_MainInterface.MainInterface;

import common.message.chatMessage;
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
public class chatWarningWindow extends JFrame implements ActionListener
{
	private JPanel			jp1;
	private JLabel			jl1;
	private JButton			jb1, jb2, jb3;
	private Dimension		screenSize, frameSize;
	private autoExit		exit_thread	= null;
	chatMessage				message;
	final static Integer	temp		= new Integer(1);

	public chatWarningWindow(chatMessage chMessage)
	{
		this.message = chMessage;
		exit_thread = new autoExit();
		if (MainInterface.status != 2 && MainInterface.status != 3)
		{
			exit_thread.start();
		}
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
		jl1 = new JLabel(message.speeker + "：" + message.text.substring(11));
		jl1.setLayout(null);
		jl1.setOpaque(false);
		jl1.setBounds(30, 50, 240, 20);
		jl1.setFont(new Font("宋体", Font.PLAIN, 12));
		c.add(jl1);
		jb1 = new JButton("打开");
		jb1.setBounds(50, 90, 60, 20);
		jb1.addActionListener(this);
		jb1.setFont(new Font("宋体", Font.PLAIN, 12));
		jb1.setActionCommand("打开");
		c.add(jb1);
		jb3 = new JButton("忽略");
		jb3.setBounds(190, 90, 60, 20);
		jb3.setFont(new Font("宋体", Font.PLAIN, 12));
		jb3.addActionListener(this);
		jb3.setActionCommand("忽略");
		c.add(jb3);
		getLayeredPane().setLayout(null);
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(300, 150);
		setVisible(true);
		setTitle("会话消息");
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
			{
				exit_thread.stop();
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("打开"))
		{
			try
			{
				if (MainInterface.map_ipPort.get(message.myQQ) == null)
				{
					getAnotherQQIpPortThread_Thread getiport = new getAnotherQQIpPortThread_Thread(message.myQQ);
				}
				if (MainInterface.map_chatWindows.get(message.myQQ) == null)
				{
					chat chat_temp = new chat(MainInterface.map_friendsInfo.get(message.myQQ), MainInterface.mInfo.myself);
					MainInterface.map_chatWindows.put(message.myQQ, chat_temp);
					for (int i = 0; MainInterface.map_ipPort == null && i < 10; i++)
					{
						Thread.sleep(100);
					}
				}
				// Thread.sleep(300);
				send_messageThread sender = new send_messageThread(message);
				if (MainInterface.map_friendsInfo.get(message.myQQ).status == 0)
				{
					MainInterface.map_friendsInfo.get(message.myQQ).status = 1;
					MainInterface.setFriend(message.myQQ, MainInterface.map_friendsInfo.get(message.myQQ));
					MainInterface.c_temp_main.removeAll();
					MainInterface.main_frame.refresh();
					MainInterface.c_temp_main.repaint();
				}
			}
			catch (Exception e1)
			{}
			dispose();
		}
		else if (e.getActionCommand().equals("忽略"))
		{
			dispose();
		}
	}

	class autoExit extends Thread
	{
		public void run()
		{
			try
			{
				Thread.sleep(7000);
				dispose();
			}
			catch (InterruptedException e)
			{}
		}
	}
}
