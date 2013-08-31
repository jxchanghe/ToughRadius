package org.toughradius.model;

import java.io.Serializable;

public class Ticket implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String ticketCode;
    private String userName;//�û���
    private int acctStatusType;//�Ʒ�����
    private String eventTimestamp;//
    private String nasIpAddress;//�����������IP��ַ
    private String acctSessionId;//�ʻỰ��ʶ
    private String acctStartTime;//�Ʒѿ�ʼʱ��
    private String acctStopTime;//�Ʒѽ���ʱ��
    private int acctSessionTime;//�Ựʱ��
    private int acctInputGigawords;//�Ự���е��֣�4�ֽڣ��ļ�����
    private long acctInputOctets;//�����ֽ�����
    private int acctInputPackets;//�������ݰ�
    private int acctOutputGigawords;//�Ự���е��֣�4�ֽڣ��ļ�����
    private long acctOutputOctets;//����ֽ�����
    private int acctOutputPackets;//������ݰ�
    private String callingStationId;//�û����к���
    private int nasPort;//��������������NAS���˿�
    private int nasPortType;//��������������NAS���˿�����
    private String nasPortId;//������������ (NAS) �����Ϣ
    private String nasClass;//��
    private int serviceType;//��������
    private int sessionTimeout;//�Ự��ʱ
    private String framedIpAddress;//�ͻ��˵�IP��ַ
    private String framedIpNetmask;//�ͻ��˵���������
    private int acctTerminateCause;//������ֹԭ��
    private int startSource;//���߰���Դ(1=start,2=stop,3=update,7=acct-on,8=acct-off)
    private int stopSource;//���߰���Դ(1=start,2=stop,3=update,7=acct-on,8=acct-off)
    private int acctFee;//�Ʒѽ���λ����
    private int feeReceivables;//Ӧ���������λ����
    private int isDeduct;//�Ƿ��ѿ۷ѣ�0/1 û�۷�/�ѿ۷ѣ�

    public String toString()
    {
        return new StringBuffer("�����ƷѼ�¼:[")
            .append("ticketCode=").append(ticketCode).append(";")
            .append("nasIpAddress=").append(nasIpAddress).append(";")
            .append("acctSessionId=").append(acctSessionId).append(";")
            .append("userName=").append(userName).append(";")
            .append("acctStartTime=").append(acctStartTime).append(";")
            .append("acctStopTime=").append(acctStopTime).append(";")
            .append("acctSessionTime=").append(acctSessionTime).append(";")
            .append("acctInputGigawords=").append(acctInputGigawords).append(";")
            .append("acctInputOctets=").append(acctInputOctets).append(";")
            .append("acctInputPackets=").append(acctInputPackets).append(";")
            .append("acctOutputGigawords=").append(acctOutputGigawords).append(";")
            .append("acctOutputOctets=").append(acctOutputOctets).append(";")
            .append("acctOutputPackets=").append(acctOutputPackets).append(";")
            .append("callingStationId=").append(callingStationId).append(";")
            .append("nasPort=").append(nasPort).append(";")
            .append("nasPortType=").append(nasPortType).append(";")
            .append("nasPortId=").append(nasPortId).append(";")
            .append("nasClass=").append(nasClass).append(";")
            .append("serviceType=").append(serviceType).append(";")
            .append("sessionTimeout=").append(sessionTimeout).append(";")
            .append("framedIpAddress=").append(framedIpAddress).append(";")
            .append("framedIpNetmask=").append(framedIpNetmask).append(";")
            .append("acctTerminateCause=").append(acctTerminateCause).append(";")
            .append("startSource=").append(startSource).append(";")
            .append("stopSource=").append(stopSource).append(";")
            .append("acctFee=").append(acctFee).append(";")
            .append("feeReceivables=").append(feeReceivables).append(";")
            .append("isDeduct=").append(isDeduct).append(";")
            .append("]").toString();
    }

    
    public String toTicket()
    {
        return new StringBuffer()
            .append(getNasIpAddress()).append(",")//NOT NULL
            .append(getAcctSessionId()).append(",")//NOT NULL
            .append(getUserName()).append(",")//NOT NULL
            .append(getCallingStationId()==null?"":getCallingStationId()).append(",")//String
            
            .append(getAcctStartTime()).append(",")//NOT NULL
            .append(getAcctStopTime()).append(",")//NOT NULL
            .append(getAcctSessionTime()).append(",")//int
            
            .append(getAcctInputGigawords()).append(",")//int
            .append(getAcctInputOctets()).append(",")//long
            .append(getAcctInputPackets()).append(",")//int
            .append(getAcctOutputGigawords()).append(",")//int
            .append(getAcctOutputOctets()).append(",")//long
            .append(getAcctOutputPackets()).append(",")//int
            
            .append(getNasPort()).append(",")//int
            .append(getNasPortType()).append(",")//int
            .append(getNasPortId()==null?"":getNasPortId()).append(",")//String
            .append(getNasClass()==null?"":getNasClass()).append(",")
            
            .append(getServiceType()).append(",")//int
            .append(getSessionTimeout()).append(",")//int
            .append(getFramedIpAddress()==null?"":getFramedIpAddress()).append(",")
            .append(getFramedIpNetmask()==null?"":getFramedIpNetmask()).append(",")
            .append(getAcctTerminateCause()).append(",")//int
            
            .append(getStartSource()).append(",")//int
            .append(getStopSource()).append(",")//int
            .append(getAcctFee()).append(",")//int
            .append(getFeeReceivables()).append(",")//int
            .append(getIsDeduct())//int
            .toString();
    }
    

    public int getAcctStatusType()
    {
        return acctStatusType;
    }


    public void setAcctStatusType(int acctStatusType)
    {
        this.acctStatusType = acctStatusType;
    }


    public String getEventTimestamp()
    {
        return eventTimestamp;
    }


    public void setEventTimestamp(String eventTimestamp)
    {
        this.eventTimestamp = eventTimestamp;
    }


    public String getTicketCode()
    {
        return ticketCode;
    }


    public void setTicketCode(String ticketCode)
    {
        this.ticketCode = ticketCode;
    }


    public String getUserName()
    {
        return userName;
    }


    public void setUserName(String userName)
    {
        this.userName = userName;
    }


    public String getNasIpAddress()
    {
        return nasIpAddress;
    }


    public void setNasIpAddress(String nasIpAddress)
    {
        this.nasIpAddress = nasIpAddress;
    }


    public String getAcctSessionId()
    {
        return acctSessionId;
    }


    public void setAcctSessionId(String acctSessionId)
    {
        this.acctSessionId = acctSessionId;
    }


    public String getAcctStartTime()
    {
        return acctStartTime;
    }


    public void setAcctStartTime(String acctStartTime)
    {
        this.acctStartTime = acctStartTime;
    }


    public String getAcctStopTime()
    {
        return acctStopTime;
    }


    public void setAcctStopTime(String acctStopTime)
    {
        this.acctStopTime = acctStopTime;
    }


    public int getAcctSessionTime()
    {
        return acctSessionTime;
    }


    public void setAcctSessionTime(int acctSessionTime)
    {
        this.acctSessionTime = acctSessionTime;
    }


    public int getAcctInputGigawords()
    {
        return acctInputGigawords;
    }


    public void setAcctInputGigawords(int acctInputGigawords)
    {
        this.acctInputGigawords = acctInputGigawords;
    }


    public long getAcctInputOctets()
    {
        return acctInputOctets;
    }


    public void setAcctInputOctets(long acctInputOctets)
    {
        this.acctInputOctets = acctInputOctets;
    }


    public int getAcctInputPackets()
    {
        return acctInputPackets;
    }


    public void setAcctInputPackets(int acctInputPackets)
    {
        this.acctInputPackets = acctInputPackets;
    }


    public int getAcctOutputGigawords()
    {
        return acctOutputGigawords;
    }


    public void setAcctOutputGigawords(int acctOutputGigawords)
    {
        this.acctOutputGigawords = acctOutputGigawords;
    }


    public long getAcctOutputOctets()
    {
        return acctOutputOctets;
    }


    public void setAcctOutputOctets(long acctOutputOctets)
    {
        this.acctOutputOctets = acctOutputOctets;
    }


    public int getAcctOutputPackets()
    {
        return acctOutputPackets;
    }


    public void setAcctOutputPackets(int acctOutputPackets)
    {
        this.acctOutputPackets = acctOutputPackets;
    }


    public String getCallingStationId()
    {
        return callingStationId;
    }


    public void setCallingStationId(String callingStationId)
    {
        this.callingStationId = callingStationId;
    }


    public int getNasPort()
    {
        return nasPort;
    }


    public void setNasPort(int nasPort)
    {
        this.nasPort = nasPort;
    }


    public int getNasPortType()
    {
        return nasPortType;
    }


    public void setNasPortType(int nasPortType)
    {
        this.nasPortType = nasPortType;
    }


    public String getNasPortId()
    {
        return nasPortId;
    }


    public void setNasPortId(String nasPortId)
    {
        this.nasPortId = nasPortId;
    }


    public String getNasClass()
    {
        return nasClass;
    }


    public void setNasClass(String nasClass)
    {
        this.nasClass = nasClass;
    }


    public int getServiceType()
    {
        return serviceType;
    }


    public void setServiceType(int serviceType)
    {
        this.serviceType = serviceType;
    }


    public int getSessionTimeout()
    {
        return sessionTimeout;
    }


    public void setSessionTimeout(int sessionTimeout)
    {
        this.sessionTimeout = sessionTimeout;
    }


    public String getFramedIpAddress()
    {
        return framedIpAddress;
    }


    public void setFramedIpAddress(String framedIpAddress)
    {
        this.framedIpAddress = framedIpAddress;
    }


    public String getFramedIpNetmask()
    {
        return framedIpNetmask;
    }


    public void setFramedIpNetmask(String framedIpNetmask)
    {
        this.framedIpNetmask = framedIpNetmask;
    }


    public int getAcctTerminateCause()
    {
        return acctTerminateCause;
    }


    public void setAcctTerminateCause(int acctTerminateCause)
    {
        this.acctTerminateCause = acctTerminateCause;
    }


    public int getStartSource()
    {
        return startSource;
    }


    public void setStartSource(int startSource)
    {
        this.startSource = startSource;
    }


    public int getStopSource()
    {
        return stopSource;
    }


    public void setStopSource(int stopSource)
    {
        this.stopSource = stopSource;
    }


    public int getAcctFee()
    {
        return acctFee;
    }


    public void setAcctFee(int acctFee)
    {
        this.acctFee = acctFee;
    }


    public int getFeeReceivables()
    {
        return feeReceivables;
    }


    public void setFeeReceivables(int feeReceivables)
    {
        this.feeReceivables = feeReceivables;
    }


    public int getIsDeduct()
    {
        return isDeduct;
    }


    public void setIsDeduct(int isDeduct)
    {
        this.isDeduct = isDeduct;
    }

    
    
}
