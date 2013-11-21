package server.serverMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jws.Oneway;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import common.message.node_info;
import common.message.node_public;
import common.message.testMessage;
import server.thread.addFriendsThread.addFriendsThread;
import server.thread.addFriendsThread.authenticationMessageReceiverThread;
import server.thread.addFriendsThread.receiveAgreeRefuseThread;
import server.thread.addqqCanUseThread.addqqCanUseThread;
import server.thread.applicateThread.applicateServerThread;
import server.thread.deleteFridendsThread.deleteFriendsThread;
import server.thread.getAnotherQQIpThread.getAnotherQQIpThread;
import server.thread.getInfoThread.getInfoThread;
import server.thread.groupDealThread.groupDealThread;
import server.thread.heartbeatThread.heartbeatReceiver;
import server.thread.heartbeatThread.judgeAliveThread;
import server.thread.loginThread.loginServerThread;
import server.thread.moveFriendThread.moveFriendThread;
import server.thread.safeThread.judgeWhetherQuestionExistThread;
import server.thread.safeThread.receiveSafeAnswerThread;
import server.thread.safeThread.setSafeQuestionThread;
import server.thread.safeThread.updatePasswordThread;
import server.thread.searchThread.searchThread;
import server.thread.systemMessageThread.sendSystemMessageThread;
import server.thread.systemSettingsThread.systemSettingThread;
import server.thread.updatePersonalInfoThread.updatePersonalInfoThread;
import server.thread.updateRemarkThread.updateRemarkThead;

/**
 * 2011年10月
 * 
 * 山东科技大学信息学院 版权所有
 * 
 * 联系邮箱：415939252@qq.com
 * 
 * Copyright © 1999-2012, sdust, All Rights Reserved
 * 
 * @author 王昌帅
 * 
 */
public class serverMain extends JFrame
{
	JPanel j1 = null;
	JPanel j2 = null;
	JPanel j3 = null;
	JPanel j4 = null;
	JScrollPane jsroll0 = null;
	JScrollPane jsroll1 = null;
	JScrollPane jsroll2 = null;
	JScrollPane jsroll3 = null;
	JTextPane textPane0 = null;
	JTextPane textPane1 = null;
	public static JTextPane textPane2 = null;
	JTextField textf_setLimit = null;
	JButton jb_setLimit = null;
	JLabel jlable_setLimit = null;
	JComboBox jcom_setDatabase = null;
	JButton jb_setDatabase = null;
	JLabel jlable_setDatabase = null;
	JTable table = null;
	JLabel jlable1 = null;
	JLabel jlable2 = null;
	JLabel jlable_warning = null;
	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	JButton jb4 = null;
	MyTableModel myModel = null;
	addqqCanUseThread addCanUse;
	heartbeatReceiver heartbeat;
	judgeAliveThread judgeAlive;
	loginServerThread loginServer;
	applicateServerThread applicateServer;
	searchThread searchServer;
	addFriendsThread addServer;
	authenticationMessageReceiverThread authenticationReceiveServer;
	receiveAgreeRefuseThread agreeRefuseServer;
	deleteFriendsThread deleteServer;
	getAnotherQQIpThread getIpServer;
	getInfoThread getInfoServer;
	judgeWhetherQuestionExistThread getPasswordServer;
	receiveSafeAnswerThread receiverAnswerServer;
	groupDealThread groupDealServer;
	moveFriendThread moveFriendServer;
	setSafeQuestionThread safeQuestionServer;
	systemSettingThread systemSettingServer;
	updatePasswordThread updatePasswordServer;
	updatePersonalInfoThread updatepersonalServer;
	updateRemarkThead updateRemarkServer;
	public static int limit = 3; // 申请次数限制
	public static HashMap<String, node_public> map_onlineInfo = new HashMap<String, node_public>();
	public static HashMap<String, Integer> map_IsSysMessageExist = new HashMap<String, Integer>();
	public static HashMap<String, Date> map_date = new HashMap<String, Date>();
	public static HashMap<String, Integer> map_status = new HashMap<String, Integer>();
	public static HashMap<String, String> map_array_passwordMap = new HashMap<String, String>();
	public static ArrayList<String> array_qqCanUse = new ArrayList<String>();
	public static Connection con1 = null;
	public static Connection con2 = null;
	public static int databaseFlag = 0; // 0是mysql 1是SQLServer 默认mysql
	public static int startFlag = 0;
	private String ip = "localhost";
	private int connectException = 0; // 连接正确与否 1、正确 2、错误

