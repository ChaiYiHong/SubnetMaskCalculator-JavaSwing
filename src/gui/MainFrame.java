package gui;



import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private Controller controller;

    public MainFrame() {
        super("Subnet Calculator");

        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        tablePanel = new TablePanel();

        controller = new Controller();
        tablePanel.setData(controller.getSubnetGroup());

        // Adding panel into position
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(1200, 900));
        setSize(1200, 900);
        setVisible(true);
        // Set up close confirmation dialog
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to close app?", "close", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (x == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                controller.Clear();
                controller.addSubnetGroup(e);
                tablePanel.refresh();
            }
        });
    }
}


