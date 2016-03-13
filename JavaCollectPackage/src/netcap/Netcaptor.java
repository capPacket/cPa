package netcap;


import java.io.File;

import java.util.Vector;



import javax.swing.JFileChooser;

import javax.swing.JOptionPane;



import jpcap.JpcapCaptor;

import jpcap.PacketReceiver;

import jpcap.JpcapWriter;

import jpcap.packet.Packet;


//抓包类
public class Netcaptor {



      JpcapCaptor jpcap = null;

      JFrameMain frame;

      

      public void setJFrame(JFrameMain frame){

             this.frame=frame;

      }

      
/*
 * 从选择的网卡中捕获数据包
 */
      public void capturePacketsFromDevice() {



             if(jpcap!=null)
//关闭调用该方法的设备的连接
                    jpcap.close();

                    

             jpcap = Jcapturedialog.getJpcap(frame);//从设置对话框回到父对话框

             

             if (jpcap != null) {

                    startCaptureThread();

             }



      }

      
//创建抓包线程
      private Thread captureThread;

      

      private void startCaptureThread(){//开始捕获数据包（具体函数）

             

             if(captureThread != null)

                    return;

             captureThread = new Thread(new Runnable(){

                    public void run(){

                           while(captureThread != null){

//processPacket捕捉指定数目的数据包，并交由实现了PacketReceiver接口的类的实例处理，并返回捕捉到的数据包数目。
//超过指定时间自动返回捕捉到数据包的数目。handler用来处理捕获的数据包
                                  jpcap.processPacket(1, handler);

                           }

                    }

             });
//设置最大优先级
             captureThread.setPriority(Thread.MIN_PRIORITY);

             captureThread.start();
             

      }

      

      void stopcaptureThread(){

             captureThread = null;

      }

      

      public void stopCapture(){

             System.out.println("停止捕获");
//停止线程运行
             stopcaptureThread();

      }

      
//将捕获的数据包显示在界面上
      private PacketReceiver handler=new PacketReceiver(){

             public void receivePacket(Packet packet) {

                    //System.out.println(packet);

                    frame.dealPacket(packet);

             }

             

      };

}
