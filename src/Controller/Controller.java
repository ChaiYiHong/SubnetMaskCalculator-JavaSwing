package Controller;

import Model.Database;
import Model.SubnetGroup;
import gui.FormEvent;

import java.util.List;

public class Controller {
    Database db = new Database();

    public List<SubnetGroup> getSubnetGroup() {
        return db.getSubnetGroup();
    }

    public void addSubnetGroup (FormEvent ev) {

        String[] NetworkAddress = ev.getNetworkAddress();
        String[] BroadcastAddress = ev.getBroadcastAddress();
        String[] RangeOfUsableIPAddress = ev.getRangeOfUsableIPAddress();
        String TotalSubnet = ev.getNoOfSubnet();
        String[][] Data = ev.getData();
        String[] Group = ev.getGroup();

        for (int x = 0; x < (Integer.parseInt(TotalSubnet)); x++) {
            SubnetGroup subnetGroup = new SubnetGroup(Group[x], NetworkAddress[x], BroadcastAddress[x], RangeOfUsableIPAddress[x]);
            db.addSubnetGroup(subnetGroup);
        }
    }
    public void Clear() {
        db.Clear();
    }
}
