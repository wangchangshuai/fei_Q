package Windows_safe;

import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.LineBorder;

import common.message.safeQuestionOrAnswer;
import common.message.updatePassword;

import client.thread.getPasswordThread.getSafeQuestionThread;
import client.thread.getPasswordThread.sendSafeAnswerThread;
import client.thread.updatePasswordThread.updatePasswordThread;
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
public class find_password extends JFrame implements ActionListener
{
	private String question[] = { "您母亲的生日是？", "您父亲的生日是？", "您的生日是？", "您高中班主任的名字是？", "您初中班主任的名字是？", "您的出生地是？", "您学号（工号）是？", "您大学的名字是？" };
	private Dimension screenSize;
	private JButton jb1, jb2;
	private JTextArea jt1_1, jt1_2, jt1_3;
	private JPasswordField jpw1, jpw2;
	private CardLayout cardl;
	private JPanel card, jp1, jp2;
	private JLabel jl1_1, jl1_2, jl1_3, jl1_4, jl1_5, jl1_6, jl1_7, jl1_8, jl1_9, jl1_10, jl1_11, jl1_12, jl2_1, jl2_2, jl2_3, jl2_4, jl2_5, jl2_6, jl2_7, jl2_8, jl2_9;
	getSafeQuestionThread getQ = null;
	String qq = "";

