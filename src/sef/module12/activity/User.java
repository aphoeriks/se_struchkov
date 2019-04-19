package sef.module12.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class User implements Runnable {
	
	private String name;
	
	private InputStreamReader stream;
	
	public User(String name, InputStream stream) {
		this.stream = new InputStreamReader(stream);
		this.name = name;
	}

	@Override
	public void run() {
		this.start();
	}
	
	public void start() {
		try {
			BufferedReader in = new BufferedReader(this.stream);
			
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

}
