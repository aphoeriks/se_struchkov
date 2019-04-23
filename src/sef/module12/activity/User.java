package sef.module12.activity;

import java.io.*;

public class User implements Runnable {
	
	private String name;
	
	private InputStreamReader istream;
	private OutputStreamWriter ostream;
	
	public User(String name, InputStream stream, OutputStream ostream) {
		this.istream = new InputStreamReader(stream);
		this.name = name;
		this.ostream = new OutputStreamWriter(ostream);
	}

	@Override
	public void run() {
		this.start();
	}
	
	public void start() {
		try {
			BufferedReader in = new BufferedReader(this.istream);
			
			String line="";
			while ((line= in.readLine()) != null) {
				Chat.CHAT.addMessage(this, line);
				
				if (line.equalsIgnoreCase("exit"))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("User disconnect");
		}
	}

	public String getName() {
		return name;
	}
	public OutputStreamWriter getOStream() {return ostream;}

}
