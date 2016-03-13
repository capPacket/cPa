package netcap;


import java.io.File;

import java.util.Vector;



import javax.swing.JFileChooser;

import javax.swing.JOptionPane;



import jpcap.JpcapCaptor;

import jpcap.PacketReceiver;

import jpcap.JpcapWriter;

import jpcap.packet.Packet;


//ץ����
public class Netcaptor {



      JpcapCaptor jpcap = null;

      JFrameMain frame;

      

      public void setJFrame(JFrameMain frame){

             this.frame=frame;

      }

      
/*
 * ��ѡ��������в������ݰ�
 */
      public void capturePacketsFromDevice() {



             if(jpcap!=null)
//�رյ��ø÷������豸������
                    jpcap.close();

                    

             jpcap = Jcapturedialog.getJpcap(frame);//�����öԻ���ص����Ի���

             

             if (jpcap != null) {

                    startCaptureThread();

             }



      }

      
//����ץ���߳�
      private Thread captureThread;

      

      private void startCaptureThread(){//��ʼ�������ݰ������庯����

             

             if(captureThread != null)

                    return;

             captureThread = new Thread(new Runnable(){

                    public void run(){

                           while(captureThread != null){

//processPacket��׽ָ����Ŀ�����ݰ���������ʵ����PacketReceiver�ӿڵ����ʵ�����������ز�׽�������ݰ���Ŀ��
//����ָ��ʱ���Զ����ز�׽�����ݰ�����Ŀ��handler��������������ݰ�
                                  jpcap.processPacket(1, handler);

                           }

                    }

             });
//����������ȼ�
             captureThread.setPriority(Thread.MIN_PRIORITY);

             captureThread.start();
             

      }

      

      void stopcaptureThread(){

             captureThread = null;

      }

      

      public void stopCapture(){

             System.out.println("ֹͣ����");
//ֹͣ�߳�����
             stopcaptureThread();

      }

      
//����������ݰ���ʾ�ڽ�����
      private PacketReceiver handler=new PacketReceiver(){

             public void receivePacket(Packet packet) {

                    //System.out.println(packet);

                    frame.dealPacket(packet);

             }

             

      };

}
