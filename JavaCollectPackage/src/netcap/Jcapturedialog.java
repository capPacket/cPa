package netcap;


import javax.swing.JFrame;

import jpcap.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;




/**
 * 主要作用是根据用户的设置对jpcap进行设置，方便后面的捕捉
 * @author dell123
 *
 */
public class Jcapturedialog extends javax.swing.JDialog implements ActionListener {



      /**

      * Auto-generated main method to display this JDialog

      */

      static JpcapCaptor jpcap=null;

      private JRadioButton wholeRadioButton;

      private JPanel buttonPanel;

      private JButton cancelButton;

      private JButton okButton;

      private JRadioButton userRadioButton;

      private JRadioButton headRadioButton;

      private JPanel netPanel;

      private JTextField caplenTextField;

      private JPanel caplenPanel;

      private JTextField TextField;

      private JPanel filterPanel;

      private JCheckBox CheckBox;

      private JComboBox netJComboBox;

      private JPanel jPanel_east;

      private JPanel jPanel_west;



      NetworkInterface[] devices;

      

      public static void main(String[] args) {

             JFrame frame = new JFrame();

             Jcapturedialog inst = new Jcapturedialog(frame);

             inst.setVisible(true);

      }

  //初始化函数    

      public Jcapturedialog(JFrame frame) {

             super(frame,"选择要检测的网卡并设置参数",true);



             try {

                    BoxLayout thisLayout = new BoxLayout(

                           getContentPane(),

                           javax.swing.BoxLayout.X_AXIS);

                    getContentPane().setLayout(thisLayout);

                    {

                           jPanel_west = new JPanel();

                           jPanel_west.setLayout(new BoxLayout(jPanel_west,BoxLayout.Y_AXIS));

                           getContentPane().add(jPanel_west);

                           {

                                  netPanel = new JPanel();

                                  FlowLayout netPanelLayout = new FlowLayout();

                                  netPanelLayout.setAlignOnBaseline(true);

                                  netPanel.setBorder(BorderFactory.createTitledBorder("选择网卡"));

                                  netPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  jPanel_west.add(netPanel);

                                  netPanel.setLayout(netPanelLayout);

//                                netPanel.setPreferredSize(new java.awt.Dimension(239, 56));

                                  {

                                         devices = JpcapCaptor.getDeviceList();//找到网卡列表

                                         if(devices == null){

                                                JOptionPane.showMessageDialog(frame, "没有找到网卡");

                                                dispose();

                                                return;

                                         }

                                         else{

                                                String[] names = new String[devices.length];

                                                for(int i=0;i < names.length;i++){

                                                       names[i] = (devices[i].description == null?devices[i].name:devices[i].description);

                                                }

                                                netJComboBox = new JComboBox(names);

                                         }

                                                netPanel.add(netJComboBox);      

                                  }

                           }

                           {

                                  CheckBox = new JCheckBox();

                                  jPanel_west.add(CheckBox);

                                  FlowLayout CheckBoxLayout = new FlowLayout();

                                  CheckBoxLayout.setAlignOnBaseline(true);

                                  CheckBox.setText("是否开启混杂模式");

                                  CheckBox.setLayout(null);

                           }

                           {

                                  filterPanel = new JPanel();

                                  filterPanel.setBorder(BorderFactory.createTitledBorder("捕获过滤器"));

                                  filterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  FlowLayout filterPanelLayout = new FlowLayout();

                                  filterPanelLayout.setAlignment(FlowLayout.LEFT);

                                  filterPanelLayout.setAlignOnBaseline(true);

                                  jPanel_west.add(filterPanel);

                                  filterPanel.setLayout(filterPanelLayout);

                                  {

                                         TextField = new JTextField(20);//用来记录过滤需求

                                         filterPanel.add(TextField);

                                  }

                           }

                    }

                    {

                           jPanel_east = new JPanel();

                           jPanel_east.setLayout(new BoxLayout(jPanel_east,BoxLayout.Y_AXIS));

                           getContentPane().add(jPanel_east);



                           {

                                  caplenPanel = new JPanel();

                                  caplenPanel.setBorder(BorderFactory.createTitledBorder("最长字长"));

                                  caplenPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  jPanel_east.add(caplenPanel);

                                  caplenPanel.setLayout(new BoxLayout(caplenPanel,BoxLayout.Y_AXIS));



                                  {

                                         caplenTextField = new JTextField(20);//用来记录设置的数据包的长度

                                         caplenPanel.add(caplenTextField);

                                         caplenTextField.setText("1514");

                                         caplenTextField.setEnabled(false);

                                  }

                                  {

                                         wholeRadioButton = new JRadioButton();

                                         FlowLayout userRadioButtonLayout = new FlowLayout();

                                         userRadioButtonLayout.setAlignOnBaseline(true);

                                         caplenPanel.add(wholeRadioButton);

                                         wholeRadioButton.setText("捕获完整数据包");

                                         wholeRadioButton.setSelected(true);



                                         wholeRadioButton.addActionListener(this);

                                  }

                                  {

                                         headRadioButton = new JRadioButton();

                                         caplenPanel.add(headRadioButton);

                                         headRadioButton.setText("捕获数据包头");



                                         headRadioButton.addActionListener(this);

                                  }

                                  {

                                         userRadioButton = new JRadioButton();

                                         caplenPanel.add(userRadioButton);

                                         userRadioButton.setText("用户设定长度");



                                         userRadioButton.addActionListener(this);

                                  }

                                  ButtonGroup group=new ButtonGroup();

                                  group.add(wholeRadioButton);

                                  wholeRadioButton.setActionCommand("Whole");

                                  group.add(headRadioButton);

                                  headRadioButton.setActionCommand("Head");

                                  group.add(userRadioButton);

                                  userRadioButton.setActionCommand("user");

                           }

                           {

                                  buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

//                                buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));

                                  jPanel_east.add(buttonPanel);



                                  {

                                         okButton = new JButton();

                                         buttonPanel.add(okButton);

                                         FlowLayout cancelButtonLayout = new FlowLayout();

                                         cancelButtonLayout.setAlignOnBaseline(true);

                                         okButton.setText("确定");



                                         okButton.setActionCommand("ok");

                                         okButton.addActionListener(this);

                                  }

                                  {

                                         cancelButton = new JButton();

                                         buttonPanel.add(cancelButton);

                                         cancelButton.setText("取消");



                                         cancelButton.setActionCommand("cancel");

                                         cancelButton.addActionListener(this);

                                  }

//                                buttonPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

                           }

                    }

                    getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));



