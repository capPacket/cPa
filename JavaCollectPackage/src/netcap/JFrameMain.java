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

       private JMenuItem startMenuItem;//�˵���

       private JMenu Menu;//�˵�����file

       private JMenuBar jMenuBar1;//�˵�������file+edit

       

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

                            {//��ʼ����˵�

                                   Menu = new JMenu();

                                   jMenuBar1.add(Menu);

                                   Menu.setText("�˵�");

                                   Menu.setPreferredSize(new java.awt.Dimension(35, 21));

                                   {

                                          startMenuItem = new JMenuItem();

                                          Menu.add(startMenuItem);

                                          startMenuItem.setText("��ʼ");

                                          startMenuItem.setActionCommand("start");

                                          startMenuItem.addActionListener(this);

                                   }

                                   {

                                          stopMenuItem = new JMenuItem();

                                          Menu.add(stopMenuItem);

                                          stopMenuItem.setText("ֹͣ");

                                          stopMenuItem.setActionCommand("stop");

                                          stopMenuItem.addActionListener(this);

                                   }

                                   {

                                          saveMenuItem = new JMenuItem();

                                          Menu.add(saveMenuItem);

                                          saveMenuItem.setText("����");

                                   }

                                   {

                                          saveAsMenuItem = new JMenuItem();

                                          Menu.add(saveAsMenuItem);

                                          saveAsMenuItem.setText("����Ϊ ...");

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

                     columns= new Vector();//�γ�һ������

                     

                     columns.addElement("���ݱ�ʱ��");

                     columns.addElement("ԴIP��ַ");

                     columns.addElement("Ŀ��IP��ַ");

                     columns.addElement("�ײ�����");

                     columns.addElement("���ݳ���");

                    /* columns.addElement("�Ƿ�ֶ�");

                     columns.addElement("�ֶ�ƫ����");
*/
                     columns.addElement("Э������");

                     columns.addElement("��������");

                     tabModel=new DefaultTableModel();

                     tabModel.setDataVector(rows,columns);

                     tabledisplay = new JTable( tabModel );

                     scrollPane= new JScrollPane(tabledisplay);

                     this.getContentPane().add( new JScrollPane(tabledisplay),BorderLayout.CENTER);

                     

                     statusLabel=new JLabel("javaץ������");

                     this.getContentPane().add(statusLabel,BorderLayout.SOUTH);

              } catch (Exception e) {

                     e.printStackTrace();

              }

       }

       
//�¼��������������¼�����Ӧ
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

 
//�Բ�������ݰ����д���
       public void dealPacket( Packet packet )

       {

              try

              {
            	  

                     Vector r=new Vector();
                     pd.simplePacketDeal(packet,r);
                                                    

                     rows.addElement(r);

                     tabledisplay.addNotify();//ˢ��ҳ�潫�µ�������ʾ

              }

              catch( Exception e)

              {

                     

              }

       }

}
