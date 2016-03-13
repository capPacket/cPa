package model;

import javax.swing.tree.DefaultMutableTreeNode;

import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

public class MyTCPPacket {
	boolean isAck;
	long ackNum;
	int dstPort;
	boolean isFin;
	long sequence;
	int srcPort;
	boolean isSyn;
	int windowSize;
	boolean isUrgent;
	int urgentPoint;
	
	
	
	public String dealTCPPacket(MyPacket myPacket){
		Packet p=myPacket.getPacket();
		if(p instanceof TCPPacket){
			TCPPacket tcp=(TCPPacket)p;
			MyTCPPacket myTCPPacket=new MyTCPPacket(tcp.ack,
					tcp.ack_num,tcp.dst_port,tcp.fin,tcp.sequence,
					tcp.src_port,tcp.syn,tcp.window,tcp.urg,tcp.urgent_pointer);
			myPacket.setMyTCPPacket(myTCPPacket);
			//System.out.println("tcp: "+tcp.toString());
			return "success";
				
		}
		else{
			return "fail";
		}
	}


	public MyTCPPacket(boolean isAck, long ackNum, int dstPort, boolean isFin,
			long sequence, int srcPort, boolean isSyn, int windowSize,boolean isUrgent,
	int urgentPoint) {
		super();
		this.isAck = isAck;
		this.ackNum = ackNum;
		this.dstPort = dstPort;
		this.isFin = isFin;
		this.sequence = sequence;
		this.srcPort = srcPort;
		this.isSyn = isSyn;
		this.windowSize = windowSize;
		this.urgentPoint=urgentPoint;
		this.isUrgent=isUrgent;
	}




	public MyTCPPacket() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	@Override
	public String toString() {
		return "MyTCPPacket [isAck=" + isAck + ", ackNum=" + ackNum
				+ ", dstPort=" + dstPort + ", isFin=" + isFin + ", sequence="
				+ sequence + ", srcPort=" + srcPort + ", isSyn=" + isSyn
				+ ", windowSize=" + windowSize + ", isUrgent=" + isUrgent
				+ ", urgentPoint=" + urgentPoint + "]";
	}

	public DefaultMutableTreeNode showDetail(MyPacket myPacket){
		MyTCPPacket tcp=myPacket.getMyTCPPacket();
		if(tcp==null){
			return null;
		}
		DefaultMutableTreeNode tcpTree = new DefaultMutableTreeNode("Transmission Control Protocol, Src Port: "
		+tcp.getSrcPort()+", Dst Port: "+tcp.getDstPort()+", Seq: "+tcp.getSequence());
		tcpTree.add(new DefaultMutableTreeNode("Source Port: "+tcp.getSrcPort()));
		tcpTree.add(new DefaultMutableTreeNode("Destination Port: "+tcp.getDstPort()));
		tcpTree.add(new DefaultMutableTreeNode("Sequence number: "+tcp.getSequence()));
		tcpTree.add(new DefaultMutableTreeNode("Acknowledgment number: "+tcp.getAckNum()));
		tcpTree.add(new DefaultMutableTreeNode("Window size value: "+tcp.getWindowSize()));
		tcpTree.add(new DefaultMutableTreeNode("urgent pointer: "+tcp.getUrgentPoint()));
		tcpTree.add(new DefaultMutableTreeNode(tcp.isAck+"(ACK)"));
		tcpTree.add(new DefaultMutableTreeNode(tcp.isFin+"(FIN)"));
		tcpTree.add(new DefaultMutableTreeNode(tcp.isSyn+"(SYN)"));
		return tcpTree;
	}
	public boolean isAck() {
		return isAck;
	}


	public void setAck(boolean isAck) {
		this.isAck = isAck;
	}


	public long getAckNum() {
		return ackNum;
	}


	public void setAckNum(long ackNum) {
		this.ackNum = ackNum;
	}


	public int getDstPort() {
		return dstPort;
	}


	public void setDstPort(int dstPort) {
		this.dstPort = dstPort;
	}


	public boolean isFin() {
		return isFin;
	}


	public void setFin(boolean isFin) {
		this.isFin = isFin;
	}


	public boolean isUrgent() {
		return isUrgent;
	}


	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}


	public int getUrgentPoint() {
		return urgentPoint;
	}


	public void setUrgentPoint(int urgentPoint) {
		this.urgentPoint = urgentPoint;
	}


	public long getSequence() {
		return sequence;
	}


	public void setSequence(long sequence) {
		this.sequence = sequence;
	}


	public int getSrcPort() {
		return srcPort;
	}


	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}


	public boolean isSyn() {
		return isSyn;
	}


	public void setSyn(boolean isSyn) {
		this.isSyn = isSyn;
	}


	public int getWindowSize() {
		return windowSize;
	}


	public void setWindowSize(int windowSize) {
		this.windowSize = windowSize;
	}
	

}
