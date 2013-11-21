package Windows_search;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
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
public class TableCellRender implements TableCellRenderer
{
	public static final DefaultTableCellRenderer	DEFAULT_RENDERER	= new DefaultTableCellRenderer();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color foreground, background;
		if (isSelected)
		{
			foreground = Color.black;
			background = Color.orange;
		}
		else
		{
			foreground = Color.black;
			background = Color.pink;
		}
		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}
}