	public find_password(String qq) throws UnknownHostException, IOException, InterruptedException
	{
		this.qq = qq;
		getQ = new getSafeQuestionThread(qq);
		getQ.join(10000);
		if (getQ.safe != null)
		{
			Image image = Toolkit.getDefaultToolkit().getImage("pic//face//b.jpg");
			setIconImage(image);
			// 获取当前屏幕大小
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(screenSize.width / 5, screenSize.height / 6);
			Container container = getContentPane();
			// 设置背景图片
			JLabel jl_down = new JLabel();
			// 把背景图片显示在一个标签里面
			jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
			jl_down.setSize(550, 450);// setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
			// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
			JPanel c = (JPanel) container;
			c.add(jl_down);
			c.setOpaque(false);
			c.setLayout(null);
			jp1 = new JPanel();
			jp2 = new JPanel();
			cardl = new CardLayout();
			card = new JPanel();
			card.setOpaque(false);
			card.setLayout(cardl);
			card.setBounds(10, 10, 375, 300);
			card.add("验证密保", jp1);
			card.add("修改密码", jp2);
			c.add(card);
			// 验证密保
			jp1.setLayout(null);
			jp1.setOpaque(false);
			jp1.setBorder(new LineBorder(Color.gray));
			jl1_2 = new JLabel();
			jl1_2.setIcon(new ImageIcon("pic//main//key.png"));
			jl1_2.setBounds(30, 30, 40, 40);
			jl1_2.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_2);
			jl1_3 = new JLabel();
			jl1_3.setText("验证密保");
			jl1_3.setBounds(75, 40, 150, 20);
			jl1_3.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_3);
			jl1_4 = new JLabel();
			jl1_4.setText("问题一：" + getQ.safe.text1);
			jl1_4.setBounds(30, 80, 300, 20);
			jl1_4.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_4);
			jl1_5 = new JLabel();
			jl1_5.setText("答案一：");
			jl1_5.setBounds(30, 110, 60, 20);
			jl1_5.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_5);
			jt1_1 = new JTextArea();
			jt1_1.setBounds(87, 110, 200, 20);
			jt1_1.setBorder(new LineBorder(Color.gray));
			jp1.add(jt1_1);
			jl1_6 = new JLabel();
			jl1_6.setText("问题二：" + getQ.safe.text2);
			jl1_6.setBounds(30, 140, 300, 20);
			jl1_6.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_6);
			jl1_7 = new JLabel();
			jl1_7.setText("答案二：");
			jl1_7.setBounds(30, 170, 60, 20);
			jl1_7.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_7);
			jt1_2 = new JTextArea();
			jt1_2.setBounds(87, 170, 200, 20);
			jt1_2.setBorder(new LineBorder(Color.gray));
			jp1.add(jt1_2);
			jl1_8 = new JLabel();
			jl1_8.setText("问题三：" + getQ.safe.text3);
			jl1_8.setBounds(30, 200, 300, 20);
			jl1_8.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_8);
			jl1_9 = new JLabel();
			jl1_9.setText("答案三：");
			jl1_9.setBounds(30, 230, 60, 20);
			jl1_9.setFont(new Font("宋体", Font.PLAIN, 14));
			jp1.add(jl1_9);
			jt1_3 = new JTextArea();
			jt1_3.setBounds(87, 230, 200, 20);
			jt1_3.setBorder(new LineBorder(Color.gray));
			jp1.add(jt1_3);
			jb1 = new JButton();
			jb1.setBounds(310, 260, 50, 20);
			jb1.setIcon(new ImageIcon("pic//main//tijiao.gif"));
			jb1.addActionListener(this);
			jb1.setActionCommand("提交");
			jp1.add(jb1);
			// 修改密码
			jp2.setLayout(null);
			jp2.setOpaque(false);
			jp2.setBorder(new LineBorder(Color.gray));
			jl2_1 = new JLabel();
			jl2_1.setText("修改密码");
			jl2_1.setBounds(20, 40, 100, 20);
			jl2_1.setFont(new Font("宋体", Font.PLAIN, 16));
			jl2_1.setForeground(Color.red);
			jp2.add(jl2_1);
			jl2_2 = new JLabel();
			jl2_2.setText("请输入新密码：");
			jl2_2.setBounds(20, 80, 300, 20);
			jl2_2.setFont(new Font("宋体", Font.PLAIN, 14));
			jp2.add(jl2_2);
			jpw1 = new JPasswordField();
			jpw1.setBounds(20, 110, 200, 20);
			jpw1.setFont(new Font("宋体", Font.PLAIN, 14));
			jp2.add(jpw1);
			jl2_3 = new JLabel();
			jl2_3.setText("( 6-20个字符组成，区分大小写 )");
			jl2_3.setBounds(20, 140, 350, 20);
			jl2_3.setFont(new Font("宋体", Font.PLAIN, 12));
			jl2_3.setForeground(Color.red);
			jp2.add(jl2_3);
			jl2_4 = new JLabel();
			jl2_4.setText("请重复输入新密码：");
			jl2_4.setBounds(20, 170, 300, 20);
			jl2_4.setFont(new Font("宋体", Font.PLAIN, 14));
			jp2.add(jl2_4);
			jpw2 = new JPasswordField();
			jpw2.setBounds(20, 200, 200, 20);
			jpw2.setFont(new Font("宋体", Font.PLAIN, 14));
			jp2.add(jpw2);
			jb2 = new JButton();
			jb2.setBounds(310, 260, 50, 20);
			jb2.setIcon(new ImageIcon("pic//main//tijiao.gif"));
			jb2.addActionListener(this);
			jb2.setActionCommand("提交2");
			jp2.add(jb2);
			getLayeredPane().setLayout(null);
			// 把背景图片添加到分层窗格的最底层作为背景
			getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
			setResizable(false);
			setSize(400, 350);
			setVisible(true);
			setTitle("找回密码");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "对不起，由于您没有设置密保或系统繁忙，您不能找回密码！", "系统提示", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("提交"))
		{
			sendSafeAnswerThread sender_answer = null;
			try
			{
				sender_answer = new sendSafeAnswerThread(new safeQuestionOrAnswer(qq, jt1_1.getText(), jt1_2.getText(), jt1_3.getText()));
				sender_answer.join(10000);
			}
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (sender_answer.password != null)
			{
				cardl.show(card, "修改密码");
			}
			else
			{
				jt1_1.setText("");
				jt1_2.setText("");
				jt1_3.setText("");
				JOptionPane.showMessageDialog(null, "对不起，密保问题错误或系统繁忙，请重新输入！", "系统提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getActionCommand().equals("提交2"))
		{
			int len1 = jpw1.getText().length();
			int len2 = jpw2.getText().length();
			if (jpw1.getText().equals("") || jpw2.getText().equals(""))
			{
				jl2_3.setText("*密码不能为空");
				jpw1.setText("");
				jpw2.setText("");
			}
			else if (!jpw1.getText().equals(jpw2.getText()))
			{
				jl2_3.setText("两次密码输入不一致");
				jpw1.setText("");
				jpw2.setText("");
			}
			else if ((len1 < 6 || len1 > 20) || (len2 < 6 || len2 > 20))
			{
				jl2_3.setText("密码必须6-20位");
				jpw1.setText("");
				jpw2.setText("");
			}
			else
			{
				try
				{
					updatePasswordThread updatePws = new updatePasswordThread(new updatePassword(qq, jpw1.getText()));
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "不好意思，服务器正在维护中，请稍后再试......");
				}
				JOptionPane.showMessageDialog(null, "修改密码成功！");
				dispose();
			}
		}
	}
}
