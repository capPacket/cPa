package netcap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import model.*;
import jpcap.packet.*;

public class PacketDeal {
	Hashtable<String,Packet> packetList = new Hashtable<String,Packet>();// �����洢���в�������ݰ�
	

	public void simplePacketDeal(Packet packet, Vector r) {
		
		if (packet instanceof ARPPacket) {
			simpleARPPacketDeal(packet, r);
		} else {
			simpleIPPacketDeal(packet, r);
		}
	}

	/**
	 * ��ARP�����м򵥴���
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
		r.addElement(timestamp.toString()); // ���ݱ�ʱ��
		r.addElement(arp.getSenderHardwareAddress().toString()); // ԴMAC��ַ

		r.addElement(arp.getTargetHardwareAddress()); // Ŀ��MAC��ַ

		r.addElement(packet.header.length); // �ײ�����

		r.addElement(packet.data.length); // ���ݳ���

	/*	r.addElement(((IPPacket) packet).dont_frag == true ? "�ֶ�" : "���ֶ�"); // �Ƿ񲻷ֶ�

		r.addElement(((IPPacket) packet).offset); // ���ݳ���
*/

		String protocol = "ARP";
		r.addElement(protocol); // Э������
		r.addElement(packet.toString()); // ��������

	}

	/**
	 * ��IP�����м򵥴���
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
	

		r.addElement(timestamp.toString()); // ���ݱ�ʱ��

		r.addElement(ip.src_ip.toString()); // ԴIP��ַ

		r.addElement(ip.dst_ip.toString()); // Ŀ��IP��ַ

		r.addElement(packet.header.length); // �ײ�����

		r.addElement(packet.data.length); // ���ݳ���

	/*	r.addElement(((IPPacket) packet).dont_frag == true ? "�ֶ�" : "���ֶ�"); // �Ƿ񲻷ֶ�

		r.addElement(((IPPacket) packet).offset); // ���ݳ���
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
		r.addElement(protocol); // Э������
		
		String content="";
		String[] contents=(packet.toString().split(" "));
		for(int i=0;i<contents.length;i++){
			if(i>2){
				content+=" "+contents[i];
			}
		}
		r.addElement(content); // ��������

	}
}