	public serverMain()
	{
		createUserInterface();
		try
		{
			startServer();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "服务器启动失败，请查看其是否已经在运行！", "失败",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	class refresh extends Thread
	{}

	private void createUserInterface()
	{
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		//
		//
		// 系统消息
		j1 = new JPanel();
		j1.setLayout(null);
		//
		jlable1 = new JLabel("已发送的系统消息：");
		jlable1.setFont(new Font("宋体", Font.PLAIN, 12));
		jlable1.setBounds(0, 0, 120, 15);
		j1.add(jlable1);
		//
		//
		textPane0 = new JTextPane();
		textPane0.setEditable(false);
		textPane0.setFont(new Font("宋体", Font.PLAIN, 12));
		textPane0.setDisabledTextColor(Color.red);
		textPane0.setBounds(0, 20, 600, 180);
		j1.add(textPane0);
		jsroll0 = new JScrollPane(textPane0);
		jsroll0.setBounds(0, 20, 590, 180);
		jsroll0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		j1.add(jsroll0);
		//
		//
		jlable2 = new JLabel("请在下面输入系统消息，按ctrl+enter发送：");
		jlable2.setBounds(0, 200, 250, 15);
		jlable2.setFont(new Font("宋体", Font.PLAIN, 12));
		j1.add(jlable2);
		//
		jb1 = new JButton("清空已发送");
		jb1.setBounds(470, 202, 100, 15);
		jb1.setFont(new Font("宋体", Font.PLAIN, 12));
		j1.add(jb1);
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jb1ActionPerfromed(e);
			}
		});
		//
		textPane1 = new JTextPane();
		textPane1.setBounds(0, 220, 600, 80);
		textPane1.setFont(new Font("宋体", Font.PLAIN, 12));
		j1.add(textPane1);
		jsroll3 = new JScrollPane(textPane1);
		jsroll3.setBounds(0, 220, 590, 80);
		jsroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		j1.add(jsroll3);
		//
		jb2 = new JButton("发送");
		jb2.setBounds(470, 302, 100, 15);
		jb2.setFont(new Font("宋体", Font.PLAIN, 12));
		j1.add(jb2);
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					jb2ActionPerfromed(e);
				}
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//
		// 系统日志
		j2 = new JPanel();
		j2.setLayout(null);
		textPane2 = new JTextPane();
		textPane2.setBounds(0, 0, 600, 300);
		textPane2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		textPane2.setText("系统日志：\n");
		textPane2.setFont(new Font("宋体", Font.PLAIN, 12));
		textPane2.setEditable(false);
		j2.add(textPane2);
		jsroll1 = new JScrollPane(textPane2);
		jsroll1.setBounds(0, 0, 590, 300);
		jsroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		j2.add(jsroll1);
		jb3 = new JButton("清空");
		jb3.setBounds(470, 302, 100, 15);
		jb3.setFont(new Font("宋体", Font.PLAIN, 12));
		j2.add(jb3);
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jb3ActionPerfromed(e);
			}
		});
		//
		j3 = new JPanel();
		j3.setLayout(null);
		// 表格
		myModel = new MyTableModel();
		table = new JTable(myModel);
		table.setEnabled(true);
		table.setRowSelectionAllowed(true);// 设置可否被选择.默认为false
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.blue);
		table.setBounds(0, 0, 600, 200);
		jsroll2 = new JScrollPane(table);
		jsroll2.setBounds(0, 0, 590, 300);
		jsroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		j3.add(jsroll2);
		//
		jb4 = new JButton("刷新");
		jb4.setBounds(470, 302, 100, 15);
		jb4.setFont(new Font("宋体", Font.PLAIN, 12));
		j3.add(jb4);
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jb4ActionPerfromed(e);
			}
		});
		//
		//
		j4 = new JPanel();
		j4.setLayout(null);
		jlable_setLimit = new JLabel("同一个IP一天可申请的次数：");
		jlable_setLimit.setBounds(10, 10, 200, 20);
		textf_setLimit = new JTextField();
		textf_setLimit.setBounds(200, 10, 90, 20);
		jb_setLimit = new JButton("确定");
		jb_setLimit.setBounds(300, 10, 60, 20);
		jb_setLimit.setFont(new Font("宋体", Font.PLAIN, 12));
		j4.add(jlable_setLimit);
		j4.add(jb_setLimit);
		j4.add(textf_setLimit);
		jb_setLimit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				jb_setActionPerfromed(e);
			}
		});
		jlable_setDatabase = new JLabel("请选择连接数据库：");
		jlable_setDatabase.setBounds(10, 40, 200, 20);
		String item[] = { "MySql", "SQLServer" };
		jcom_setDatabase = new JComboBox(item);
		jcom_setDatabase.setBounds(200, 40, 90, 20);
		jlable_warning = new JLabel("(提示：请先停止服务器再更改此选项！)");
		jlable_warning.setBounds(300, 40, 300, 20);
		jlable_warning.setFont(new Font("宋体", Font.PLAIN, 12));
		j4.add(jlable_setDatabase);
		j4.add(jcom_setDatabase);
		j4.add(jlable_warning);
		jcom_setDatabase.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				jcom_setDatabaseStateChanged(e);
			}
		});
		//
		JTabbedPane tab = new JTabbedPane();
		tab.setBounds(0, 0, 600, 400);
		tab.addTab("系统日志", j2);
		tab.addTab("发送系统消息", j1);
		tab.addTab("在线情况", j3);
		tab.addTab("系统设置", j4);
		tab.setFont(new Font("宋体", Font.PLAIN, 12));
		contentPane.add(tab);
		Menu m = new Menu();
		m.setFont(new Font("宋体", Font.PLAIN, 12));
		this.setJMenuBar(m);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = this.getSize();
		int x = (screensize.width - framesize.width) / 5;
		int y = (screensize.height - framesize.height) / 5;
		this.setLocation(x, y);
		this.show();
		setTitle("飞Q2012服务器");
		// this.setIconImage(new ImageIcon("\\icon.jpg").getImage());
		setResizable(false);
		setSize(600, 400);
		setVisible(true);
		// ------------------------给textPane1添加键盘事件--------------------------------//
		textPane1.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e)
			{
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					try
					{
						if (startFlag == 1)
						{
							sendSystemMessageThread sendSys = new sendSystemMessageThread(
									textPane1.getText());
							textPane0.setText(textPane0.getText()
									+ (new Date()) + "：" + textPane1.getText()
									+ "\n");
							textPane1.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"服务器已停止运行，请启动后再发系统消息！", "系统信息",
									JOptionPane.INFORMATION_MESSAGE);
							textPane1.setText("");
						}
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
				{}
			}

			public void keyReleased(KeyEvent e)
			{}
		});
		// ---------------------------------------------------------------------//
	}

	protected void jcom_setDatabaseStateChanged(ItemEvent e)
	{
		databaseFlag = jcom_setDatabase.getSelectedIndex();
	}

	protected void jb_setActionPerfromed(ActionEvent e)
	{
		String str1 = textf_setLimit.getText();
		if (str1.equals(""))
		{
			JOptionPane.showMessageDialog(null, "输入 不能为空！", "输入错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (int i = 0; i < str1.length(); i++)
		{
			if (str1.charAt(i) < '0' || str1.charAt(i) > '9')
			{
				textf_setLimit.setText("");
				JOptionPane.showMessageDialog(null, "输入的只能是数字！", "输入错误",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		limit = Integer.parseInt(str1);
		textf_setLimit.setText("");
		JOptionPane.showMessageDialog(null, "设置成功！", "设置成功",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void jb4ActionPerfromed(ActionEvent e)
	{
		myModel = new MyTableModel();
		table.setModel(myModel);
		table.repaint();
	}

	protected void jb3ActionPerfromed(ActionEvent e)
	{
		textPane2.setText("");
		textPane2.setText("系统日志：\n");
	}

	protected void jb2ActionPerfromed(ActionEvent e) throws SQLException
	{
		if (startFlag == 1)
		{
			sendSystemMessageThread sendSys = new sendSystemMessageThread(
					textPane1.getText());
			textPane0.setText(textPane0.getText() + (new Date()) + "："
					+ textPane1.getText() + "\n");
			textPane1.setText("");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "服务器已停止运行，请启动后再发系统消息！", "系统信息",
					JOptionPane.INFORMATION_MESSAGE);
			textPane1.setText("");
		}
	}

	protected void jb1ActionPerfromed(ActionEvent e)
	{
		textPane0.setText("");
	}

	// 菜单
	class Menu extends JMenuBar
	{
		private JDialog aboutDialog;

		/**
		 * 菜单初始化操作
		 */
		public Menu()
		{
			JMenu fileMenu1 = new JMenu("文件");
			JMenu fileMenu2 = new JMenu("操作");
			JMenu fileMenu3 = new JMenu("帮助");
			JMenuItem aboutMenuItem = new JMenuItem("关于...");
			JMenuItem exitMenuItem = new JMenuItem("退出");
			JMenuItem startMenuItem = new JMenuItem("重启服务器");
			JMenuItem stopMenuItem = new JMenuItem("停止服务器");
			fileMenu1.setFont(new Font("宋体", Font.PLAIN, 12));
			fileMenu2.setFont(new Font("宋体", Font.PLAIN, 12));
			fileMenu3.setFont(new Font("宋体", Font.PLAIN, 12));
			aboutMenuItem.setFont(new Font("宋体", Font.PLAIN, 12));
			exitMenuItem.setFont(new Font("宋体", Font.PLAIN, 12));
			startMenuItem.setFont(new Font("宋体", Font.PLAIN, 12));
			stopMenuItem.setFont(new Font("宋体", Font.PLAIN, 12));
			fileMenu1.add(exitMenuItem);
			fileMenu2.add(startMenuItem);
			fileMenu2.add(stopMenuItem);
			fileMenu3.add(aboutMenuItem);
			this.add(fileMenu1);
			this.add(fileMenu2);
			this.add(fileMenu3);
			aboutDialog = new JDialog();
			initAboutDialog();
			// 菜单事件
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					String sql_update = "update mainInfo set status = 0;";
					dispose();
					try
					{
						con1.createStatement().execute(sql_update);
					}
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					aboutDialog.show();
				}
			});
			startMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						stopServer();
						Thread.sleep(2000);
						startServer();
						textPane2.setText(textPane2.getText() + (new Date())
								+ "：" + "服务器重新启动成功！\n");
						JOptionPane.showMessageDialog(null, "服务器重启成功！", "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			stopMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						stopServer();
						JOptionPane.showMessageDialog(null, "服务器已停止运行！",
								"系统信息", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			setFont(new Font("宋体", Font.PLAIN, 12));
		}

		/**
		 * 返回关于对话框
		 */
		public JDialog getAboutDialog()
		{
			return aboutDialog;
		}

		/**
		 * 设置"关于"对话框的外观及响应事件,操作和JFrame一样都是在内容 框架上进行的
		 */
		public void initAboutDialog()
		{
			aboutDialog.setTitle("关于QQserver");
			Container con = aboutDialog.getContentPane();
			// Swing 中使用html语句
			JLabel aboutLabel = new JLabel(
					"<html><b>"
							+ "<center><br>QQserver</br><br>verson:1.0</br><br>Copyright © 2011 sdust,All rights reserved 版权所有 山东科技大学 王昌帅 司吉峰 王松松</br></html></b>",
					JLabel.CENTER);
			aboutLabel.setFont(new Font("宋体", Font.PLAIN, 15));
			con.add(aboutLabel, BorderLayout.CENTER);
			aboutDialog.setResizable(false);
			aboutDialog.setSize(600, 200);
			aboutDialog.setLocation(205, 300);
		}
	}

	protected void processWindowEvent(WindowEvent e)
	{
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			String sql_update = "update mainInfo set status = 0;";
			dispose();
			try
			{
				con1.createStatement().execute(sql_update);
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
		}
		else
		{
			super.processWindowEvent(e);
		}
	}

	// 启动服务器
	public void startServer()
	{
		try
		{
			if (databaseFlag == 0)
			{
				// 定义MySQL的数据库驱动程序
				String DBDRIVER = "org.gjt.mm.mysql.Driver";
				// 定义MySQL数据库的连接地址
				String DBURL1 = "jdbc:mysql://" + ip
						+ ":3306/main?useUnicode=true&characterEncoding=utf8";
				String DBURL2 = "jdbc:mysql://" + ip
						+ ":3306/users?useUnicode=true&characterEncoding=utf8";
				// MySQL数据库的连接用户名
				String DBUSER = "root";
				// MySQL数据库的连接密码
				String DBPASS = "yizhan";
				Class.forName(DBDRIVER); // 加载驱动程序
				con1 = DriverManager.getConnection(DBURL1, DBUSER, DBPASS);
				con2 = DriverManager.getConnection(DBURL2, DBUSER, DBPASS);
				textPane2.setText(textPane2.getText() + (new Date()) + "："
						+ "已连接MySQL数据库\n");
			}
			else
			{
				// /////////////////////////////////////////////////////
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				String url1 = "jdbc:sqlserver://" + ip
						+ ":1433;DatabaseName=main;";
				String url2 = "jdbc:sqlserver://" + ip
						+ ":1433;DatabaseName=users;";
				String user = "sa";
				String password = "yizhan";
				con1 = DriverManager.getConnection(url1, user, password);
				con2 = DriverManager.getConnection(url2, user, password);
				// ////////////////////////////////////////////////////////
				textPane2.setText(textPane2.getText() + (new Date()) + "："
						+ "已连接SQLServer数据库\n");
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "连接数据库失败！", "系统信息",
					JOptionPane.ERROR_MESSAGE);
			connectException = 1;
			return;
		}
		try
		//
		{
			String sql = "select * from Password;";// 预读密码
			Statement state = con1.createStatement();
			ResultSet res = state.executeQuery(sql);
			while (res.next())
			{
				map_array_passwordMap.put(res.getString("qq"),
						res.getString("password"));
			}
			String sql_sys = "select * from systemMessage;";// 预读系统消息
			ResultSet res_sys = state.executeQuery(sql_sys);
			while (res_sys.next())
			{
				if (res_sys.getString("warning") != null)
					map_IsSysMessageExist.put(res_sys.getString("qq"), 1);
				else
					map_IsSysMessageExist.put(res_sys.getString("qq"), 0);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "预读数据库信息失败！", "系统信息",
					JOptionPane.ERROR_MESSAGE);
			connectException = 1;
			return;
		}
		try
		{
			addCanUse = new addqqCanUseThread();
			heartbeat = new heartbeatReceiver();
			judgeAlive = new judgeAliveThread();
			loginServer = new loginServerThread();
			applicateServer = new applicateServerThread();
			searchServer = new searchThread();
			addServer = new addFriendsThread();
			authenticationReceiveServer = new authenticationMessageReceiverThread();
			agreeRefuseServer = new receiveAgreeRefuseThread();
			deleteServer = new deleteFriendsThread();
			getIpServer = new getAnotherQQIpThread();
			getInfoServer = new getInfoThread();
			getPasswordServer = new judgeWhetherQuestionExistThread();
			receiverAnswerServer = new receiveSafeAnswerThread();
			groupDealServer = new groupDealThread();
			moveFriendServer = new moveFriendThread();
			safeQuestionServer = new setSafeQuestionThread();
			systemSettingServer = new systemSettingThread();
			updatePasswordServer = new updatePasswordThread();
			updatepersonalServer = new updatePersonalInfoThread();
			updateRemarkServer = new updateRemarkThead();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "启动服务器子程序异常，请重启！", "系统信息",
					JOptionPane.ERROR_MESSAGE);
			connectException = 1;
			return;
		}
		startFlag = 1;
		jcom_setDatabase.setEnabled(false);
		textPane2.setText(textPane2.getText() + (new Date()) + "："
				+ "服务器启动成功！\n");
	}

	@SuppressWarnings("deprecation")
	public void stopServer() throws ClassNotFoundException, SQLException,
			IOException, InterruptedException
	{
		if (connectException != 1)
		{
			addCanUse.stop();
			heartbeat.stop();
			judgeAlive.stop();
			loginServer.stop();
			applicateServer.stop();
			searchServer.stop();
			addServer.stop();
			authenticationReceiveServer.stop();
			agreeRefuseServer.stop();
			deleteServer.stop();
			getIpServer.stop();
			getInfoServer.stop();
			getPasswordServer.stop();
			receiverAnswerServer.stop();
			groupDealServer.stop();
			moveFriendServer.stop();
			safeQuestionServer.stop();
			systemSettingServer.stop();
			updatePasswordServer.stop();
			updatepersonalServer.stop();
			updateRemarkServer.stop();
			heartbeat.server.close();
			loginServer.server.close();
			applicateServer.server.close();
			searchServer.server.close();
			addServer.server.close();
			authenticationReceiveServer.server.close();
			agreeRefuseServer.server.close();
			deleteServer.server.close();
			getIpServer.server.close();
			getInfoServer.server.close();
			getPasswordServer.server.close();
			receiverAnswerServer.server.close();
			groupDealServer.server.close();
			moveFriendServer.server.close();
			safeQuestionServer.server.close();
			systemSettingServer.server.close();
			updatePasswordServer.server.close();
			updatepersonalServer.server.close();
			updateRemarkServer.server.close();
			map_date.clear();
			map_IsSysMessageExist.clear();
			map_onlineInfo.clear();
			map_status.clear();
			map_array_passwordMap.clear();
			array_qqCanUse.clear();
			String sql_update = "update mainInfo set status = 0;";
			con1.createStatement().execute(sql_update);
			startFlag = 0;
			jcom_setDatabase.setEnabled(true);
			textPane2.setText(textPane2.getText() + (new Date()) + "："
					+ "服务器已停止！\n");
		}
	}

	class MyTableModel extends AbstractTableModel
	{
		int count = map_onlineInfo.size();
		private String[] columnNames = { "QQ", "ip", "main_port", "sys_port",
				"heartbeat_port", "chat_port" };
		private String[][] data = new String[count][];

		public MyTableModel()
		{
			Map m = serverMain.map_onlineInfo;
			Iterator ite = m.keySet().iterator();
			for (int i = 0; ite.hasNext(); i++)
			{
				Object key = ite.next();
				data[i] = new String[6];
				data[i][0] = key.toString();
				data[i][1] = map_onlineInfo.get(key).ip;
				data[i][2] = "" + map_onlineInfo.get(key).port_main;
				data[i][3] = "" + map_onlineInfo.get(key).port_sys;
				data[i][4] = "" + map_onlineInfo.get(key).port_alive;
				data[i][5] = "" + map_onlineInfo.get(key).port_chat;
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

	public static void main(String args[]) throws ClassNotFoundException,
			SQLException, IOException, InterruptedException
	{
		serverMain server = new serverMain();
	}
}
