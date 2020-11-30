package gui;


import Model.SubnetGroup;

import javax.swing.*;

import java.awt.*;

import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private SubnetGroupModel model;


    public TablePanel() {
        model = new SubnetGroupModel();
        table = new JTable(model);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<SubnetGroup> db) {
        model.setData(db);
    }
    public void refresh() {
        model.fireTableDataChanged();
    }
}


