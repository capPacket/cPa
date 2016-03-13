package model;

import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

public class MyIPPacket {
	boolean is_dont;// 是否分段
	String DAddress;
	String SAddress;
	int ident;// 识别码，用于重组封装的包
	String protocol;
	int IPLength;
	int version;
	int hopLimit;// 生命周期
	boolean moreFlag;// 是否是最后一个包

	int offset;// 分包位置

	public String dealIPPacket(MyPacket myPacket) {
		Packet p = myPacket.getPacket();
		if (p instanceof IPPacket) {
			IPPacket ip = (IPPacket) p;
			MyIPPacket myIPPacket = new MyIPPacket(ip.dont_frag,
					ip.dst_ip.toString(), ip.offset, ip.src_ip.toString(),
					ip.ident, ip.length, ip.version, ip.hop_limit,ip.more_frag);

			String protocol = "";

			switch (new Integer(ip.protocol))

			{

			case 1:
				protocol = "ICMP ("+1+")";
				break;
			case 2:
				protocol = "IGMP("+2+")";
				break;
			case 6:
				protocol = "TCP("+6+")";
				break;
			case 8:
				protocol = "EGP("+8+")";
				break;
			case 9:
				protocol = "IGP("+9+")";
				break;
			case 17:
				protocol = "UDP("+17+")";
				break;
			case 41:
				protocol = "IPv6("+41+")";
				break;
			case 89:
				protocol = "OSPF("+89+")";
				break;
			default:
				break;
			}
			myIPPacket.setProtocol(protocol);
			myPacket.setMyIPPacket(myIPPacket);
			//System.out.println("ip: "+ip.toString());
			return "success";
		} else {
			return "fail";
		}
	}

	public MyIPPacket(boolean is_dont, String dAddress, int offset,
			String sAddress, int ident, int iPLength, 
			int version, int hopLimit,boolean moreFlag) {
		super();
		this.is_dont = is_dont;
		DAddress = dAddress;
		SAddress = sAddress;
		IPLength = iPLength;
		this.offset = offset;
		// this.timeToLive = timeToLive;
		this.version = version;
		this.hopLimit = hopLimit;
		this.moreFlag=moreFlag;
	}

	
	public MyIPPacket() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "MyIPPacket [is_dont=" + is_dont + ", DAddress=" + DAddress
				+ ", SAddress=" + SAddress + ", ident=" + ident + ", protocol="
				+ protocol + ", IPLength=" + IPLength + ", version=" + version
				+ ", hopLimit=" + hopLimit + ", moreFlag=" + moreFlag
				+ ", offset=" + offset + "]";
	}

	/*
	 * public int getCheckSum() { return checkSum; } public void setCheckSum(int
	 * checkSum) { this.checkSum = checkSum; }
	 */
	public String getDAddress() {
		return DAddress;
	}

	public void setDAddress(String dAddress) {
		DAddress = dAddress;
	}

	public String getSAddress() {
		return SAddress;
	}

	public void setSAddress(String sAddress) {
		SAddress = sAddress;
	}

	public boolean isIs_dont() {
		return is_dont;
	}

	public void setIs_dont(boolean is_dont) {
		this.is_dont = is_dont;
	}

	public int getIdent() {
		return ident;
	}

	public void setIdent(int ident) {
		this.ident = ident;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getIPLength() {
		return IPLength;
	}

	public void setIPLength(int iPLength) {
		IPLength = iPLength;
	}



	public boolean isMoreFlag() {
		return moreFlag;
	}

	public void setMoreFlag(boolean moreFlag) {
		this.moreFlag = moreFlag;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getHopLimit() {
		return hopLimit;
	}

	public void setHopLimit(int hopLimit) {
		this.hopLimit = hopLimit;
	}

}
