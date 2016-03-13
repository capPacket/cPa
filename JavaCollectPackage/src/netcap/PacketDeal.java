package netcap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import model.*;
import jpcap.packet.*;

public class PacketDeal {
	Hashtable<String,Packet> packetList = new Hashtable<String,Packet>();// 用来存储所有捕获的数据包
	

	public void simplePacketDeal(Packet packet, Vector r) {
		
		if (packet instanceof ARPPacket) {
			simpleARPPacketDeal(packet, r);
		} else {
			simpleIPPacketDeal(packet, r);
		}
	}

	/**
	 * 对ARP包进行简单处理
	 * 
	 * @param packet
	 * @param r
	 */
	public void simpleARPPacketDeal(Packet packet, Vector r) {
		ARPPacket arp=(ARPPacket)packet;
		String strtmp;
		///System.out.println("packet: " + packet.toString());

		Timestamp timestamp = new Timestamp((packet.sec * 1000)
				+ (packet.usec / 1000));
		packetList.put(timestamp.toString(), packet);
		r.addElement(timestamp.toString()); // 数据报时间
		r.addElement(arp.getSenderHardwareAddress().toString()); // 源MAC地址

		r.addElement(arp.getTargetHardwareAddress()); // 目的MAC地址

		r.addElement(packet.header.length); // 首部长度

		r.addElement(packet.data.length); // 数据长度

	/*	r.addElement(((IPPacket) packet).dont_frag == true ? "分段" : "不分段"); // 是否不分段

		r.addElement(((IPPacket) packet).offset); // 数据长度
*/

		String protocol = "ARP";
		r.addElement(protocol); // 协议类型
		r.addElement(packet.toString()); // 数据内容

	}

	/**
	 * 对IP包进行简单处理
	 * 
	 * @param packet
	 * @param r
	 */
	public void simpleIPPacketDeal(Packet packet, Vector r) {
		String strtmp;
		//System.out.println("packet: " + packet.toString());

		Timestamp timestamp = new Timestamp((packet.sec * 1000)
				+ (packet.usec / 1000));
		packetList.put(timestamp.toString(), packet);
		IPPacket ip = (IPPacket) packet;
	

		r.addElement(timestamp.toString()); // 数据报时间

		r.addElement(ip.src_ip.toString()); // 源IP地址

		r.addElement(ip.dst_ip.toString()); // 目的IP地址

		r.addElement(packet.header.length); // 首部长度

		r.addElement(packet.data.length); // 数据长度

	/*	r.addElement(((IPPacket) packet).dont_frag == true ? "分段" : "不分段"); // 是否不分段

		r.addElement(((IPPacket) packet).offset); // 数据长度
*/
		

		String protocol = "";

		switch (new Integer(ip.protocol))

		{

		case 1:
			protocol = "ICMP";
			break;
		case 2:
			protocol = "IGMP";
			break;
		case 6:
			protocol = "TCP";
			
			break;
		case 8:
			protocol = "EGP";
			break;
		case 9:
			protocol = "IGP";
			break;
		case 17:
			protocol = "UDP";
			break;
		case 41:
			protocol = "IPv6";
			break;
		case 89:
			protocol = "OSPF";
			break;
		default:
			break;
		}
		r.addElement(protocol); // 协议类型
		
		String content="";
		String[] contents=(packet.toString().split(" "));
		for(int i=0;i<contents.length;i++){
			if(i>2){
				content+=" "+contents[i];
			}
		}
		r.addElement(content); // 数据内容

	}
}
