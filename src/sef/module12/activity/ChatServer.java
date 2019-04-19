package sef.module12.activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket server=null;
		Socket client =null;
		PrintWriter out=null;
		boolean endless=true;
		int port=9999;
		
		try {
			server = new ServerSocket(port, 50, InetAddress.getLocalHost());
			
			System.out.println("ServerSocket created at " + server.getInetAddress().getHostAddress());
			System.out.println("Waiting for connection");
			
			int i = 0;
			while(endless){
				client = server.accept();
				
				System.out.println("Got a connection from " + client.getInetAddress());
				
				User user = new User("user_" + i++, client.getInputStream());
				
				Thread thread = new Thread(user);
				thread.start();
				
				out = new PrintWriter(client.getOutputStream(), true);
				out.println("You have reached server " + client.getInetAddress() + " Have a nice day!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if(out != null)
					out.close();
				
				if (client != null)
					client.close();
				
				if (server != null)
					server.close();
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}

}
