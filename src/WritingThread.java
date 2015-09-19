import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class WritingThread extends Thread 
{
	DatagramSocket clientsocket,serversocket;
	DatagramPacket dp;
	boolean isServer;
	InetAddress ip;
	byte[] buf = new byte[1024];
	static int cport,  sport=1790;
	BufferedReader dis;
	InetAddress ia;
	
	WritingThread(DatagramSocket clientsocket, DatagramPacket dp, InetAddress ia, byte[] buf,  int sport, boolean c) throws UnknownHostException, SocketException
	{
		this.clientsocket = clientsocket;
		this.dp = dp;
		this.dis = new BufferedReader(new InputStreamReader(System.in));
		this.ia = ia;
		
		isServer = false;
	}
	
	WritingThread(DatagramSocket serversocket, DatagramPacket dp, InetAddress ia, byte[] buf, int sport) throws UnknownHostException, SocketException
	{
		this.serversocket = serversocket;
		this.dp = dp;
		this.dis = new BufferedReader(new InputStreamReader(System.in));
		this.ia = ia;
		
		isServer = true;
	}
	
	public void run()
	{
		if(!isServer)
		{
			String s = "Client is Running... Type 'STOP'to Quit";
			System.out.println(s);
			try {
				clientsocket.send(new
						DatagramPacket(s.getBytes(),s.length(), ia,
						sport));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while(true){
				String str;
				try {
					str = new String(dis.readLine());
					buf = str.getBytes();
					if(str.equals("STOP"))
					{
						System.out.println("Terminated...");
						clientsocket.send(new
						DatagramPacket(buf,str.length(), ia,
						sport));
						break;
					}
					clientsocket.send(new DatagramPacket(buf,
					str.length(), ia, sport));
				}
				 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		else{
			
			while(true)
			{
				try{
				String str1 = new String(dis.readLine());
				buf = str1.getBytes();
				serversocket.send(new
				DatagramPacket(buf,str1.length(), ia, cport));
				}
				catch(Exception e){
					
				}
			}
		}
	}
}
