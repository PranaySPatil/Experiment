import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPServer {
	public static DatagramSocket serversocket;
	public static DatagramPacket dp;
	public static BufferedReader dis;
	public static InetAddress ia;
	public static byte buf[] = new byte[1024];
	public static int cport,sport=1790;
	public static void main(String[] a) throws IOException
	{
		serversocket = new DatagramSocket(sport);
		dp = new DatagramPacket(buf,buf.length);
		dis = new BufferedReader
		(new InputStreamReader(System.in));
		ia = InetAddress.getLocalHost();
		WritingThread wt = new WritingThread(serversocket, dp, ia, buf, sport);
		ReadingTHread rt = new ReadingTHread(serversocket, dp, ia, buf, sport, wt);
		rt.start();
	
	}
}
