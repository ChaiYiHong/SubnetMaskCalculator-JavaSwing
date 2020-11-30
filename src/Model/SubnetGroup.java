package Model;

import java.io.Serializable;

public class SubnetGroup implements Serializable {

    private static final long serialVersionUID = -8219218627533074108L;

    private String IPAddress;
    private String Prefix;
    private String BinarySubnetMask;
    private String DecimalSubnetMask;
    private String NoOfSubnet;
    private String HostPerSubnet;
    private String NetworkAddress;
    private String BroadcastAddress;
    private String RangeOfUsableIPAddress;
    private String HostPortionHost;
    private String HostPortionSubnet;
    private String[][] Data;
    private String Group;

    public SubnetGroup(String Group, String NetworkAddress, String BroadcastAddress, String RangeOfUsableIPAddress) {
        this.NetworkAddress = NetworkAddress;
        this.BroadcastAddress = BroadcastAddress;
        this.RangeOfUsableIPAddress = RangeOfUsableIPAddress;
        this.Group = Group;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
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

    public String getNetworkAddress() {
        return NetworkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        NetworkAddress = networkAddress;
    }

    public String getBroadcastAddress() {
        return BroadcastAddress;
    }

    public void setBroadcastAddress(String broadcastAddress) {
        BroadcastAddress = broadcastAddress;
    }

    public String getRangeOfUsableIPAddress() {
        return RangeOfUsableIPAddress;
    }

    public void setRangeOfUsableIPAddress(String rangeOfUsableIPAddress) {
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
}
