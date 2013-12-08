/**
 * QQ主界面
 */
package Windows_MainInterface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.tree.*;
import javax.swing.*;

import Windows_chat.chat;
import Windows_chat.chatWarningWindow;
import Windows_info.friendinfo;
import Windows_info.information;
import Windows_safe.safe;
import Windows_search.search;
import Windows_systemMessage.message;
import common.playAudio;
import common.time;
import common.message.chatMessage;
import common.message.deleteMessage;
import common.message.getAvailableServerSocketFunction;
import common.message.groupDeal;
import common.message.ipPort;
import common.message.login_message;
import common.message.mainInfo;
import common.message.moveFriend;
import common.message.personalInfo;
import common.message.singleGroupInfoInMain;
import common.message.systemMessage;
import common.message.testMessage;
import common.message.updateRemark;

import client.thread.chatWithAnotherQQThread.getAnotherQQIpPortThread_Thread;
import client.thread.chatWithAnotherQQThread.send_messageThread;
import client.thread.deleteFriendsThread.sendDeleteFriendsThread;
import client.thread.groupDealThread.groupDealThread;
import client.thread.loginThread.loginClientThread;
import client.thread.moveFriendThread.moveFriendThread;
import client.thread.updateRemarkThread.updateRemarkThread;
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
public class MainInterface extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long					serialVersionUID				= 1L;
	static public String						ip								= "192.168.1.23";
	HashMap<String, String>						map_qq							= new HashMap<String, String>();
	// 定义组件
	String										b2								= ".jpg";
	String										b1								= "images//main//";
	JLabel										imgLabel1						= new JLabel();
	JLabel										imgLabel2						= new JLabel();
	JMenuBar									jmb1, jmb2;
	JMenu										jm1, jm2;
	JMenuItem									jm1_online, jm1_hiden, jm1_leave, jm2_sysedit, jm2_editpw, jm2_help, jm2_exit;
	JPanel										top, top_1, top_2, bottom, bottom_1, bottom_2, middle1, middle2, middle3;
	JLabel										headimg, name, jl_down;
	JButton										add, sysseting, Safe, skin;
	JButton										q_zone, blog, email, friend_net, wallet, soso, news, messagebox, shopping;
	JTabbedPane									jtp;
	JScrollPane									jsp1, jsp2, jsp3;
	JComboBox									jcb;
	JTree										jt;
	JPopupMenu									popupMenu;
	JPopupMenu									popupMenugp;
	JPopupMenu									popupMenuchild;
	JFrame										login_jf;
	JPanel										login_jp;
	JLabel										login_jl;
	JButton										login_jb;
	DefaultMutableTreeNode						root;
	DefaultMutableTreeNode[]					Node							= new DefaultMutableTreeNode[20];
	receiveMainInfoThread						receiver						= new receiveMainInfoThread();
	receiveSystemMessageThread					receiver_sys					= new receiveSystemMessageThread();
	clientAliveMessageReceiverThread			receiver_alive					= new clientAliveMessageReceiverThread();
	messageTransmitThread						transmit						= new messageTransmitThread();
	receiveTransmitWarningThread				transmitWarning					= new receiveTransmitWarningThread();
	sendHeartbeatThread_main					sendHeartbeatThread_main_temp	= new sendHeartbeatThread_main();
	loginFounction								login							= new loginFounction();
	login_message								lmessage						= null;
	static public mainInfo						mInfo							= null;
	static public HashMap<String, chat>			map_chatWindows					= new HashMap<String, chat>();
	static public HashMap<String, ipPort>		map_ipPort						= new HashMap<String, ipPort>();
	static public HashMap<String, Integer>		map_portLocal					= new HashMap<String, Integer>();
	static public HashMap<String, personalInfo>	map_friendsInfo					= new HashMap<String, personalInfo>();
	static public Integer						main_port						= 20001;
	static public Integer						sys_port						= 20101;
	static public Integer						alive_port						= 20201;
	static public Integer						transmit_port					= 20301;
	static public Integer						TransmitWarning_port			= 20401;										// 会话信息右下角弹窗
	static public Integer						begin_window_port				= 20501;
	static public Container						c_temp_main						= null;										// 会话信息右下角弹窗
	static public MainInterface					main_frame						= null;
	static public int							status;																		// 当期状态
	static public String						text_leave						= "20000000000您好，我现在不在电脑旁，稍后联系您......";
	static public String						text_busy						= "20000000000您好，我现在有点忙，稍后联系您......";
	static public Date							lastExceptionTime				= null;										// 记录发心跳异常时间以便对比
	static public int							heartbeatJudge					= 0;
	static public int							heartbeatFrequency				= 6;											// 心跳频率
	static public int							changed							= 0;											// 判断是否接收过mInfo
	String										back							= "main_background.jpg";
	final static Integer						temp							= new Integer(1);
	Dimension									dm								= null;

	// static public ArrayList<String> chatWindows = new ArrayList<String>();
	public MainInterface(login_message lmessage)
	{
		// ---------------------登陆中的等待界面-------------------------\
		status = lmessage.status;// 与后面status-1有关
		main_frame = this;
		c_temp_main = this.getContentPane();
		this.lmessage = lmessage;
		receiver.start();
		receiver_alive.start();
		receiver_sys.start();
		transmit.start();
		transmitWarning.start();
		// 创建组件
		login_jf = new JFrame();
		Toolkit tk = this.getToolkit();// 得到窗口工具条
		dm = tk.getScreenSize();
		login_jf.setLocation((int) (dm.getWidth() - 315), 20);
		login_jp = new JPanel();
		login_jp.setLayout(null);
		login_jp.setOpaque(false);
		login_jb = new JButton("取消");
		login_jb.setFont(new Font("黑体", Font.BOLD, 15));
		login_jb.setActionCommand("取消登录");
		login_jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if (e.getActionCommand() == "取消登录")
				{
					System.exit(0);// 并且停止对服务器的申请(未完成)
				}
			}
		});
		login_jl = new JLabel(new ImageIcon("images/logining_Vip.gif"));
		// 添加组件
		login_jl.setBounds(0, 100, 295, 150);
		login_jb.setBounds(100, 300, 80, 30);
		login_jp.add(login_jl);
		login_jp.add(login_jb);
		login_jf.add(login_jp);
		// 设置背景图片
		final Container c1 = login_jf.getContentPane();
		ImageIcon img1 = new ImageIcon("images/loginingBg.jpg");
		imgLabel2.setIcon(img1);
		imgLabel2.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		((JPanel) login_jf.getContentPane()).setOpaque(false);
		login_jf.getLayeredPane().add(imgLabel2, new Integer(Integer.MIN_VALUE));
		// 设置背景图片完成
		// 窗体设置
		login_jf.setTitle("飞Q2012");
		Image icon = Toolkit.getDefaultToolkit().getImage("pic\\headpic.jpg");
		login_jf.setIconImage(icon);
		login_jf.setSize(295, 589);
		login_jf.setMinimumSize(new Dimension(295, 450));
		login_jf.setResizable(false);
		login_jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login_jf.setVisible(true);
		login.start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				try
				{
					status = 0;
					heartbeatJudge = 0;
					heartbeatFrequency = 1;
					for (int i = 0; heartbeatJudge == 0 && i < 100; i++)
					{
						Thread.sleep(100);
					};
					System.exit(0);
				}
				catch (InterruptedException e1)
				{}
			}
		});
	}

	// --------------------------------登录线程--------------------------
	class loginFounction extends Thread
	{
		public void run()
		{
			try
			{
				login_message lm = new login_message(lmessage.qq, lmessage.password, sys_port, main_port, alive_port, transmit_port, lmessage.status);
				loginClientThread loginThread = new loginClientThread(lm);
				loginThread.join(30000);
				if (loginThread.changed == 1)
				{
					sendHeartbeatThread_main_temp.start();
					load();
					changed = 1;
				}
				else if (loginThread.changed == 2)// 当账号和密码不匹配时
				{
					JOptionPane.showMessageDialog(null, "您的账号和密码不匹配！", "提示", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				else
				// 当登录超时的情况
				{
					JOptionPane.showMessageDialog(null, "系统繁忙，请稍后再试！", "提示", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
			catch (Exception e)
			{}
		}
	}

	// --------------------------------接受主信息线程--------------------------
	class receiveMainInfoThread extends Thread
	{
		ServerSocket	server;
		Socket			client;

		public receiveMainInfoThread()
		{
			server = getAvailableServerSocketFunction.getServerSocket(main_port);
			main_port = server.getLocalPort();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					client = server.accept();
					receiveMain_dealingThread receiver = new receiveMain_dealingThread(client);
					receiver.start();
				}
				catch (IOException e)
				{}
			}
		}
	}

	class receiveMain_dealingThread extends Thread
	{
		Socket	client;

		public receiveMain_dealingThread(Socket client) throws IOException
		{
			this.client = client;
		}

		public void run()
		{
			try
			{
				ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
				mainInfo mainInf = (mainInfo) oin.readObject();
				if (mainInf != null)
				{
					mInfo = new mainInfo(mainInf);
					init(); // 初始化一些控件
					for (int i = 0; i < 200 && changed != 1; i++)
					{
						sleep(100);
					}
					if (changed == 1)
					{
						map_friendsInfo.clear();
						map_qq.clear();
						c_temp_main.removeAll();
						refresh();// 根据服务器传过来的好友信息构建具体QQ主界面
						c_temp_main.repaint();
						// 刷新好友列表
						jt.updateUI();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "系统出错，请重新登录！", "系统错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "系统繁忙，请稍后再试！", "提醒！", JOptionPane.ERROR_MESSAGE);
				}
				client.close();
			}
			catch (IOException e)
			{}
			catch (ClassNotFoundException e)
			{}
			catch (InterruptedException e)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------接受心跳询问消息线程--------------------------
	class clientAliveMessageReceiverThread extends Thread
	{
		ServerSocket	server;

		public clientAliveMessageReceiverThread()
		{
			server = getAvailableServerSocketFunction.getServerSocket(alive_port);
			alive_port = server.getLocalPort();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					Socket socketFromServer = server.accept();
					socketFromServer.close();
				}
				catch (IOException e)
				{}
			}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------发送心跳线程--------------------------
	class sendHeartbeatThread_main extends Thread
	{
		public void run()
		{
			while (true)
			{
				try
				{
					for (int i = 0; i < heartbeatFrequency; i++)// 每6秒发送一次心跳
					{
						sleep(1000);
					}
					sendHeartbeatThread heart = new sendHeartbeatThread();
				}
				catch (InterruptedException e)
				{}
			}
		}
	}

	class sendHeartbeatThread extends Thread
	{
		final int			port	= 10003;
		ObjectOutputStream	oout;

		public sendHeartbeatThread()
		{
			start();
		}

		public void run()
		{
			try
			{
				Socket client = new Socket(ip, port);
				ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(new testMessage(lmessage.qq, status));
				heartbeatJudge = 1;
				client.close();
			}
			catch (IOException e)
			{
				if (lastExceptionTime != null)
				{
					if (((new Date()).getTime() - lastExceptionTime.getTime()) < 17000) // 假如连续三次发送失败
					{
						sendHeartbeatThread_main_temp.stop();
						JOptionPane.showMessageDialog(null, "服务器意外关闭，请重新登陆...");
						System.exit(0);
					}
				}
				else
				{
					lastExceptionTime = new Date();
				}
			}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------聊天信息本地转发线程--------------------------
	class messageTransmitThread extends Thread
	{
		ServerSocket	server;
		Socket			client;

		public messageTransmitThread()
		{
			server = getAvailableServerSocketFunction.getServerSocket(transmit_port);
			transmit_port = server.getLocalPort();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					client = server.accept();
					transmit_dealingThread transmit_temp = new transmit_dealingThread(client);
					transmit_temp.start();
				}
				catch (IOException e)
				{}
			}
		}
	}

	class transmit_dealingThread extends Thread
	{
		Socket		client;
		chatMessage	message;

		public transmit_dealingThread(Socket client) throws IOException
		{
			this.client = client;
		}

		public void run()
		{
			try
			{
				ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
				this.message = new chatMessage((chatMessage) oin.readObject());
				if (message.anotherQQ.equals(mInfo.myself.qq))
				{
					if ((!message.text.equals(text_busy)) && (!message.text.equals(text_leave)))// 自动回复功能
					{
						if (status == 2)
						{
							send_messageThread sender_leaveReturn = new send_messageThread(new chatMessage(MainInterface.mInfo.myself.qq, message.myQQ, time.gettime(),
									MainInterface.mInfo.myself.nickname, text_leave));
						}
						else if (status == 3)
						{
							send_messageThread sender_leaveReturn = new send_messageThread(new chatMessage(MainInterface.mInfo.myself.qq, message.myQQ, time.gettime(),
									MainInterface.mInfo.myself.nickname, text_busy));
						}
					}
					if (map_chatWindows.get(message.myQQ) != null) // 假如已经打开此人聊天窗口
					{
						if (map_ipPort.get(message.myQQ) == null) // 如果此人的ipPort还没有，则请求
						{
							getAnotherQQIpPortThread_Thread getiport = new getAnotherQQIpPortThread_Thread(message.myQQ);
						}
						Socket sendToMyself = new Socket(InetAddress.getLocalHost(), map_portLocal.get(message.myQQ));
						ObjectOutputStream oout = new ObjectOutputStream(sendToMyself.getOutputStream());
						oout.writeObject(message);
					}
					else
					{
						Socket sendToMyself = new Socket(InetAddress.getLocalHost(), TransmitWarning_port);
						ObjectOutputStream oout = new ObjectOutputStream(sendToMyself.getOutputStream());
						oout.writeObject(message);
					}
					playAudio play = new playAudio(2);
				}
				else
				{
					if (MainInterface.map_ipPort.get(message.anotherQQ) != null)
					{
						Socket sendToMyself = new Socket(map_ipPort.get(message.anotherQQ).ip, map_ipPort.get(message.anotherQQ).chatPort);
						ObjectOutputStream oout = new ObjectOutputStream(sendToMyself.getOutputStream());
						oout.writeObject(message);
					}
					else if (MainInterface.map_ipPort.get(message.anotherQQ) == null && MainInterface.map_friendsInfo.get(message.anotherQQ).status != 0)
					{
						getAnotherQQIpPortThread_Thread getiport = new getAnotherQQIpPortThread_Thread(message.anotherQQ);
					}
					else
					{
						// 离线功能暂时没开发
					}
				}
				client.close();
			}
			catch (IOException e)
			{}
			catch (ClassNotFoundException e)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------接受系统消息线程--------------------------
	class receiveSystemMessageThread extends Thread
	{
		ServerSocket	server;
		Socket			client;

		public receiveSystemMessageThread()
		{
			server = getAvailableServerSocketFunction.getServerSocket(sys_port);
			sys_port = server.getLocalPort();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					client = server.accept();
					receive_dealingThread receiver = new receive_dealingThread(client);
					receiver.start();
					sleep(2000);
					// 此处需要添加消息框
				}
				catch (IOException e)
				{}
				catch (InterruptedException e)
				{}
			}
		}
	}

	class receive_dealingThread extends Thread
	{
		private Socket			client;
		private systemMessage	sys;

		public systemMessage getSys() // 返回系统消息包
		{
			return sys;
		}

		public receive_dealingThread(Socket s_client) throws IOException
		{
			this.client = s_client;
		}

		public void run()
		{
			try
			{
				ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
				systemMessage message = (systemMessage) oin.readObject();
				message m = new message(message);
				if (message.sign == 3)
				{
					status = 0;
					heartbeatJudge = 0;
					heartbeatFrequency = 1;
					for (int i = 0; heartbeatJudge == 0 && i < 100; i++)
					{
						Thread.sleep(100);
					};
					sendHeartbeatThread_main_temp.stop();
					receiver_alive.stop();
					receiver.stop();
					receiver_sys.stop();
					c_temp_main.setEnabled(false);
					JOptionPane.showMessageDialog(null, "您的QQ帐号在另一地点登录，此QQ被迫下线！", "下线警告！", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				// 添加系统消息框
				client.close();
			}
			catch (IOException e)
			{}
			catch (ClassNotFoundException e)
			{}
			catch (InterruptedException e)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------接受会话消息线程--------------------------
	class receiveTransmitWarningThread extends Thread
	{
		ServerSocket	server	= null;
		Socket			client	= null;

		public receiveTransmitWarningThread()
		{
			server = getAvailableServerSocketFunction.getServerSocket(TransmitWarning_port);
			TransmitWarning_port = server.getLocalPort();
		}

		public void run()
		{
			while (true)
			{
				try
				{
					client = server.accept();
					TransmitWarning_dealingThread receiver_warning = new TransmitWarning_dealingThread(client);
					receiver_warning.start();
					// 此处需要添加消息框
				}
				catch (IOException e)
				{}
			}
		}
	}

	class TransmitWarning_dealingThread extends Thread
	{
		private Socket			client;
		private systemMessage	sys;

		public systemMessage getSys() // 返回系统消息包
		{
			return sys;
		}

		public TransmitWarning_dealingThread(Socket s_client) throws IOException
		{
			this.client = s_client;
		}

		public void run()
		{
			try
			{
				ObjectInputStream oin = new ObjectInputStream(client.getInputStream());
				chatMessage chMessage = (chatMessage) oin.readObject();
				chatWarningWindow chat_window_temp = new chatWarningWindow(chMessage);
				client.close();
			}
			catch (IOException e)
			{}
			catch (ClassNotFoundException e)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// --------------------------------聊天窗口事件的实现--------------------------
	public class ChatEvent implements MouseListener
	{
		public void mousePressed(MouseEvent e)
		{
			JTree tree = (JTree) e.getSource();
			int selRow = tree.getRowForLocation(e.getX(), e.getY());
			TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
			if (selRow != -1)
			{
				if (e.getClickCount() == 1)
				{
					// 单击
				}
				else if (e.getClickCount() == 2 && selPath.getPathCount() == 3)
				{
					// 双击
					TreeNode node = (TreeNode) selPath.getLastPathComponent();
					if (node.isLeaf())// 此处用于聊天窗口事件-------------需要与服务器数据库交互（待完成）
					{
						if (SwingUtilities.isRightMouseButton(e))
						{}
						else
						{
							TreePath path = jt.getSelectionPath();
							DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) path.getLastPathComponent();
							String textOnJlable = ((JLabel) node1.getUserObject()).getText();
							String qq_temp = map_qq.get(textOnJlable);
							if (map_chatWindows.get(qq_temp) == null) // 假如没打开窗口
							{
								try
								{
									chat chat_temp = new chat(map_friendsInfo.get(qq_temp), mInfo.myself);
									map_chatWindows.put(qq_temp, chat_temp);
									if (map_ipPort.get(qq_temp) == null)// && map_friendsInfo.get(qq_temp).status != 0)
									{
										getAnotherQQIpPortThread_Thread get_loop = new getAnotherQQIpPortThread_Thread(qq_temp);
									}
								}
								catch (UnknownHostException e1)
								{}
								catch (IOException e1)
								{}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "已打开此人聊天窗口！", "温馨提示", JOptionPane.WARNING_MESSAGE);
							}
						}// 是叶节点并且是左击
					}
					//
				}
			}
		}

		public void mouseEntered(MouseEvent e)
		{}

		public void mouseExited(MouseEvent e)
		{}

		public void mouseReleased(MouseEvent e)
		{}

		public void mouseClicked(MouseEvent e)
		{}
	}

	ChatEvent	c	= new ChatEvent();	// 此"c"监听器用于监听好友头像，用于聊天

	// --------------------------------------------------------------------------
	// -------
	// --------------------------对点击头像的事件处理----------------------------------
	public class head_event implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			if (e.getClickCount() == 1)
			{
				// 开启个人资料的界面
				final information in = new information(mInfo.myself, c_temp_main, main_frame);
			}
		}

		public void mouseEntered(MouseEvent e)
		{}

		public void mouseExited(MouseEvent e)
		{}

		public void mousePressed(MouseEvent e)
		{}

		public void mouseReleased(MouseEvent e)
		{}
	}

	head_event	t	= new head_event();

	// --------------------------------------------------------------------------
	// ---------
	// -----------------------对在线状态事件监听并响应------------------------------
	public class con implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			int index = ((JComboBox) e.getSource()).getSelectedIndex() + 1;
			if (index == 1)
			{
				status = 1;
			}
			else if (index == 2)
			{
				status = 2;
			}
			else if (index == 3)
			{
				status = 3;
			}
			else if (index == 4)// 隐身的情况
			{
				status = 4;
				// 并且在其他好友的状态中显示为不在线
			}
			else
			{}
		}
	}

	// --------------------------------------------------------------------------
	// ------------此监听器用于对好友列表中的popupmenu中的选项进行响应-----------------
	public class Event_popuMenu implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				// TODO Auto-generated method stub
				if (e.getActionCommand() == "修改备注")
				{
					int x, y;
					Point p = MouseInfo.getPointerInfo().getLocation();
					x = p.x;
					y = p.y;
					Change_Beizhu cb = new Change_Beizhu(x, y);
				}
				else if (e.getActionCommand() == "删除好友")
				{
					TreePath path = jt.getSelectionPath();
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					node.removeFromParent();
					deleteFriend(map_qq.get(((JLabel) node.getUserObject()).getText()));
					sendDeleteFriendsThread sender_del = new sendDeleteFriendsThread(new deleteMessage(lmessage.qq, map_qq.get(((JLabel) node.getUserObject()).getText())));
					jt.updateUI();
				}
				else if (e.getActionCommand() == "查看好友资料")
				{
					TreePath path = jt.getSelectionPath();
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					String str2 = ((JLabel) node.getUserObject()).getText();
					final friendinfo fif = new friendinfo(map_friendsInfo.get(map_qq.get(str2)));
				}
				else if (e.getActionCommand() == "发邮件")
				{
					JOptionPane.showMessageDialog(null, "暂未开通此功能，抱歉!", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (e.getActionCommand() == "添加分组")
				{
					int x, y;
					Point p = MouseInfo.getPointerInfo().getLocation();
					x = p.x;
					y = p.y;
					add_group ag = new add_group(x, y);
				}
				else if (e.getActionCommand() == "删除分组")
				{
					int i, k;
					TreePath path = jt.getSelectionPath();
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (node.getUserObject().toString().equals("我的好友"))
					{
						JOptionPane.showMessageDialog(null, "不能删除\"我的好友\"组！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "选定的分组将被删除,其中的好友将自动转到我的好友里！", "提示", JOptionPane.INFORMATION_MESSAGE);
						for (k = 1, i = 0; k <= root.getChildCount(); k++)
						{
							if ((root.getChildAt(i)).toString().equals("我的好友"))
							{
								break;
							}
							i++;
						}
						k--;
						if (node.getChildCount() == 0)// 该分组没有子节点时
						{
							node.removeFromParent();
							// 以下是对服务器的操作(包括将已删除分组中的好友放到好友分组中)
							jt.updateUI();
						}
						else
						{
							for (int j = 0; j < node.getChildCount(); j++)
							{
								Node[i].add((DefaultMutableTreeNode) node.getChildAt(j));
							}
							node.removeFromParent();
							// 以下是对服务器的操作(包括将已删除分组中的好友放到好友分组中)
							jt.updateUI();
						}
						deleteGroup(node.getUserObject().toString());
						groupDealThread group_del = new groupDealThread(new groupDeal(2, 1, mInfo.myself.qq, node.getUserObject().toString()));
					}
				}
				else if (e.getActionCommand() == "修改分组")
				{
					int x, y;
					Point p = MouseInfo.getPointerInfo().getLocation();
					x = p.x;
					y = p.y;
					// ///////////////////
					update_group Gt = new update_group(x, y);
				}
				else if (e.getActionCommand() == "将好友移至各分组")
				{
					int x, y;
					Point p = MouseInfo.getPointerInfo().getLocation();
					x = p.x;
					y = p.y;
					// ///////////////////
					mgroup_transit Gt = new mgroup_transit(x, y);
				}
				else
				{}
			}
			catch (UnknownHostException e2)
			{}
			catch (IOException e2)
			{}
		}
	}

	Event_popuMenu	eee	= new Event_popuMenu();

	// ---------------------------------------------------------------------
	// -------------------bottom中的button事件处理-------------------------------
	public class head_inf implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "添加好友")
			{
				final search s_info = new search();
			}
			else if (e.getActionCommand() == "系统设置")
			{
				// 弹出系统设置窗口
				// DefaultTreeModel model = (DefaultTreeModel)jt.getModel();
				// model.reload();这两句是刷新的功能
			}
			else if (e.getActionCommand() == "安全管理")
			{
				try
				{
					final safe s = new safe(lmessage);
				}
				catch (UnknownHostException e1)
				{}
				catch (IOException e1)
				{}
				catch (InterruptedException e1)
				{}// 弹出安全窗口
			}
			else
			{}
		}
	}

	head_inf	h	= new head_inf();	// 此"h"监听器用于监听器用于监听三个button和头像等组件

	// --------------------------------------------------------------------------
	// -------
	// -------------------------换皮肤用到的类和函数---------------------------------------
	public void change_background(String back)
	{
		imgLabel1.setIcon(new ImageIcon(b1 + back + b2));
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == "换肤")
		{
			Point p = MouseInfo.getPointerInfo().getLocation();
			final Background b = new Background(p.x, p.y);
			b.addWindowListener(new WindowAdapter() {
				public void windowActivated(WindowEvent e)
				{}

				// 当窗口不活跃的时候，自动关闭该窗口
				public void windowDeactivated(WindowEvent e)
				{
					b.dispose();
				}
			});
		}
		else
		{}
	}

	public class Background extends JFrame implements ActionListener
	{
		private JButton	jb_a, jb_b, jb_c, jb_d, jb_e, jb_f, jb_g, jb_h, jb_i, jb_j;
		Container		container	= getContentPane();

		public Background(int x, int y)
		{
			// 设置背景图片
			JLabel jl_down = new JLabel();
			// 把背景图片显示在一个标签里面
			jl_down.setIcon(new ImageIcon("images//background//2.jpg"));
			jl_down.setSize(260, 420);// setBounds(0, 0,
			// background.getIconWidth(),
			// background.getIconHeight());
			// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
			JPanel c = (JPanel) container;
			c.add(jl_down);
			c.setOpaque(false);
			c.setLayout(null);
			jb_a = new JButton();
			jb_a.setBounds(20, 25, 100, 55);
			jb_a.setActionCommand("a");
			jb_a.addActionListener(this);
			jb_a.setIcon(new ImageIcon("images//main//a.jpg"));
			jb_a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_a);
			jb_b = new JButton();
			jb_b.setBounds(140, 25, 100, 55);
			jb_b.setIcon(new ImageIcon("images//main//b.jpg"));
			jb_b.setActionCommand("b");
			jb_b.addActionListener(this);
			jb_b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_b);
			jb_c = new JButton();
			jb_c.setBounds(20, 100, 100, 55);
			jb_c.setActionCommand("c");
			jb_c.addActionListener(this);
			jb_c.setIcon(new ImageIcon("images//main//c.jpg"));
			jb_c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_c);
			jb_d = new JButton();
			jb_d.setBounds(140, 100, 100, 55);
			jb_d.setActionCommand("d");
			jb_d.addActionListener(this);
			jb_d.setIcon(new ImageIcon("images//main//d.jpg"));
			jb_d.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_d);
			jb_e = new JButton();
			jb_e.setBounds(20, 175, 100, 55);
			jb_e.setActionCommand("e");
			jb_e.addActionListener(this);
			jb_e.setIcon(new ImageIcon("images//main//e.jpg"));
			jb_e.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_e);
			jb_f = new JButton();
			jb_f.setBounds(140, 175, 100, 55);
			jb_f.setActionCommand("f");
			jb_f.addActionListener(this);
			jb_f.setIcon(new ImageIcon("images//main//f.jpg"));
			jb_f.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_f);
			jb_g = new JButton();
			jb_g.setBounds(20, 250, 100, 55);
			jb_g.setActionCommand("g");
			jb_g.addActionListener(this);
			jb_g.setIcon(new ImageIcon("images//main//g.jpg"));
			jb_g.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_g);
			jb_h = new JButton();
			jb_h.setBounds(140, 250, 100, 55);
			jb_h.setActionCommand("h");
			jb_h.addActionListener(this);
			jb_h.setIcon(new ImageIcon("images//main//h.jpg"));
			jb_h.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_h);
			jb_i = new JButton();
			jb_i.setBounds(20, 325, 100, 55);
			jb_i.setActionCommand("i");
			jb_i.addActionListener(this);
			jb_i.setIcon(new ImageIcon("images//main//i.jpg"));
			jb_i.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_i);
			jb_j = new JButton();
			jb_j.setBounds(140, 325, 100, 55);
			jb_j.setActionCommand("j");
			jb_j.addActionListener(this);
			jb_j.setIcon(new ImageIcon("images//main//j.jpg"));
			jb_j.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 添加小手
			c.add(jb_j);
			getLayeredPane().setLayout(null);
			// 把背景图片添加到分层窗格的最底层作为背景
			getLayeredPane().add(jl_down, new Integer(Integer.MIN_VALUE));
			setLocation(main_frame.getX(), y);
			setSize(260, 420);
			setResizable(false);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand() == "a")
			{
				back = "pic//background//1.jpg";
				change_background("1");
			}
			else if (e.getActionCommand() == "b")
			{
				back = "pic//background//2.jpg";
				change_background("2");
			}
			else if (e.getActionCommand() == "c")
			{
				back = "pic//background//3.jpg";
				change_background("3");
			}
			else if (e.getActionCommand() == "d")
			{
				back = "pic//background//4.jpg";
				change_background("4");
			}
			else if (e.getActionCommand() == "e")
			{
				back = "pic//background//5.jpg";
				change_background("5");
			}
			else if (e.getActionCommand() == "f")
			{
				back = "pic//background//6.jpg";
				change_background("6");
			}
			else if (e.getActionCommand() == "g")
			{
				back = "pic//background//7.jpg";
				change_background("7");
			}
			else if (e.getActionCommand() == "h")
			{
				back = "pic//background//8.jpg";
				change_background("8");
			}
			else if (e.getActionCommand() == "i")
			{
				back = "pic//background//9.jpg";
				change_background("9");
			}
			else if (e.getActionCommand() == "j")
			{
				back = "pic//background//10.jpg";
				change_background("10");
			}
		}
	}

	// --------------------------------------------------------------------------
	// -----------------------用于添加分组的类-------------------------------------
	public class add_group implements ActionListener, KeyListener
	{
		// 定义组件
		JFrame		jf;
		JPanel		jp;
		JLabel		jl;
		JTextField	jtf;
		JButton		jb1, jb2;

		public add_group(int x, int y)
		{
			// 创建各组件
			jf = new JFrame();
			jb1 = new JButton(new ImageIcon("images/beizhu/sure.gif"));
			jb2 = new JButton(new ImageIcon("images/beizhu/cancel.gif"));
			jp = new JPanel();
			jl = new JLabel("请输入分组的名称:");
			jtf = new JTextField();
			jb1.setPreferredSize(new Dimension(58, 17));
			jb1.setBounds(150, 80, 58, 17);
			jb1.setActionCommand("确定");
			jb1.addActionListener(this);
			jb2.setPreferredSize(new Dimension(58, 17));
			jb2.setBounds(230, 80, 58, 17);
			jb2.setActionCommand("取消");
			jb2.addActionListener(this);
			jl.setFont(new Font("华康少女文字w5(p)", Font.BOLD, 15));
			jp.setLayout(null);
			jp.setBackground(Color.pink);
			jtf.setColumns(15);
			// 将组建加入到面板jp中
			jp.add(jl);
			jp.add(jtf);
			jp.add(jb1);
			jp.add(jb2);
			jl.setBounds(20, 20, 200, 20);
			jtf.setBounds(20, 40, 200, 20);
			jtf.addKeyListener(this);
			jp.setBounds(0, 0, 330, 151);
			// 将组建加入到窗体内
			jf.setLayout(null);
			jf.add(jp);
			// 窗体设置
			jf.setTitle("添加分组");
			Image icon = Toolkit.getDefaultToolkit().getImage("images/qq.gif");
			jf.setIconImage(icon);
			jf.setSize(new Dimension(330, 151));
			jf.setResizable(false);
			jf.setVisible(true);
			jf.setLocation(x, y);
		}

		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "确定")
			{
				addGroupAction();
			}
			else if (e.getActionCommand() == "取消")
			{
				// 取消对备注名称的修改并推出
				jf.dispose();
			}
			else
			{}
		}

		public void keyTyped(KeyEvent e)
		{}

		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				addGroupAction();
			}
		}

		public void keyReleased(KeyEvent e)
		{}

		void addGroupAction()
		{
			// 确定并提交添加分组的信息
			String s;
			s = jtf.getText();
			Node[root.getChildCount()] = new DefaultMutableTreeNode(s);
			root.add(Node[root.getChildCount()]);
			jt.updateUI();
			jf.dispose();
			try
			{
				insertGroup(s);
				groupDealThread group_add = new groupDealThread(new groupDeal(1, mInfo.myself.qq, s));
			}
			catch (UnknownHostException e1)
			{}
			catch (IOException e1)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// ----------------------用于修改分组名称的类----------------------------------
	public class update_group implements ActionListener
	{
		// 定义组件
		JFrame		jf;
		JPanel		jp;
		JLabel		jl;
		JTextField	jtf;
		JButton		jb1, jb2;

		public update_group(int x, int y)
		{
			// 创建各组件
			jf = new JFrame();
			jb1 = new JButton(new ImageIcon("images/beizhu/sure.gif"));
			jb2 = new JButton(new ImageIcon("images/beizhu/cancel.gif"));
			jp = new JPanel();
			jl = new JLabel("请输入修改后的名称:");
			jtf = new JTextField();
			jb1.setPreferredSize(new Dimension(58, 17));
			jb1.setBounds(150, 80, 58, 17);
			jb1.setActionCommand("确定");
			jb1.addActionListener(this);
			jb2.setPreferredSize(new Dimension(58, 17));
			jb2.setBounds(230, 80, 58, 17);
			jb2.setActionCommand("取消");
			jb2.addActionListener(this);
			jl.setFont(new Font("华康少女文字w5(p)", Font.BOLD, 15));
			jp.setLayout(null);
			jp.setBackground(Color.pink);
			jtf.setColumns(15);
			// 将组建加入到面板jp中
			jp.add(jl);
			jp.add(jtf);
			jp.add(jb1);
			jp.add(jb2);
			jl.setBounds(20, 20, 200, 20);
			jtf.setBounds(20, 40, 200, 20);
			jp.setBounds(0, 0, 330, 151);
			// 将组建加入到窗体内
			jf.setLayout(null);
			jf.add(jp);
			// 窗体设置
			jf.setTitle("修改分组名称");
			Image icon = Toolkit.getDefaultToolkit().getImage("images/qq.gif");
			jf.setIconImage(icon);
			jf.setSize(new Dimension(330, 151));
			jf.setResizable(false);
			jf.setVisible(true);
			jf.setLocation(x, y);
		}

		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "确定")
			{
				// 确定并提交修改分组的信息
				TreePath path = jt.getSelectionPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				String s_t = node.getUserObject().toString();
				String s = jtf.getText();
				node.setUserObject(s);
				jt.updateUI();
				jf.dispose();
				// 向服务器发送消息
				try
				{
					renameGroup(s_t, s);
					groupDealThread gDeal = new groupDealThread(new groupDeal(3, mInfo.myself.qq, s_t, s));
				}
				catch (UnknownHostException e1)
				{}
				catch (IOException e1)
				{}
			}
			else if (e.getActionCommand() == "取消")
			{
				// 取消对备注名称的修改并推出
				jf.dispose();
			}
			else
			{}
		}
	}

	// ---------------------------------------------------------------------------
	// ---------------------用于修改好友备注的类------------------------------------
	public class Change_Beizhu implements ActionListener
	{
		// 定义变量
		JFrame		jf;
		JPanel		jp;
		JLabel		jl;
		JTextField	jtf;
		JButton		jb1, jb2;

		/**
		 * @param args
		 */
		public Change_Beizhu(int x, int y)
		{
			// 创建各组件
			jf = new JFrame();
			jb1 = new JButton(new ImageIcon("images/beizhu/sure.gif"));
			jb2 = new JButton(new ImageIcon("images/beizhu/cancel.gif"));
			jp = new JPanel();
			jl = new JLabel("请输入备注名称:");
			jtf = new JTextField();
			jb1.setPreferredSize(new Dimension(58, 17));
			jb1.setBounds(150, 80, 58, 17);
			jb1.setActionCommand("确定");
			jb1.addActionListener(this);
			jb2.setPreferredSize(new Dimension(58, 17));
			jb2.setBounds(230, 80, 58, 17);
			jb2.setActionCommand("取消");
			jb2.addActionListener(this);
			jl.setFont(new Font("华康少女文字w5(p)", Font.BOLD, 15));
			jp.setLayout(null);
			jp.setBackground(Color.pink);
			jtf.setColumns(15);
			// 将组建加入到面板jp中
			jp.add(jl);
			jp.add(jtf);
			jp.add(jb1);
			jp.add(jb2);
			jl.setBounds(20, 20, 200, 20);
			jtf.setBounds(20, 40, 200, 20);
			jp.setBounds(0, 0, 330, 151);
			// 将组建加入到窗体内
			jf.setLayout(null);
			jf.add(jp);
			// 窗体设置
			jf.setTitle("修改备注名称");
			Image icon = Toolkit.getDefaultToolkit().getImage("images/qq.gif");
			jf.setIconImage(icon);
			jf.setSize(new Dimension(330, 151));
			jf.setResizable(false);
			jf.setVisible(true);
			jf.setLocation(x, y - 80);
			jtf.addKeyListener(new KeyListener() { // 增加键盘事件
				public void keyTyped(KeyEvent e)
				{}

				public void keyReleased(KeyEvent e)
				{}

				public void keyPressed(KeyEvent e)
				{
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						// 确定并提交修改备注的信息
						TreePath path = jt.getSelectionPath();
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
						String oldName = ((JLabel) node.getUserObject()).getText();
						String newName = "";
						int i, j;
						for (i = 0; i < mInfo.groupCount; i++)
						{
							for (j = 0; j < mInfo.group[i].count; j++)
							{
								String str1;
								if (mInfo.group[i].pinfl[j].remark != null)
								{
									str1 = mInfo.group[i].pinfl[j].remark + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")";
								}
								else
								{
									str1 = mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq;
								}
								String str2 = ((JLabel) node.getUserObject()).getText();
								if (str1.equals(str2))
								{
									newName = jtf.getText() + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")";
									((JLabel) node.getUserObject()).setText(newName);
									personalInfo p_temp = new personalInfo(map_friendsInfo.get(map_qq.get(oldName)));
									p_temp.remark = new String(jtf.getText());
									setFriend(map_qq.get(oldName), p_temp);
									map_friendsInfo.put(map_qq.get(oldName), p_temp);
									try
									{
										updateRemarkThread sender_remark = new updateRemarkThread(new updateRemark(mInfo.myself.qq, map_qq.get(oldName), jtf.getText()));
									}
									catch (UnknownHostException e1)
									{}
									catch (IOException e1)
									{}
								}
							}
						}
						map_qq.put(newName, map_qq.get(oldName));
						jf.dispose();
						jt.updateUI();
						// 此处向服务器发送更改备注的消息
					}
				}
			});
		}

		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if (e.getActionCommand() == "确定")
			{
				// 确定并提交修改备注的信息
				TreePath path = jt.getSelectionPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
				String oldName = ((JLabel) node.getUserObject()).getText();
				String newName = "";
				int i, j;
				for (i = 0; i < mInfo.groupCount; i++)
				{
					for (j = 0; j < mInfo.group[i].count; j++)
					{
						String str1;
						if (mInfo.group[i].pinfl[j].remark != null)
						{
							str1 = mInfo.group[i].pinfl[j].remark + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")";
						}
						else
						{
							str1 = mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq;
						}
						String str2 = ((JLabel) node.getUserObject()).getText();
						if (str1.equals(str2))
						{
							newName = jtf.getText() + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")";
							((JLabel) node.getUserObject()).setText(newName);
							personalInfo p_temp = new personalInfo(map_friendsInfo.get(map_qq.get(oldName)));
							p_temp.remark = new String(jtf.getText());
							setFriend(map_qq.get(oldName), p_temp);
							map_friendsInfo.put(map_qq.get(oldName), p_temp);
							try
							{
								updateRemarkThread sender_remark = new updateRemarkThread(new updateRemark(mInfo.myself.qq, map_qq.get(oldName), jtf.getText()));
							}
							catch (UnknownHostException e1)
							{}
							catch (IOException e1)
							{}
						}
					}
				}
				map_qq.put(newName, map_qq.get(oldName));
				jf.dispose();
				jt.updateUI();
				// 此处向服务器发送更改备注的消息
			}
			else if (e.getActionCommand() == "取消")
			{
				// 取消对备注名称的修改并推出
				jf.dispose();
			}
			else
			{}
		}
	}

	// -----------------------------------------------------------------------------
	// ----------------------用于弹出要转移分组的类----------------------------------
	public class mgroup_transit implements ActionListener
	{
		// 定义组件
		JFrame			jf;
		JPanel			jp;
		JButton			jb1, jb2;
		JRadioButton[]	jrb	= new JRadioButton[mInfo.groupCount];
		ButtonGroup		bg;

		// 创建组件
		public mgroup_transit(int x, int y)
		{
			int lx, ly;
			jf = new JFrame();
			jp = new JPanel();
			jp.setLayout(null);
			jb1 = new JButton("确定");
			jb2 = new JButton("取消");
			bg = new ButtonGroup();
			int i;
			for (i = 0; i < mInfo.groupCount; i++)
			{
				jrb[i] = new JRadioButton(mInfo.group[i].groupName);
				jrb[i].setBounds(10, i * 30, 100, 20);
				bg.add(jrb[i]);
				jp.add(jrb[i]);
			}
			jb1.setBounds(10, (i - 1) * 60, 60, 30);
			jb2.setBounds(90, (i - 1) * 60, 60, 30);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb1.setActionCommand("确定");
			jb2.setActionCommand("取消");
			jp.add(jb1);
			jp.add(jb2);
			jf.add(jp);
			lx = 200;
			ly = (i - 1) * 70 + 50;
			jf.setTitle("选择你要转移的分组");
			jf.setSize(lx, ly);
			jf.setLocation(main_frame.getX(), main_frame.getY());
			jf.setVisible(true);
			jf.setResizable(false);
		}

		public void actionPerformed(ActionEvent e)
		{
			try
			{
				// TODO Auto-generated method stub
				if (e.getActionCommand() == "确定")
				{
					for (int i = 0; i < mInfo.groupCount; i++)
					{
						if (jrb[i].isSelected())
						{
							if (jrb[i].getText().equals(mInfo.group[i].groupName))// 判断要将该好友移到哪个分组中
							{
								TreePath path = jt.getSelectionPath();
								DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
								node.removeFromParent();
								Node[i].add(node);
								jt.updateUI();
								moveFriend(map_qq.get(((JLabel) node.getUserObject()).getText()), Node[i].toString());
								moveFriendThread move_f = new moveFriendThread(new moveFriend(mInfo.myself.qq, map_qq.get(((JLabel) node.getUserObject()).getText()), Node[i].toString()));
							}
						}
					}
					jf.dispose();
				}
				else if (e.getActionCommand() == "取消")
				{
					jf.dispose();
				}
				else
				{}
			}
			catch (UnknownHostException e1)
			{}
			catch (IOException e1)
			{}
		}
	}

	// --------------------------------------------------------------------------
	// -----------
	// -------------------------装载函数：用于构造简单QQ主界面---------------------------------
	public void load()
	{
		Toolkit tk = this.getToolkit();// 得到窗口工具条
		dm = tk.getScreenSize();
		this.setLocation((int) (dm.getWidth() - 315), 20);// 显示在屏幕中央
		// 创建组件
		// 设置背景图片
		ImageIcon img1 = new ImageIcon(back);
		imgLabel1.setIcon(img1);
		imgLabel1.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false);
		getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
		// // ----------------------------------------------------------------
		// -----------------------------设置窗体------------------------------------
		this.setTitle("飞Q2012");
		Image icon = Toolkit.getDefaultToolkit().getImage("pic\\headpic.jpg");
		this.setIconImage(icon);
		this.setSize(295, 589);
		this.setMinimumSize(new Dimension(295, 450));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		login_jf.dispose();
	}

	public void init()
	{
		// ------------------------创建在线隐身下拉菜单------------------------------
		JLabel[] condation = new JLabel[5];
		JPanel[] jp_con = new JPanel[5];
		condation[0] = new JLabel("离线", new ImageIcon("images/condation/0.jpg"), 0);
		condation[1] = new JLabel("在线", new ImageIcon("images/condation/1.jpg"), 0);
		condation[2] = new JLabel("离开", new ImageIcon("images/condation/2.jpg"), 0);
		condation[3] = new JLabel("忙碌", new ImageIcon("images/condation/3.jpg"), 0);
		condation[4] = new JLabel("隐身", new ImageIcon("images/condation/4.jpg"), 0);
		for (int i = 1; i < 5; i++)
		{
			condation[i].setFont(new Font("华康少女文字w5(p)", Font.BOLD, 15));
			condation[i].setOpaque(false);
		}
		for (int i = 1; i < 5; i++)
		{
			jp_con[i] = new JPanel();
			jp_con[i].add(condation[i]);
			jp_con[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			jp_con[i].setPreferredSize(new Dimension(110, 26));
			jp_con[i].setOpaque(false);
		}
		jcb = new JComboBox();
		jcb.setRenderer(new JPanelCellRender());
		for (int i = 1; i < 5; i++)
		{
			jcb.addItem(jp_con[i]);
		}
		// ///////////////////////////////////////////////
		top_event te = new top_event();
		q_zone = new JButton(new ImageIcon("images/top2_pic/1.jpg"));
		q_zone.setPreferredSize(new Dimension(35, 20));
		q_zone.setToolTipText("QQ空间");
		q_zone.addActionListener(te);
		q_zone.setActionCommand("QQ空间");
		blog = new JButton(new ImageIcon("images/top2_pic/2.jpg"));
		blog.setPreferredSize(new Dimension(22, 20));
		blog.setToolTipText("微博");
		blog.addActionListener(te);
		blog.setActionCommand("微博");
		email = new JButton(new ImageIcon("images/top2_pic/3.jpg"));
		email.setPreferredSize(new Dimension(19, 19));
		email.setToolTipText("邮箱");
		email.addActionListener(te);
		email.setActionCommand("邮箱");
		friend_net = new JButton(new ImageIcon("images/top2_pic/4.jpg"));
		friend_net.setPreferredSize(new Dimension(34, 19));
		friend_net.setToolTipText("朋友网");
		friend_net.addActionListener(te);
		friend_net.setActionCommand("朋友网");
		wallet = new JButton(new ImageIcon("images/top2_pic/5.jpg"));
		wallet.setPreferredSize(new Dimension(22, 19));
		wallet.setToolTipText("钱包");
		wallet.addActionListener(te);
		wallet.setActionCommand("钱包");
		soso = new JButton(new ImageIcon("images/top2_pic/6.jpg"));
		soso.setPreferredSize(new Dimension(22, 20));
		soso.setToolTipText("搜搜");
		soso.addActionListener(te);
		soso.setActionCommand("搜搜");
		news = new JButton(new ImageIcon("images/top2_pic/7.jpg"));
		news.setPreferredSize(new Dimension(22, 20));
		news.setToolTipText("资讯");
		news.addActionListener(te);
		news.setActionCommand("资讯");
		messagebox = new JButton(new ImageIcon("images/top2_pic/8.jpg"));
		messagebox.setPreferredSize(new Dimension(22, 20));
		messagebox.setToolTipText("消息盒子");
		messagebox.addActionListener(te);
		messagebox.setActionCommand("消息盒子");
		shopping = new JButton(new ImageIcon("images/top2_pic/9.jpg"));
		shopping.setPreferredSize(new Dimension(21, 19));
		shopping.setToolTipText("购物车");
		shopping.addActionListener(te);
		shopping.setActionCommand("购物车");
		skin = new JButton(new ImageIcon("images/top2_pic/10.jpg"));// 换皮肤
		skin.setPreferredSize(new Dimension(21, 17));
		skin.addActionListener(this);
		skin.setActionCommand("换肤");
		// -------------------------创建bottom面板的标签--------------------
		// 相关事件未完成
		add = new JButton(new ImageIcon("images/查找.jpg"));
		add.setPreferredSize(new Dimension(50, 19));
		add.setOpaque(false);
		add.setContentAreaFilled(false);
		add.addActionListener(h);
		add.setActionCommand("添加好友");
		sysseting = new JButton(new ImageIcon("images/SysSeting.jpg"));
		sysseting.setPreferredSize(new Dimension(21, 18));
		sysseting.setOpaque(false);
		sysseting.setContentAreaFilled(false);
		sysseting.addActionListener(h);
		sysseting.setActionCommand("系统设置");
		Safe = new JButton(new ImageIcon("images/safe.jpg"));
		Safe.setOpaque(false);
		Safe.setContentAreaFilled(false);
		Safe.setPreferredSize(new Dimension(50, 19));
		Safe.addActionListener(h);
		Safe.setActionCommand("安全管理");
		// ----------------------------------------------
	}

	public void refresh()
	{
		// 创建组件
		// 设置背景图片
		ImageIcon img1 = new ImageIcon("main_background.jpg");
		imgLabel1.setIcon(img1);
		imgLabel1.setBounds(0, 0, img1.getIconWidth(), img1.getIconHeight());
		((JPanel) getContentPane()).setOpaque(false);
		// getLayeredPane().add(imgLabel1, new Integer(Integer.MIN_VALUE));
		// 设置背景图片完成
		// 创建面板
		GridLayout gl1 = new GridLayout(2, 1);
		top = new JPanel(gl1);
		top.setOpaque(false);
		FlowLayout top_f1 = new FlowLayout(FlowLayout.LEFT);
		top_1 = new JPanel(top_f1);
		top_1.setOpaque(false);
		FlowLayout top_f2 = new FlowLayout(FlowLayout.LEFT);
		top_2 = new JPanel(top_f2);
		top_2.setOpaque(false);
		GridLayout gl2 = new GridLayout(2, 1);
		bottom = new JPanel(gl2);
		bottom.setOpaque(false);
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
		fl.setHgap(0);
		bottom_1 = new JPanel(fl);
		bottom_1.setOpaque(false);
		FlowLayout f2 = new FlowLayout(FlowLayout.LEFT);
		fl.setHgap(0);
		bottom_2 = new JPanel(f2);
		bottom_2.setOpaque(false);
		// ----------------------为每一个好友添加右击弹窗-----------------------------------
		JMenuItem popInformation = new JMenuItem("查看资料", new ImageIcon("images/operation_friend/information.jpg"));
		JMenuItem popChange = new JMenuItem("修改好友备注", new ImageIcon("images/operation_friend/change.jpg"));
		JMenuItem popDelete = new JMenuItem("删除好友", new ImageIcon("images/operation_friend/delete.jpg"));
		JMenuItem popEmail = new JMenuItem("给好友发邮件", new ImageIcon("images/operation_friend/email.jpg"));
		JMenuItem popMove = new JMenuItem("将好友移至...");
		popInformation.addActionListener(eee);
		popChange.addActionListener(eee);
		popDelete.addActionListener(eee);
		popEmail.addActionListener(eee);
		popMove.addActionListener(eee);
		popInformation.setActionCommand("查看好友资料");
		popChange.setActionCommand("修改备注");
		popDelete.setActionCommand("删除好友");
		popEmail.setActionCommand("发邮件");
		popMove.setActionCommand("将好友移至各分组");
		popInformation.setFont(new Font("华康少女文字w5(p)", Font.PLAIN, 15));
		popChange.setFont(new Font("华康少女文字w5(p)", Font.PLAIN, 15));
		popDelete.setFont(new Font("华康少女文字w5(p)", Font.PLAIN, 15));
		popEmail.setFont(new Font("华康少女文字w5(p)", Font.PLAIN, 15));
		popMove.setFont(new Font("华康少女文字w5(p)", Font.PLAIN, 15));
		popupMenu = new JPopupMenu();
		popupMenugp = new JPopupMenu();
		popupMenuchild = new JPopupMenu();
		popupMenu.add(popInformation);
		popupMenu.add(popDelete);
		popupMenu.add(popEmail);
		popupMenu.add(popChange);
		popupMenu.add(popMove);
		// ----------------构造对每个分组的弹窗-----------------------------------
		JMenuItem pop_addgp = new JMenuItem("添加分组");
		JMenuItem pop_deletegp = new JMenuItem("删除分组");
		JMenuItem pop_updategp = new JMenuItem("修改分组");
		pop_addgp.addActionListener(eee);
		pop_deletegp.addActionListener(eee);
		pop_updategp.addActionListener(eee);
		pop_addgp.setActionCommand("添加分组");
		pop_deletegp.setActionCommand("删除分组");
		pop_updategp.setActionCommand("修改分组");
		popupMenugp.add(pop_addgp);
		popupMenugp.add(pop_deletegp);
		popupMenugp.add(pop_updategp);
		// ----------------------------------------------------------------------
		// 建立好友的树结构
		root = new DefaultMutableTreeNode();// JTree的根
		// 根据服务器传过来的信息构造每个分组以及每个分组中的好友的具体信息（昵称，备注，头像等）
		map_friendsInfo.clear();
		map_qq.clear();
		for (int i = 0; i < mInfo.groupCount; i++)
		{
			Node[i] = new DefaultMutableTreeNode(mInfo.group[i].groupName);
			for (int j = 0; j < mInfo.group[i].count; j++)
			{
				map_friendsInfo.put(mInfo.group[i].pinfl[j].qq, mInfo.group[i].pinfl[j]);
				if (mInfo.group[i].pinfl[j].port != 0)
				{
					map_ipPort.put(mInfo.group[i].pinfl[j].qq, new ipPort(mInfo.group[i].pinfl[j].ip, mInfo.group[i].pinfl[j].port));
				}
				if (map_friendsInfo.get(mInfo.group[i].pinfl[j].qq) != null)
				{
					mInfo.group[i].pinfl[j].remark = map_friendsInfo.get(mInfo.group[i].pinfl[j].qq).remark;
				}
				JLabel jl = null;
				if (mInfo.group[i].pinfl[j].remark != null && (!mInfo.group[i].pinfl[j].remark.equals("")))
				{
					if (mInfo.group[i].pinfl[j].status == 4 || mInfo.group[i].pinfl[j].status == 0)
					{
						jl = new JLabel(mInfo.group[i].pinfl[j].remark + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")", new ImageIcon("pic/face/"
								+ mInfo.group[i].pinfl[j].headImage + "_3.jpg"), 0);
					}
					else
					{
						jl = new JLabel(mInfo.group[i].pinfl[j].remark + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")", new ImageIcon("pic/face/"
								+ mInfo.group[i].pinfl[j].headImage + "_1.jpg"), 0);
					}
					map_qq.put(mInfo.group[i].pinfl[j].remark + "(" + mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq + ")", mInfo.group[i].pinfl[j].qq);
				}
				else
				{
					if (mInfo.group[i].pinfl[j].status == 4 || mInfo.group[i].pinfl[j].status == 0)
					{
						jl = new JLabel(mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq, new ImageIcon("pic/face/" + mInfo.group[i].pinfl[j].headImage + "_3.jpg"), 0);
					}
					else
					{
						jl = new JLabel(mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq, new ImageIcon("pic/face/" + mInfo.group[i].pinfl[j].headImage + "_1.jpg"), 0);
					}
					map_qq.put(mInfo.group[i].pinfl[j].nickname + " " + mInfo.group[i].pinfl[j].qq, mInfo.group[i].pinfl[j].qq);
				}
				// 对于每个好友的头像还要等把INT型数与头像对应后再建立
				DefaultMutableTreeNode child_node = new DefaultMutableTreeNode(jl);
				Node[i].add(child_node);
			}
			root.add(Node[i]);
		}
		// --
		// ----------------完成树的构造（包括设置右键弹出窗口）--------------------
		jt = new JTree(root);
		jt.setCellRenderer(new MyTreeCellRenderer());
		jt.setRootVisible(false);
		jt.addMouseListener(c);// 此"c"监听器用于监听好友头像，用于聊天
		jt.add(popupMenu);
		jt.setRowHeight(25);
		jt.putClientProperty("JTree.lineStyle", "None");
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				TreePath selPath = jt.getPathForLocation(e.getX(), e.getY());
				DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode();
				if (selPath != null)
				{
					treeNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
					if (treeNode != null)
					{
						if (SwingUtilities.isRightMouseButton(e))
						{
							if (treeNode.isLeaf() && selPath.getPathCount() == 3)
							{
								if (selPath != null)
								{
									jt.setSelectionPath(selPath);
									popupMenu.show(e.getComponent(), e.getX(), e.getY());
								}
							}
							else if (selPath.getPathCount() == 2)
							{
								if (selPath != null)
								{
									jt.setSelectionPath(selPath);
									popupMenugp.show(e.getComponent(), e.getX(), e.getY());
								}
							}
							else
							{}
						}
					}
				}
			}
		});
		// ----------------------------------------------------------------------
		// ----
		jcb.setSelectedIndex(status - 1);
		jcb.addActionListener(new con());
		jcb.setPreferredSize(new Dimension(110, 30));
		jcb.setOpaque(false);
		// --------------------------------------------------------------------
		// --------------------------创建top面板的标签----------------------------------
		// ---
		headimg = new JLabel(new ImageIcon("pic/face/" + mInfo.myself.headImage + ".jpg")); // 此处是登录者的头像信息
		// （
		// 未完成
		// ）
		headimg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headimg.setToolTipText("个人资料");
		headimg.addMouseListener(t);
		name = new JLabel(mInfo.myself.nickname);
		name.setFont(new Font("华康少女文字w5(p)", Font.BOLD, 17));
		name.setForeground(Color.black);
		// -----------------------创建滚动窗口----------------------
		jsp1 = new JScrollPane(jt);
		jsp2 = new JScrollPane(middle2);
		jsp3 = new JScrollPane(middle3);
		// 创建选项卡
		jtp = new JTabbedPane();
		jtp.add("联系人", jsp1);
		jtp.add("群/讨论组", jsp2);
		jtp.add("最近联系人", jsp3);
		jtp.setFont(new Font("华康少女文字w5(p)", Font.BOLD, 13));
		// ------------------------------------------------------------
		// --------------------------------添加组件----------------------------------
		// -------
		// 添加顶层面板
		top_1.add(headimg);
		top_1.add(jcb);
		top_1.add(name);
		top_2.add(q_zone);
		top_2.add(blog);
		top_2.add(email);
		top_2.add(friend_net);
		top_2.add(wallet);
		top_2.add(soso);
		top_2.add(messagebox);
		top_2.add(shopping);// 添加空间等button
		top_2.add(skin); // 添加换肤button
		top.add(top_1);
		top.add(top_2);
		// 添加底层面板
		bottom_2.add(sysseting);
		bottom_2.add(add);
		bottom_2.add(Safe);
		bottom.add(bottom_1);
		bottom.add(bottom_2);
		// 添加中间滚动窗口
		this.add(jtp, BorderLayout.CENTER);
		this.add(top, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		// ----------------------------------------------------------------
		// -----------------------------设置窗体------------------------------------
		this.setTitle("QQ2012");
		Image icon = Toolkit.getDefaultToolkit().getImage("pic\\headpic.jpg");
		this.setIconImage(icon);
		this.setSize(295, 589);
		this.setMinimumSize(new Dimension(295, 450));
		this.setLocation(main_frame.getLocation().x, main_frame.getLocation().y);// 显示在屏幕中央
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		login_jf.dispose();
	}

	// --------------------------------------------------------------------------
	// ---
	public void add_gp(String s)
	{
		DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(s);
		root.add(dmt);
		DefaultTreeModel model = (DefaultTreeModel) jt.getModel();
		model.reload();// 这两句是刷新的功能
	}

	public MainInterface(String s)
	{
		add_gp(s);
	}

	static public void insertFriend(String groupName, personalInfo pinfo)
	{
		for (int i = 0; i < MainInterface.mInfo.groupCount; i++)
		{
			if (mInfo.group[i].groupName.equals(groupName))
			{
				personalInfo pl[] = new personalInfo[mInfo.group[i].count + 1];
				int j = 0;
				for (; j < mInfo.group[i].count; j++)
				{
					pl[j] = new personalInfo(mInfo.group[i].pinfl[j]);
				}
				pl[j] = new personalInfo(pinfo);
				mInfo.group[i].count++;
				mInfo.group[i].pinfl = new personalInfo[mInfo.group[i].count];
				for (j = 0; j < mInfo.group[i].count; j++)
				{
					mInfo.group[i].pinfl[j] = new personalInfo(pl[j]);
				}
				break;
			}
		}
	}

	static public personalInfo deleteFriend(String qq) //
	{
		personalInfo p_temp = null;
		for (int i = 0; i < MainInterface.mInfo.groupCount; i++)
		{
			for (int j = 0; j < mInfo.group[i].count; j++)
			{
				if (mInfo.group[i].pinfl[j].qq.equals(qq))
				{
					p_temp = new personalInfo(mInfo.group[i].pinfl[j]);
					for (int k = j; k < mInfo.group[i].count - 1; k++)
					{
						mInfo.group[i].pinfl[k] = new personalInfo(mInfo.group[i].pinfl[k + 1]);
					}
					mInfo.group[i].count--;
				}
			}
		}
		return p_temp;
	}

	static public void moveFriend(String qq, String newGroupName)
	{
		personalInfo p_temp = deleteFriend(qq);
		insertFriend(newGroupName, p_temp);
	}

	static public void setFriend(String qq, personalInfo pinfo)
	{
		for (int i = 0; i < mInfo.groupCount; i++)
		{
			for (int j = 0; j < mInfo.group[i].count; j++)
			{
				if (mInfo.group[i].pinfl[j].qq.equals(qq))
				{
					mInfo.group[i].pinfl[j] = new personalInfo(pinfo);
				}
			}
		}
	}

	static public personalInfo findFriend(String qq)
	{
		for (int i = 0; i < mInfo.groupCount; i++)
		{
			for (int j = 0; j < mInfo.group[i].count; j++)
			{
				if (mInfo.group[i].pinfl[j].qq.equals(qq))
				{
					return mInfo.group[i].pinfl[j];
				}
			}
		}
		return null;
	}

	public void insertGroup(String insertGroupName)
	{
		singleGroupInfoInMain gl[] = new singleGroupInfoInMain[mInfo.groupCount + 1];
		int i = 0;
		for (; i < mInfo.groupCount; i++)
		{
			gl[i] = new singleGroupInfoInMain(mInfo.group[i]);
		}
		gl[i] = new singleGroupInfoInMain();
		gl[i].groupName = insertGroupName;
		gl[i].count = 0;
		mInfo.groupCount++;
		mInfo.group = new singleGroupInfoInMain[mInfo.groupCount];
		for (i = 0; i < mInfo.groupCount; i++)
		{
			mInfo.group[i] = new singleGroupInfoInMain(gl[i]);
		}
	}

	static public void deleteGroup(String delGroupName) throws UnknownHostException, IOException //
	{
		for (int i = 0; i < MainInterface.mInfo.groupCount; i++)
		{
			if (mInfo.group[i].groupName.equals(delGroupName))
			{
				for (int j = 0; j < mInfo.group[i].count; j++)
				{
					moveFriend(mInfo.group[i].pinfl[j].qq, "我的好友");
				}
				for (int k = i; k < mInfo.groupCount - 1; k++)
				{
					mInfo.group[k] = new singleGroupInfoInMain(mInfo.group[k + 1]);
				}
				mInfo.groupCount--;
			}
		}
	}

	static public void renameGroup(String oldName, String newName)
	{
		for (int i = 0; i < MainInterface.mInfo.groupCount; i++)
		{
			if (mInfo.group[i].groupName.equals(oldName))
			{
				mInfo.group[i].groupName = newName;
			}
		}
	}

	static public singleGroupInfoInMain findGroup(String groupName)
	{
		for (int i = 0; i < mInfo.groupCount; i++)
		{
			if (mInfo.group[i].groupName.equals(groupName))
			{
				return mInfo.group[i];
			}
		}
		return null;
	}
}
