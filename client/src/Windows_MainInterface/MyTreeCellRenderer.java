package Windows_MainInterface;


import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
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
class MyTreeCellRenderer extends DefaultTreeCellRenderer {
	
	
    public Component getTreeCellRendererComponent(JTree tree, Object value,boolean selected, boolean expanded,boolean leaf, int row, boolean hasFocus) {
       
   DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
   
   Object obj = node.getUserObject();
   this.setOpenIcon(new ImageIcon("images/open.jpg"));
   this.setClosedIcon(new ImageIcon("images/close.jpg"));

   
   if(obj instanceof JLabel) {
	   
        JLabel label = (JLabel)obj;
        
        DefaultTreeCellRenderer tempCellRenderer = new DefaultTreeCellRenderer();
        
        tempCellRenderer.setLeafIcon(label.getIcon());
        
        return  tempCellRenderer.getTreeCellRendererComponent(tree,label.getText(),
selected,expanded,true,row,hasFocus);
   }
   else if(obj instanceof String)
   {
	   String s=(String) obj;
	   DefaultTreeCellRenderer tempCellRenderer = new DefaultTreeCellRenderer();
	   tempCellRenderer.setLeafIcon(new ImageIcon("images/close.jpg"));
	   
	   return  tempCellRenderer.getTreeCellRendererComponent(tree,s,
			   selected,expanded,true,row,hasFocus);
   }
   else{}
   
   
   return super.getTreeCellRendererComponent(tree,value,
selected,expanded,leaf,row,hasFocus);
   
   }
}