                    getContentPane().add(jPanel_west);



                    getContentPane().add(jPanel_east);



                    pack();



             } catch (Exception e) {

                    e.printStackTrace();

             }

      }

      public void actionPerformed(ActionEvent evt){

             String cmd=evt.getActionCommand();

             

             if(cmd.equals("Whole")){

                    caplenTextField.setText("1514");

                    caplenTextField.setEnabled(false);

             }else if(cmd.equals("Head")){

                    caplenTextField.setText("68");

                    caplenTextField.setEnabled(false);

             }else if(cmd.equals("user")){

                    caplenTextField.setText("");

                    caplenTextField.setEnabled(true);

                    caplenTextField.requestFocus();

             }else if(cmd.equals("ok")){

                    try{

                           int caplen=Integer.parseInt(caplenTextField.getText());

                           if(caplen<68 || caplen>1514){

                                  JOptionPane.showMessageDialog(null,"捕获长度必须介于 68 和 1514之间");

                                  return;

                           }

                           
//openDevice(NetworkInterface interface, int snaplen, boolean promisc, int to_ms)
//创建一个与指定设备的连接并返回该连接。注意，以上两个方法都是静态方法。Interface：要打开连接的设备的实例；
//Snaplen：这个是比较容易搞混的一个参数。其实这个参数不是限制只能捕捉多少数据包，而是限制每一次收到一个数据包，
//只提取该数据包中前多少字节；Promisc：设置是否混杂模式。处于混杂模式将接收所有数据包，
//若之后又调用了包过滤函数setFilter()将不起任何作用；
//To_ms：这个参数主要用于 processPacket()方法，指定超时的时间；
                           jpcap=JpcapCaptor.openDevice(devices[netJComboBox.getSelectedIndex()],caplen,

                                         CheckBox.isSelected(),50);

                           

                           if(TextField.getText()!=null && TextField.getText().length()>0){

//  setFilter(java.lang.String condition, boolean optimize) .condition：
//设定要提取的包的关键字。Optimize：这个参数在说明文档以及源代码中都没有说明，只是说这个参数如果为真，
//那么过滤器将处于优化模式。 
                        	   jpcap.setFilter("tcp",true);//对数据包进行过滤

                           }

                    }catch(NumberFormatException e){

                           JOptionPane.showMessageDialog(null,"捕获长度必须是正整数");

                    }catch(java.io.IOException e){

                           JOptionPane.showMessageDialog(null,e.toString());

                           jpcap=null;

                    }finally{

                           dispose();

                    }

             

             }else if(cmd.equals("cancel")){

                    dispose();

             }

      }

      
//返回jpcap，这个jpcap就是对应的选择上的网卡对象，接下来就从对应的网卡对象jpcap上不断得到数据包
      public static JpcapCaptor getJpcap(JFrame parent){

             new Jcapturedialog(parent).setVisible(true);

             return jpcap;

      }

}