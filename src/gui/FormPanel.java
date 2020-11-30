package gui;

import Model.Database;
import Model.SubnetGroup;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormPanel extends JPanel {
    // Import Method from FormListener
    private FormListener formListener;
    private Database db = new Database();

    // Declare Items in FormPanel
    // JLabel
    private JLabel IPAddress;
    private JLabel DecimalSubnetMask;
    private JLabel BinarySubnetMask;
    private JLabel NoOfSubnet;
    private JLabel HostPerSubnet;
    private JLabel NetworkClass;
    private JLabel Prefix;
    private JLabel SubnetMaskPortion;

    // JTextField
    private JTextField IPAddressField;
    private JTextField DecimalSubnetMaskField;
    private JTextField BinarySubnetMaskField;
    private JTextField NoOfSubnetField;
    private JTextField HostPerSubnetField;
    private JTextField PrefixField;
    private JTextField SubnetMaskPortionField;

    // JRadioButton and JButton
    private JRadioButton ClassA;
    private JRadioButton ClassB;
    private JRadioButton ClassC;
    private ButtonGroup NetworkClassGroup;
    private JButton SubmitBtn;

    private int ClassSelected;
    private String[] NetworkAddress ;
    private String[] BroadcastAddress;
    private String[] RangeOfUsableIPAddress;
    private String[] Group;
    private String TotalSubnet;

    // Main Function
    public FormPanel() {
        // Set FormPanel's size
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);

        // Assign value into variables
        // JLabel
        IPAddress = new JLabel("IP Address");
        DecimalSubnetMask = new JLabel("Subnet Mask (Decimal)");
        BinarySubnetMask = new JLabel("Subnet Mask (Binary)");
        NoOfSubnet = new JLabel("No of Subnet");
        HostPerSubnet = new JLabel("Host per Subnet");
        NetworkClass = new JLabel("Network Class");
        Prefix = new JLabel("Prefix");
        SubnetMaskPortion = new JLabel("Subnet Mask Portion");

        // JTextField
        IPAddressField = new JTextField(12);
        PrefixField = new JTextField(5);
        BinarySubnetMaskField = new JTextField(26);
        DecimalSubnetMaskField = new JTextField(12);
        NoOfSubnetField = new JTextField(12);
        HostPerSubnetField = new JTextField(12);
        SubnetMaskPortionField = new JTextField(26);

        // JRadioButton and JButton
        ClassA = new JRadioButton("Class A");
        ClassB = new JRadioButton("Class B");
        ClassC = new JRadioButton("Class C");
        NetworkClassGroup = new ButtonGroup();
        SubmitBtn = new JButton("Submit");


        // Set up network class radius
        NetworkClassGroup.add(ClassA);
        NetworkClassGroup.add(ClassB);
        NetworkClassGroup.add(ClassC);
        ClassA.setSelected(true);

        // Disable field box
        DecimalSubnetMaskField.setEditable(false);
        BinarySubnetMaskField.setEditable(false);
        NoOfSubnetField.setEditable(false);
        HostPerSubnetField.setEditable(false);

        // Implement KeyListener into submit button
        IPAddressField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    Checker();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        PrefixField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                Checker();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Implement ActionListener into submit button
        SubmitBtn.addActionListener(e -> Checker());

        // Set up Form Panel's border and title
        Border innerBorder = BorderFactory.createTitledBorder("Subnet Calculator");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Items layout
        layoutComponents();
    }

    public void layoutComponents() {
        // GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        // First row
        gc.gridy = 0;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(IPAddress, gc);
        gc.insets = new Insets(15,186,0,0);
        add(Prefix, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(IPAddressField, gc);
        gc.insets = new Insets(0,165,0,0);
        add(new JLabel("/"), gc);
        gc.insets = new Insets(0,180,0,0);
        add(PrefixField, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(NetworkClass, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(ClassA, gc);
        gc.insets = new Insets(0,90,0,0);
        add(ClassB, gc);
        gc.insets = new Insets(0,180,0,0);
        add(ClassC, gc);

        //Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx= 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(DecimalSubnetMask, gc);

        //Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx= 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(DecimalSubnetMaskField, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(BinarySubnetMask, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(BinarySubnetMaskField, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,10);
        add(NoOfSubnet, gc);

        gc.gridx = 1;
        gc.weightx= 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(HostPerSubnet, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,10);
        add(NoOfSubnetField, gc);

        gc.gridx = 1;
        gc.weightx= 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(HostPerSubnetField, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(15,6,0,0);
        add(SubnetMaskPortion, gc);

        // Next row
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(SubnetMaskPortionField, gc);

        // Last row
        gc.gridy++;
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0,0,15,10);
        add(SubmitBtn, gc);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    public void Checker() {
        // Declare Variables
        String networkIPAddress = IPAddressField.getText();
        String networkPrefix = PrefixField.getText();

        if (ClassA.isSelected()) {
            ClassSelected = 0;
        } else if (ClassB.isSelected()) {
            ClassSelected = 1;
        } else if (ClassC.isSelected()) {
            ClassSelected = 2;
        }
        NetworkClassChecker NCC=  new NetworkClassChecker(networkIPAddress, networkPrefix, ClassSelected);
        if (NCC.IPAddressPrefixChecking()) {
            Action(networkIPAddress, networkPrefix, ClassSelected);
        }
    }

    public void Action(String IPAddress, String Prefix, int classSelected) {
        FormEvent ev = new FormEvent(this, IPAddress, Prefix, classSelected);

        if(formListener != null) {
            formListener.formEventOccurred(ev);
        }

        BinarySubnetMaskField.setText(ev.getBinarySubnetMask());
        DecimalSubnetMaskField.setText(ev.getDecimalSubnetMask());
        NoOfSubnetField.setText("2^" + ev.getHostPortionSubnet() + " = " + ev.getNoOfSubnet());
        HostPerSubnetField.setText("2^" + ev.getHostPortionHost() + " = " + ev.getHostPerSubnet());
        SubnetMaskPortionField.setText(ev.getSubnetMaskPortion());
        NetworkAddress = ev.getNetworkAddress();
        BroadcastAddress = ev.getBroadcastAddress();
        RangeOfUsableIPAddress = ev.getRangeOfUsableIPAddress();
        Group = ev.getGroup();
        TotalSubnet = ev.getNoOfSubnet();

        for (int x = 0; x < (Integer.parseInt(TotalSubnet)); x++) {
            SubnetGroup subnetGroup = new SubnetGroup(Group[x], NetworkAddress[x], BroadcastAddress[x], RangeOfUsableIPAddress[x]);
            db.addSubnetGroup(subnetGroup);
        }
    }
}
