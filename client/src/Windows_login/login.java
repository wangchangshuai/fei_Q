package Windows_login;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;

import Windows_MainInterface.MainInterface;
import Windows_apply.apply;
import Windows_safe.find_password;

import javax.swing.*;

import common.message.*;
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
public class login extends JFrame implements MouseListener
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel				jp_login;
	private JTextField			jt_user;
	private JPasswordField		jp_password;
	private JButton				jb_login;
	private JLabel				jl_logo, jl_user, jl_password, jl_apply, jl_rember, jl_status;
	private JComboBox			jco_status;
	private JCheckBox			jch_rember, jch_login;
	private Dimension			screenSize;

	public login()
	{
		Image image = Toolkit.getDefaultToolkit().getImage("pic//headpic.jpg");
		setIconImage(image);
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前窗口大小
		this.setLocation(screenSize.width / 3, screenSize.height / 3);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		// 处理北部
		// logo/////////////////////////////////////////////////////////
		jl_logo = new JLabel();
		jl_logo.setIcon(new ImageIcon("pic/logo.gif"));
		c.add(jl_logo, BorderLayout.NORTH);
		// 处理中部
		// Center//////////////////////////////////////////////////////////////
		jp_login = new JPanel();
		jp_login.setLayout(null);
		// QQ账号
		jl_user = new JLabel();
		jl_user.setText("QQ账号");
		jl_user.setBounds(10, 30, 50, 20);
		jl_user.setFont(new Font("宋体", Font.PLAIN, 12));
		jp_login.add(jl_user);
		// QQ账号登陆框
		jt_user = new JTextField();
		jt_user.setHorizontalAlignment(JTextField.LEFT);
		jt_user.setBounds(70, 30, 160, 20);
		jp_login.add(jt_user);
		// 申请账号
		jl_apply = new JLabel();
		jl_apply.setText("申请账号");
		jl_apply.setFont(new Font("宋体", Font.PLAIN, 12));
		jl_apply.setBounds(250, 30, 80, 20);
		jl_apply.setForeground(Color.blue);
		jl_apply.addMouseListener(this);
		jp_login.add(jl_apply);
		// QQ密码
		jl_password = new JLabel();
		jl_password.setText("QQ密码");
		jl_password.setBounds(10, 60, 50, 20);
		jl_password.setFont(new Font("宋体", Font.PLAIN, 12));
		jp_login.add(jl_password);
		// QQ密码登陆框
		jp_password = new JPasswordField();
		jp_password.setBounds(70, 60, 160, 20);
		jp_password.setHorizontalAlignment(JTextField.LEFT);
		jp_login.add(jp_password);
		// 添加键盘事件
		jp_password.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e)
			{}

			public void keyReleased(KeyEvent e)
			{}

			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						jb_loginactionPerformed(e);
					}
					catch (Exception e1)
					{}
				}
			}
		});
		// 忘了密码
		jl_rember = new JLabel();
		jl_rember.setText("忘了密码？");
		jl_rember.setBounds(250, 60, 70, 20);
		jl_rember.setFont(new Font("宋体", Font.PLAIN, 12));
		jl_rember.setForeground(Color.blue);
		jl_rember.addMouseListener(this);
		jp_login.add(jl_rember);
		// 状态
		jl_status = new JLabel();
		jl_status.setText("状态:");
		jl_status.setBounds(40, 110, 40, 20);
		jl_status.setFont(new Font("宋体", Font.PLAIN, 13));
		jl_status.setForeground(Color.black);
		jp_login.add(jl_status);
		jco_status = new JComboBox();
		jco_status.addItem("在线");
		jco_status.addItem("离开");
		jco_status.addItem("忙碌");
		jco_status.addItem("隐身");
		jco_status.setBounds(80, 110, 60, 20);
		jco_status.setFont(new Font("宋体", Font.PLAIN, 13));
		jp_login.add(jco_status);
		// 记录密码
		jch_rember = new JCheckBox();
		jch_rember.setText("记住密码");
		jch_rember.setBounds(145, 110, 80, 20);
		jch_rember.setFont(new Font("宋体", Font.PLAIN, 13));
		jp_login.add(jch_rember);
		// 自动登录
		jch_login = new JCheckBox();
		jch_login.setText("自动登录");
		jch_login.setBounds(230, 110, 80, 20);
		jch_login.setFont(new Font("宋体", Font.PLAIN, 13));
		jp_login.add(jch_login);
		// 登陆
		jb_login = new JButton();
		jb_login.setIcon(new ImageIcon("pic//login.gif"));
		jb_login.setBounds(240, 140, 65, 20);
		jp_login.add(jb_login);
		c.add(jp_login, BorderLayout.CENTER);
		// 响应
		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					jb_loginactionPerformed(e);
				}
				catch (Exception e1)
				{}
			}
		});
		setResizable(false);
		setSize(348, 250);
		setVisible(true);
		setTitle("飞Q2012");
	}

	protected void processWindowEvent(WindowEvent e)
	{
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			System.exit(0);
		}
		else
		{
			super.processWindowEvent(e);
		}
	}

	public void setUser(String s)
	{
		jt_user.setText(s);
	}

	private void jb_loginactionPerformed(ActionEvent e) throws UnknownHostException, IOException, InterruptedException
	{
		if (jt_user.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "请输入账号后再登陆", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (jt_user.getText().length() < 6 || jt_user.getText().length() > 20)
		{
			JOptionPane.showMessageDialog(null, "请输入正确的账号,账号可以位数字或Email地址。", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (jp_password.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "请输入密码后再登陆", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			login_message lmessage = new login_message(jt_user.getText(), jp_password.getText(), 0, 0, 0, 0, jco_status.getSelectedIndex() + 1);
			MainInterface main = new MainInterface(lmessage);
			this.dispose();
		}
	}

	private void jb_loginactionPerformed(KeyEvent e) throws UnknownHostException, IOException, InterruptedException
	{
		if (jt_user.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "请输入账号后再登陆", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (jt_user.getText().length() < 6 || jt_user.getText().length() > 20)
		{
			JOptionPane.showMessageDialog(null, "请输入正确的账号,账号可以位数字或Email地址。", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (jp_password.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "请输入密码后再登陆", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			login_message lmessage = new login_message(jt_user.getText(), jp_password.getText(), 0, 0, 0, 0, jco_status.getSelectedIndex() + 1);
			MainInterface main = new MainInterface(lmessage);
			this.dispose();
		}
	}

	public void mouseReleased(MouseEvent e)
	{}

	public void mousePressed(MouseEvent e)
	{}

	public void mouseEntered(MouseEvent e)
	{}

	public void mouseExited(MouseEvent e)
	{}

	public void mouseClicked(MouseEvent e)
	{
		JLabel jl = (JLabel) e.getSource();
		if (jl == jl_rember)
		{
			if (jt_user.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请先输入账号", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				try
				{
					find_password a = new find_password(jt_user.getText());
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
			}
		}
		else if (jl == jl_apply)
		{
			apply a = new apply();
		}
	}

	public static void main(String args[]) throws Exception
	{
		login application = new login();
	}
}
