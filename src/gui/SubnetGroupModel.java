package gui;

import Model.SubnetGroup;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SubnetGroupModel extends AbstractTableModel {

    private String[] ColumnNames = {"Group", "Network Address", "Broadcast Address", "Range of Usable IP Address"};
    private List<SubnetGroup> db;

    public String getColumnName(int column) {
        return ColumnNames[column];
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    public void setData(List<SubnetGroup> db) {
        this.db = db;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        SubnetGroup subnetGroup = db.get(row);

        switch (col) {
            case 0: {
                return subnetGroup.getGroup();
            }
            case 1: {
                return subnetGroup.getNetworkAddress();
            }
            case 2: {
                return subnetGroup.getBroadcastAddress();
            }
            case 3: {
                return subnetGroup.getRangeOfUsableIPAddress();
            }
        }
        return null;
    }
}

