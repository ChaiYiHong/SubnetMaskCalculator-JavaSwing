package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.Arrays;


public class SubnetCalculation {

    // Convert Binary to Decimal
    public static int getDecimal(int binary) {
        int decimal = 0;
        int n = 0;
        while (true) {
           if (binary == 0) {
               break;
           } else {
               int temp = binary%10;
               decimal += temp*Math.pow(2, n);
               binary = binary/10;
               n++;
           }
        }
        return decimal;
    }

    // Convert Decimal to Binary
    public static int getBinary(int decimal) {
        String binary = Integer.toBinaryString(decimal);
        return Integer.parseInt(binary);
    }

    // Convert Input into arrays form and Remove (/ and space)
    public static int[] ConvertIntoArrays(String Input) {
        String[] IPAddress_Prefix = Input.split("[.]");
        int[] TrimmedArray = new int[IPAddress_Prefix.length];
        for (int i = 0; i < (IPAddress_Prefix.length); i++) {
            TrimmedArray[i] = Integer.parseInt(IPAddress_Prefix[i].trim());
        }
        return TrimmedArray;
    }

    // Convert Prefix into binary subnet mask
    public static String BinarySubnetMask(int Prefix) {
        int NetworkPortion = Prefix;
        String[] Temp = new String[4];
        String BinarySubnetMask;
        for (int i = 0; i < 4; i++) {
            int TempNumber = 0;
            for (int x = 7; x >= 0; x--) {
                if (NetworkPortion > 0) {
                    TempNumber += (Math.pow(10, x));
                    NetworkPortion--;
                }
            }
            if (TempNumber == 0 ) {
                Temp[i] = "00000000";
            } else {
                Temp[i] = Integer.toString(TempNumber);
            }
        }
        BinarySubnetMask = String.join(".", Temp);
        return BinarySubnetMask;
    }

    public static long ceilDiv(long x, long y){
        return -Math.floorDiv(-x,y);
    }


    // Main Function
    public SubnetCalculation(FormEvent ev, String IPAddress, String NetworkPrefix, int ClassSelected) {

        if (!IPAddress.equals("") && !NetworkPrefix.equals("")) {
            // 2. Convert Input into Arrays
            int[] IPAddressArray = ConvertIntoArrays(IPAddress);
            int Prefix = Integer.parseInt(NetworkPrefix);

            // 3. Calculate Subnet Mask in Binary form
            String BinarySubnetMask = BinarySubnetMask(Prefix);
            // System.out.println("Subnet Mask(Binary): " + BinarySubnetMask);

            // 4. Calculate Subnet Mask in Decimal form
            int[] BinarySubnetMaskTemp = ConvertIntoArrays(BinarySubnetMask);
            String[] Converted = new String[4];
            for (int i = 0; i < 4; i++) {
                BinarySubnetMaskTemp[i] = getDecimal(BinarySubnetMaskTemp[i]);
                Converted[i] = Integer.toString(BinarySubnetMaskTemp[i]);
            }
            String DecimalSubnetMask = String.join(".", Converted);
            // System.out.println("Subnet Mask(Decimal): " + DecimalSubnetMask);

            // 5. No of subnet
            int NetworkPortion = (ClassSelected + 1) * 8;
            int HostPortionSubnet = Prefix - NetworkPortion;
            int TotalSubnet = (int)Math.pow(2, HostPortionSubnet);
            // System.out.println("No of subnet: 2^" + HostPortionSubnet + " = " + TotalSubnet);

            // 6. No of host per subnet
            int HostPortionHost = 32 - Prefix;
            int TotalHost = (int)Math.pow(2, HostPortionHost);
            // System.out.println("No of host per subnet: 2^" + HostPortionHost + " = " + TotalHost);

            // 7. Subnet Mask portion
            String SubnetMaskPortion;
            String[] Temp = new String[4];
            String[][] SubnetMaskPortionArray = new String[4][8];
            int Count = Prefix;
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 8; y++) {
                    if (Count > 0) {
                        SubnetMaskPortionArray[x][y] = "n";
                        Count--;
                    } else {
                        SubnetMaskPortionArray[x][y] = "h";
                    }
                }
                Temp[x] = String.join("", SubnetMaskPortionArray[x]);
            }
            SubnetMaskPortion = String.join(".",  Temp);

            // 8. Network Address, Broadcast Address and Range of Usable IP Address calculation
            String[][] NetworkAddress = new String[TotalSubnet][4];
            String[][] BroadcastAddress = new String[TotalSubnet][4];
            String[][] UpperUsableIPAddress = new String[TotalSubnet][4];
            String[][] LowerUsableIPAddress = new String[TotalSubnet][4];

            String TempNetworkAddress[] = new String[TotalSubnet];
            String TempBroadcastAddress[] = new String[TotalSubnet];
            String TempUsableIPAddress[] = new String[TotalSubnet];
            String Group[] = new String[TotalSubnet];

