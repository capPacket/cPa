package model;

import jpcap.packet.*;

public class MyEthernetPacket {
	String destinationHwAddress;
	String sourceHwAddress;
	String type;
	public String dealEPacket(MyPacket myPacket){
		DatalinkPacket dp=myPacket.getPacket().datalink;
		
		if(dp instanceof EthernetPacket){
			EthernetPacket e=(EthernetPacket)dp;
			String[] temp=e.toString().split(" ");
			String[] addresses=temp[1].split("->");
			MyEthernetPacket myEthernetPacket=new MyEthernetPacket(addresses[1],
					addresses[0]);
			//System.out.println(myEthernetPacket.getDestinationHwAddress());
			if(e.frametype==e.ETHERTYPE_ARP){
				type="ARP("+e.ETHERTYPE_ARP+")";
			}
			else if(e.frametype==e.ETHERTYPE_IP){
				type="IP("+e.ETHERTYPE_IP+")";
			}
			else if(e.frametype==e.ETHERTYPE_IPV6){
				type="IPV6("+e.ETHERTYPE_IPV6+")";
			}
			myEthernetPacket.setType(type);
			//System.out.println("ethernet: "+e.toString());
			myPacket.setMyEthernetPacket(myEthernetPacket);
			return "success";
		}
		else{
			return "fail";
		}
	}
	
	public MyEthernetPacket(String destinationHwAddress, String sourceHwAddress) {
		super();
		this.destinationHwAddress = destinationHwAddress;
		this.sourceHwAddress = sourceHwAddress;
	}

	
	public MyEthernetPacket() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "MyEthernetPacket [destinationHwAddress=" + destinationHwAddress
				+ ", sourceHwAddress=" + sourceHwAddress + ", type=" + type
				+ "]";
	}

	public String getDestinationHwAddress() {
		return destinationHwAddress;
	}
	public void setDestinationHwAddress(String destinationHwAddress) {
		this.destinationHwAddress = destinationHwAddress;
	}
	
	public String getSourceHwAddress() {
		return sourceHwAddress;
	}
	public void setSourceHwAddress(String sourceHwAddress) {
		this.sourceHwAddress = sourceHwAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
