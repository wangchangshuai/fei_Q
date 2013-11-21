package Windows_chat;

import java.awt.*;
import java.awt.event.*;

import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.*;

import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;


import common.time;
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
public class group extends JFrame implements ActionListener, WindowListener
{
	private int	online_num	= 1, offline_num = 1;
	private int	chat_head	= 1, show_num1 = 1, show_num2 = 2, expression_num = 0;
	private String	user	= "707181749", sign = "Come on !", nickname = "泡沫", myname = "xiaosi";
	private String	content	= "";
	private JTextPane1	jt_send, jt_recive;
	private JLabel		jl_1, jl_2, jl_3, jl_4, jl_5;
	private JButton		jb_close, jb_send, bold_button, italic_button, color_button, expression_button;
	private Dimension	screenSize, frameSize;
	private JPanel		jp_left, jp_right;
	private JScrollPane	js_recive, js_send;
	private JComboBox	font_option, size_option;
	private Color		color;
	private int			color1, color2, color3;
	private JScrollPane	js;

	public group()
	{
		String s = "pic//face//" + String.valueOf(chat_head) + ".jpg";
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
		jl_down.setSize(600, 500);// setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
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
			public void keyTyped(KeyEvent e)
			{
				char c = e.getKeyChar();
				if (((int) c) != 10)
				{
					content = content + c;
				}
				else
				{
					if (!content.equals(""))
					{
						jt_send.setText("");
						jt_recive.insert_text2(myname + "  " + time.gettime());
						jt_recive.insertln(content);
						content = "";
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
		jp_right.setOpaque(false);
		jp_right.setPreferredSize(new Dimension(160, 600));
		jl_5 = new JLabel();
		jl_5.setText("群成员(" + String.valueOf(online_num) + "/" + String.valueOf(online_num + offline_num) + ")");
		jl_5.setBounds(10, 10, 100, 20);
		jl_5.setFont(new Font("宋体", Font.PLAIN, 13));
		jp_right.add(jl_5);
		for (int i = 1; i <= online_num; i++)
		{}
		js = new JScrollPane(jp_right);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		js.setBounds(373, 100, 178, 365);
		c.add(js);
		/*
		 * 
		 * 
		 * 
		 * 
		 * 群名单
		 */
		size_option.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				int font_size = Integer.parseInt((String) size_option.getSelectedItem());
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
		setSize(560, 500);
		setVisible(true);
		setTitle(nickname + "(" + user + ")" + "----" + sign);
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
			jt_recive.setBold();
		}
		else if (e.getActionCommand() == "斜体")
		{
			jt_recive.setItalic();
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
			dispose();
		}
		else if (e.getActionCommand() == "发送")
		{
			if (!jt_send.getText().equals(""))
			{
				jt_recive.insert_text2(myname + "  " + time.gettime());
				jt_recive.insertln(content);
				jt_send.setText("");
				content = "";
			}
			else
			{
				JOptionPane.showMessageDialog(null, "发送内容不能为空，请重新输入!", "警告", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/*
	 * public void set(int chat_head,int show_num1,int show_num2,String user,String nickname,String sign) { this.chat_head = chat_head; this.show_num1 = show_num1; this.show_num2 = show_num2; this.user = user; this.nickname = nickname; this.sign = sign; }
	 */
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
			jt_send.insert_icon(h1 + String.valueOf(num) + h2);
			content = content + "/" + String.valueOf(num) + "\\";
			jt_send.requestFocus();
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
}
