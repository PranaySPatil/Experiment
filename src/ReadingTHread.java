import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ReadingTHread extends Thread {

	DatagramSocket clientsocket,serversocket;
	DatagramPacket dp;
	boolean isServer;
	String g = "This is master branch"
	InetAddress ip;
	String temp = "For test branch";
	byte[] buf = new byte[1024];
	public static int cport,  sport=1790;
	WritingThread wr;
	BufferedReader dis;
	InetAddress ia;
	
	ReadingTHread(DatagramSocket clientsocket, DatagramPacket dp, InetAddress ia, byte[] buf, int sport, boolean c) throws UnknownHostException, SocketException
	{
		this.clientsocket = clientsocket;
		this.dp = dp;
		this.dis = new BufferedReader(new InputStreamReader(System.in));
		this.ia = ia;
		
		isServer = false;
	}
	
	ReadingTHread(DatagramSocket serversocket, DatagramPacket dp, InetAddress ia, byte[] buf, int sport, WritingThread wr) throws UnknownHostException, SocketException
	{
		this.serversocket = serversocket;
		this.dp = dp;
		this.dis = new BufferedReader(new InputStreamReader(System.in));
		this.ia = ia;
		this.wr = wr;
		isServer = true;
	}
	
	public void run()
	{
		try{
			if(!isServer){
				while(true)
				{
					clientsocket.receive(dp);
					String str2 = new String(dp.getData(), 0,
					dp.getLength());
					System.out.println("Server: " + str2);
				}
			}
			else{
				System.out.println("Server is Running... ");
				while(true){
					serversocket.receive(dp);
					String str = new String(dp.getData(), 0,
					dp.getLength());
					cport = dp.getPort();
					WritingThread.cport = cport;
					if(str.equals("Client is Running... Type 'STOP'to Quit"))
					{
						wr.start();
						continue;
					}
					if(str.equals("STOP"))
					{
						System.out.println("Terminated...");
						break;
					}
					System.out.println("Client: " + str);
				}
			}
		}catch(Exception e){
			
		}
	}
	
}
