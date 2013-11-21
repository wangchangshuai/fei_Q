package Windows_MainInterface;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
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
class LabelCellRender extends JLabel implements ListCellRenderer
{
	public  LabelCellRender()   
	{ 
        this.setOpaque(true); 
    } 
	public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)   
	{ 
        if(value!=null) 
        { 
            setText(value.toString()); 
            setIcon(new ImageIcon("pic//main//"+(index+2)+".png"));
        } 
        if(isSelected)
        {
        	setBackground(list.getSelectionBackground());
        	setForeground(list.getSelectionForeground());
        }
        else
        {
        	setBackground(list.getBackground());
        	setForeground(list.getForeground());
        }
        return   this; 
    } 


}
