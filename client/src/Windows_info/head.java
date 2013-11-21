package Windows_info;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
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
public class head extends JFrame implements ActionListener, WindowListener
{
	private int		head_num;
	private String	IMAGE_1	= "pic//face//";
	private String	IMAGE_2	= ".jpg";
	private JPanel	jp_left, jp_buttom, jp_right;
	private JLabel	jl_logo, jl_1, jl_2, jl_3, jl_pic1, jl_pic2, jl_4;
	private JButton	jb[][], jb_sure, jb_cancel;
	private Dimension	screenSize, frameSize;
	private JScrollPane	js;

	public head()
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
		}
		else if (e.getActionCommand().equals("cancel"))
		{
			dispose();
		}
		else if (e.getActionCommand().equals("1"))
		{
			head_num = 1;
			jl_pic1.setIcon(new ImageIcon("pic//face//1.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//1_4.jpg"));
		}
		else if (e.getActionCommand().equals("2"))
		{
			head_num = 2;
			jl_pic1.setIcon(new ImageIcon("pic//face//2.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//2_4.jpg"));
		}
		else if (e.getActionCommand().equals("3"))
		{
			head_num = 3;
			jl_pic1.setIcon(new ImageIcon("pic//face//3.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//3_4.jpg"));
		}
		else if (e.getActionCommand().equals("4"))
		{
			head_num = 4;
			jl_pic1.setIcon(new ImageIcon("pic//face//4.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//4_4.jpg"));
		}
		else if (e.getActionCommand().equals("5"))
		{
			head_num = 5;
			jl_pic1.setIcon(new ImageIcon("pic//face//5.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//5_4.jpg"));
		}
		else if (e.getActionCommand().equals("6"))
		{
			head_num = 6;
			jl_pic1.setIcon(new ImageIcon("pic//face//6.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//6_4.jpg"));
		}
		else if (e.getActionCommand().equals("7"))
		{
			head_num = 7;
			jl_pic1.setIcon(new ImageIcon("pic//face//7.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//7_4.jpg"));
		}
		else if (e.getActionCommand().equals("8"))
		{
			head_num = 8;
			jl_pic1.setIcon(new ImageIcon("pic//face//8.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//8_4.jpg"));
		}
		else if (e.getActionCommand().equals("9"))
		{
			head_num = 9;
			jl_pic1.setIcon(new ImageIcon("pic//face//9.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//9_4.jpg"));
		}
		else if (e.getActionCommand().equals("10"))
		{
			head_num = 10;
			jl_pic1.setIcon(new ImageIcon("pic//face//10.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//10_4.jpg"));
		}
		else if (e.getActionCommand().equals("11"))
		{
			head_num = 11;
			jl_pic1.setIcon(new ImageIcon("pic//face//11.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//11_4.jpg"));
		}
		else if (e.getActionCommand().equals("12"))
		{
			head_num = 12;
			jl_pic1.setIcon(new ImageIcon("pic//face//12.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//12_4.jpg"));
		}
		else if (e.getActionCommand().equals("13"))
		{
			head_num = 13;
			jl_pic1.setIcon(new ImageIcon("pic//face//13.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//13_4.jpg"));
		}
		else if (e.getActionCommand().equals("14"))
		{
			head_num = 14;
			jl_pic1.setIcon(new ImageIcon("pic//face//14.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//14_4.jpg"));
		}
		else if (e.getActionCommand().equals("15"))
		{
			head_num = 15;
			jl_pic1.setIcon(new ImageIcon("pic//face//15.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//15_4.jpg"));
		}
		else if (e.getActionCommand().equals("16"))
		{
			head_num = 16;
			jl_pic1.setIcon(new ImageIcon("pic//face//16.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//16_4.jpg"));
		}
		else if (e.getActionCommand().equals("17"))
		{
			head_num = 17;
			jl_pic1.setIcon(new ImageIcon("pic//face//17.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//17_4.jpg"));
		}
		else if (e.getActionCommand().equals("18"))
		{
			head_num = 18;
			jl_pic1.setIcon(new ImageIcon("pic//face//18.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//18_4.jpg"));
		}
		else if (e.getActionCommand().equals("19"))
		{
			head_num = 19;
			jl_pic1.setIcon(new ImageIcon("pic//face//19.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//19_4.jpg"));
		}
		else if (e.getActionCommand().equals("20"))
		{
			head_num = 20;
			jl_pic1.setIcon(new ImageIcon("pic//face//20.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//20_4.jpg"));
		}
		else if (e.getActionCommand().equals("21"))
		{
			head_num = 21;
			jl_pic1.setIcon(new ImageIcon("pic//face//21.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//21_4.jpg"));
		}
		else if (e.getActionCommand().equals("22"))
		{
			head_num = 22;
			jl_pic1.setIcon(new ImageIcon("pic//face//22.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//22_4.jpg"));
		}
		else if (e.getActionCommand().equals("23"))
		{
			head_num = 23;
			jl_pic1.setIcon(new ImageIcon("pic//face//23.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//23_4.jpg"));
		}
		else if (e.getActionCommand().equals("24"))
		{
			head_num = 24;
			jl_pic1.setIcon(new ImageIcon("pic//face//24.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//24_4.jpg"));
		}
		else if (e.getActionCommand().equals("25"))
		{
			head_num = 25;
			jl_pic1.setIcon(new ImageIcon("pic//face//25.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//25_4.jpg"));
		}
		else if (e.getActionCommand().equals("26"))
		{
			head_num = 26;
			jl_pic1.setIcon(new ImageIcon("pic//face//26.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//26_4.jpg"));
		}
		else if (e.getActionCommand().equals("27"))
		{
			head_num = 27;
			jl_pic1.setIcon(new ImageIcon("pic//face//27.jpg"));
			jl_pic2.setIcon(new ImageIcon("pic//face//27_4.jpg"));
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
