package netcap;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.MyPacket;
import jpcap.packet.Packet;

public class ShowPacketDetail {
	PacketDetailAna ana=new PacketDetailAna();
	
	public void showPacketDetail(Packet p,int index){
		System.out.println("index 2 "+index);
		MyPacket myPacket=ana.analyzePacket(p,index);
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("ÏêÏ¸·ÖÎö£º");
		  
		if(myPacket.getMyEthernetPacket()!=null){
			
			root.add(myPacket.getMyEthernetPacket().showDetail(myPacket));
		
		}
		if(myPacket.getMyArpPacket()!=null){
			root.add(myPacket.getMyArpPacket().showDetail(myPacket));
			}
		if(myPacket.getMyIPPacket()!=null){
			root.add(myPacket.getMyIPPacket().showDetail(myPacket));
			}
		if(myPacket.getMyTCPPacket()!=null){
			root.add(myPacket.getMyTCPPacket().showDetail(myPacket));
			}
		if(myPacket.getMyUDPPacket()!=null){
			root.add(myPacket.getMyUDPPacket().showDetail(myPacket));
			}
		JTree tree=new JTree(root);
		tree.setRootVisible(false);
			new MyTree(tree).setVisible(true);	
		
	}

}
