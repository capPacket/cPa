package model;

import javax.swing.tree.DefaultMutableTreeNode;

import jpcap.packet.*;

public class MyUDPPacket {
	int dstPort;
	int srcPort;
	int udpLength;
	
	public String dealUDPPacket(MyPacket myPacket){
		
		Packet p=myPacket.getPacket();
		if(p instanceof UDPPacket){
			UDPPacket udp=(UDPPacket)p;
			MyUDPPacket myUDPPacket=new MyUDPPacket(udp.dst_port,udp.src_port,udp.length);
			
			//System.out.println("udpPacket:"+udp.toString());
			myPacket.setMyUDPPacket(myUDPPacket);
			return "success";
		}
		else{
			return "fail";
		}
	}

	public MyUDPPacket(int dstPort, int srcPort, int udpLength) {
		super();
		this.dstPort = dstPort;
		this.srcPort = srcPort;
		this.udpLength = udpLength;
	}

	
	public MyUDPPacket() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "MyUDPPacket [dstPort=" + dstPort + ", srcPort=" + srcPort
				+ ", udpLength=" + udpLength + "]";
	}
	public DefaultMutableTreeNode showDetail(MyPacket myPacket){
		MyUDPPacket udp=myPacket.getMyUDPPacket();
		if(udp==null){
			return null;
		}
		DefaultMutableTreeNode udpTree = new DefaultMutableTreeNode("User Datagram Protocol, Src Port "+
	udp.getSrcPort()+", Dst Port: "+udp.getDstPort());
		udpTree.add(new DefaultMutableTreeNode("Src Port: "+udp.getSrcPort()));
		udpTree.add(new DefaultMutableTreeNode("Dst Port: "+udp.getDstPort()));
		udpTree.add(new DefaultMutableTreeNode("Length: "+udp.getUdpLength()));
		return udpTree;
	}

	public int getDstPort() {
		return dstPort;
	}

	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}

	public int getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}

	public int getUdpLength() {
		return udpLength;
	}

	public void setUdpLength(int udpLength) {
		this.udpLength = udpLength;
	}

	
}
