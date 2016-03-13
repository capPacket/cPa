package netcap;

import java.util.ArrayList;
import java.util.Hashtable;

import jpcap.packet.Packet;
import model.*;

public class PacketDetailAna {
	Hashtable<Integer,MyPacket> myPacketList=new Hashtable<Integer,MyPacket>() ;
	MyArpPacket myArpPacket=new MyArpPacket();
	MyEthernetPacket myEthernetPacket=new MyEthernetPacket();
	MyIPPacket myIPPacket=new MyIPPacket();
	MyTCPPacket myTCPPacket=new MyTCPPacket();
	MyUDPPacket myUDPPacket=new MyUDPPacket();

	public MyPacket analyzePacket(Packet p,int index){
		if(myPacketList.containsKey(index)){
			System.out.println("已经分析过咯");
			return myPacketList.get(index);
		}
		
		MyPacket myPacket=new MyPacket(p);
		myEthernetPacket.dealEPacket(myPacket);
		System.out.println(myPacket.getMyEthernetPacket().toString());
		
		myPacket.dealPacket(myPacket);
		System.out.println(myPacket.toString());
		String result="fail";
		result=myArpPacket.dealArpPacket(myPacket);
		if(result.equals("success")){
			System.out.println(myPacket.getMyArpPacket().toString());
		}
		else {
			try{
			result=myIPPacket.dealIPPacket(myPacket);
			System.out.println(myPacket.getMyIPPacket().toString());
			result=myTCPPacket.dealTCPPacket(myPacket);
			if(result.equals("success")){
				System.out.println(myPacket.getMyTCPPacket().toString());
			}
			else {
				myUDPPacket.dealUDPPacket(myPacket);
				System.out.println(myPacket.getMyUDPPacket().toString());
			}
		}
			catch(Exception e){
				System.out.println("捕获到了ipv6的包");
			}
		}
		myPacketList.put(index, myPacket);
		return myPacket;
	}
		
	

}
