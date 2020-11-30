package gui;

import Model.SubnetGroup;

import javax.swing.*;
import java.util.Arrays;

public class NetworkClassChecker {
    // Declare Variables

    private MainFrame mainFrame;
    private String NetworkIPAddress;
    private String NetworkPrefix;
    private int ClassSelected;
    private FormPanel formPanel = new FormPanel();

    public NetworkClassChecker(String IPAddress, String Prefix, int Class) {
        this.NetworkIPAddress = IPAddress;
        this.NetworkPrefix = Prefix;
        this.ClassSelected = Class;
    }

    public Boolean IPAddressPrefixChecking() {
        Boolean ClassChecker = false;
        if (!NetworkIPAddress.equals("") && !NetworkPrefix.equals("")) {
            ClassChecker = NCC(NetworkIPAddress, ClassSelected);
            try {
                if (ClassSelected == 0) {
                    if (Integer.parseInt(NetworkPrefix) >= 8 && (Integer.parseInt(NetworkPrefix)) <= 30) {
                        if (ClassChecker) {
                            ClassChecker = true;
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Class A IP Address range should be between 0.0.0.0 and 127.255.255.255", "IP Address: Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Class A network Prefix should be between 8 and 30", "Prefix: Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (ClassSelected == 1) {
                    if (Integer.parseInt(NetworkPrefix) >= 16 && (Integer.parseInt(NetworkPrefix)) <= 30) {
                        if (ClassChecker) {
                            ClassChecker = true;
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Class B IP Address range should be between 128.0.0.0 and 191.255.255.255", "IP Address: Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Class B network Prefix should be between 16 and 30", "Prefix: Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (ClassSelected == 2) {
                    if (Integer.parseInt(NetworkPrefix) >= 24 && (Integer.parseInt(NetworkPrefix)) <= 30) {
                        if (ClassChecker) {
                            ClassChecker = true;
                        } else {
                            JOptionPane.showMessageDialog(mainFrame, "Class C IP Address range should be between 192.0.0.0 and 223.255.255.255", "IP Address: Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Class A network Prefix should be between 24 and 30", "Prefix: Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch (Exception e1) {
                    JOptionPane.showMessageDialog(mainFrame, "Please input IPAddress and Prefix as format below \n IPAddress: 192.168.1.1 \n Prefix: 1", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Please input IPAddress and Prefix", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return ClassChecker;
    }

    public static int[] ConvertIntoArrays(String Input) {
        String[] IPAddress_Prefix = Input.split("[.]");
        int[] TrimmedArray = new int[IPAddress_Prefix.length];
        for (int i = 0; i < (IPAddress_Prefix.length); i++) {
            TrimmedArray[i] = Integer.parseInt(IPAddress_Prefix[i].trim());
        }
        return TrimmedArray;
    }

    public static Boolean NCC(String IPAddress, int ClassSelected) {
        Boolean TempBool = false;

        try {
            int[] TempIpAddress = ConvertIntoArrays(IPAddress);

            if (TempIpAddress.length == 4) {
                switch (ClassSelected) {
                    case 0: {
                        if (TempIpAddress[0] >= 0 && TempIpAddress[0] <= 127) {
                            TempBool = true;
                        }
                        break;
                    }
                    case 1: {
                        if (TempIpAddress[0] >= 128 && TempIpAddress[0] <= 191) {
                            TempBool = true;
                        }
                        break;
                    }
                    case 2: {
                        if (TempIpAddress[0] >= 192 && TempIpAddress[0] <= 223) {
                            TempBool = true;
                        }
                        break;
                    }
                }
                for (int x = 0; x < 4; x++) {
                    if (!(TempIpAddress[x] >= 0 && TempIpAddress[x] <= 255)) {
                        TempBool = false;
                    }
                }
            }
        } catch (Exception e) {
            TempBool = false;
        }

        return TempBool;
    }
}
