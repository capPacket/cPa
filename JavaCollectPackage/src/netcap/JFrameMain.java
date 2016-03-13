package netcap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 



import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

 



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import netcap.*;
import jpcap.*;
import jpcap.packet.*;

import java.util.*;
import java.sql.Timestamp;

 


public class JFrameMain extends javax.swing.JFrame implements ActionListener{

 
		PacketDeal pd=new PacketDeal();
		PacketDetailAna ana=new PacketDetailAna();
		
       private JMenuItem exitMenuItem;

       private JSeparator jSeparator2;

       private JMenuItem saveAsMenuItem;

       private JMenuItem saveMenuItem;

       private JMenuItem stopMenuItem;

       private JMenuItem startMenuItem;//菜单项

       private JMenu Menu;//菜单例如file

       private JMenuBar jMenuBar1;//菜单条例如file+edit

       

       JTable tabledisplay = null;

       Vector rows,columns;

       DefaultTableModel tabModel;

       JScrollPane scrollPane;

       JLabel statusLabel;

       

       Netcaptor captor = new Netcaptor();

 

       /**

       * Auto-generated main method to display this JFrame

       */

       public static void main(String[] args) {

              JFrameMain inst = new JFrameMain();

              inst.setVisible(true);

       }

       

       public JFrameMain() {

              super();

              initGUI();

       }

       

       private void initGUI() {

              try {

                     setSize(400, 300);

                     {

                            jMenuBar1 = new JMenuBar();

                            setJMenuBar(jMenuBar1);

                            {//开始构造菜单

                                   Menu = new JMenu();

                                   jMenuBar1.add(Menu);

                                   Menu.setText("菜单");

                                   Menu.setPreferredSize(new java.awt.Dimension(35, 21));

                                   {

                                          startMenuItem = new JMenuItem();

                                          Menu.add(startMenuItem);

                                          startMenuItem.setText("开始");

                                          startMenuItem.setActionCommand("start");

                                          startMenuItem.addActionListener(this);

                                   }

                                   {

                                          stopMenuItem = new JMenuItem();

                                          Menu.add(stopMenuItem);

                                          stopMenuItem.setText("停止");

                                          stopMenuItem.setActionCommand("stop");

                                          stopMenuItem.addActionListener(this);

                                   }

                                   {

                                          saveMenuItem = new JMenuItem();

                                          Menu.add(saveMenuItem);

                                          saveMenuItem.setText("保存");

                                   }

                                   {

                                          saveAsMenuItem = new JMenuItem();

                                          Menu.add(saveAsMenuItem);

                                          saveAsMenuItem.setText("保存为 ...");

                                   }

                                   {

                                          jSeparator2 = new JSeparator();

                                          Menu.add(jSeparator2);

                                   }

                                   {

                                          exitMenuItem = new JMenuItem();

                                          Menu.add(exitMenuItem);

                                          exitMenuItem.setText("Exit");

                                          exitMenuItem.setActionCommand("exit");

                                          exitMenuItem.addActionListener(this);

                                   }

                            }

                     }

                     

                     rows=new Vector();

                     columns= new Vector();//形成一个数组

                     

                     columns.addElement("数据报时间");

                     columns.addElement("源IP地址");

                     columns.addElement("目的IP地址");

                     columns.addElement("首部长度");

                     columns.addElement("数据长度");

                    /* columns.addElement("是否分段");

                     columns.addElement("分段偏移量");
*/
                     columns.addElement("协议类型");

                     columns.addElement("数据内容");

                     tabModel=new DefaultTableModel();

                     tabModel.setDataVector(rows,columns);

                     tabledisplay = new JTable( tabModel );

                     scrollPane= new JScrollPane(tabledisplay);

                     this.getContentPane().add( new JScrollPane(tabledisplay),BorderLayout.CENTER);

                     

                     statusLabel=new JLabel("java抓包测试");

                     this.getContentPane().add(statusLabel,BorderLayout.SOUTH);

              } catch (Exception e) {

                     e.printStackTrace();

              }

       }

       
//事件监听，并进行事件的响应
       public void actionPerformed(ActionEvent event){

              String cmd=event.getActionCommand();

              

              if(cmd.equals("start")){

                     captor.capturePacketsFromDevice();

                     captor.setJFrame(this);

              }
////////////////////////////////////////////////////////////////////////test
              else if(cmd.equals("stop")){
            	  
            	  /*ana.analyzePacket(pd.packetList.get(1),1);
            	  ana.analyzePacket(pd.packetList.get(2),2);
            	  ana.analyzePacket(pd.packetList.get(2),2);*/
                     captor.stopCapture();

              }

              else if(cmd.equals("exit")){

                     System.exit(0);

              }

       }

 
//对捕获的数据包进行处理
       public void dealPacket( Packet packet )

       {

              try

              {
            	  

                     Vector r=new Vector();
                     pd.simplePacketDeal(packet,r);
                                                    

                     rows.addElement(r);

                     tabledisplay.addNotify();//刷新页面将新的数据显示

              }

              catch( Exception e)

              {

                     

              }

       }

}
