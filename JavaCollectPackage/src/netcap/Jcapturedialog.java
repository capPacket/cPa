package netcap;


import javax.swing.JFrame;

import jpcap.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;




/**
 * ��Ҫ�����Ǹ����û������ö�jpcap�������ã��������Ĳ�׽
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

  //��ʼ������    

      public Jcapturedialog(JFrame frame) {

             super(frame,"ѡ��Ҫ�������������ò���",true);



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

                                  netPanel.setBorder(BorderFactory.createTitledBorder("ѡ������"));

                                  netPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  jPanel_west.add(netPanel);

                                  netPanel.setLayout(netPanelLayout);

//                                netPanel.setPreferredSize(new java.awt.Dimension(239, 56));

                                  {

                                         devices = JpcapCaptor.getDeviceList();//�ҵ������б�

                                         if(devices == null){

                                                JOptionPane.showMessageDialog(frame, "û���ҵ�����");

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

                                  CheckBox.setText("�Ƿ�������ģʽ");

                                  CheckBox.setLayout(null);

                           }

                           {

                                  filterPanel = new JPanel();

                                  filterPanel.setBorder(BorderFactory.createTitledBorder("���������"));

                                  filterPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  FlowLayout filterPanelLayout = new FlowLayout();

                                  filterPanelLayout.setAlignment(FlowLayout.LEFT);

                                  filterPanelLayout.setAlignOnBaseline(true);

                                  jPanel_west.add(filterPanel);

                                  filterPanel.setLayout(filterPanelLayout);

                                  {

                                         TextField = new JTextField(20);//������¼��������

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

                                  caplenPanel.setBorder(BorderFactory.createTitledBorder("��ֳ�"));

                                  caplenPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                                  jPanel_east.add(caplenPanel);

                                  caplenPanel.setLayout(new BoxLayout(caplenPanel,BoxLayout.Y_AXIS));



                                  {

                                         caplenTextField = new JTextField(20);//������¼���õ����ݰ��ĳ���

                                         caplenPanel.add(caplenTextField);

                                         caplenTextField.setText("1514");

                                         caplenTextField.setEnabled(false);

                                  }

                                  {

                                         wholeRadioButton = new JRadioButton();

                                         FlowLayout userRadioButtonLayout = new FlowLayout();

                                         userRadioButtonLayout.setAlignOnBaseline(true);

                                         caplenPanel.add(wholeRadioButton);

                                         wholeRadioButton.setText("�����������ݰ�");

                                         wholeRadioButton.setSelected(true);



                                         wholeRadioButton.addActionListener(this);

                                  }

                                  {

                                         headRadioButton = new JRadioButton();

                                         caplenPanel.add(headRadioButton);

                                         headRadioButton.setText("�������ݰ�ͷ");



                                         headRadioButton.addActionListener(this);

                                  }

                                  {

                                         userRadioButton = new JRadioButton();

                                         caplenPanel.add(userRadioButton);

                                         userRadioButton.setText("�û��趨����");



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

                                         okButton.setText("ȷ��");



                                         okButton.setActionCommand("ok");

                                         okButton.addActionListener(this);

                                  }

                                  {

                                         cancelButton = new JButton();

                                         buttonPanel.add(cancelButton);

                                         cancelButton.setText("ȡ��");



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

                                  JOptionPane.showMessageDialog(null,"���񳤶ȱ������ 68 �� 1514֮��");

                                  return;

                           }

                           
//openDevice(NetworkInterface interface, int snaplen, boolean promisc, int to_ms)
//����һ����ָ���豸�����Ӳ����ظ����ӡ�ע�⣬���������������Ǿ�̬������Interface��Ҫ�����ӵ��豸��ʵ����
//Snaplen������ǱȽ����׸���һ����������ʵ���������������ֻ�ܲ�׽�������ݰ�����������ÿһ���յ�һ�����ݰ���
//ֻ��ȡ�����ݰ���ǰ�����ֽڣ�Promisc�������Ƿ����ģʽ�����ڻ���ģʽ�������������ݰ���
//��֮���ֵ����˰����˺���setFilter()�������κ����ã�
//To_ms�����������Ҫ���� processPacket()������ָ����ʱ��ʱ�䣻
                           jpcap=JpcapCaptor.openDevice(devices[netJComboBox.getSelectedIndex()],caplen,

                                         CheckBox.isSelected(),50);

                           

                           if(TextField.getText()!=null && TextField.getText().length()>0){

//  setFilter(java.lang.String condition, boolean optimize) .condition��
//�趨Ҫ��ȡ�İ��Ĺؼ��֡�Optimize�����������˵���ĵ��Լ�Դ�����ж�û��˵����ֻ��˵����������Ϊ�棬
//��ô�������������Ż�ģʽ�� 
                        	   jpcap.setFilter("tcp",true);//�����ݰ����й���

                           }

                    }catch(NumberFormatException e){

                           JOptionPane.showMessageDialog(null,"���񳤶ȱ�����������");

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

      
//����jpcap�����jpcap���Ƕ�Ӧ��ѡ���ϵ��������󣬽������ʹӶ�Ӧ����������jpcap�ϲ��ϵõ����ݰ�
      public static JpcapCaptor getJpcap(JFrame parent){

             new Jcapturedialog(parent).setVisible(true);

             return jpcap;

      }

}