            int ArrayLocation = 0;
            for (int x = 0; x < (ceilDiv(256,(256 - (BinarySubnetMaskTemp[ClassSelected + 1])))); x++) {
                long AddressRange1 = 256/(ceilDiv(256,(256 - (BinarySubnetMaskTemp[ClassSelected + 1]))));
                if (ClassSelected == 2) {

                    for (int a = 0; a < (ClassSelected + 1); a++) {
                        NetworkAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                        BroadcastAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                        UpperUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                        LowerUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                    }

                    NetworkAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange1 * x));
                    BroadcastAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange1 * (x + 1)) - 1);
                    UpperUsableIPAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange1 * x) + 1);
                    LowerUsableIPAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange1 * (x + 1) - 2));

                    TempNetworkAddress[ArrayLocation] = String.join(".", NetworkAddress[ArrayLocation]);
                    TempBroadcastAddress[ArrayLocation] = String.join(".", BroadcastAddress[ArrayLocation]);
                    TempUsableIPAddress[ArrayLocation] = String.join(".", UpperUsableIPAddress[ArrayLocation]) + " - " + String.join(".", LowerUsableIPAddress[ArrayLocation]);
                    Group[ArrayLocation] = Integer.toString(ArrayLocation + 1);
                    ArrayLocation ++;
                } else {
                    for (int y = 0; y < (ceilDiv(256,(256 - (BinarySubnetMaskTemp[ClassSelected + 2])))); y++) {
                        long AddressRange2 = 256 / (ceilDiv(256, (256 - (BinarySubnetMaskTemp[ClassSelected + 2]))));
                        if (ClassSelected == 1) {

                            for (int a = 0; a < (ClassSelected + 1); a++) {
                                NetworkAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                BroadcastAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                UpperUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                LowerUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                            }

                            NetworkAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange1 * x));
                            NetworkAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange2 * y));

                            BroadcastAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange1 * (x + 1)) - 1);
                            BroadcastAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange2 * (y + 1)) - 1);

                            UpperUsableIPAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange1 * x));
                            UpperUsableIPAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange2 * y) + 1);

                            LowerUsableIPAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange1 * (x + 1) - 1));
                            LowerUsableIPAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange2 * (y + 1) - 2));

                            TempNetworkAddress[ArrayLocation] = String.join(".", NetworkAddress[ArrayLocation]);
                            TempBroadcastAddress[ArrayLocation] = String.join(".", BroadcastAddress[ArrayLocation]);
                            TempUsableIPAddress[ArrayLocation] = String.join(".", UpperUsableIPAddress[ArrayLocation]) + " - " + String.join(".", LowerUsableIPAddress[ArrayLocation]);
                            Group[ArrayLocation] = Integer.toString(ArrayLocation + 1);
                            ArrayLocation ++;
                        } else {
                            for (int z = 0; z < (ceilDiv(256,(256 - (BinarySubnetMaskTemp[ClassSelected + 3])))); z++) {
                                if (ClassSelected == 0) {
                                    long AddressRange3 = 256/(ceilDiv(256,(256 - (BinarySubnetMaskTemp[ClassSelected + 3]))));

                                    for (int a = 0; a < (ClassSelected + 1); a++) {
                                        NetworkAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                        BroadcastAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                        UpperUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                        LowerUsableIPAddress[ArrayLocation][a] = Integer.toString(IPAddressArray[a]);
                                    }

                                    NetworkAddress[ArrayLocation][1] = Integer.toString((int) (AddressRange1 * x));
                                    NetworkAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange2 * y));
                                    NetworkAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange3 * z));

                                    BroadcastAddress[ArrayLocation][1] = Integer.toString((int) (AddressRange1 * (x + 1)) - 1);
                                    BroadcastAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange2 * (y + 1)) - 1);
                                    BroadcastAddress[ArrayLocation][3] = Integer.toString((int) (AddressRange3 * (z + 1)) - 1);

                                    UpperUsableIPAddress[ArrayLocation][1] = Integer.toString((int) (AddressRange1 * x));
                                    UpperUsableIPAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange2 * y));
                                    UpperUsableIPAddress[ArrayLocation][3] = Integer.toString((int) ((AddressRange3 * z) + 1));

                                    LowerUsableIPAddress[ArrayLocation][1] = Integer.toString((int) (AddressRange1 * (x + 1) - 1));
                                    LowerUsableIPAddress[ArrayLocation][2] = Integer.toString((int) (AddressRange2 * (y + 1) - 1));
                                    LowerUsableIPAddress[ArrayLocation][3] = Integer.toString((int) ((AddressRange3 * (z + 1) - 2)));

                                    TempNetworkAddress[ArrayLocation] = String.join(".", NetworkAddress[ArrayLocation]);
                                    TempBroadcastAddress[ArrayLocation] = String.join(".", BroadcastAddress[ArrayLocation]);
                                    TempUsableIPAddress[ArrayLocation] = String.join(".", UpperUsableIPAddress[ArrayLocation]) + " - " + String.join(".", LowerUsableIPAddress[ArrayLocation]);
                                    Group[ArrayLocation] = Integer.toString(ArrayLocation + 1);
                                    ArrayLocation ++;
                                }
                            }
                        }
                    }
                }
            }

            ev.setBinarySubnetMask(BinarySubnetMask);
            ev.setDecimalSubnetMask(DecimalSubnetMask);
            ev.setNoOfSubnet(Integer.toString(TotalSubnet));
            ev.setHostPerSubnet(Integer.toString(TotalHost));
            ev.setHostPortionHost(Integer.toString(HostPortionHost));
            ev.setHostPortionSubnet(Integer.toString(HostPortionSubnet));

            ev.setNetworkAddress(TempNetworkAddress);
            ev.setBroadcastAddress(TempBroadcastAddress);
            ev.setRangeOfUsableIPAddress(TempUsableIPAddress);
            ev.setSubnetMaskPortion(SubnetMaskPortion);
            ev.setGroup(Group);
        }
    }
}