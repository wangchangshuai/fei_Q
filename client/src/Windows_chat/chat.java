package Windows_chat;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.border.LineBorder;

import client.thread.chatWithAnotherQQThread.send_messageThread;
import Windows_MainInterface.MainInterface;

import common.time;
import common.message.chatMessage;
import common.message.getAvailableServerSocketFunction;
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
public class chat extends JFrame implements ActionListener, WindowListener
{
	Integer					show_num1;
	Integer					show_num2;
	private int				chat_head	= 1, expression_num;
	private String			anotherQQ	= null, sign = null, anotherQQnickname = null, myname = null;
	private JTextPane1		jt_send, jt_recive;
	private JLabel			show_up, show_down, jl_1, jl_2, jl_3, jl_4;
	private JButton			jb_close, jb_send, bold_button, italic_button, color_button, expression_button;
	private Dimension		screenSize, frameSize;
	private JPanel			jp_left, jp_right;
	private JScrollPane		js_recive, js_send;
	private JComboBox		font_option, size_option;
	private Color			color;
	private int				color1		= 0, color2 = 0, color3 = 0;
	private int				countBold	= 1, countItaic = 1;
	private personalInfo	personInfo, mypersonInfo;
	private String			text		= "";
	private int				font_size	= 17;
	static int				i			= 0;

	// private String virtualString = "";
	// private int index = 0;
	// 消息接受线程
	class receiver_messageThread extends Thread
	{
		ServerSocket	server	= null;
		Socket			client	= null;

		public receiver_messageThread() throws IOException
		{
			server = getAvailableServerSocketFunction.getServerSocket(MainInterface.begin_window_port);
			MainInterface.map_portLocal.put(anotherQQ, server.getLocalPort());
		}

		public void run()
		{
			try
			{
				while (true)
				{
					client = server.accept();
					receiverMessage_dealingThread receiveMsg = new receiverMessage_dealingThread(client);
				}
			}
			catch (IOException e)
			{}
		}
	}

	// ////////////////////////////下面是处理线程////////////////////////////////////////////////
	class receiverMessage_dealingThread extends Thread
	{
		Socket				client	= null;
		chatMessage			message	= null;
		ObjectInputStream	oin		= null;

		public receiverMessage_dealingThread(Socket client)
		{
			this.client = client;
			start();
		}

		public void run()
		{
			try
			{
				oin = new ObjectInputStream(client.getInputStream());
				message = (chatMessage) oin.readObject();
				// 获取对方QQ号码，备注（昵称），个性签名，性别
				jt_recive.insert_text2(anotherQQnickname + "  " + message.time);
				// jt_recive.insertln(message.text);
				int c1 = color1, c2 = color2, c3 = color3, sizeTemp = font_size;
				transformToReceive(message.text);
				color1 = c1;
				color2 = c2;
				color3 = c3;
				font_size = sizeTemp;
				jt_recive.setFontSize(font_size);
				jt_recive.setColor(new Color(c1, color2, color3));
				jt_recive.selectAll();
				jt_recive.setCaretPosition(jt_recive.getSelectedText().length());
				jt_recive.requestFocus();
				jt_send.requestFocus();
				client.close();
			}
			catch (Exception e)
			{}
		}
	}

