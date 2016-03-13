package model;

import javax.swing.tree.DefaultMutableTreeNode;

import jpcap.packet.ARPPacket;
import jpcap.packet.Packet;

public class MyArpPacket {
	String hardwareType;
	String protocolType;
	String opration;
	String senMACAddress;
	String senProAddress;
	String tarMACAddress;
	String tarProAddress;
	
	
	public String dealArpPacket(MyPacket myPacket){
		Packet p=myPacket.getPacket();
		if(p instanceof ARPPacket){
			ARPPacket arp=(ARPPacket)p;
			MyArpPacket myArpPacket=new MyArpPacket(arp.getSenderHardwareAddress().toString(),
					arp.getSenderProtocolAddress().toString(),
					arp.getTargetHardwareAddress().toString(),
					arp.getTargetProtocolAddress().toString());
			int op=arp.operation;
			int proType=arp.prototype;
			switch(op){
			case 1:opration="request(1)";
			break;
			case 2:opration="reply(2)";
			break;
			}
			myArpPacket.setProtocolType(protocolType);
			if(arp.hardtype==arp.HARDTYPE_ETHER){
				hardwareType="HARDTYPE_ETHER";
			}
			else if(arp.hardtype==arp.HARDTYPE_FRAMERELAY){
				hardwareType="HARDTYPE_FRAMERELAY";
			}
			else if(arp.hardtype==arp.HARDTYPE_IEEE802){
				hardwareType="HARDTYPE_IEEE802";
			}
			myArpPacket.setHardwareType(hardwareType);
			
			if(arp.prototype==arp.PROTOTYPE_IP)
				protocolType="PROTOTYPE_IP";
			myArpPacket.setProtocolType(protocolType);
			
			myPacket.setMyArpPacket(myArpPacket);
			//System.out.println("arp: "+arp.toString());
			return "success";
		}
		else{
			return "fail";
		}
	}


	public MyArpPacket(String senMACAddress, String senProAddress,
			String tarMACAddress, String tarProAddress) {
		super();
		this.senMACAddress = senMACAddress;
		this.senProAddress = senProAddress;
		this.tarMACAddress = tarMACAddress;
		this.tarProAddress = tarProAddress;
	}


	public MyArpPacket() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "MyArpPacket [hardwareType=" + hardwareType + ", protocolType="
				+ protocolType + ", opration=" + opration + ", senMACAddress="
				+ senMACAddress + ", senProAddress=" + senProAddress
				+ ", tarMACAddress=" + tarMACAddress + ", tarProAddress="
				+ tarProAddress + "]";
	}

	public DefaultMutableTreeNode showDetail(MyPacket myPacket){
		MyArpPacket arp=myPacket.getMyArpPacket();
		if(arp==null){
			return null;
		}
		DefaultMutableTreeNode arpTree = new DefaultMutableTreeNode("Address Resolution Protocol ( "+
	arp.getOpration()+" ARP ) ");
		arpTree.add(new DefaultMutableTreeNode("Hardware Type: "+arp.getHardwareType()));
		arpTree.add(new DefaultMutableTreeNode("Protocol Type: "+arp.getProtocolType()));
		arpTree.add(new DefaultMutableTreeNode("Opcode: "+arp.getOpration()));
		arpTree.add(new DefaultMutableTreeNode("Sender MAC address: "+arp.getSenMACAddress()));
		arpTree.add(new DefaultMutableTreeNode("Sender IP address: "+arp.getSenProAddress()));
		arpTree.add(new DefaultMutableTreeNode("Target MAC address: "+arp.getTarMACAddress()));
		arpTree.add(new DefaultMutableTreeNode("Target IP address: "+arp.getTarProAddress()));
		return arpTree;
	}

	public String getHardwareType() {
		return hardwareType;
	}


	public void setHardwareType(String hardwareType) {
		this.hardwareType = hardwareType;
	}


	public String getProtocolType() {
		return protocolType;
	}


	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}


	public String getOpration() {
		return opration;
	}


	public void setOpration(String opration) {
		this.opration = opration;
	}


	public String getSenMACAddress() {
		return senMACAddress;
	}


	public void setSenMACAddress(String senMACAddress) {
		this.senMACAddress = senMACAddress;
	}


	public String getSenProAddress() {
		return senProAddress;
	}


	public void setSenProAddress(String senProAddress) {
		this.senProAddress = senProAddress;
	}


	public String getTarMACAddress() {
		return tarMACAddress;
	}


	public void setTarMACAddress(String tarMACAddress) {
		this.tarMACAddress = tarMACAddress;
	}


	public String getTarProAddress() {
		return tarProAddress;
	}


	public void setTarProAddress(String tarProAddress) {
		this.tarProAddress = tarProAddress;
	}
	

}
