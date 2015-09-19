import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {
	public static DatagramSocket clientsocket;
	public static DatagramPacket dp;
	public static BufferedReader dis;
	public static InetAddress ia;
	public static byte buf[] = new byte[1024];
	public static int cport = 1789, sport = 1790;
	
	public static void main(String[] a) throws IOException
	{
		clientsocket = new DatagramSocket(cport);
		int j = 4;
		dp = new DatagramPacket(buf, buf.length);
		dis = new BufferedReader(new InputStreamReader(System.in));
		ia = InetAddress.getLocalHost();
		WritingThread wt = new WritingThread(clientsocket, dp, ia, buf, sport, true);
		ReadingTHread rt = new ReadingTHread(clientsocket, dp, ia, buf, sport, true);
		wt.start();
		rt.start();
		
	} 
}