	// //////////////////////////
	public chat(personalInfo pInfo, personalInfo myInfo) throws IOException
	{
		personInfo = pInfo;
		mypersonInfo = myInfo;
		anotherQQ = personInfo.qq;
		sign = personInfo.signature;
		myname = mypersonInfo.nickname;
		chat_head = personInfo.headImage;
		if ((personInfo.remark != null) && (!personInfo.remark.equals("")))
		{
			anotherQQnickname = personInfo.remark;
		}
		else
		{
			anotherQQnickname = personInfo.nickname;
		}
		if (personInfo.sex.equals("男"))
		{
			show_num1 = 2;
		}
		else
		{
			show_num1 = 4;
		}
		if (mypersonInfo.sex.equals("男"))
		{
			show_num2 = 2;
		}
		else
		{
			show_num2 = 4;
		}
		String s;
		if (pInfo.status != 0)
		{
			s = "pic//face//" + String.valueOf(chat_head) + ".jpg";
		}
		else
		{
			s = "pic//face//" + String.valueOf(chat_head) + "_3.jpg";
		}
		Image image = Toolkit.getDefaultToolkit().getImage(s);
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前窗口大小
		frameSize = this.getPreferredSize();
		// 保持弹出窗口居中
		this.setLocation((screenSize.width - frameSize.width) / 5, (screenSize.height - frameSize.height) / 6);
		Container container = getContentPane();
		// 设置背景图片
		JLabel jl_down = new JLabel();
		// 把背景图片显示在一个标签里面
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(600, 500);
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		// 上部--logo
		jl_1 = new JLabel();
		jl_1.setBounds(0, 0, 600, 70);
		jl_1.setIcon(new ImageIcon("pic//head1.jpg"));
		c.add(jl_1);
		// 右部
		jp_left = new JPanel();
		jp_left.setOpaque(false);
		jp_left.setLayout(null);
		jp_left.setBounds(0, 70, 370, 430);
		jp_left.setBackground(Color.yellow);
		c.add(jp_left);
		jl_2 = new JLabel();
		jl_2.setBounds(0, 8, 20, 20);
		jl_2.setIcon(new ImageIcon("pic//a.png"));
		jp_left.add(jl_2);
		jl_3 = new JLabel();
		jl_3.setBounds(20, 5, 400, 20);
		jl_3.setText("<html><font face = '宋体' color = red>交谈中请勿轻信汇款、中奖信息、陌生电话，勿使用外挂软件。</font></html>");
		jp_left.add(jl_3);
		// 右部--输出框
		jt_recive = new JTextPane1();
		jt_recive.setColor(new Color(color1, color2, color3));
		// jt_send.requestFocus();
		jt_recive.setOpaque(false);// ???
		js_recive = new JScrollPane(jt_recive);
		js_recive.setBounds(0, 30, 370, 220);
		js_recive.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		js_recive.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jt_recive.setEditable(false);
		jp_left.add(js_recive);
		// 右部--中间功能键--字体
		font_option = new JComboBox();
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < fontNames.length; i++)
		{
			font_option.addItem(fontNames[i]);
		}
		font_option.setBounds(2, 253, 100, 20);
		jp_left.add(font_option);
		// 右部--中间功能键--字体大小
		size_option = new JComboBox();
		size_option.setMaximumRowCount(4);
		for (int i = 12; i <= 22; i += 2)
		{
			size_option.addItem(String.valueOf(i));
		}
		size_option.setBounds(105, 253, 50, 20);
		jp_left.add(size_option);
		bold_button = new JButton(new ImageIcon("pic//option//bold.jpg"));
		bold_button.setActionCommand("粗体");
		bold_button.setBounds(160, 253, 20, 20);
		jp_left.add(bold_button);
		italic_button = new JButton(new ImageIcon("pic//option//italic.jpg"));
		italic_button.setActionCommand("斜体");
		italic_button.setBounds(185, 253, 20, 20);
		jp_left.add(italic_button);
		color_button = new JButton(new ImageIcon("pic//option//color.jpg"));
		color_button.setActionCommand("颜色");
		color_button.setBounds(210, 253, 20, 20);
		jp_left.add(color_button);
		expression_button = new JButton(new ImageIcon("pic//option//expression.jpg"));
		expression_button.setActionCommand("表情");
		expression_button.setBounds(235, 253, 20, 20);
		jp_left.add(expression_button);
		// 右部--输入框
		jt_send = new JTextPane1();
		jt_send.setOpaque(false);// ???
		js_send = new JScrollPane(jt_send);
		js_send.setBounds(0, 275, 370, 100);
		js_send.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		js_send.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jp_left.add(js_send);
		jt_send.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					text = jt_send.getText();// .substring(0, jt_send.getText().length() - 1);
					if (!text.equals(""))
					{
						jt_send.setText("");
						jt_recive.insert_text2(myname + "  " + time.gettime());
						transformToReceive(addSize((addColor(text))));
						// jt_recive.insertln(text);
						// virtualString = "";
						// index = 0;
						jt_recive.selectAll();
						jt_recive.setCaretPosition(jt_recive.getSelectedText().length());
						jt_recive.requestFocus();
						jt_send.requestFocus();
						try
						{
							send_messageThread sender_message = new send_messageThread(
									new chatMessage(mypersonInfo.qq, personInfo.qq, time.gettime(), mypersonInfo.nickname, addSize((addColor(text)))));
						}
						catch (Exception e1)
						{}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "发送内容不能为空，请重新输入!", "警告", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		jl_4 = new JLabel();
		jl_4.setText("<html><font face = '隶书' size = '4'>拍拍网秋冬女装  全场包邮</font></html>");
		jl_4.setBounds(0, 380, 200, 20);
		jp_left.add(jl_4);
		// 右部--发送
		jb_send = new JButton();
		jb_send.setActionCommand("发送");
		jb_send.setIcon(new ImageIcon("pic//fa.jpg"));
		jb_send.setBounds(320, 380, 50, 20);
		jp_left.add(jb_send);
		// 右部--关闭
		jb_close = new JButton();
		jb_close.setActionCommand("关闭");
		jb_close.setIcon(new ImageIcon("pic//close.jpg"));
		jb_close.setBounds(250, 380, 50, 20);
		jp_left.add(jb_close);
		// 左部
		jp_right = new JPanel();
		jp_right.setLayout(null);
		jp_right.setBounds(370, 70, 160, 500);
		jp_right.setOpaque(false);
		c.add(jp_right);
		// 左部--QQ秀1
		String s1 = "pic//people//";
		String s2 = ".JPG";
		String s3 = ".gif";
		show_up = new JLabel();
		show_up.setBounds(10, 10, 140, 226);
		show_up.setIcon(new ImageIcon(s1 + show_num1.toString() + s2));
		jp_right.add(show_up);
		// 左部--QQ秀1
		show_down = new JLabel();
		show_down.setBounds(10, 246, 140, 170);
		show_down.setIcon(new ImageIcon(s1 + show_num2.toString() + s2));
		jp_right.add(show_down);
		size_option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				font_size = Integer.parseInt((String) size_option.getSelectedItem());
				jt_recive.setFontSize(font_size);
			}
		});
		font_option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				jt_recive.setFontFamily((String) font_option.getSelectedItem());
			}
		});
		bold_button.addActionListener(this);
		color_button.addActionListener(this);
		italic_button.addActionListener(this);
		expression_button.addActionListener(this);
		jb_send.addActionListener(this);
		jb_close.addActionListener(this);
		getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setResizable(false);
		setSize(530, 500);
		setVisible(true);
		if (sign != null && (!sign.equals("")))
		{
			setTitle(anotherQQnickname + "(" + anotherQQ + ")" + "----" + sign);
		}
		else
		{
			setTitle(anotherQQnickname + "(" + anotherQQ + ")");
		}
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				MainInterface.map_portLocal.remove(anotherQQ);
				MainInterface.map_chatWindows.remove(anotherQQ);
				dispose();
			}
		});
		receiver_messageThread receiver = new receiver_messageThread();
		receiver.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == "表情")
		{
			// 获取点击点坐标
			Point p = MouseInfo.getPointerInfo().getLocation();
			final expression ex = new expression(p.x, p.y);
			ex.addWindowListener(new WindowAdapter() {
				public void windowActivated(WindowEvent e)
				{}

				// 当窗口不活跃的时候，自动关闭该窗口
				public void windowDeactivated(WindowEvent e)
				{
					ex.dispose();
				}
			});
			ex.setVisible(true);
		}
		else if (e.getActionCommand() == "粗体")
		{
			if ((countBold++) % 2 == 0)
			{
				jt_recive.setUnBold();
			}
			else
			{
				jt_recive.setBold();
			}
		}
		else if (e.getActionCommand() == "斜体")
		{
			if ((countItaic++) % 2 == 0)
			{
				jt_recive.setUnItalic();
			}
			else
			{
				jt_recive.setItalic();
			}
		}
		else if (e.getActionCommand() == "颜色")
		{
			color = JColorChooser.showDialog(null, "选择颜色", Color.black);// 显示调色板
			color1 = color.getRed();
			color2 = color.getGreen();
			color3 = color.getBlue();
			jt_recive.setColor(new Color(color1, color2, color3));
			jt_send.requestFocus();
		}
		else if (e.getActionCommand() == "关闭")
		{
			MainInterface.map_portLocal.remove(anotherQQ);
			MainInterface.map_chatWindows.remove(anotherQQ);
			dispose();
		}
		else if (e.getActionCommand() == "发送")
		{
			if (!jt_send.getText().equals(""))
			{
				text = jt_send.getText();
				jt_recive.insert_text2(myname + "  " + time.gettime());
				transformToReceive(addSize((addColor(text))));
				jt_send.setText("");
				jt_recive.selectAll();
				jt_recive.setCaretPosition(jt_recive.getSelectedText().length());
				jt_recive.requestFocus();
				jt_send.requestFocus();
				try
				{
					send_messageThread sender_message = new send_messageThread(new chatMessage(mypersonInfo.qq, personInfo.qq, time.gettime(), mypersonInfo.nickname, addSize((addColor(text)))));
				}
				catch (Exception e1)
				{}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "发送内容不能为空，请重新输入!", "警告", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// 表情类
	public class expression extends JFrame implements MouseListener
	{
		int				lx, ly;
		private JLabel	jl[][];
		Container		c	= getContentPane();

		public expression(int x, int y)
		{
			lx = 1 * 14 + 24 * 15;
			ly = 1 * 7 + 24 * 8;
			setSize(lx, ly);
			setLocation(x - lx / 2, y - ly);
			setUndecorated(true);
			setVisible(true);
			jl = new JLabel[9][];
			String h1 = "pic//expression//";
			String h2 = ".gif";
			for (int i = 1; i <= 8; i++)
			{
				jl[i] = new JLabel[16];
				for (int j = 1; j <= 15; j++)
				{
					int num = (i - 1) * 15 + j;
					jl[i][j] = new JLabel();
					jl[i][j].setBounds(new Rectangle((j - 1) * 25, (i - 1) * 25, 24, 24));
					jl[i][j].setIcon(new ImageIcon(h1 + String.valueOf(num) + h2));
					jl[i][j].addMouseListener(this);
					jl[i][j].setBorder(new LineBorder(Color.gray));// 边框
					jl[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
					c.add(jl[i][j]);
				}
			}
		}

		public void mouseReleased(MouseEvent e)
		{}

		public void mousePressed(MouseEvent e)
		{}

		public void mouseEntered(MouseEvent e)
		{
			JLabel l = (JLabel) e.getSource();
			for (int i = 1; i <= 8; i++)
			{
				for (int j = 1; j <= 15; j++)
				{
					if (l == jl[i][j])
					{
						jl[i][j].setBorder(new LineBorder(Color.blue, 2));
						break;
					}
				}
			}
		}

		public void mouseExited(MouseEvent e)
		{
			JLabel l = (JLabel) e.getSource();
			for (int i = 1; i <= 8; i++)
			{
				for (int j = 1; j <= 15; j++)
				{
					if (l == jl[i][j])
					{
						jl[i][j].setBorder(new LineBorder(Color.gray));
						break;
					}
				}
			}
		}

		public void mouseClicked(MouseEvent e)
		{
			int x, y, num = 7, i, j;
			JLabel l = (JLabel) e.getSource();
			for (i = 1; i <= 8; i++)
			{
				for (j = 1; j <= 15; j++)
				{
					if (l == jl[i][j])
					{
						x = i;
						y = j;
						num = (x - 1) * 15 + y;
						break;
					}
				}
			}
			String h1 = "pic//expression//";
			String h2 = ".gif";
			String number_str = "";
			if (num < 10)
			{
				number_str += String.valueOf(0) + String.valueOf(0) + String.valueOf(num);
				jt_send.insert("\\" + number_str);
			}
			else if (num >= 10 && num < 100)
			{
				number_str += String.valueOf(0) + String.valueOf(num);
				jt_send.insert("\\" + number_str);
			}
			else
			{
				number_str += String.valueOf(num);
				jt_send.insert("\\" + number_str);
			}
			// jt_send.insert_icon(h1 + String.valueOf(num) + h2);
			jt_send.requestFocus();
		}
	}

	public void windowOpened(WindowEvent e)
	{}

	public void windowClosing(WindowEvent e)
	{
		MainInterface.map_portLocal.remove(anotherQQ);
		MainInterface.map_chatWindows.remove(anotherQQ);
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

	public String addColor(String str)
	{
		String color_str1 = "";
		String color_str2 = "";
		String color_str3 = "";
		if (color1 < 10)
		{
			color_str1 = String.valueOf(0) + String.valueOf(0) + String.valueOf(color1);
		}
		else if (color1 >= 10 && color1 < 100)
		{
			color_str1 = String.valueOf(0) + String.valueOf(color1);
		}
		else
		{
			color_str1 = String.valueOf(color1);
		}
		if (color2 < 10)
		{
			color_str2 = String.valueOf(0) + String.valueOf(0) + String.valueOf(color2);
		}
		else if (color2 >= 10 && color2 < 100)
		{
			color_str2 = String.valueOf(0) + String.valueOf(color2);
		}
		else
		{
			color_str2 = String.valueOf(color2);
		}
		if (color3 < 10)
		{
			color_str3 = String.valueOf(0) + String.valueOf(0) + String.valueOf(color3);
		}
		else if (color3 >= 10 && color3 < 100)
		{
			color_str3 = String.valueOf(0) + String.valueOf(color3);
		}
		else
		{
			color_str3 = String.valueOf(color3);
		}
		return color_str1 + color_str2 + color_str3 + str;
	}

	public String addSize(String str)
	{
		return String.valueOf(font_size) + str;
	}

	public void transformToReceive(String str)
	{
		font_size = Integer.parseInt(str.substring(0, 2));
		color1 = Integer.parseInt(str.substring(2, 5));
		color2 = Integer.parseInt(str.substring(5, 8));
		color3 = Integer.parseInt(str.substring(8, 11));
		jt_recive.setFontSize(font_size);
		jt_recive.setColor(new Color(color1, color2, color3));
		jt_send.requestFocus();
		String h1 = "pic//expression//";
		String h2 = ".gif";
		for (i = 11; i <= str.length() - 4; i++)
		{
			if (str.charAt(i) == '\\' && '0' <= str.charAt(i + 1) && str.charAt(i + 1) <= '9' && '0' <= str.charAt(i + 2) && str.charAt(i + 2) <= '9' && str.charAt(i + 1) <= '9'
					&& '0' <= str.charAt(i + 3) && str.charAt(i + 3) <= '9')
			{
				String int_temp = str.substring(i + 1, i + 4);
				int num = Integer.parseInt(int_temp);
				if (num > 120) // 假如超出图片的个数
				{
					jt_recive.insert(str.substring(i, i + 1));
					continue;
				}
				jt_recive.insert_icon(h1 + String.valueOf(num) + h2);
				i += 3;
				continue;
			}
			jt_recive.insert(str.substring(i, i + 1));
		}
		if (i != str.length())
			jt_recive.insert(str.substring(i));
		jt_recive.insertln("");
	}
}
