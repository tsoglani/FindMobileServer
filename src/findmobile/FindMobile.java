/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findmobile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikos
 */
public final class FindMobile {

    public static final int PORT = 2429;

    public static void main(String[] args) {
        try {
            sendToAllIpInNetwork();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendToAllIpInNetwork() throws UnknownHostException, IOException {

        ArrayList<String> ipList = getLocal();

        for (String ip : ipList) {
            for (int i = 1; i < 255; i++) {
                final String checkIp = ip + i;
                new Thread() {
                    public void run() {
                        try {
                            //      System.out.println(checkIp + "  :  " + InetAddress.getByName(checkIp).isReachable(2000));

                            Socket s = new Socket(checkIp, PORT);

                            System.out.println("success   " + checkIp);

                            s.close();

                        } catch (IOException ex) {
                            //   System.out.println(checkIp + " is not available");
                        }

                    }
                }.start();
            }
        }
        
    }

    private static ArrayList<String> getLocal() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        ArrayList<String> list = new ArrayList<String>();
        while (e.hasMoreElements()) {

            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {

                InetAddress inet = (InetAddress) ee.nextElement();
                if (!inet.isLinkLocalAddress()) {

                    String hostAdd = inet.getHostAddress();
                    // System.out.println(hostAdd);
                    String str = "";
                    String[] ars = hostAdd.split("\\.");
                    //    System.out.println("ars.length = " + ars.length);
                    for (int j = 0; j < ars.length - 1; j++) {
                        //    System.out.println(ars[j]);
                        str += ars[j] + ".";
                    }
                    //  System.out.println("str = " + str);
                    list.add(str);
                }
            }
        }
        return list;
    }
}
