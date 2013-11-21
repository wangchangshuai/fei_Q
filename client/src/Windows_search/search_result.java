package Windows_search;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import Windows_MainInterface.MainInterface;
import Windows_addFriend.addfriend;
import Windows_info.friendinfo;

import client.thread.getInfoThread.getInfoThread;

import common.message.mainInfo;
import common.message.personalSmallInfoList;
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
public class search_result extends JFrame implements ActionListener, MouseListener
{
	private personalSmallInfoList	personList;
	private int						row			= 0;
	private JPanel					jp;
	private JButton					jb_add, jb_close;
	private JLabel					jl1, jl2;
	private JScrollPane				js;
	private JTable					jtable;
	mainInfo						mInfo		= MainInterface.mInfo;
	int								count;
	private String[]				columnNames	= { "账号", "昵称", "来自", "状态", "性别" };
	private String[][]				data		= new String[count][];

	public search_result(personalSmallInfoList pList)
	{
		personList = new personalSmallInfoList(pList);
		count = personList.count;
		data = new String[count][];
		Image image = Toolkit.getDefaultToolkit().getImage("pic//face//18.jpg");
		setIconImage(image);
		Container container = getContentPane();
		JLabel jl_down = new JLabel();
		jl_down.setIcon(new ImageIcon("pic//background//2.jpg"));
		jl_down.setSize(500, 300);
		JPanel c = (JPanel) container;
		c.add(jl_down);
		c.setOpaque(false);
		c.setLayout(null);
		jl1 = new JLabel();
		jl1.setIcon(new ImageIcon("pic//search_result//search.gif"));
		jl1.setBounds(30, 30, 43, 20);
		c.add(jl1);
		jl2 = new JLabel();
		jl2.setText("以下是为你查找到的用户");
		jl2.setBounds(90, 30, 200, 20);
		jl2.setFont(new Font("宋体", Font.PLAIN, 14));
		c.add(jl2);
		MyTableModel Mytable = new MyTableModel();
		jtable = new JTable(Mytable);
		jtable.setShowGrid(false);// 不显示边框
		jtable.setPreferredScrollableViewportSize(new Dimension(470, 400));
		jtable.setDefaultRenderer(Object.class, new TableCellRender());
		jtable.addMouseListener(this);
		js = new JScrollPane(jtable);
		js.setBounds(30, 60, 440, 170);
		c.add(js);
		jb_add = new JButton();
		jb_add.setBounds(300, 245, 67, 22);
		jb_add.setIcon(new ImageIcon("pic//search_result//add.jpg"));
		jb_add.addActionListener(this);
		jb_add.setActionCommand("添加好友");
		if (jtable.getRowCount() == 0)
		{
			jb_add.setEnabled(false);
		}
		else
		{
			jb_add.setEnabled(true);
		}
		c.add(jb_add);
		jb_close = new JButton();
		jb_close.setBounds(390, 245, 67, 22);
		jb_close.setIcon(new ImageIcon("pic//search_result//close.jpg"));
		jb_close.addActionListener(this);
		jb_close.setActionCommand("关闭");
		c.add(jb_close);
		getLayeredPane().setLayout(null);
		getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
		setLocation(300, 200);
		setResizable(false);
		setSize(500, 300);
		setVisible(true);
		setTitle("添加好友");
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
		Point p = e.getPoint();
		row = jtable.rowAtPoint(p);
		if (e.getClickCount() == 2)
		{
			// 双击时获得她的个人资料
			try
			{
				getInfoThread get_info = new getInfoThread(new qq_num(data[row][0]));
				get_info.join(10000);
				if (get_info.pInfo != null)
				{
					friendinfo info = new friendinfo(get_info.pInfo);
				}
				else if (get_info.pInfo == null)
				{
					JOptionPane.showMessageDialog(null, "系统繁忙，请稍候再试...", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "查看资料超时，请稍候再试...", "温馨提示", JOptionPane.WARNING_MESSAGE);
				}
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("添加好友"))
		{
			addfriend in = new addfriend((String) (jtable.getValueAt(row, 1)), (String) (jtable.getValueAt(row, 0)), 2);// mInfo.myself.headImage
		}
		else if (e.getActionCommand().equals("关闭"))
		{
			dispose();
		}
	}

	class MyTableModel extends AbstractTableModel
	{
		public MyTableModel()
		{
			for (int i = 0; i < count; i++)
			{
				data[i] = new String[5];
				data[i][0] = personList.pinf[i].qq;
				data[i][1] = personList.pinf[i].nickname;
				data[i][2] = personList.pinf[i].country + personList.pinf[i].province + personList.pinf[i].city;
				if (personList.pinf[i].status == 0 || personList.pinf[i].status == 4)
				{
					data[i][3] = "离线";
				}
				else if (personList.pinf[i].status == 1 || personList.pinf[i].status == 2 || personList.pinf[i].status == 3)
				{
					data[i][3] = "在线";
				}
				data[i][4] = personList.pinf[i].sex;
			}
		}

		public int getColumnCount()
		{
			return columnNames.length;
		}

		public int getRowCount()
		{
			return data.length;
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public String getValueAt(int row, int col)
		{
			return data[row][col];
		}

		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col)
		{
			return false;
		}
	}
}
