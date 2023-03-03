package com.socketprogramming;

// A Java Program for server

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MainServer {
	public static void main(String[] args) throws Exception {
		// Initialize socket
		Socket s = null;
		ServerSocket ss2 = null;
		System.out.println("Server Listening.......");
		ss2 = new ServerSocket(7050);
		
		while(true) {
			try {
				s = ss2.accept();
				System.out.println("Connection Established");
				ServerThread st = new ServerThread(s);
				st.start();
			}catch(Exception e) {
				System.out.println("Connection Error!!!");
			}
		}
		
	}
}

class ServerThread extends Thread{
	
	String line = null;
	DataInputStream is = null;
	DataInputStream br = null;
	PrintWriter os = null;
	Socket s1 = null;
	
	public ServerThread(Socket s) {
		s1 = s;
	}
	@SuppressWarnings("deprecation")
	public void run() {
		try {
			 // takes input from the client socket
			is = new DataInputStream(s1.getInputStream());
			os = new PrintWriter(s1.getOutputStream());
			line = is.readLine();
			
			// reads message from client until "Quit" is sent
			while(line.compareTo("Quit")!=0) {
				os.println(line);
				os.flush();
				System.out.println("Response of Client : " + line);
				line = is.readLine();
			}
			// close connection
			is.close();
			os.close();
			s1.close();
		}catch(Exception e) {
			
		}
	
	}
}

