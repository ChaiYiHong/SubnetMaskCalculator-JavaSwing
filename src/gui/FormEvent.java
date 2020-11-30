package gui;


import java.util.EventObject;

public class FormEvent extends EventObject {
    private String IPAddress;
    private String Prefix;
    private String BinarySubnetMask;
    private String DecimalSubnetMask;
    private String NoOfSubnet;
    private String HostPerSubnet;
    private String[] NetworkAddress;
    private String[] BroadcastAddress;
    private String[] RangeOfUsableIPAddress;
    private String HostPortionHost;
    private String HostPortionSubnet;
    private String[][] Data;
    private String[] Group;
    private int GroupSelected;
    private String SubnetMaskPortion;

    public FormEvent(Object source, String IPAddress, String Prefix, int GroupSelected) {
        super(source);

        this.IPAddress = IPAddress;
        this.Prefix = Prefix;
        this.GroupSelected = GroupSelected;

        SubnetCalculation sc = new SubnetCalculation(this, IPAddress, Prefix, GroupSelected);
        SubnetGroupModel tp = new SubnetGroupModel();
        TablePanel tablePanel = new TablePanel();
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    public String getBinarySubnetMask() {
        return BinarySubnetMask;
    }

    public void setBinarySubnetMask(String binarySubnetMask) {
        BinarySubnetMask = binarySubnetMask;
    }

    public String getDecimalSubnetMask() {
        return DecimalSubnetMask;
    }

    public void setDecimalSubnetMask(String decimalSubnetMask) {
        DecimalSubnetMask = decimalSubnetMask;
    }

    public String getNoOfSubnet() {
        return NoOfSubnet;
    }

    public void setNoOfSubnet(String noOfSubnet) {
        NoOfSubnet = noOfSubnet;
    }

    public String getHostPerSubnet() {
        return HostPerSubnet;
    }

    public void setHostPerSubnet(String hostPerSubnet) {
        HostPerSubnet = hostPerSubnet;
    }

    public String[] getNetworkAddress() {
        return NetworkAddress;
    }

    public void setNetworkAddress(String[] networkAddress) {
        NetworkAddress = networkAddress;
    }

    public String[] getBroadcastAddress() {
        return BroadcastAddress;
    }

    public void setBroadcastAddress(String[] broadcastAddress) {
        BroadcastAddress = broadcastAddress;
    }

    public String[] getRangeOfUsableIPAddress() {
        return RangeOfUsableIPAddress;
    }

    public void setRangeOfUsableIPAddress(String[] rangeOfUsableIPAddress) {
        RangeOfUsableIPAddress = rangeOfUsableIPAddress;
    }

    public String getHostPortionHost() {
        return HostPortionHost;
    }

    public void setHostPortionHost(String hostPortionHost) {
        HostPortionHost = hostPortionHost;
    }

    public String getHostPortionSubnet() {
        return HostPortionSubnet;
    }

    public void setHostPortionSubnet(String hostPortionSubnet) {
        HostPortionSubnet = hostPortionSubnet;
    }

    public String[][] getData() {
        return Data;
    }

    public void setData(String[][] data) {
        Data = data;
    }

    public String[] getGroup() {
        return Group;
    }

    public void setGroup(String[] group) {
        Group = group;
    }

    public int getGroupSelected() {
        return GroupSelected;
    }

    public void setGroupSelected(int groupSelected) {
        GroupSelected = groupSelected;
    }

    public String getSubnetMaskPortion() {
        return SubnetMaskPortion;
    }

    public void setSubnetMaskPortion(String subnetMaskPortion) {
        SubnetMaskPortion = subnetMaskPortion;
    }
}

