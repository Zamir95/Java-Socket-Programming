import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * @author Zamir Lalji 212779997
 */
public class WoWClient {
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: WoWClient <hostname> <port>");
            return;
        }
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
            InetAddress addr = InetAddress.getByName(hostname);
            DatagramSocket skt = new DatagramSocket();
 
            while (true) {
 
                DatagramPacket request = new DatagramPacket(new byte[1], 1, addr, port);
                skt.send(request);
 
                byte[] buf = new byte[600];
                DatagramPacket rsp = new DatagramPacket(buf, buf.length);
                skt.receive(rsp);
 
                String qte = new String(buf, 0, rsp.getLength());
 
                System.out.println(qte);
                System.out.println();
 
                Thread.sleep(5000);
            }
 
        } catch (Exception e){
        	System.out.println("Error");
        }
    }
}