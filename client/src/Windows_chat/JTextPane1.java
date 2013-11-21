package Windows_chat;
import java.awt.*; 
import java.awt.event.*; 
import java.io.File; 

import javax.swing.*; 
import javax.swing.text.*; 
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
public class JTextPane1 extends JTextPane
{
	
	private StyledDocument doc = null;
	SimpleAttributeSet attrSet = new SimpleAttributeSet(); 
	SimpleAttributeSet attrSet1 = new SimpleAttributeSet(); 
	public JTextPane1()
	{
		StyleConstants.setFontFamily(attrSet1,"宋体");
		StyleConstants.setFontSize(attrSet1,16);
		doc = this.getStyledDocument(); // 获得JTextPane的Document
	}
	public void insert_icon(String path) 
	{ 
		setCaretPosition(doc.getLength()); // 设置插入位置 
		insertIcon(new ImageIcon(path)); // 插入图片 
	}
	public void insert(String content)//发送到输出窗口
	{
		int i,j,flag=0;
		String temp = "",temp1 = "";
		for(i=0;i<content.length();)
		{	flag =0;
			if(content.charAt(i)=='/')
			{
				for(j=i+1;j<content.length();j++)
				{
					if(content.charAt(j)=='\\')
					{
						if(!temp1.equals(""))
						{
							insert_text(temp1);
						}
						insert_icon("pic//expression//"+temp+".gif");
						i = j+1;
						flag = 1;
						temp = "";
						temp1 = "";
						break;
					}
					else
					{
						temp+=content.charAt(j);
					}
				}
				if(j>=content.length())
				{
					if(!content.equals(null))
					{
						insert_text(content);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"发送内容不能为空，请重新输入!","警告",JOptionPane.WARNING_MESSAGE);
					}
					break;
				}
			}
			else
			{
				temp1+=content.charAt(i);
				i++;
			}
		}
		if(i>=content.length()&&flag == 0)
		{
			if(!content.equals(null))
			{
				insert_text(temp1);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"发送内容不能为空，请重新输入!","警告",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	public void insert_text(String content)//插入纯文本
	{
		try 
		{ 
			doc.insertString(doc.getLength(),content,attrSet);
		} 
		catch (BadLocationException e) 
		{ 	
			e.printStackTrace(); 	
		}
	}
	public void insert_text2(String content)//用于
	{
		try 
		{ 
			StyleConstants.setForeground(attrSet1,Color.blue);
			StyleConstants.setFontFamily(attrSet1,"宋体");
			StyleConstants.setFontSize(attrSet1,17);
			doc.insertString(doc.getLength(),content,attrSet1);
			doc.insertString(doc.getLength(),"\n",attrSet1); 
		} 
		catch (BadLocationException e) 
		{ 	
			e.printStackTrace(); 	
		}
	}
	public void insertln(String content)//插入文本换行
	{
		try 
		{ 
			insert(content);
			doc.insertString(doc.getLength(),"\n",attrSet); 
		} 
		catch (BadLocationException e) 
		{ 	
			e.printStackTrace(); 	
		}
	}
	public void setUnBold()
	{
		StyleConstants.setBold(attrSet,false); 
	}
	public void setBold()
	{
		StyleConstants.setBold(attrSet,true); 
	}
	public void setUnItalic()
	{ 
		StyleConstants.setItalic(attrSet, false); 
	}
	public void setItalic()
	{ 
		StyleConstants.setItalic(attrSet, true); 
	}
	public void setBI()
	{
		StyleConstants.setItalic(attrSet, true);
		StyleConstants.setBold(attrSet, true); 
	}
	public void setColor(Color color)
	{
		StyleConstants.setForeground(attrSet,color);
	}
	public void setFontFamily(String font_name)
	{
		StyleConstants.setFontFamily(attrSet,font_name);
	}
	public void setFontSize(int font_size)
	{
		StyleConstants.setFontSize(attrSet,font_size);
	}
	public void setCommon()
	{
		StyleConstants.setBold(attrSet, false); 
		StyleConstants.setItalic(attrSet, false);
	}
}
