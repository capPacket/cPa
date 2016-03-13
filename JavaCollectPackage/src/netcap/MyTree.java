package netcap;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class MyTree extends JFrame {
	
	public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            JTree tree =new  JTree();
            new MyTree(tree);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //���Ӵ�����������  
    private JScrollPane jScrollPane1 = new JScrollPane();
    //private JTree tree;
    private JPopupMenu popMenu;

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JPopupMenu getPopMenu() {
        return popMenu;
    }

    public void setPopMenu(JPopupMenu popMenu) {
        this.popMenu = popMenu;
    }

    public MyTree(JTree tree) {

        try {
            init();
            treeInit(tree);
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.setSize(800, 600);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //�˳�ʱ��Ҫ��ֹ��ǰjvm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //���õ�ǰ���ڵ���Ϣ
    private void init() {
        getContentPane().setLayout(null);
        setTitle("������");
    }

    //��ʼ�����������
    public void treeInit(JTree tree) {
        if (jScrollPane1 != null) {
            this.remove(jScrollPane1);
        }
        jScrollPane1.setBounds(new Rectangle(0, 0, 800, 600));
        jScrollPane1.setAutoscrolls(true);
        this.getContentPane().add(jScrollPane1);
        expandTree(tree);
       
    }

   

    /**
     * ��ȫչ��һ��JTree
     *
     * @param tree JTree
     */
    public void expandTree(JTree tree) {
     
        tree.updateUI();
        jScrollPane1.getViewport().add(tree);
    }

    /**
     * ��ȡͼƬ�ļ�����
     *
     * @param fileName
     * @return
     */
    public Image getImage(String fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteBuffer bb = ByteBuffer.allocate(1024 * 1024);
            byte[] buffer = new byte[1];
            while (bis.read(buffer) > 0) {
                bb.put(buffer);
            }
            ImageIcon icon = new ImageIcon(bb.array());
            return icon.getImage();
        } catch (IOException ex) {
            Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(MyTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

 /*   public JTree getTree() {
        return tree;
    }*/

    /**
     * popmenu����Ҽ������Ӵ���
     */
  /*  class TreeAddViewMenuEvent implements ActionListener {

        private MyTree adaptee;

        public TreeAddViewMenuEvent(MyTree adaptee) {
            this.adaptee = adaptee;
        }*/

      /*  public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("���������ڵ����ƣ�");
            DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(name);
            ((DefaultMutableTreeNode) this.adaptee.getTree().getLastSelectedPathComponent()).add(treenode);
            this.adaptee.getTree().expandPath(new TreePath(((DefaultMutableTreeNode) this.adaptee.getTree().getLastSelectedPathComponent()).getPath()));
            this.adaptee.getTree().updateUI();
        }*/
    //}

    /**
     * popmenu����Ҽ���ɾ������
     */
   /* class TreeDeleteViewMenuEvent implements ActionListener {

        private MyTree adaptee;

        public TreeDeleteViewMenuEvent(MyTree adaptee) {
            this.adaptee = adaptee;
        }*/

       /* public void actionPerformed(ActionEvent e) {
            int conform = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��ɾ����", "ɾ������ȷ��", JOptionPane.YES_NO_OPTION);
            if (conform == JOptionPane.YES_OPTION) {
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (((DefaultMutableTreeNode) this.adaptee.getTree().getLastSelectedPathComponent()).getParent());
                ((DefaultMutableTreeNode) this.adaptee.getTree().getLastSelectedPathComponent()).removeFromParent();
                this.adaptee.getTree().updateUI();
            }
        }*/
    }
//}

/**
 * popmenu����Ҽ����޸Ĵ���
 */



