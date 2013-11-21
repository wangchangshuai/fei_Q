package Windows_MainInterface;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
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
class JPanelCellRender extends JLabel implements ListCellRenderer
{
	public   JPanelCellRender()   { 
        setOpaque(true); 
        setHorizontalAlignment(CENTER); 
        setVerticalAlignment(CENTER); 
} 


	public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus)   
	{ 
        if(value!=null && value instanceof JPanel) 
        { 	
        	if(isSelected){//设置选中时的背景色  
                ((JPanel)value).setBackground(Color.LIGHT_GRAY);  
            }else{  
                ((JPanel)value).setBackground(Color.white);  
            }  
            return (JPanel)value;  
        } 
        return   this; 
    } 


}
