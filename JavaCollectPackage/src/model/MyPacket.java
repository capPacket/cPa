package model;

import java.sql.Timestamp;

import jpcap.packet.Packet;

public class MyPacket {
	Packet packet;
	String time;
	int hLength;
	int dLength;
	MyEthernetPacket myEthernetPacket=null;
	MyIPPacket myIPPacket=null;
	MyArpPacket myArpPacket=null;
	MyTCPPacket myTCPPacket=null;
	MyUDPPacket myUDPPacket=null;
	public MyPacket(Packet packet){
		this.packet=packet;
	}
	public MyPacket(){
		
	}
	public void dealPacket(MyPacket myPacket){
		Packet packet=myPacket.getPacket();
		Timestamp timestamp = new Timestamp((packet.sec * 1000)
				+ (packet.usec / 1000));
		myPacket.time=timestamp.toString();
		myPacket.hLength=packet.header.length;
		myPacket.dLength=packet.data.length;
		//System.out.println("packet: "+packet.toString());
	}
	
	@Override
	public String toString() {
		return "MyPacket [packet=" + packet + ", time=" + time + ", hLength="
				+ hLength + ", dLength=" + dLength +  "]";
	}
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int gethLength() {
		return hLength;
	}
	public void sethLength(int hLength) {
		this.hLength = hLength;
	}
	public int getdLength() {
		return dLength;
	}
	public void setdLength(int dLength) {
		this.dLength = dLength;
	}
	public MyEthernetPacket getMyEthernetPacket() {
		return myEthernetPacket;
	}
	public void setMyEthernetPacket(MyEthernetPacket myEthernetPacket) {
		this.myEthernetPacket = myEthernetPacket;
	}
	public MyIPPacket getMyIPPacket() {
		return myIPPacket;
	}
	public void setMyIPPacket(MyIPPacket myIPPacket) {
		this.myIPPacket = myIPPacket;
	}
	public MyArpPacket getMyArpPacket() {
		return myArpPacket;
	}
	public void setMyArpPacket(MyArpPacket myArpPacket) {
		this.myArpPacket = myArpPacket;
	}
	public MyTCPPacket getMyTCPPacket() {
		return myTCPPacket;
	}
	public void setMyTCPPacket(MyTCPPacket myTCPPacket) {
		this.myTCPPacket = myTCPPacket;
	}
	public MyUDPPacket getMyUDPPacket() {
		return myUDPPacket;
	}
	public void setMyUDPPacket(MyUDPPacket myUDPPacket) {
		this.myUDPPacket = myUDPPacket;
	}
	

}
